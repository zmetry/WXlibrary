package com.wx.demo.controller;

import com.alibaba.fastjson.JSON;
import com.wx.demo.mapper.SquareMsgMapper;
import com.wx.demo.mapper.UserMapper;
import com.wx.demo.mybatis.entity.SquareMsg;
import com.wx.demo.mybatis.entity.User;
import com.wx.demo.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/test")
public class UserImageController{

    @Autowired
    UserMapper userMapper;

    @Autowired
    SquareMsgMapper squareMsgMapper;

    @Autowired
    RedisUtil redisUtil;

    //用户图像
    @RequestMapping("/upuserimage/{name}/{random}")
    public String upload(HttpServletRequest request,
                         @RequestParam("description") String  description,
                         @RequestParam("file") MultipartFile file,@PathVariable("name") String name,
                         @PathVariable("random") String random) throws Exception{
        //接收参数description
        System.out.println("description: " + description);
        //如果文件不为空，写入上传路径
        if (!file.isEmpty()){
            //上传文件路径
            File directory = new File("images");
            String path = directory.getCanonicalPath();
            System.out.println("path = " + path);
            User user = userMapper.getUserByName(name);
            System.out.println(user.getId()+" "+user.getName());
            //上传文件名
            String filename = user.getId() + ".jpg";
            File filePath = new File(path, filename);
            //判断路径是否存在，如果不存在就创建一个
            if (!filePath.getParentFile().exists()){
                filePath.getParentFile().mkdirs();
            }
            //将上传文件保存到一个目标文件当中
            file.transferTo(new File(path+File.separator + filename));
            System.out.println(filename);
            return "success";
        }else {
            return "error";
        }
    }

    //朋友圈纯文字
    @RequestMapping("/upsquaretext/{name}/{text}")
    public String uptext(HttpServletRequest request,
                           @PathVariable("name") String name,
                           @PathVariable("text") String text) throws Exception {

        boolean flag = false;

        Timestamp timestamp = new Timestamp(new Date().getTime());
        SquareMsg squareMsg = new SquareMsg();
        squareMsg.setText(text);
        squareMsg.setDate(timestamp);
        squareMsg.setName(name);
        squareMsg.setImgurl("");

        try {
            squareMsgMapper.addMsg(squareMsg);
            flag = true;
        } catch (Exception e) {
            flag = false;
            e.printStackTrace();
        }

        //添加进redis
        if(redisUtil.hasKey("squareMsgs")){
            String str = (String) redisUtil.get("squareMsgs");
            List<SquareMsg> squareMsgs = JSON.parseArray(str, SquareMsg.class);
            //不为空的情况
            if(squareMsgs.size() != 0){
                //取了读之后加1
                squareMsg.setId(squareMsgs.get(squareMsgs.size() - 1).getId() + 1);
                System.out.println(squareMsg);
                squareMsgs.add(squareMsg);
            }else{
                //直接进数据库读
                squareMsgs = squareMsgMapper.getAllMsg();
            }
            redisUtil.set("squareMsgs",JSON.toJSONString(squareMsgs));
        }

        return flag+"";
    }

    //朋友圈图像
    @RequestMapping("/upsquareimg/{name}/{text}/{radon}")
    public String upsquimg(HttpServletRequest request,
                         @RequestParam("description") String  description,
                         @RequestParam("file") MultipartFile file,@PathVariable("name") String name,
                         @PathVariable("text") String text,
                         @PathVariable("radon") String radon) throws Exception {


        //判断图片是否为空

        if (!file.isEmpty()){
            Timestamp timestamp = new Timestamp(new Date().getTime());

            File directory = new File("squareimg");
            String path = directory.getCanonicalPath();
            System.out.println(path);
            String filename = radon+".jpg";
            SquareMsg squareMsg = new SquareMsg();
            squareMsg.setDate(timestamp);
            squareMsg.setName(name);

            //文本空与非空
            if(!text.equals("null"))
                squareMsg.setText(text);
            else
                squareMsg.setText("");

            squareMsg.setImgurl("/squareimg/"+filename);
            squareMsgMapper.addMsg(squareMsg);

            //添加进redis
            if(redisUtil.hasKey("squareMsgs")){
                String str = (String) redisUtil.get("squareMsgs");
                List<SquareMsg> squareMsgs = JSON.parseArray(str, SquareMsg.class);
                //不为空的情况
                if(squareMsgs.size() != 0){
                    //取了读之后加1
                    squareMsg.setId(squareMsgs.get(squareMsgs.size() - 1).getId() + 1);
                    System.out.println(squareMsg);
                    squareMsgs.add(squareMsg);
                }else{
                    //直接进数据库读
                    squareMsgs = squareMsgMapper.getAllMsg();
                }
                redisUtil.set("squareMsgs",JSON.toJSONString(squareMsgs));
            }
            File filePath = new File(path, filename);
            //判断路径是否存在，如果不存在就创建一个
            if (!filePath.getParentFile().exists()){
                filePath.getParentFile().mkdirs();
            }
            //将上传文件保存到一个目标文件当中
            file.transferTo(new File(path+File.separator + filename));
            System.out.println(filename);
            return "success";
        }else {
            Timestamp timestamp = new Timestamp(new Date().getTime());
            SquareMsg squareMsg = new SquareMsg();
            squareMsg.setDate(timestamp);
            squareMsg.setName(name);
            squareMsg.setText(text);
            squareMsg.setImgurl("");
            squareMsgMapper.addMsg(squareMsg);

            //存进redis
            if(redisUtil.hasKey("squareMsgs")){
                String str = (String) redisUtil.get("squareMsgs");
                List<SquareMsg> squareMsgs = JSON.parseArray(str, SquareMsg.class);
                //不为空的情况
                if(squareMsgs.size() != 0){
                    //取了读之后加1
                    squareMsg.setId(squareMsgs.get(squareMsgs.size() - 1).getId() + 1);
                    System.out.println(squareMsg);
                    squareMsgs.add(squareMsg);
                }else{
                    //直接进数据库读
                    squareMsgs = squareMsgMapper.getAllMsg();
                }
                redisUtil.set("squareMsgs",JSON.toJSONString(squareMsgs));
            }

            return "sucess";
        }
    }

}

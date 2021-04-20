package com.wx.demo.controller;


import com.alibaba.fastjson.JSON;
import com.wx.demo.mapper.SquareMsgMapper;
import com.wx.demo.mybatis.entity.SquareMsg;
import com.wx.demo.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/test")
public class SquareMsgController {

    @Autowired
    SquareMsgMapper squareMsgMapper;

    @Autowired
    RedisUtil redisUtil;

    @RequestMapping("/square/allmsg")
    public String getAll() throws Exception{
        String str;
        if(redisUtil.hasKey("squareMsgs")){
            str = (String) redisUtil.get("squareMsgs");
        }else{
            List<SquareMsg> squareMsgs = squareMsgMapper.getAllMsg();
            squareMsgs.sort(Comparator.comparing(SquareMsg::getDate));
            str = JSON.toJSONString(squareMsgs);
            redisUtil.set("squareMsgs",str);
        }
        return str;
    }

    @RequestMapping("/square/deletemsg/{name}/{text}")
    public String deletemsg(@PathVariable("name") String name,@PathVariable("text") String text) throws Exception{

        boolean flag = false;

        SquareMsg squareMsg = new SquareMsg();
        squareMsg.setName(name);

        //文本非空与空
        if(!text.equals("null"))
            squareMsg.setText(text);
        else
            squareMsg.setText("");

        try {

            squareMsgMapper.deleteMsg(squareMsg);
            //删除redis中的数据
            String str = (String) redisUtil.get("squareMsgs");
            List<SquareMsg> squareMsgs = JSON.parseArray(str, SquareMsg.class);


            for (int i = 0; i < squareMsgs.size(); i++) {
                if(squareMsgs.get(i).getName().equals(name) &&
                        (squareMsgs.get(i).getText().equals(text) || squareMsgs.get(i).getText().length() == 0)){
                    squareMsg = squareMsgs.get(i);
                    squareMsgs.remove(i);
                    //获取要删除的信息
                    break;
                }
            }
            //将之后的结果存入redis
            str = JSON.toJSONString(squareMsgs);
            redisUtil.set("squareMsgs",str);

            flag = true;

            //删除本地图片
            if(squareMsg.getImgurl().equals("") != true){
                //删除本地图片
                File file = new File("squareimg");
                String path = file.getCanonicalPath();
                String s = squareMsg.getImgurl().substring(11);
                String filePath = path + "\\" + s;
                file = new File(filePath);
                flag = file.delete();
            }

        } catch (Exception e) {
            e.printStackTrace();
            flag = false;
        }
        return flag+"";
    }

}

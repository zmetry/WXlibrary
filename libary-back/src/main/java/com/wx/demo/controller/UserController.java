package com.wx.demo.controller;

import com.alibaba.fastjson.JSON;
import com.mysql.cj.jdbc.Blob;
import com.wx.demo.mapper.MailMapper;
import com.wx.demo.mapper.SquareMsgMapper;
import com.wx.demo.mapper.UserMapper;
import com.wx.demo.mybatis.entity.Mail;
import com.wx.demo.mybatis.entity.SquareMsg;
import com.wx.demo.mybatis.entity.User;
import com.wx.demo.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.annotation.WebServlet;
import java.awt.*;
import java.io.File;
import java.util.List;

@RestController
@RequestMapping("/test")
public class UserController {

    @Autowired
    UserMapper userMapper;

    @Autowired
    SquareMsgMapper squareMsgMapper;

    @Autowired
    RedisUtil redisUtil;

    @Autowired
    MailMapper mailMapper;

    int count;

    @RequestMapping("/hello")
    public String hello(){
        return "hello";
    }

    @CrossOrigin
    @RequestMapping("/login/{name}/{password}")
    public String login(@PathVariable("name") String name,@PathVariable("password") String password){

        boolean flag = false;

        if(redisUtil.hasKey(name)){
            String pwd = (String) redisUtil.get(name);
            System.out.println("redis缓存读密码");
            if(pwd.equals(password))
                flag = true;
            else
                flag = false;
        }else{
            int i = 0;
            try {
                i = userMapper.logIn(name, password);
                System.out.println(i);
                flag = (i != 0)?true:false;
                if(flag){
                    redisUtil.set(name,password);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.println(name+" " +password);
        return flag+"";
    }

    @RequestMapping("/login")
    public String login2(){

        int i = 0;
        try {
            i = userMapper.logIn("tmp", "tmp");
        } catch (Exception e) {
            e.printStackTrace();
        }

        String str;

        if(i == 0){
            str = "false";
        }else {
            str = "true";
        }
        System.out.println(str+"123");
        return str + "123";
    }

    @RequestMapping("/resign/{name}/{password}")
    public String resign(@PathVariable("name") String name,@PathVariable("password") String password){
        boolean flag = true;

        //创建用户
        User user = new User();
        user.setName(name);
        user.setPassword(password);

        //创建邮箱
        Mail mail = new Mail();
        mail.setName(name);

        try {
            userMapper.addUser(user);
            mailMapper.InsertMail(mail);
            redisUtil.set(name,password);
        } catch (Exception e) {
            flag = false;
            e.printStackTrace();
        }

        return flag+"";
    }

    @RequestMapping("/resign/check/{name}")
    public String check(@PathVariable("name") String name){
        boolean flag = false;
        if(redisUtil.hasKey(name)){
            flag = true;
        }else{
            User user = userMapper.getUserByName(name);
            if(user == null){
                flag = false;
            }else{
                flag = true;
            }
        }

        return flag + "";
    }

    @RequestMapping("/resign/findpwd/{name}")
    public String getpwd(@PathVariable("name") String name){
        String password;
        if(redisUtil.hasKey(name)){
            password = (String) redisUtil.get(name);
        }else{
            User user = userMapper.getUserByName(name);
            password = user.getPassword();
        }
        return password;
    }

    @RequestMapping("/changename/{name}/{chname}")
    public String changename(@PathVariable("name") String name,@PathVariable("chname") String chname){

        boolean flag = false;

        User user = userMapper.getUserByName(name);
        user.setName(chname);
        redisUtil.delete(name);
        redisUtil.set(chname,user.getPassword());
        System.out.println(user.toString());

        List<SquareMsg> msgList = squareMsgMapper.getAllMsg();

        try {
            for (SquareMsg msg : msgList) {
                if(msg.getName().equals(name)){
                    msg.setName(chname);
                    squareMsgMapper.changeName(msg);
                }
            }
            userMapper.updateUser(user);
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
            flag = false;
        }

        return flag + "";
    }


    @RequestMapping("/changepwd/{name}/{password}")
    public String changepwd(@PathVariable("name") String name,@PathVariable("password") String password){
        boolean flag = false;
        User user = userMapper.getUserByName(name);
        user.setPassword(password);

        try {
            userMapper.updateUser(user);
            redisUtil.set(name,password);
            flag = true;
        } catch (Exception e) {
            flag = false;
        }

        return flag + "";
    }

    @RequestMapping("/getname/{name}")
    public String getname(@PathVariable("name") String name) {
        User user = userMapper.getUserByName(name);
        return JSON.toJSONString(user);
    }

}

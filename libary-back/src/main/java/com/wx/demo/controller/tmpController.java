package com.wx.demo.controller;

import com.wx.demo.mapper.UserMapper;
import com.wx.demo.mybatis.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/test")
public class tmpController {

    @Autowired
    UserMapper userMapper;

    //清楚参数中的时间戳
    @RequestMapping("/upimage/{name}/{timestamp}")
    public String upimg(@PathVariable("name") String name,
                        @PathVariable("timestamp") String timestamp){

        User user = userMapper.getUserByName(name);

        return "/image/"+user.getId()+".jpg";
    }

    @RequestMapping("/userimage/{name}")
    public String getimg(@PathVariable("name") String name){
        User user = userMapper.getUserByName(name);
        String str = "";
        if(name.equals("user.jpg")){
            str = "/image/user.jpg";
        }else{
            str = "/image/"+user.getId()+".jpg";
        }

        return str;
    }

}

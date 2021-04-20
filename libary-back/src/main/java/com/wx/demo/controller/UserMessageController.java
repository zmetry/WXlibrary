package com.wx.demo.controller;

import com.alibaba.fastjson.JSON;
import com.wx.demo.mapper.UserMessageMapper;
import com.wx.demo.mybatis.entity.UserMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/test")
public class UserMessageController {

    @Autowired
    UserMessageMapper userMessageMapper;

    @RequestMapping("/usermessage/allmessage/{name}")
    public String getallusermessage(@PathVariable("name") String name){

        List<UserMessage> userMessages = userMessageMapper.getmessage(name);

        StringBuilder stringBuilder = new StringBuilder();


        stringBuilder.append(JSON.toJSONString(userMessages));


        return stringBuilder.toString();

    }

    @RequestMapping("/usermessage/setmessage/{name}/{message}")
    public String setmessage(@PathVariable("name") String name,@PathVariable("message") String message){

        boolean flag = true;

        UserMessage userMessage = new UserMessage();

        userMessage.setMessage(message);
        userMessage.setName(name);

        try {
            userMessageMapper.setmessage(userMessage);
            flag = true;
        } catch (Exception e) {
            flag = false;
            e.printStackTrace();
        }

        return flag+"";

    }


}

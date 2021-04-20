package com.wx.demo.controller;

import com.alibaba.fastjson.JSON;
import com.wx.demo.mapper.StuHallMapper;
import com.wx.demo.mybatis.entity.StudyHall;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/test")
public class StuHallController {

    @Autowired
    StuHallMapper stuHallMapper;

    @RequestMapping("/studyhall/{name}")
    public String getMessage(@PathVariable("name") String name){

        System.out.println(name);
        StudyHall studyHall = stuHallMapper.getMessage(name);
        String jsonString = JSON.toJSONString(studyHall);
        return jsonString;
    }

    @RequestMapping("/studyhall/allmessage")
    public String getallStudyhallmessage(){

        List<StudyHall> studyHalls = stuHallMapper.getAllMessage();

        StringBuilder stringBuilder = new StringBuilder();

       stringBuilder.append(JSON.toJSONString(studyHalls));

        return stringBuilder.toString();
    }

}

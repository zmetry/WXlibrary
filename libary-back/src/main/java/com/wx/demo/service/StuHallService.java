package com.wx.demo.service;


import com.wx.demo.mapper.StuHallMapper;
import com.wx.demo.mybatis.entity.StudyHall;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StuHallService implements StuHallMapper {

    @Autowired
    StuHallMapper stuHallMapper;

    @Override
    public StudyHall getMessage(String name) {
        return stuHallMapper.getMessage(name);
    }

    @Override
    public int addNowNum(String name) {
        return stuHallMapper.addNowNum(name);
    }

    @Override
    public int ReduceNowNum(String name) {
        return stuHallMapper.ReduceNowNum(name);
    }

    @Override
    public List<StudyHall> getAllMessage() {
        return stuHallMapper.getAllMessage();
    }
}

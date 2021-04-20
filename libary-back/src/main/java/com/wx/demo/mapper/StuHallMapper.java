package com.wx.demo.mapper;


import com.wx.demo.mybatis.entity.StudyHall;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface StuHallMapper {

    StudyHall getMessage(String name);

    int addNowNum(String name);

    int ReduceNowNum(String name);

    List<StudyHall> getAllMessage();

}

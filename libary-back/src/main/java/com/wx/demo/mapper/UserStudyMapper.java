package com.wx.demo.mapper;

import com.wx.demo.mybatis.entity.UserStudy;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserStudyMapper {

    UserStudy getMsgByName(String name);

    int DeleteByName(String name);

    int InsertMsg(UserStudy userStudy);

}

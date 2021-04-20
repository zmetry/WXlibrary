package com.wx.demo.service;

import com.wx.demo.mapper.UserStudyMapper;
import com.wx.demo.mybatis.entity.UserStudy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserStudyService implements UserStudyMapper {

    @Autowired
    UserStudyMapper userStudyMapper;

    @Override
    public UserStudy getMsgByName(String name) {
        return userStudyMapper.getMsgByName(name);
    }

    @Override
    public int DeleteByName(String name) {
        return userStudyMapper.DeleteByName(name);
    }

    @Override
    public int InsertMsg(UserStudy userStudy) {
        return userStudyMapper.InsertMsg(userStudy);
    }
}

package com.wx.demo.service;

import com.wx.demo.mapper.UserMessageMapper;
import com.wx.demo.mybatis.entity.UserMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserMessageService implements UserMessageMapper {

    @Autowired
    UserMessageMapper userMessageMapper;

    @Override
    public List<UserMessage> getmessage(String name) {
        return userMessageMapper.getmessage(name);
    }

    @Override
    public int setmessage(UserMessage userMessage) {
        return userMessageMapper.setmessage(userMessage);
    }
}

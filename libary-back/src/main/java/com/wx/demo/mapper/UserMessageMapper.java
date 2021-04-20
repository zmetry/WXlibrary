package com.wx.demo.mapper;

import com.wx.demo.mybatis.entity.UserMessage;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMessageMapper {

    List<UserMessage> getmessage(String name);

    int setmessage(UserMessage userMessage);

}

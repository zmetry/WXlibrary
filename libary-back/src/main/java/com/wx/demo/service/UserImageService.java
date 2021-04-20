package com.wx.demo.service;

import com.wx.demo.mapper.UserImageMapper;
import com.wx.demo.mybatis.entity.UserImage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserImageService implements UserImageMapper {

    @Autowired
    UserImageMapper userImageMapper;

    @Override
    public int addUserImage(UserImage userImage) {
        return userImageMapper.addUserImage(userImage);
    }

    @Override
    public int updateUserImage(UserImage userImage) {
        return userImageMapper.updateUserImage(userImage);
    }

    @Override
    public UserImage getUserImage(String name) {
        return userImageMapper.getUserImage(name);
    }
}

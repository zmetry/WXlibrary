package com.wx.demo.mapper;

import com.wx.demo.mybatis.entity.UserImage;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserImageMapper {

    int addUserImage(UserImage userImage);

    int updateUserImage(UserImage userImage);

    UserImage getUserImage(String name);

}

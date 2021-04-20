package com.wx.demo.mapper;


import com.mysql.cj.jdbc.Blob;
import com.wx.demo.mybatis.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
public interface UserMapper {

    List<User> getUserList();

    User getUserById(int id);

    User getUserByName(String name);

    int addUser(User user);

    int updateUser(User user);

    int deleteUser(String name);

    int logIn(@Param("name") String name,@Param("password") String password);

}

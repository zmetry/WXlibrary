package com.wx.demo.service;

import com.mysql.cj.jdbc.Blob;
import com.wx.demo.mapper.UserMapper;
import com.wx.demo.mybatis.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements UserMapper{

    @Autowired
    UserMapper userMapper;


    @Override
    public List<User> getUserList() {
        return userMapper.getUserList();
    }

    @Override
    public User getUserById(int id) {
        return userMapper.getUserById(id);
    }

    @Override
    public User getUserByName(String name) {
        return userMapper.getUserByName(name);
    }

    @Override
    public int addUser(User user) {
        return userMapper.addUser(user);
    }

    @Override
    public int updateUser(User user) {
        return userMapper.updateUser(user);
    }

    @Override
    public int deleteUser(String name) {
        return userMapper.deleteUser(name);
    }

    @Override
    public int logIn(String name,String password) {
        return userMapper.logIn(name, password);
    }


}

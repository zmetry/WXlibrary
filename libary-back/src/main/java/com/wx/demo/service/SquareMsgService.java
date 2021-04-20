package com.wx.demo.service;

import com.wx.demo.mapper.SquareMsgMapper;
import com.wx.demo.mybatis.entity.SquareMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SquareMsgService implements SquareMsgMapper {

    @Autowired
    SquareMsgMapper squareMsgMapper;

    @Override
    public List<SquareMsg> getAllMsg() {
        return squareMsgMapper.getAllMsg();
    }

    @Override
    public int addMsg(SquareMsg squareMsg) {
        return squareMsgMapper.addMsg(squareMsg);
    }

    @Override
    public int changeName(SquareMsg squareMsg) {
        return squareMsgMapper.changeName(squareMsg);
    }

    @Override
    public int deleteMsg(SquareMsg squareMsg) {
        return squareMsgMapper.deleteMsg(squareMsg);
    }

}

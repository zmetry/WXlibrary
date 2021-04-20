package com.wx.demo.mapper;

import com.wx.demo.mybatis.entity.SquareMsg;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SquareMsgMapper {

    List<SquareMsg> getAllMsg();

    int addMsg(SquareMsg squareMsg);

    int changeName(SquareMsg squareMsg);


    int deleteMsg(SquareMsg squareMsg);

}

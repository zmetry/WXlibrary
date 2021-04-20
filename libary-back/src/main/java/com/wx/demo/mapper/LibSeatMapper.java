package com.wx.demo.mapper;

import com.wx.demo.mybatis.entity.LibSeat;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface LibSeatMapper {

    List<LibSeat> getall();

    int SetSeat(@Param("cols") int cols,@Param("ros") int ros, @Param("exist") int exist);

    //根据id找
    LibSeat getSeatById(int id);
    //预定座位
    int reserveSeat(int id);
    //取消预订
    int UnreserveSeat(int id);

}

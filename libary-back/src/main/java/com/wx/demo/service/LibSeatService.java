package com.wx.demo.service;

import com.wx.demo.mapper.LibSeatMapper;
import com.wx.demo.mybatis.entity.LibSeat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LibSeatService implements LibSeatMapper {

    @Autowired
    LibSeatMapper libSeatMapper;

    @Override
    public List<LibSeat> getall() {
        return libSeatMapper.getall();
    }

    @Override
    public int SetSeat(int cols, int ros, int exist) {
        return libSeatMapper.SetSeat(cols,ros,exist);
    }

    @Override
    public LibSeat getSeatById(int id) {
        return libSeatMapper.getSeatById(id);
    }

    @Override
    public int reserveSeat(int id) {
        return libSeatMapper.reserveSeat(id);
    }

    @Override
    public int UnreserveSeat(int id) {
        return libSeatMapper.UnreserveSeat(id);
    }
}

package com.wx.demo.controller;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wx.demo.mapper.LibSeatMapper;
import com.wx.demo.mapper.UserStudyMapper;
import com.wx.demo.mybatis.entity.LibSeat;
import com.wx.demo.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/test")
public class LibSeatController {

    @Autowired
    LibSeatMapper libSeatMapper;

    @Autowired
    RedisUtil redisUtil;

    @Autowired
    UserStudyMapper userStudyMapper;

    @RequestMapping("/libseat/allmessage")
    public String getallmes() throws JsonProcessingException {

        List<LibSeat> libSeats;
        if(redisUtil.hasKey("libSeats")){
            //先读取，在做序列化
            String str = (String) redisUtil.get("libSeats");
            libSeats = JSON.parseArray(str,LibSeat.class);
        }else{
            libSeats = libSeatMapper.getall();
            String string = JSON.toJSONString(libSeats);
            redisUtil.set("libSeats",string);
        }

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(JSON.toJSONString(libSeats));

        return stringBuilder.toString();
    }

    @RequestMapping("/libseat/setseat/{cols}/{ros}/{exist}")
    public String setseat(@PathVariable("cols") int cols,
                          @PathVariable("ros") int ros,
                          @PathVariable("exist") int exist){
        boolean flag = true;
        try {
            libSeatMapper.SetSeat(cols, ros, exist);

        } catch (Exception e) {
            flag = false;
            e.printStackTrace();
        }
        return flag+"";

    }

    @RequestMapping("/libseat/reserve/{id}")
    public String reserveSeat(@PathVariable("id") int id){
        boolean flag = false;
        try {
            libSeatMapper.reserveSeat(id);
            //更新redis
            String str = (String) redisUtil.get("libSeats");
            List<LibSeat> libSeats = JSON.parseArray(str,LibSeat.class);
            LibSeat libSeat = libSeats.get(id - 1);
            libSeat.setExist(1);
            redisUtil.set("libSeats",JSON.toJSONString(libSeats));

            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag+"";
    }

    @RequestMapping("/libseat/unreserve/{id}")
    public String UnreserveSear(@PathVariable("id") int id){
        boolean flag = false;
        try {
            libSeatMapper.UnreserveSeat(id);

            //更新redis
            String str = (String) redisUtil.get("libSeats");
            List<LibSeat> libSeats = JSON.parseArray(str,LibSeat.class);
            System.out.println(libSeats);
            LibSeat libSeat = libSeats.get(id - 1);
            libSeat.setExist(0);
            redisUtil.set("libSeats",JSON.toJSONString(libSeats));

            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag+"";
    }

}

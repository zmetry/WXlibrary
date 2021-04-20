package com.wx.demo.controller;


import com.alibaba.fastjson.JSON;
import com.wx.demo.mapper.UserStudyMapper;
import com.wx.demo.mybatis.entity.UserStudy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class UserStudyController {

    @Autowired
    UserStudyMapper userStudyMapper;

    @RequestMapping("/UserStudy/userexist/{name}")
    public String UserExist(@PathVariable("name") String name){

        boolean flag = false;

        UserStudy user = userStudyMapper.getMsgByName(name);

        if(user == null){
            flag = false;
        }else {
            flag = true;
        }
        return flag+"";
    }

    @RequestMapping("/UserStudy/getMsg/{name}")
    public String GetMsg(@PathVariable("name") String name){

        UserStudy user = userStudyMapper.getMsgByName(name);

        String str = JSON.toJSONString(user);

        return str;
    }

    @RequestMapping("/UserStduy/InsMsg/{name}/{col}/{row}/{BfDate}/{LearnId}")
    public String InsertMsg(@PathVariable("name") String name,
                            @PathVariable("col") int col,
                            @PathVariable("row") int row,
                            @PathVariable("BfDate") long BfDate,
                            @PathVariable("LearnId") int LearnId){
        boolean flag = false;

        UserStudy userStudy = new UserStudy();
        userStudy.setBfDate(BfDate);
        userStudy.setCols(col);
        userStudy.setName(name);
        userStudy.setRos(row);
        userStudy.setLearnId(LearnId);

        try {
            userStudyMapper.InsertMsg(userStudy);
            flag = true;
        } catch (Exception e) {
            flag = false;
            e.printStackTrace();
        }

        return flag+"";
    }

    @RequestMapping("/UserStudy/unreserve/{name}")
    public String delete(@PathVariable("name") String name){

        boolean flag = false;

        try {
            userStudyMapper.DeleteByName(name);
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
            flag = false;
        }


        return flag+"";
    }

}

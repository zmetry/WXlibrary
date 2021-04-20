package com.wx.demo.mapper;

import com.wx.demo.mybatis.entity.Mail;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MailMapper {

    public Mail getMsgByName(String name);

    public List<Mail> getAllMsg();

    public int ChangeMail(Mail mail);

    public int ChangeName(Mail mail);

    public Mail getMsgByMail(String mail);

    public int InsertMail(Mail mail);

}

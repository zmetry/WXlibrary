package com.wx.demo.service;

import com.wx.demo.mapper.MailMapper;
import com.wx.demo.mybatis.entity.Mail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MailService implements MailMapper {

    @Autowired
    MailMapper mailMapper;

    @Override
    public Mail getMsgByName(String name) {
        return mailMapper.getMsgByName(name);
    }

    @Override
    public List<Mail> getAllMsg() {
        return mailMapper.getAllMsg();
    }

    @Override
    public int ChangeMail(Mail mail) {
        return mailMapper.ChangeMail(mail);
    }

    @Override
    public int ChangeName(Mail mail) {
        return mailMapper.ChangeName(mail);
    }

    @Override
    public Mail getMsgByMail(String mail) {
        return mailMapper.getMsgByMail(mail);
    }

    @Override
    public int InsertMail(Mail mail) {
        return mailMapper.InsertMail(mail);
    }
}

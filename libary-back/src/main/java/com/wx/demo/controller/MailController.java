package com.wx.demo.controller;

import com.alibaba.fastjson.JSON;
import com.wx.demo.mapper.MailMapper;
import com.wx.demo.mybatis.entity.Mail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/test")
public class MailController {

    @Autowired
    MailMapper mailMapper;

    @Autowired
    JavaMailSenderImpl mailSender;

    @RequestMapping("/mail/allmsg")
    public String getall(){

        List<Mail> list = mailMapper.getAllMsg();

        String str = JSON.toJSONString(list);

        return str;

    }

    @RequestMapping("/mail/name/{bename}/{afname}")
    public String changename(@PathVariable("bename") String bename,@PathVariable("afname") String afname){
        boolean flag = false;
        Mail mail = mailMapper.getMsgByName(bename);

        mail.setName(afname);

        try {
            mailMapper.ChangeName(mail);
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return flag + "";
    }

    @RequestMapping("/mail/mail/{name}/{afmail}")
    public String changemail(@PathVariable("name") String name,@PathVariable("afmail") String afmail){
        boolean flag = false;
        Mail mail = mailMapper.getMsgByName(name);

        mail.setMail(afmail);

        flag = isEmail(afmail);

        if(flag){
            try {
                mailMapper.ChangeMail(mail);
                flag = true;
            } catch (Exception e) {
                flag = false;
                e.printStackTrace();
            }
        }

        return flag + "";
    }

    @RequestMapping("/mail/msg/{name}")
    public String getPersonalMsg(@PathVariable("name") String name){
        Mail mail = mailMapper.getMsgByName(name);
        String str = mail.getMail();
        return str;
    }

    @RequestMapping("/mail/SendMail/{name}")
    public String SendMail(@PathVariable("name") String name){

        Mail mail = mailMapper.getMsgByName(name);

        String randomString = getRandomString(6);

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom("2236913183@qq.com");
        simpleMailMessage.setSubject("找回密码");
        simpleMailMessage.setText("您的验证码为：" + randomString);
        simpleMailMessage.setTo(mail.getMail());

        mailSender.send(simpleMailMessage);

        return randomString;
    }

    //生成随机数函数
    private String getRandomString(int length) {
        String str = "abcdefghigklmnopqrstuvwxyzABCDEFGHIGKLMNOPQRSTUVWXYZ1234567890";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();

        for (int i = 0; i < length; i++) {
            // 从1-62的字符中随机取出一位
            int number = random.nextInt(62);
            sb.append(str.charAt(number));
        }
        return sb.toString();
    }

    //判断Email合法性
    private boolean isEmail(String email) {
        if (email == null)
            return false;
        String rule = "[\\w!#$%&'*+/=?^_`{|}~-]+(?:\\.[\\w!#$%&'*+/=?^_`{|}~-]+)*@(?:[\\w](?:[\\w-]*[\\w])?\\.)+[\\w](?:[\\w-]*[\\w])?";
        Pattern pattern;
        Matcher matcher;
        pattern = Pattern.compile(rule);
        matcher = pattern.matcher(email);
        if (matcher.matches())
            return true;
        else
            return false;
    }

}

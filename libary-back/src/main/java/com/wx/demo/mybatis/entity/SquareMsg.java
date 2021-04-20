package com.wx.demo.mybatis.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "squaremsg")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SquareMsg {

    @Id
    int id;
    String name;
    String imgurl;
    String text;
    Date date;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "SquareMsg{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", imgurl='" + imgurl + '\'' +
                ", text='" + text + '\'' +
                ", date=" + date +
                '}';
    }
}

package com.wx.demo.mybatis.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "studyhall")
public class StudyHall {
    @Id
    public String name;
    public int capacity;
    public int NowNum;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getNowNum() {
        return NowNum;
    }

    public void setNowNum(int nowNum) {
        NowNum = nowNum;
    }

    @Override
    public String toString() {
        return "StudyHall{" +
                "name='" + name + '\'' +
                ", capacity=" + capacity +
                ", NowNum=" + NowNum +
                '}';
    }
}

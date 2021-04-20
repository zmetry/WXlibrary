package com.wx.demo.mybatis.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import javax.persistence.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "userstudy")
public class UserStudy {

    @Id
    int id;
    String name;
    int cols;
    int ros;
    long BfDate;
    int LearnId;

    public int getCols() {
        return cols;
    }

    public void setCols(int cols) {
        this.cols = cols;
    }

    public int getRos() {
        return ros;
    }

    public void setRos(int ros) {
        this.ros = ros;
    }

    public int getLearnId() {
        return LearnId;
    }

    public void setLearnId(int learnId) {
        LearnId = learnId;
    }

    public long getBfDate() {
        return BfDate;
    }

    public void setBfDate(long bfDate) {
        BfDate = bfDate;
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

}

package com.wx.demo.mybatis.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "libseat")
public class LibSeat {

    @Id
    int id;
    int cols;
    int ros;
    int exist;

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getExist() {
        return exist;
    }

    public void setExist(int exist) {
        this.exist = exist;
    }

    @Override
    public String toString() {
        return "LibSeat{" +
                "cols=" + cols +
                ", ros=" + ros +
                ", id=" + id +
                ", exist=" + exist +
                '}';
    }
}

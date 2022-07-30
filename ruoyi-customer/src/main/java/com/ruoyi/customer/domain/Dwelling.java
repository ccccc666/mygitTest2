package com.ruoyi.customer.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.TreeEntity;

import java.util.List;

public class Dwelling extends TreeEntity {

    private static final long serialVersionUID = 1L;

    public Integer code;

    public String name;

    public Integer level;

    public Integer type;

    public String abName;

    public Integer pid;

    public String pName;

    public String note;

    @Excel(name = "加载二级目录子节点")
    @JsonSerialize(include=JsonSerialize.Inclusion.NON_EMPTY)//没有的数据就不显示的一个注释，处理前端
    @TableField(exist = false)
    private List<Dwelling> children;//list里面包list集合

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getAbName() {
        return abName;
    }

    public void setAbName(String abName) {
        this.abName = abName;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    // TODO: 2022/5/11


    @Override
    public String toString() {
        return "Dwelling{" +
                "code=" + code +
                ", name='" + name + '\'' +
                ", level=" + level +
                ", type=" + type +
                ", abName='" + abName + '\'' +
                ", pid=" + pid +
                ", pName='" + pName + '\'' +
                ", note='" + note + '\'' +
                '}';
    }
}

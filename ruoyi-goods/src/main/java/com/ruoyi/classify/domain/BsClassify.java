package com.ruoyi.classify.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.TreeEntity;

import java.util.List;

/**
 * 分类查询对象 bs_classify
 * 
 * @author ruoyi
 * @date 2022-05-06
 */
public class BsClassify extends TreeEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Integer id;

    /** 分类名称 */
    @Excel(name = "分类名称")
    private String cfName;

    /** 分类等级 */
    @Excel(name = "分类等级")
    private Integer cfLevel;

    /** 父级ID */
    @Excel(name = "父级ID")
    private Integer cfParentid;

    @Excel(name = "加载二级目录子节点")
    @JsonSerialize(include=JsonSerialize.Inclusion.NON_EMPTY)
    @TableField(exist = false)
    private List<BsClassify> children;


    public void setId(Integer id) 
    {
        this.id = id;
    }

    public Integer getId() 
    {
        return id;
    }
    public void setCfName(String cfName) 
    {
        this.cfName = cfName;
    }

    public String getCfName() 
    {
        return cfName;
    }
    public void setCfLevel(Integer cfLevel) 
    {
        this.cfLevel = cfLevel;
    }

    public Integer getCfLevel() 
    {
        return cfLevel;
    }
    public void setCfParentid(Integer cfParentid) 
    {
        this.cfParentid = cfParentid;
    }

    public Integer getCfParentid() 
    {
        return cfParentid;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("cfName", getCfName())
            .append("cfLevel", getCfLevel())
            .append("cfParentid", getCfParentid())
            .toString();
    }
}

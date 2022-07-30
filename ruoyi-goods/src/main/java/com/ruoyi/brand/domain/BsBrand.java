package com.ruoyi.brand.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 品牌信息对象 bs_brand
 * 
 * @author ruoyi
 * @date 2022-05-06
 */
public class BsBrand extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** Id */
    private Integer brId;

    /** 品牌名称 */
    @Excel(name = "品牌名称")
    private String brName;

    /** 品牌类型 */
    @Excel(name = "品牌类型")
    private Integer brType;

    public void setBrId(Integer brId) 
    {
        this.brId = brId;
    }

    public Integer getBrId() 
    {
        return brId;
    }
    public void setBrName(String brName) 
    {
        this.brName = brName;
    }

    public String getBrName() 
    {
        return brName;
    }
    public void setBrType(Integer brType) 
    {
        this.brType = brType;
    }

    public Integer getBrType() 
    {
        return brType;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("brId", getBrId())
            .append("brName", getBrName())
            .append("brType", getBrType())
            .toString();
    }
}

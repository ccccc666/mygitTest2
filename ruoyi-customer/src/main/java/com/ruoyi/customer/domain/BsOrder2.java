package com.ruoyi.customer.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 业务信息对象 bs_order
 * 
 * @author ruoyi
 * @date 2022-05-05
 */
public class BsOrder2 extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 业务订单编号 */
    private Long id;

    /** 业务客户姓名 */
    @Excel(name = "业务客户姓名")
    private String bsCtname;

    /** 客户联系方式 */
    @Excel(name = "客户联系方式")
    private Long bsCtphone;

    /** 商品交易单价 */
    @Excel(name = "商品交易单价")
    private Long bsPrice;

    /** 商品交易总量 */
    @Excel(name = "商品交易总量")
    private Long bsNumber;

    /** 业务贸易总额 */
    @Excel(name = "业务贸易总额")
    private Long bsAllprice;

    /** 交易方式 */
    @Excel(name = "交易方式")
    private String bsTsway;

    /** 客户Id */
    @Excel(name = "客户Id")
    private Integer bsCustomerid;

    private String gsName;
    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setBsCtname(String bsCtname) 
    {
        this.bsCtname = bsCtname;
    }

    public String getBsCtname() 
    {
        return bsCtname;
    }
    public void setBsCtphone(Long bsCtphone) 
    {
        this.bsCtphone = bsCtphone;
    }

    public Long getBsCtphone() 
    {
        return bsCtphone;
    }
    public void setBsPrice(Long bsPrice) 
    {
        this.bsPrice = bsPrice;
    }

    public Long getBsPrice() 
    {
        return bsPrice;
    }
    public void setBsNumber(Long bsNumber) 
    {
        this.bsNumber = bsNumber;
    }

    public Long getBsNumber() 
    {
        return bsNumber;
    }
    public void setBsAllprice(Long bsAllprice) 
    {
        this.bsAllprice = bsAllprice;
    }

    public Long getBsAllprice() 
    {
        return bsAllprice;
    }
    public void setBsTsway(String bsTsway) 
    {
        this.bsTsway = bsTsway;
    }

    public String getBsTsway() 
    {
        return bsTsway;
    }

    public Integer getBsCustomerid() {
        return bsCustomerid;
    }

    public void setBsCustomerid(Integer bsCustomerid) {
        this.bsCustomerid = bsCustomerid;
    }

    public String getGsName() {
        return gsName;
    }

    public void setGsName(String gsName) {
        this.gsName = gsName;
    }

    @Override
    public String toString() {
        return "BsOrder2{" +
                "id=" + id +
                ", bsCtname='" + bsCtname + '\'' +
                ", bsCtphone=" + bsCtphone +
                ", bsPrice=" + bsPrice +
                ", bsNumber=" + bsNumber +
                ", bsAllprice=" + bsAllprice +
                ", bsTsway='" + bsTsway + '\'' +
                ", bsCustomerid=" + bsCustomerid +
                ", gsName='" + gsName + '\'' +
                '}';
    }
}

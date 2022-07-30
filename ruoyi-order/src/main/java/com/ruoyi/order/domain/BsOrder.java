package com.ruoyi.order.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 业务信息对象 bs_order
 * 
 * @author ruoyi
 * @date 2022-05-16
 */
public class BsOrder extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 业务订单编号 */
    private Long id;
    /** 业务客户姓名 */
    @Excel(name = "业务客户姓名")
    private String bsCtname;
    /** 客户联系方式 */
    @Excel(name = "客户联系方式")
    private String bsCtphone;
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
    /*权限信息*/
    private String bsAuthentication;
    /*商品id*/
    private Integer bsGoodsid;
    /*支付状态*/
    private Long bsGoodsStates;
    /*发货状态*/
    private Long bsSentStates;
    @TableField(exist = false)
    private String strBsGoodsSt;
    @TableField(exist = false)
    private String strBsSentSt;
    /*商品名称*/
    private String goodsName;
    /*交换数据的差值*/
    @TableField(exist = false)
    private Long exChangeBsNumber;

    public String getBsAuthentication() {
        return bsAuthentication;
    }

    public void setBsAuthentication(String bsAuthentication) {
        this.bsAuthentication = bsAuthentication;
    }

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
    public void setBsCtphone(String bsCtphone) 
    {
        this.bsCtphone = bsCtphone;
    }

    public String getBsCtphone() 
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
    public void setBsCustomerid(Integer bsCustomerid) 
    {
        this.bsCustomerid = bsCustomerid;
    }

    public Integer getBsCustomerid() 
    {
        return bsCustomerid;
    }


    public Integer getBsGoodsid() {
        return bsGoodsid;
    }

    public void setBsGoodsid(Integer bsGoodsid) {
        this.bsGoodsid = bsGoodsid;
    }

    public Long getBsGoodsStates() {
        return bsGoodsStates;
    }

    public void setBsGoodsStates(Long bsGoodsStates) {
        this.bsGoodsStates = bsGoodsStates;
    }

    public Long getBsSentStates() {
        return bsSentStates;
    }

    public void setBsSentStates(Long bsSentStates) {
        this.bsSentStates = bsSentStates;
    }

    public String getStrBsGoodsSt() {
        return strBsGoodsSt;
    }

    public void setStrBsGoodsSt(String strBsGoodsSt) {
        this.strBsGoodsSt = strBsGoodsSt;
    }

    public String getStrBsSentSt() {
        return strBsSentSt;
    }

    public void setStrBsSentSt(String strBsSentSt) {
        this.strBsSentSt = strBsSentSt;
    }


    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public Long getExChangeBsNumber() {
        return exChangeBsNumber;
    }

    public void setExChangeBsNumber(Long exChangeBsNumber) {
        this.exChangeBsNumber = exChangeBsNumber;
    }

    @Override
    public String toString() {
        return "BsOrder{" +
                "id=" + id +
                ", bsCtname='" + bsCtname + '\'' +
                ", bsCtphone='" + bsCtphone + '\'' +
                ", bsPrice=" + bsPrice +
                ", bsNumber=" + bsNumber +
                ", bsAllprice=" + bsAllprice +
                ", bsTsway='" + bsTsway + '\'' +
                ", bsCustomerid=" + bsCustomerid +
                ", bsAuthentication='" + bsAuthentication + '\'' +
                ", bsGoodsid=" + bsGoodsid +
                ", bsGoodsStates=" + bsGoodsStates +
                ", bsSentStates=" + bsSentStates +
                ", strBsGoodsSt='" + strBsGoodsSt + '\'' +
                ", strBsSentSt='" + strBsSentSt + '\'' +
                ", goodsName='" + goodsName + '\'' +
                ", exChangeBsNumber=" + exChangeBsNumber +
                '}';
    }
}

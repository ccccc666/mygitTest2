package com.ruoyi.order.vo;

import com.ruoyi.common.annotation.Excel;

import java.util.List;

public class AddOrderVo {

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

    private String bsAuthentication;
    private Long bsGoodsStates;

    private Long bsSentStates;

    private List<AddOrderGoodsVo> goods;//类中夹杂List集合类

    @Override
    public String toString() {
        return "AddOrderVo{" +
                "id=" + id +
                ", bsCtname='" + bsCtname + '\'' +
                ", bsCtphone='" + bsCtphone + '\'' +
                ", bsPrice=" + bsPrice +
                ", bsNumber=" + bsNumber +
                ", bsAllprice=" + bsAllprice +
                ", bsTsway='" + bsTsway + '\'' +
                ", bsCustomerid=" + bsCustomerid +
                ", bsAuthentication='" + bsAuthentication + '\'' +
                ", bsGoodsStates=" + bsGoodsStates +
                ", bsSentStates=" + bsSentStates +
                ", goods=" + goods +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBsCtname() {
        return bsCtname;
    }

    public void setBsCtname(String bsCtname) {
        this.bsCtname = bsCtname;
    }

    public String getBsCtphone() {
        return bsCtphone;
    }

    public void setBsCtphone(String bsCtphone) {
        this.bsCtphone = bsCtphone;
    }

    public Long getBsPrice() {
        return bsPrice;
    }

    public void setBsPrice(Long bsPrice) {
        this.bsPrice = bsPrice;
    }

    public Long getBsNumber() {
        return bsNumber;
    }

    public void setBsNumber(Long bsNumber) {
        this.bsNumber = bsNumber;
    }

    public Long getBsAllprice() {
        return bsAllprice;
    }

    public void setBsAllprice(Long bsAllprice) {
        this.bsAllprice = bsAllprice;
    }

    public String getBsTsway() {
        return bsTsway;
    }

    public void setBsTsway(String bsTsway) {
        this.bsTsway = bsTsway;
    }

    public Integer getBsCustomerid() {
        return bsCustomerid;
    }

    public void setBsCustomerid(Integer bsCustomerid) {
        this.bsCustomerid = bsCustomerid;
    }

    public List<AddOrderGoodsVo> getGoods() {
        return goods;
    }

    public void setGoods(List<AddOrderGoodsVo> goods) {
        this.goods = goods;
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

    public String getBsAuthentication() {
        return bsAuthentication;
    }

    public void setBsAuthentication(String bsAuthentication) {
        this.bsAuthentication = bsAuthentication;
    }
}

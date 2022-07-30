package com.ruoyi.order.vo;

import com.ruoyi.common.annotation.Excel;

public class AddOrderGoodsVo {

    /** 商品id */
    private Integer gsId;

    /** 商品名称 */
    @Excel(name = "商品名称")
    private String gsName;

    /** 商品编号 */
    @Excel(name = "商品编号")
    private String gsGsidnumber;

    /** 商品单价 */
    @Excel(name = "商品单价")
    private Long gsSgprice;

    private Long num;

    @Override
    public String toString() {
        return "AddOrderGoodsVo{" +
                "gsId=" + gsId +
                ", gsName='" + gsName + '\'' +
                ", gsGsidnumber='" + gsGsidnumber + '\'' +
                ", gsSgprice=" + gsSgprice +
                ", num=" + num +
                '}';
    }

    public Integer getGsId() {
        return gsId;
    }

    public void setGsId(Integer gsId) {
        this.gsId = gsId;
    }

    public String getGsName() {
        return gsName;
    }

    public void setGsName(String gsName) {
        this.gsName = gsName;
    }

    public String getGsGsidnumber() {
        return gsGsidnumber;
    }

    public void setGsGsidnumber(String gsGsidnumber) {
        this.gsGsidnumber = gsGsidnumber;
    }

    public Long getGsSgprice() {
        return gsSgprice;
    }

    public void setGsSgprice(Long gsSgprice) {
        this.gsSgprice = gsSgprice;
    }

    public Long getNum() {
        return num;
    }

    public void setNum(Long num) {
        this.num = num;
    }
}

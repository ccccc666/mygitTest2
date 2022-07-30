package com.ruoyi.customer.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 客户信息管理对象 bs_customer
 * 
 * @author ruoyi
 * @date 2022-05-06
 */
public class BsCustomer extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 客户id */
    private Integer id;

    /** 客户姓名 */
    @Excel(name = "客户姓名")
    private String ctName;

    /** 客户地址 */
    @Excel(name = "客户地址")
    private String ctAddress;

    /** 客户电话号码 */
    @Excel(name = "客户电话号码")
    private String ctTelephone;

    /** 客户会员编号 */
    @Excel(name = "客户会员编号")
    private String ctVipnumber;

    /** 客户信誉等级 */
    @Excel(name = "客户信誉等级")
    private Integer ctCredit;

    /** 客户会员等级 */
    @Excel(name = "客户会员等级")
    private String ctViplevel;

    @TableField(exist = false)
    private String addressName;

    public void setId(Integer id) 
    {
        this.id = id;
    }

    public Integer getId() 
    {
        return id;
    }
    public void setCtName(String ctName) 
    {
        this.ctName = ctName;
    }

    public String getCtName() 
    {
        return ctName;
    }
    public void setCtAddress(String ctAddress) 
    {
        this.ctAddress = ctAddress;
    }

    public String getCtAddress() 
    {
        return ctAddress;
    }
    public void setCtTelephone(String ctTelephone) 
    {
        this.ctTelephone = ctTelephone;
    }

    public String getCtTelephone() 
    {
        return ctTelephone;
    }
    public void setCtVipnumber(String ctVipnumber) 
    {
        this.ctVipnumber = ctVipnumber;
    }

    public String getCtVipnumber() 
    {
        return ctVipnumber;
    }
    public void setCtCredit(Integer ctCredit) 
    {
        this.ctCredit = ctCredit;
    }

    public Integer getCtCredit() 
    {
        return ctCredit;
    }
    public void setCtViplevel(String ctViplevel) 
    {
        this.ctViplevel = ctViplevel;
    }

    public String getCtViplevel() 
    {
        return ctViplevel;
    }

    public String getAddressName() {
        return addressName;
    }

    public void setAddressName(String addressName) {
        this.addressName = addressName;
    }

    @Override
    public String toString() {
        return "BsCustomer{" +
                "id=" + id +
                ", ctName='" + ctName + '\'' +
                ", ctAddress='" + ctAddress + '\'' +
                ", ctTelephone='" + ctTelephone + '\'' +
                ", ctVipnumber='" + ctVipnumber + '\'' +
                ", ctCredit=" + ctCredit +
                ", ctViplevel='" + ctViplevel + '\'' +
                ", addressName='" + addressName + '\'' +
                '}';
    }
}

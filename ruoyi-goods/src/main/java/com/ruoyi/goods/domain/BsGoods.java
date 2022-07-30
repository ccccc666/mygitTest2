package com.ruoyi.goods.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 商品管理对象 bs_goods
 * 
 * @author ruoyi
 * @date 2022-05-06
 */
public class BsGoods extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 商品id */
    private Integer gsId;

    /** 商品名称 */
    @Excel(name = "商品名称")
    private String gsName;

    /** 商品编号 */
    @Excel(name = "商品编号")
    private String gsGsidnumber;

    /** 品牌 */
    @Excel(name = "品牌")
    private Integer gsBrand;

    /** 商品单价 */
    @Excel(name = "商品单价")
    private Integer gsSgprice;

    /** 商品库存 */
    @Excel(name = "商品库存")
    private Integer gsGsnumber;

    @TableField(exist = false)
    private Integer gsGsnumberNew;

    /** 是否自营 */
    @Excel(name = "是否自营")
    private String gsIfsupport;

    /** 一级分类 */
    @Excel(name = "一级分类")
    private Integer gsCyid1;

    /** 二级分类 */
    @Excel(name = "二级分类")
    private Integer gsCyid2;

    /** 三级分类 */
    @Excel(name = "三级分类")
    private Integer gsCyid3;

    private String cfname1;

    private String cfname2;

    private String cfname3;

    public String getCfname1() {
        return cfname1;
    }

    public void setCfname1(String cfname1) {
        this.cfname1 = cfname1;
    }

    public String getCfname2() {
        return cfname2;
    }

    public void setCfname2(String cfname2) {
        this.cfname2 = cfname2;
    }

    public String getCfname3() {
        return cfname3;
    }

    public void setCfname3(String cfname3) {
        this.cfname3 = cfname3;
    }

    public void setGsId(Integer gsId)
    {
        this.gsId = gsId;
    }

    public Integer getGsId() 
    {
        return gsId;
    }
    public void setGsName(String gsName) 
    {
        this.gsName = gsName;
    }

    public String getGsName() 
    {
        return gsName;
    }
    public void setGsGsidnumber(String gsGsidnumber) 
    {
        this.gsGsidnumber = gsGsidnumber;
    }

    public String getGsGsidnumber() 
    {
        return gsGsidnumber;
    }
    public void setGsBrand(Integer gsBrand) 
    {
        this.gsBrand = gsBrand;
    }

    public Integer getGsBrand() 
    {
        return gsBrand;
    }
    public void setGsSgprice(Integer gsSgprice) 
    {
        this.gsSgprice = gsSgprice;
    }

    public Integer getGsSgprice() 
    {
        return gsSgprice;
    }
    public void setGsGsnumber(Integer gsGsnumber) 
    {
        this.gsGsnumber = gsGsnumber;
    }

    public Integer getGsGsnumber() 
    {
        return gsGsnumber;
    }
    public void setGsIfsupport(String gsIfsupport) 
    {
        this.gsIfsupport = gsIfsupport;
    }

    public String getGsIfsupport() 
    {
        return gsIfsupport;
    }
    public void setGsCyid1(Integer gsCyid1) 
    {
        this.gsCyid1 = gsCyid1;
    }

    public Integer getGsCyid1() 
    {
        return gsCyid1;
    }
    public void setGsCyid2(Integer gsCyid2) 
    {
        this.gsCyid2 = gsCyid2;
    }

    public Integer getGsCyid2() 
    {
        return gsCyid2;
    }
    public void setGsCyid3(Integer gsCyid3) 
    {
        this.gsCyid3 = gsCyid3;
    }

    public Integer getGsCyid3() 
    {
        return gsCyid3;
    }

    public Integer getGsGsnumberNew() {
        return gsGsnumberNew;
    }

    public void setGsGsnumberNew(Integer gsGsnumberNew) {
        this.gsGsnumberNew = gsGsnumberNew;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("gsId", getGsId())
            .append("gsName", getGsName())
            .append("gsGsidnumber", getGsGsidnumber())
            .append("gsBrand", getGsBrand())
            .append("gsSgprice", getGsSgprice())
            .append("gsGsnumber", getGsGsnumber())
            .append("gsIfsupport", getGsIfsupport())
            .append("gsCyid1", getGsCyid1())
            .append("gsCyid2", getGsCyid2())
            .append("gsCyid3", getGsCyid3())
            .toString();
    }
}

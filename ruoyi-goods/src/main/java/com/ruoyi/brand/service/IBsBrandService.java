package com.ruoyi.brand.service;

import java.util.List;
import com.ruoyi.brand.domain.BsBrand;

/**
 * 品牌信息Service接口
 * 
 * @author ruoyi
 * @date 2022-05-06
 */
public interface IBsBrandService 
{
    /**
     * 查询品牌信息
     * 
     * @param brId 品牌信息主键
     * @return 品牌信息
     */
    public BsBrand selectBsBrandByBrId(Integer brId);

    /**
     * 查询品牌信息列表
     * 
     * @param bsBrand 品牌信息
     * @return 品牌信息集合
     */
    public List<BsBrand> selectBsBrandList(BsBrand bsBrand);

    /**
     * 新增品牌信息
     * 
     * @param bsBrand 品牌信息
     * @return 结果
     */
    public int insertBsBrand(BsBrand bsBrand);

    /**
     * 修改品牌信息
     * 
     * @param bsBrand 品牌信息
     * @return 结果
     */
    public int updateBsBrand(BsBrand bsBrand);

    /**
     * 删除品牌信息信息
     * 
     * @param brId 品牌信息主键
     * @return 结果
     */
    public boolean deleteBsBrandByBrId(Integer brId);

    public int addBrand(BsBrand brand);

    public int delDict(String dictValue);

}

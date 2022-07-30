package com.ruoyi.brand.mapper;

import java.util.List;
import com.ruoyi.brand.domain.BsBrand;
import com.ruoyi.common.core.domain.entity.SysDictData;
import org.apache.ibatis.annotations.Param;

/**
 * 品牌信息Mapper接口
 * 
 * @author ruoyi
 * @date 2022-05-06
 */
public interface BsBrandMapper 
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
     * 删除品牌信息
     * 
     * @param brId 品牌信息主键
     * @return 结果
     */
    public int deleteBsBrandByBrId(Integer brId);

    public int addArandToData(SysDictData sysDictData);

    public int delDict(String dictValue);

    public Long reNumber(Integer BrandId);
}

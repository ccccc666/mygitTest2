package com.ruoyi.goods.mapper;

import java.util.List;

import com.ruoyi.classify.domain.BsClassify;
import com.ruoyi.goods.domain.BsGoods;

/**
 * 商品管理Mapper接口
 * 
 * @author ruoyi
 * @date 2022-05-06
 */
public interface BsGoodsMapper 
{
    /**
     * 查询商品管理
     * 
     * @param gsId 商品管理主键
     * @return 商品管理
     */
    public BsGoods selectBsGoodsByGsId(Integer gsId);

    /**
     * 查询商品管理列表
     * 
     * @param bsGoods 商品管理
     * @return 商品管理集合
     */
    public List<BsGoods> selectBsGoodsList(BsGoods bsGoods);

    /**
     * 新增商品管理
     * 
     * @param bsGoods 商品管理
     * @return 结果
     */
    public int insertBsGoods(BsGoods bsGoods);

    /**
     * 修改商品管理
     * 
     * @param bsGoods 商品管理
     * @return 结果
     */
    public int updateBsGoods(BsGoods bsGoods);

    /**
     * 删除商品管理
     * 
     * @param gsId 商品管理主键
     * @return 结果
     */
    public int deleteBsGoodsByGsId(Integer gsId);

    /**
     * 批量删除商品管理
     * 
     * @param gsIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteBsGoodsByGsIds(Integer[] gsIds);


    public List<BsClassify> selectAllNameById(BsGoods bsGoods);


    public Long reNumber(Integer id);
}

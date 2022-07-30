package com.ruoyi.order.mapper;

import java.util.List;
import com.ruoyi.order.domain.BsOrder;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * 业务信息Mapper接口
 * 
 * @author ruoyi
 * @date 2022-05-16
 */
@Repository
public interface BsOrderMapper 
{
    /**
     * 查询业务信息
     * 
     * @param id 业务信息主键
     * @return 业务信息
     */
    public BsOrder selectBsOrderById(Long id);

    /**
     * 查询业务信息列表
     * 
     * @param bsOrder 业务信息
     * @return 业务信息集合
     */
    public List<BsOrder> selectBsOrderList(BsOrder bsOrder);

    /**
     * 新增业务信息
     * 
     * @param bsOrder 业务信息
     * @return 结果
     */
    public int insertBsOrder(BsOrder bsOrder);

    /**
     * 修改业务信息
     * 
     * @param bsOrder 业务信息
     * @return 结果
     */
    public int updateBsOrder(BsOrder bsOrder);

    /**
     * 删除业务信息
     * 
     * @param id 业务信息主键
     * @return 结果
     */
    public int deleteBsOrderById(Long id);

    /**
     * 批量删除业务信息
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteBsOrderByIds(Long[] ids);

    public int updateKuCun(@Param("number") Long number,@Param("id") Integer id);

    public int reGoodsNumberById(Integer id);

    public BsOrder reBsOrderById(int id);

    public void updateKuCun2(@Param("number") Long number,@Param("id") Integer id);

    public int deleteBsOrderByIds2(Long[] ids);
}

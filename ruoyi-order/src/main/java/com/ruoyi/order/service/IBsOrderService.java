package com.ruoyi.order.service;

import java.util.List;
import com.ruoyi.order.domain.BsOrder;
import com.ruoyi.order.vo.AddOrderVo;

/**
 * 业务信息Service接口
 * 
 * @author ruoyi
 * @date 2022-05-16
 */
public interface IBsOrderService 
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
    public int insertBsOrder(AddOrderVo bsOrder);

    /**
     * 修改业务信息
     * 
     * @param bsOrder 业务信息
     * @return 结果
     */
    public int updateBsOrder(BsOrder bsOrder);

    /**
     * 批量删除业务信息
     * 
     * @param ids 需要删除的业务信息主键集合
     * @return 结果
     */
    public int deleteBsOrderByIds(Long[] ids);

    public int deleteBsOrderByIds2(Long[] ids);
    /**
     * 删除业务信息信息
     * 
     * @param id 业务信息主键
     * @return 结果
     */
    public int deleteBsOrderById(Long id);


    public boolean UpdateKuCun(Long Number,Integer id);

    public BsOrder reBsOrderById(int id);

    public void UpdateKuCun2(Long Number,Integer id);


}

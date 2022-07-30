package com.ruoyi.customer.service;

import java.util.List;
import com.ruoyi.customer.domain.BsCustomer;
import com.ruoyi.customer.domain.BsOrder2;
import com.ruoyi.customer.domain.Dwelling;

/**
 * 客户信息管理Service接口
 * 
 * @author ruoyi
 * @date 2022-05-06
 */
public interface IBsCustomerService 
{
    /**
     * 查询客户信息管理
     * 
     * @param id 客户信息管理主键
     * @return 客户信息管理
     */
    public BsCustomer selectBsCustomerById(Integer id);

    /**
     * 查询客户信息管理列表
     * 
     * @param bsCustomer 客户信息管理
     * @return 客户信息管理集合
     */
    public List<BsCustomer> selectBsCustomerList(BsCustomer bsCustomer);

    /**
     * 新增客户信息管理
     * 
     * @param bsCustomer 客户信息管理
     * @return 结果
     */
    public int insertBsCustomer(BsCustomer bsCustomer);

    /**
     * 修改客户信息管理
     * 
     * @param bsCustomer 客户信息管理
     * @return 结果
     */
    public int updateBsCustomer(BsCustomer bsCustomer);

    /**
     * 批量删除客户信息管理
     * 
     * @param ids 需要删除的客户信息管理主键集合
     * @return 结果
     */
    public boolean deleteBsCustomerByIds(Integer[] ids);

    /**
     * 删除客户信息管理信息
     * 
     * @param id 客户信息管理主键
     * @return 结果
     */
    public int deleteBsCustomerById(Integer id);


    List<Dwelling> TreeDwelling();

    /**
     * 返回该用户的所有订单信息
     * @param csid 客户id
     * @return 结果
     */
    List<BsOrder2> selectAllOrder(Integer csid);
}

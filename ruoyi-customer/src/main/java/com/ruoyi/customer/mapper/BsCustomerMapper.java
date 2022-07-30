package com.ruoyi.customer.mapper;

import java.util.List;
import com.ruoyi.customer.domain.BsCustomer;
import com.ruoyi.customer.domain.BsOrder2;
import com.ruoyi.customer.domain.Dwelling;
import org.apache.ibatis.annotations.Param;

/**
 * 客户信息管理Mapper接口
 * 
 * @author ruoyi
 * @date 2022-05-06
 */
public interface BsCustomerMapper 
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
     * 删除客户信息管理
     * 
     * @param id 客户信息管理主键
     * @return 结果
     */
    public int deleteBsCustomerById(Integer id);

    /**
     * 批量删除客户信息管理
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteBsCustomerByIds(Integer[] ids);


    List<Dwelling> treeDwelling(@Param("pid") String pid);

    List<BsOrder2> selectOrdersById(Integer csid);

    public Long reNumber(Integer id);
}

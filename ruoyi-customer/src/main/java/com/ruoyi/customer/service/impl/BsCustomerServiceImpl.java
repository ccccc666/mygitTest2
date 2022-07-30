package com.ruoyi.customer.service.impl;

import java.util.List;

import com.ruoyi.customer.domain.BsOrder2;
import com.ruoyi.customer.domain.Dwelling;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.customer.mapper.BsCustomerMapper;
import com.ruoyi.customer.domain.BsCustomer;
import com.ruoyi.customer.service.IBsCustomerService;

/**
 * 客户信息管理Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-05-06
 */
@Service
public class BsCustomerServiceImpl implements IBsCustomerService 
{
    @Autowired
    private BsCustomerMapper bsCustomerMapper;

    /**
     * 查询客户信息管理
     * 
     * @param id 客户信息管理主键
     * @return 客户信息管理
     */
    @Override
    public BsCustomer selectBsCustomerById(Integer id)
    {
        return bsCustomerMapper.selectBsCustomerById(id);
    }

    /**
     * 查询客户信息管理列表
     * 
     * @param bsCustomer 客户信息管理
     * @return 客户信息管理
     */
    @Override
    public List<BsCustomer> selectBsCustomerList(BsCustomer bsCustomer)
    {
        return bsCustomerMapper.selectBsCustomerList(bsCustomer);
    }

    /**
     * 新增客户信息管理
     * 
     * @param bsCustomer 客户信息管理
     * @return 结果
     */
    @Override
    public int insertBsCustomer(BsCustomer bsCustomer)
    {
        return bsCustomerMapper.insertBsCustomer(bsCustomer);
    }

    /**
     * 修改客户信息管理
     * 
     * @param bsCustomer 客户信息管理
     * @return 结果
     */
    @Override
    public int updateBsCustomer(BsCustomer bsCustomer)
    {
        return bsCustomerMapper.updateBsCustomer(bsCustomer);
    }

    /**
     * 批量删除客户信息管理
     * 
     * @param ids 需要删除的客户信息管理主键
     * @return 结果
     */
    @Override
    public boolean deleteBsCustomerByIds(Integer[] ids)
    {
        boolean flag=true;
        for (int i = 0; i < ids.length; i++) {
            BsCustomer bsCustomer=bsCustomerMapper.selectBsCustomerById(ids[i]);
            Long shanchu=bsCustomerMapper.reNumber(bsCustomer.getId());
            //根据id获取是否有相关的数据
            if(shanchu!=null && shanchu!=0){
                //如果数据不为空，表明仍然有数据存在
                flag=false;
                break;
            }
        }
        if(flag==true){
            for (int i = 0; i < ids.length; i++) {
                BsCustomer customer=bsCustomerMapper.selectBsCustomerById(ids[i]);
                bsCustomerMapper.deleteBsCustomerById(customer.getId());
            }
        }
        return flag;
    }

    /**
     * 删除客户信息管理信息
     * 
     * @param id 客户信息管理主键
     * @return 结果
     */
    @Override
    public int deleteBsCustomerById(Integer id)
    {
        return bsCustomerMapper.deleteBsCustomerById(id);
    }

    /**
     * 返回树级列表
     * @return
     */
    @Override
    public List<Dwelling> TreeDwelling() {
        return bsCustomerMapper.treeDwelling("0");
    }

    @Override
    public List<BsOrder2> selectAllOrder(Integer csid) {
        return bsCustomerMapper.selectOrdersById(csid);
    }
}

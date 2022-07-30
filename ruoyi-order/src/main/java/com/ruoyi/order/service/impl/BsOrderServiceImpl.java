package com.ruoyi.order.service.impl;

import java.util.List;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.order.vo.AddOrderGoodsVo;
import com.ruoyi.order.vo.AddOrderVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import com.ruoyi.order.mapper.BsOrderMapper;
import com.ruoyi.order.domain.BsOrder;
import com.ruoyi.order.service.IBsOrderService;
import org.springframework.transaction.annotation.Transactional;

/**
 * 业务信息Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-05-16
 */
@Service
public class BsOrderServiceImpl implements IBsOrderService 
{
    @Autowired
    private BsOrderMapper bsOrderMapper;
    /**
     * 查询业务信息
     * 
     * @param id 业务信息主键
     * @return 业务信息
     */
    @Override
    public BsOrder selectBsOrderById(Long id)
    {
        return bsOrderMapper.selectBsOrderById(id);
    }

    /**
     * 查询业务信息列表
     * 
     * @param bsOrder 业务信息
     * @return 业务信息
     */
    @Override
    public List<BsOrder> selectBsOrderList(BsOrder bsOrder)
    {

        return bsOrderMapper.selectBsOrderList(bsOrder);
    }

    /**
     * 新增业务信息
     * 
     * @param bsOrder 业务信息
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = RuntimeException.class)//若依自带框架的事务处理回滚机制
    public int insertBsOrder(AddOrderVo bsOrder)
    {
        int result = 0;

        for (AddOrderGoodsVo good : bsOrder.getGoods()) {

            // 1. 更新每一个商品库存
            boolean kucun = UpdateKuCun(good.getNum(), good.getGsId());
            if (!kucun) {
                throw new RuntimeException("库存错误！");
            }
            // 2. 完善订单，生成订单
            BsOrder order = new BsOrder();
            BeanUtils.copyProperties(bsOrder, order);
            Long Allprice=good.getGsSgprice() * good.getNum();
            order.setBsAllprice(Allprice);
            // TODO: 把单个商品信息放到order
            order.setBsPrice(good.getGsSgprice());
            order.setBsNumber(good.getNum());
            order.setBsCtname(bsOrder.getBsCtname());
            order.setBsCtphone(bsOrder.getBsCtphone());
            order.setBsGoodsStates(bsOrder.getBsGoodsStates());
            order.setBsSentStates(bsOrder.getBsSentStates());
            order.setBsAuthentication(bsOrder.getBsAuthentication());
            order.setBsGoodsid(good.getGsId());
            LoginUser authentication = (LoginUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            order.setBsAuthentication(authentication.getUsername());
            result += bsOrderMapper.insertBsOrder(order);
        }
        return result;
    }

    /**
     * 修改业务信息
     * 
     * @param bsOrder 业务信息
     * @return 结果
     */
    @Override
    public int updateBsOrder(BsOrder bsOrder)
    {
        Long changeNumber=bsOrder.getExChangeBsNumber() == null ? 0 : bsOrder.getExChangeBsNumber();//现在修改的订单量
        Long oldNumber=bsOrder.getBsNumber() == null ? 0 : bsOrder.getBsNumber();//原本要下的订单量
        Long reNumber=oldNumber-changeNumber;//老的减去新的差值
        int flag=0;
        //从商品信息表中根据id返回该商品对应的所有库存
        int newNumber1=bsOrderMapper.reGoodsNumberById(bsOrder.getBsGoodsid());//库存总量

        if(reNumber<0){//如果大于0 说明要给库存表新增回去少掉的数据
           if(-reNumber>newNumber1){//差值如果比库存大 那就报错 报库存不足
               flag=8;//回去判断信息错误。
               return flag;
           }else {//如果差值比库存小 说明可以额外新增数据
               bsOrder.setBsNumber(bsOrder.getExChangeBsNumber());
               bsOrderMapper.updateKuCun2(reNumber,bsOrder.getBsGoodsid());
               if(changeNumber != 0) {
                   bsOrder.setBsAllprice(bsOrder.getExChangeBsNumber() * bsOrder.getBsPrice());//重新计算商品交易总额
               }
               return bsOrderMapper.updateBsOrder(bsOrder);
           }
        }else {//这里是老的比新的要多 那么我就是要把多出来的数据给他还回去
          bsOrderMapper.updateKuCun2(reNumber,bsOrder.getBsGoodsid());
          bsOrder.setBsNumber(bsOrder.getExChangeBsNumber());//更新商品总量
          if(changeNumber != 0) {
              bsOrder.setBsAllprice(bsOrder.getExChangeBsNumber() * bsOrder.getBsPrice());//重新计算订单交易总额
          }
          return bsOrderMapper.updateBsOrder(bsOrder);
        }
    }
    /**
     * 批量删除业务信息
     * 
     * @param ids 需要删除的业务信息主键
     * @return 结果
     */
    @Override
    public int deleteBsOrderByIds(Long[] ids)
    {
        return bsOrderMapper.deleteBsOrderByIds(ids);
    }

    @Override
    public int deleteBsOrderByIds2(Long[] ids) {
        return bsOrderMapper.deleteBsOrderByIds2(ids);
    }

    /**
     * 删除业务信息信息
     * 
     * @param id 业务信息主键
     * @return 结果
     */
    @Override
    public int deleteBsOrderById(Long id)
    {
        return bsOrderMapper.deleteBsOrderById(id);
    }

    @Override
    public boolean UpdateKuCun(Long Number, Integer id) {
        int newNumber1=bsOrderMapper.reGoodsNumberById(id);
        Long newNumber2=newNumber1-Number;
        if(newNumber2>=0){
            return  1 == bsOrderMapper.updateKuCun(newNumber2,id);//将计算出来的新的库存结果存放到新的该商品id对应的库存处
        }else {
            return false;
        }
    }

    @Override
    public BsOrder reBsOrderById(int id) {
        return bsOrderMapper.reBsOrderById(id);
    }

    @Override
    public void UpdateKuCun2(Long Number, Integer id) {
        bsOrderMapper.updateKuCun2(Number,id);//将输入的内容传入到数据库中，在数据库中计算后加入
    }

}

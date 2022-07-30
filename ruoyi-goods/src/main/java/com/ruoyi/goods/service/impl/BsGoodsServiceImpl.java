package com.ruoyi.goods.service.impl;

import java.util.List;

import com.ruoyi.classify.domain.BsClassify;
import com.ruoyi.order.mapper.BsOrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.goods.mapper.BsGoodsMapper;
import com.ruoyi.goods.domain.BsGoods;
import com.ruoyi.goods.service.IBsGoodsService;

/**
 * 商品管理Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-05-06
 */
@Service
public class BsGoodsServiceImpl implements IBsGoodsService 
{
    @Autowired
    private BsGoodsMapper bsGoodsMapper;

    /**
     * 查询商品管理
     * 
     * @param gsId 商品管理主键
     * @return 商品管理
     */
    @Override
    public BsGoods selectBsGoodsByGsId(Integer gsId)
    {
        return bsGoodsMapper.selectBsGoodsByGsId(gsId);
    }

    /**
     * 查询商品管理列表
     * 
     * @param bsGoods 商品管理
     * @return 商品管理
     */
    @Override
    public List<BsGoods> selectBsGoodsList(BsGoods bsGoods)
    {
        return bsGoodsMapper.selectBsGoodsList(bsGoods);
    }

    /**
     * 新增商品管理
     * 
     * @param bsGoods 商品管理
     * @return 结果
     */
    @Override
    public int insertBsGoods(BsGoods bsGoods)
    {
        return bsGoodsMapper.insertBsGoods(bsGoods);
    }

    /**
     * 修改商品管理
     * 
     * @param bsGoods 商品管理
     * @return 结果
     */
    @Override
    public int updateBsGoods(BsGoods bsGoods)
    {
        return bsGoodsMapper.updateBsGoods(bsGoods);
    }

    /**
     * 批量删除商品管理
     * 
     * @param gsIds 需要删除的商品管理主键
     * @return 结果
     */
    @Override
    public boolean deleteBsGoodsByGsIds(Integer[] gsIds)
    {
        //如果都可以的话才能进行删除
        boolean flag=true;
        for (int i = 0; i < gsIds.length; i++) {
            BsGoods bsGoods=bsGoodsMapper.selectBsGoodsByGsId(gsIds[i]);
            //根据获取的商品id，从订单表中查看是否有相应订单中的商品id。
            Long shanchu=bsGoodsMapper.reNumber(bsGoods.getGsId());
            if(shanchu!=null && shanchu!=0){
                flag=false;
                break;
            }
        }
        //在所有数据都被审核之后可以进行多个或者单个的删除
        if(flag==true){
            for (int i = 0; i < gsIds.length; i++) {
                BsGoods bsGoods=bsGoodsMapper.selectBsGoodsByGsId(gsIds[i]);
                bsGoodsMapper.deleteBsGoodsByGsId(bsGoods.getGsId());
            }
        }
        return flag;
//        return bsGoodsMapper.deleteBsGoodsByGsIds(gsIds);
    }

    /**
     * 删除商品管理信息
     * 
     * @param gsId 商品管理主键
     * @return 结果
     */
    @Override
    public int deleteBsGoodsByGsId(Integer gsId)
    {
        return bsGoodsMapper.deleteBsGoodsByGsId(gsId);
    }


    @Override
    public List<BsClassify> selectClassifyById(BsGoods bsGoods) {
        return bsGoodsMapper.selectAllNameById(bsGoods);
    }
}

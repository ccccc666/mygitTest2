package com.ruoyi.brand.service.impl;

import java.util.List;

import com.ruoyi.common.core.domain.entity.SysDictData;
import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.utils.DictUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import com.ruoyi.brand.mapper.BsBrandMapper;
import com.ruoyi.brand.domain.BsBrand;
import com.ruoyi.brand.service.IBsBrandService;
import org.springframework.transaction.annotation.Transactional;

/**
 * 品牌信息Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-05-06
 */
@Service
public class BsBrandServiceImpl implements IBsBrandService 
{
    @Autowired
    private BsBrandMapper bsBrandMapper;

    /**
     * 查询品牌信息
     * 
     * @param brId 品牌信息主键
     * @return 品牌信息
     */
    @Override
    public BsBrand selectBsBrandByBrId(Integer brId)
    {
        return bsBrandMapper.selectBsBrandByBrId(brId);
    }

    /**
     * 查询品牌信息列表
     * 
     * @param bsBrand 品牌信息
     * @return 品牌信息
     */
    @Override
    public List<BsBrand> selectBsBrandList(BsBrand bsBrand)
    {
        return bsBrandMapper.selectBsBrandList(bsBrand);
    }

    /**
     * 新增品牌信息
     * 
     * @param bsBrand 品牌信息
     * @return 结果
     */
    @Override
    public int insertBsBrand(BsBrand bsBrand)
    {
        return bsBrandMapper.insertBsBrand(bsBrand);
    }

    /**
     * 修改品牌信息
     * 
     * @param bsBrand 品牌信息
     * @return 结果
     */
    @Override
    public int updateBsBrand(BsBrand bsBrand)
    {
        return bsBrandMapper.updateBsBrand(bsBrand);
    }


    /**
     * 删除品牌信息信息
     * 
     * @param brId 品牌信息主键
     * @return 结果
     */
    @Override
    public boolean deleteBsBrandByBrId(Integer brId)
    {
        boolean flag = true;
        BsBrand brand = bsBrandMapper.selectBsBrandByBrId(brId);
        Long number=bsBrandMapper.reNumber(brand.getBrId());//获取数据 判断品牌在id中是否存在对应的数组
        if(number!=null && number!=0){
            flag=false;//如果没有数据返回false
            return flag;
        }else {
            bsBrandMapper.deleteBsBrandByBrId(brId);//如果数据被删除了
            return flag;//return ture
        }
//        return bsBrandMapper.deleteBsBrandByBrId(brId);
    }


    @Override
    public int addBrand(BsBrand brand) {

        DictUtils.removeDictCache("brand");
        //新建一个实体类
        //给实体类赋值
        SysDictData sysDictData=new SysDictData();
        sysDictData.setDictType("brand");
        sysDictData.setDictLabel(brand.getBrName());
        sysDictData.setDictValue(brand.getBrId().toString());
        LoginUser authentication = (LoginUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        sysDictData.setListClass("default");
        sysDictData.setCreateBy(authentication.getUsername());
        sysDictData.setUpdateBy(authentication.getUsername());
        return bsBrandMapper.addArandToData(sysDictData);
    }

    @Override
    public int delDict(String dictValue) {
        return bsBrandMapper.delDict(dictValue);
    }

}

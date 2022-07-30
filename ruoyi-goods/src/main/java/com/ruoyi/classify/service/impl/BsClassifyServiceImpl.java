package com.ruoyi.classify.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.classify.mapper.BsClassifyMapper;
import com.ruoyi.classify.domain.BsClassify;
import com.ruoyi.classify.service.IBsClassifyService;
import org.springframework.transaction.annotation.Transactional;

/**
 * 分类查询Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-05-06
 */
@Service
public class BsClassifyServiceImpl implements IBsClassifyService 
{
    @Autowired
    private BsClassifyMapper bsClassifyMapper;

    /**
     * 查询分类查询
     * 
     * @param id 分类查询主键
     * @return 分类查询
     */
    @Override
    public BsClassify selectBsClassifyById(Integer id)
    {
        return bsClassifyMapper.selectBsClassifyById(id);
    }

    /**
     * 查询分类查询列表
     * 
     * @param bsClassify 分类查询
     * @return 分类查询
     */
    @Override
    public List<BsClassify> selectBsClassifyList(BsClassify bsClassify)
    {
        return bsClassifyMapper.selectBsClassifyList(bsClassify);
    }

    /**
     * 新增分类查询
     * 
     * @param bsClassify 分类查询
     * @return 结果
     */
    @Override
    public int insertBsClassify(BsClassify bsClassify)
    {
        return bsClassifyMapper.insertBsClassify(bsClassify);
    }

    /**
     * 修改分类查询
     * 
     * @param bsClassify 分类查询
     * @return 结果
     */
    @Override
    public int updateBsClassify(BsClassify bsClassify)
    {
        return bsClassifyMapper.updateBsClassify(bsClassify);
    }

    /**
     * 批量删除分类查询
     * 
     * @param ids 需要删除的分类查询主键
     * @return 结果
     */
    @Override
    public boolean deleteBsClassifyByIds(Integer[] ids)
    {
        boolean flag=true;
        for (int i = 0; i < ids.length; i++) {
             BsClassify classify=bsClassifyMapper.selectBsClassifyById(ids[i]);
             Long shanchu=bsClassifyMapper.reNumber(classify.getId());//得到是否有一条或者多条数据
             if(shanchu!=null && shanchu!=0){//如果返回值不为null或者不为0说明存在相关的数据。
                 flag=false;
                 break;//中断循环
             }
        }
        if(flag==true){
            for (int i = 0; i < ids.length; i++) {
                BsClassify classify=bsClassifyMapper.selectBsClassifyById(ids[i]);
                bsClassifyMapper.deleteBsClassifyById(classify.getId());//循环遍历删除数据
            }
        }
        return flag;//根据返回值判断是否有进行数据的删除
    }

    /**
     * 删除分类查询信息
     * 
     * @param id 分类查询主键
     * @return 结果
     */
    @Override
    public int deleteBsClassifyById(Integer id)
    {
        return bsClassifyMapper.deleteBsClassifyById(id);
    }

    @Override
    public List<BsClassify> TreeClassify() {
        return bsClassifyMapper.treeClassify("0");
    }


}

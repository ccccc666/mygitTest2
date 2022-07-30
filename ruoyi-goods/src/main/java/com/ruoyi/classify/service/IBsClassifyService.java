package com.ruoyi.classify.service;

import java.util.List;
import com.ruoyi.classify.domain.BsClassify;

/**
 * 分类查询Service接口
 * 
 * @author ruoyi
 * @date 2022-05-06
 */
public interface IBsClassifyService 
{
    /**
     * 查询分类查询
     * 
     * @param id 分类查询主键
     * @return 分类查询
     */
    public BsClassify selectBsClassifyById(Integer id);

    /**
     * 查询分类查询列表
     * 
     * @param bsClassify 分类查询
     * @return 分类查询集合
     */
    public List<BsClassify> selectBsClassifyList(BsClassify bsClassify);

    /**
     * 新增分类查询
     * 
     * @param bsClassify 分类查询
     * @return 结果
     */
    public int insertBsClassify(BsClassify bsClassify);

    /**
     * 修改分类查询
     * 
     * @param bsClassify 分类查询
     * @return 结果
     */
    public int updateBsClassify(BsClassify bsClassify);

    /**
     * 批量删除分类查询
     * 
     * @param ids 需要删除的分类查询主键集合
     * @return 结果
     */
    public boolean deleteBsClassifyByIds(Integer[] ids);

    /**
     * 删除分类查询信息
     * 
     * @param id 分类查询主键
     * @return 结果
     */
    public int deleteBsClassifyById(Integer id);

    List<BsClassify> TreeClassify();

}

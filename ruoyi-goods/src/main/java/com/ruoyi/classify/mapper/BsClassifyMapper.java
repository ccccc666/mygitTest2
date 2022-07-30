package com.ruoyi.classify.mapper;

import java.util.List;
import com.ruoyi.classify.domain.BsClassify;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * 分类查询Mapper接口
 * 
 * @author ruoyi
 * @date 2022-05-06
 */
@Repository
public interface BsClassifyMapper 
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
     * 删除分类查询
     * 
     * @param id 分类查询主键
     * @return 结果
     */
    public int deleteBsClassifyById(Integer id);

    /**
     * 批量删除分类查询
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteBsClassifyByIds(Integer[] ids);

    List<BsClassify> treeClassify(@Param("cfParentid") String cfParentid);

    public Long reNumber(Integer id);
}

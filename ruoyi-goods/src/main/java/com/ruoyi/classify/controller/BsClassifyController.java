package com.ruoyi.classify.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.classify.domain.BsClassify;
import com.ruoyi.classify.service.IBsClassifyService;
import com.ruoyi.common.utils.poi.ExcelUtil;

/**
 * 分类查询Controller
 * 
 * @author ruoyi
 * @date 2022-05-06
 */
@RestController
@RequestMapping("/classify/classify")
public class BsClassifyController extends BaseController
{
    @Autowired
    private IBsClassifyService bsClassifyService;

    /**
     * 查询分类查询列表
     */
    @PreAuthorize("@ss.hasPermi('classify:classify:list')")
    @GetMapping("/list")
    public AjaxResult list(BsClassify bsClassify)
    {
        List<BsClassify> list = bsClassifyService.selectBsClassifyList(bsClassify);
        return AjaxResult.success(list);
    }

    /**
     * 导出分类查询列表
     */
    @PreAuthorize("@ss.hasPermi('classify:classify:export')")
    @Log(title = "分类查询", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BsClassify bsClassify)
    {
        List<BsClassify> list = bsClassifyService.selectBsClassifyList(bsClassify);
        ExcelUtil<BsClassify> util = new ExcelUtil<BsClassify>(BsClassify.class);
        util.exportExcel(response, list, "分类查询数据");
    }

    /**
     * 获取分类查询详细信息
     */
    @PreAuthorize("@ss.hasPermi('classify:classify:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Integer id)
    {
        return AjaxResult.success(bsClassifyService.selectBsClassifyById(id));
    }

    /**
     * 新增分类查询
     */
    @PreAuthorize("@ss.hasPermi('classify:classify:add')")
    @Log(title = "分类查询", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BsClassify bsClassify)
    {
        return toAjax(bsClassifyService.insertBsClassify(bsClassify));
    }

    /**
     * 修改分类查询
     */
    @PreAuthorize("@ss.hasPermi('classify:classify:edit')")
    @Log(title = "分类查询", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BsClassify bsClassify)
    {
        return toAjax(bsClassifyService.updateBsClassify(bsClassify));
    }

    /**
     * 删除分类查询
     */
    @PreAuthorize("@ss.hasPermi('classify:classify:remove')")
    @Log(title = "分类查询", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable("ids") Integer[] ids)
    {
        boolean flag=bsClassifyService.deleteBsClassifyByIds(ids);
        if(flag==false){
            return AjaxResult.error("商品表中仍然有相关信息，请先删除");
        }else {
            return AjaxResult.success("删除成功");
        }
//      return toAjax(bsClassifyService.deleteBsClassifyByIds(ids));
    }


    @Log(title = "返回树级列表", businessType = BusinessType.DELETE)
    @GetMapping("/classifyTree")
    public AjaxResult getAllClassify(){
        List<BsClassify> classifies=bsClassifyService.TreeClassify();
        return AjaxResult.success(classifies);
    }
}

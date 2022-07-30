package com.ruoyi.brand.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.annotations.Param;
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
import com.ruoyi.brand.domain.BsBrand;
import com.ruoyi.brand.service.IBsBrandService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 品牌信息Controller
 * 
 * @author ruoyi
 * @date 2022-05-06
 */
@RestController
@RequestMapping("/brand/brand")
public class BsBrandController extends BaseController
{
    @Autowired
    private IBsBrandService bsBrandService;

    /**
     * 查询品牌信息列表
     */
    @PreAuthorize("@ss.hasPermi('brand:brand:list')")
    @GetMapping("/list")
    public TableDataInfo list(BsBrand bsBrand)//前端将多个查询封装成一个对象
    {
        startPage();
        List<BsBrand> list = bsBrandService.selectBsBrandList(bsBrand);//将对象传入
        return getDataTable(list);
    }

    /**
     * 导出品牌信息列表
     */
    @PreAuthorize("@ss.hasPermi('brand:brand:export')")
    @Log(title = "品牌信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BsBrand bsBrand)
    {
        List<BsBrand> list = bsBrandService.selectBsBrandList(bsBrand);
        ExcelUtil<BsBrand> util = new ExcelUtil<BsBrand>(BsBrand.class);
        util.exportExcel(response, list, "品牌信息数据");
    }

    /**
     * 获取品牌信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('brand:brand:query')")
    @GetMapping(value = "/{brId}")
    public AjaxResult getInfo(@PathVariable("brId") Integer brId)
    {
        return AjaxResult.success(bsBrandService.selectBsBrandByBrId(brId));
    }

    /**
     * 新增品牌信息
     */
    @PreAuthorize("@ss.hasPermi('brand:brand:add')")
    @Log(title = "品牌信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BsBrand bsBrand)
    {
        bsBrandService.insertBsBrand(bsBrand);//新增brand在品牌表中
        bsBrandService.addBrand(bsBrand);//在字典表中继续添加brand信息内容
        return AjaxResult.success("添加成功！！");
    }

    /**
     * 修改品牌信息
     */
    @PreAuthorize("@ss.hasPermi('brand:brand:edit')")
    @Log(title = "品牌信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BsBrand bsBrand)
    {
        return toAjax(bsBrandService.updateBsBrand(bsBrand));
    }

    /**
     * 删除品牌信息
     */
    @PreAuthorize("@ss.hasPermi('brand:brand:remove')")
    @Log(title = "品牌信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{brIds}")
    public AjaxResult remove(@PathVariable("brIds") Integer brIds){
        boolean flag=bsBrandService.deleteBsBrandByBrId(brIds);
        if(flag==false){
            return AjaxResult.error("删除失败，商品表中仍有相关数据");
        }else {
            return AjaxResult.success("删除成功");
        }
    }

    /**
     * 删除字典表中的值
     * @param dictValue
     * @return
     */
    @DeleteMapping("/delDict/{dictValue}")
    public AjaxResult delDict(@PathVariable("dictValue") String dictValue){
        bsBrandService.delDict(dictValue);
        return AjaxResult.success("删除成功");
    }

}

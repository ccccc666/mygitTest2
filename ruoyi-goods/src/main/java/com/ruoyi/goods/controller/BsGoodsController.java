package com.ruoyi.goods.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.classify.domain.BsClassify;
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
import com.ruoyi.goods.domain.BsGoods;
import com.ruoyi.goods.service.IBsGoodsService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 商品管理Controller
 * 
 * @author ruoyi
 * @date 2022-05-06
 */
@RestController
@RequestMapping("/goods/goods")
public class BsGoodsController extends BaseController
{
    @Autowired
    private IBsGoodsService bsGoodsService;

    /**
     * 查询商品管理列表
     */
    @PreAuthorize("@ss.hasPermi('goods:goods:list')")
    @GetMapping("/list")
    public TableDataInfo list(BsGoods bsGoods)
    {
        startPage();
        List<BsGoods> list = bsGoodsService.selectBsGoodsList(bsGoods);
        return getDataTable(list);
    }

    /**
     * 导出商品管理列表
     */
    @PreAuthorize("@ss.hasPermi('goods:goods:export')")
    @Log(title = "商品管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BsGoods bsGoods)
    {
        List<BsGoods> list = bsGoodsService.selectBsGoodsList(bsGoods);
        ExcelUtil<BsGoods> util = new ExcelUtil<BsGoods>(BsGoods.class);
        util.exportExcel(response, list, "商品管理数据");
    }

    /**
     * 获取商品管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('goods:goods:query')")
    @GetMapping(value = "/{gsId}")
    public AjaxResult getInfo(@PathVariable("gsId") Integer gsId)
    {
        return AjaxResult.success(bsGoodsService.selectBsGoodsByGsId(gsId));
    }

    /**
     * 新增商品管理
     */
    @PreAuthorize("@ss.hasPermi('goods:goods:add')")
    @Log(title = "商品管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BsGoods bsGoods)
    {
        return toAjax(bsGoodsService.insertBsGoods(bsGoods));
    }

    /**
     * 修改商品管理
     */
    @PreAuthorize("@ss.hasPermi('goods:goods:edit')")
    @Log(title = "商品管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BsGoods bsGoods)
    {
        if(bsGoods.getGsGsnumberNew()!=null){
            Integer AllGs=bsGoods.getGsGsnumber()+bsGoods.getGsGsnumberNew();//计算输入之后新增的库存数量
            bsGoods.setGsGsnumber(AllGs);//将属性设置到对象类中
        }
        return toAjax(bsGoodsService.updateBsGoods(bsGoods));//更新对象方法
    }

    /**
     * 删除商品管理
     */
    @PreAuthorize("@ss.hasPermi('goods:goods:remove')")
    @Log(title = "商品管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{gsIds}")
    public AjaxResult remove(@PathVariable Integer[] gsIds)
    {
        boolean flag=bsGoodsService.deleteBsGoodsByGsIds(gsIds);
        if(flag==false){
            return AjaxResult.error("删除失败，订单中仍有相关数据");
        }else {
            return AjaxResult.success("删除成功");
        }
    }

    @GetMapping("/getName")
    public AjaxResult getThreeIdName(BsGoods goods){
        List<BsClassify> bsClassifies=bsGoodsService.selectClassifyById(goods);
        return AjaxResult.success(bsClassifies);
    }
}

package com.ruoyi.customer.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.customer.domain.BsOrder2;
import com.ruoyi.customer.domain.Dwelling;
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
import com.ruoyi.customer.domain.BsCustomer;
import com.ruoyi.customer.service.IBsCustomerService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 客户信息管理Controller
 * 
 * @author ruoyi
 * @date 2022-05-06
 */
@RestController
@RequestMapping("/customer/customer")
public class BsCustomerController extends BaseController
{
    @Autowired
    private IBsCustomerService bsCustomerService;

    /**
     * 查询客户信息管理列表
     */
    @PreAuthorize("@ss.hasPermi('customer:customer:list')")//若依框架自带的验权
    @GetMapping("/list")
    public TableDataInfo list(BsCustomer bsCustomer)
    {
        startPage();
        List<BsCustomer> list = bsCustomerService.selectBsCustomerList(bsCustomer);
        return getDataTable(list);
    }

    /**
     * 导出客户信息管理列表
     */
    @PreAuthorize("@ss.hasPermi('customer:customer:export')")
    @Log(title = "客户信息管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BsCustomer bsCustomer)
    {
        List<BsCustomer> list = bsCustomerService.selectBsCustomerList(bsCustomer);
        ExcelUtil<BsCustomer> util = new ExcelUtil<BsCustomer>(BsCustomer.class);
        util.exportExcel(response, list, "客户信息管理数据");
    }

    /**
     * 获取客户信息管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('customer:customer:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Integer id)
    {
        return AjaxResult.success(bsCustomerService.selectBsCustomerById(id));
    }

    /**
     * 新增客户信息管理
     */
    @PreAuthorize("@ss.hasPermi('customer:customer:add')")
    @Log(title = "客户信息管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BsCustomer bsCustomer)
    {
        return toAjax(bsCustomerService.insertBsCustomer(bsCustomer));
    }

    /**
     * 修改客户信息管理
     */
    @PreAuthorize("@ss.hasPermi('customer:customer:edit')")
    @Log(title = "客户信息管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BsCustomer bsCustomer)
    {
        return toAjax(bsCustomerService.updateBsCustomer(bsCustomer));
    }

    /**
     * 删除客户信息管理
     */
    @PreAuthorize("@ss.hasPermi('customer:customer:remove')")
    @Log(title = "客户信息管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Integer[] ids)
    {
        boolean flag=bsCustomerService.deleteBsCustomerByIds(ids);
        if(flag==false){
            return AjaxResult.error("订单表中仍然有相关信息，请先删除");
        }else {
            return AjaxResult.success("成功从客户表中删除该信息");
        }
//        return toAjax(bsCustomerService.deleteBsCustomerByIds(ids));
    }


    @Log(title = "返回树级列表", businessType = BusinessType.DELETE)
    @GetMapping("/DwellingTree")
    public AjaxResult getAllDwelling(){
        //根据父级Id返回多个list的集合,每个list中含有子集list，利用mybatis中的collection进行捆绑扎包
        List<Dwelling> dwellingList=bsCustomerService.TreeDwelling();
        return AjaxResult.success(dwellingList);
    }

    @GetMapping("/getOrderById/{id}")
    public AjaxResult getOrderById(@PathVariable("id") Integer id){
        List<BsOrder2> list=bsCustomerService.selectAllOrder(id);
        return AjaxResult.success(list);
    }

}

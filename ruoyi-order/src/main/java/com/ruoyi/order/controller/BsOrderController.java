package com.ruoyi.order.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.order.vo.AddOrderGoodsVo;
import com.ruoyi.order.vo.AddOrderVo;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;
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
import com.ruoyi.order.domain.BsOrder;
import com.ruoyi.order.service.IBsOrderService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 业务信息Controller
 * 
 * @author ruoyi
 * @date 2022-05-16
 */
@RestController
@RequestMapping("/order/order")
public class BsOrderController extends BaseController
{
    @Autowired
    private IBsOrderService bsOrderService;

    /**
     * 查询业务信息列表
     */
    @PreAuthorize("@ss.hasPermi('order:order:list')")
    @GetMapping("/list")
    public TableDataInfo list(BsOrder bsOrder)
    {
        startPage();
        //获取当前用户的权限信息
        LoginUser authentication = (LoginUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        bsOrder.setBsAuthentication(authentication.getUsername());
        List<BsOrder> list = bsOrderService.selectBsOrderList(bsOrder);
        //修改付款状态以及发货状态
        for (int i = 0; i < list.size(); i++) {
            if(list.get(i).getBsGoodsStates()==0){
                list.get(i).setStrBsGoodsSt("已付款");
            }else {
                list.get(i).setStrBsGoodsSt("未付款");
            }
            if (list.get(i).getBsSentStates()==0){
                list.get(i).setStrBsSentSt("未发货");
            }else {
                list.get(i).setStrBsSentSt("已发货");
            }
        }
        return getDataTable(list);
    }

    /**
     * 导出业务信息列表
     */
    @PreAuthorize("@ss.hasPermi('order:order:export')")
    @Log(title = "业务信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BsOrder bsOrder)
    {
        List<BsOrder> list = bsOrderService.selectBsOrderList(bsOrder);
        ExcelUtil<BsOrder> util = new ExcelUtil<BsOrder>(BsOrder.class);
        util.exportExcel(response, list, "业务信息数据");
    }

    /**
     * 获取业务信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('order:order:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(bsOrderService.selectBsOrderById(id));
    }

    /**
     * 新增业务信息
     */
    @PreAuthorize("@ss.hasPermi('order:order:add')")
    @Log(title = "业务信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody AddOrderVo bsOrder)
    {
        return toAjax(bsOrderService.insertBsOrder(bsOrder));
    }



    /**
     * 修改业务信息
     */
    @PreAuthorize("@ss.hasPermi('order:order:edit')")
    @Log(title = "业务信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BsOrder bsOrder)
    {
        int ee=bsOrderService.updateBsOrder(bsOrder);
        if (ee!=8){
            return AjaxResult.success(bsOrderService.updateBsOrder(bsOrder));
        }else {
            return AjaxResult.error("新增额外需要的库存不足");
        }
    }

    /**
     * 批量取消
     * 取消订单业务信息
     */
    @PreAuthorize("@ss.hasPermi('order:order:remove')")
    @Log(title = "业务信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        for (int i = 0; i < ids.length; i++) {
           BsOrder order=bsOrderService.reBsOrderById(ids[i].intValue());//intValue将数据强制转换成int类型进行返回
           Long GsNumber=order.getBsNumber();//获取当前订单中的商品数量
           Integer GsGoodId=order.getBsGoodsid();//获取当前订单商品的id
           bsOrderService.UpdateKuCun2(GsNumber,GsGoodId);//将商品数量和订单id传入，修改库存，将撤销的订单库存还回去。
        }
        return toAjax(bsOrderService.deleteBsOrderByIds(ids));//批量删除订单
    }

    /**
     * 批量删除订单
     * @param ids
     * @return
     */
    @DeleteMapping("/deleteAll/{ids}")
    public AjaxResult deleteAll(@PathVariable Long[] ids){

        return toAjax(bsOrderService.deleteBsOrderByIds2(ids));

    }


}

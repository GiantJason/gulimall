package com.giantjason.gulimall.warehouse.controller;

import com.giantjason.common.utils.PageUtils;
import com.giantjason.common.utils.R;
import com.giantjason.gulimall.warehouse.entity.PurchaseEntity;
import com.giantjason.gulimall.warehouse.service.PurchaseService;
import com.giantjason.gulimall.warehouse.vo.MergeVO;
import com.giantjason.gulimall.warehouse.vo.PurchaseDoneVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;



/**
 * 采购信息
 *
 * @author GiantJason
 * @email 1720003053@qq.com
 * @date 2023-05-02 19:16:27
 */
@RestController
@RequestMapping("warehouse/purchase")
public class PurchaseController {

    @Autowired
    private PurchaseService purchaseService;


    /**
     * 完成采购单
     */
//    /ware/purchase/done
    @PostMapping("/done")
    public R finish(@RequestBody PurchaseDoneVO purchaseDoneVO){

        purchaseService.finishPurchase(purchaseDoneVO);

        return R.ok();
    }
    /**
     * 领取采购单
     */
    @PostMapping("/received")
    public R received(@RequestBody List<Long> ids){

        purchaseService.received(ids);

        return R.ok();
    }

    /**
     *合并采购单
     */
    ///ware/purchase/unreceive/list
    ///ware/purchase/merge
    @PostMapping("/merge")
    public R merge(@RequestBody MergeVO mergeVO){

        purchaseService.mergePurchase(mergeVO);
        return R.ok();
    }

    /**
     *查询未分配的采购单
     */
    @RequestMapping("/unreceive/list")
    public R unreceivedList(@RequestParam Map<String, Object> params){
        PageUtils page = purchaseService.queryUnreceivedPurchase(params);

        return R.ok().put("page", page);
    }
    /**
     * 列表
     */
    @RequestMapping("/list")
//    @RequiresPermissions("warehouse:purchase:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = purchaseService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
//    @RequiresPermissions("warehouse:purchase:info")
    public R info(@PathVariable("id") Long id){
		PurchaseEntity purchase = purchaseService.getById(id);

        return R.ok().put("purchase", purchase);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
//    @RequiresPermissions("warehouse:purchase:save")
    public R save(@RequestBody PurchaseEntity purchase){
		purchaseService.save(purchase);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
//    @RequiresPermissions("warehouse:purchase:update")
    public R update(@RequestBody PurchaseEntity purchase){
		purchaseService.updateById(purchase);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
//    @RequiresPermissions("warehouse:purchase:delete")
    public R delete(@RequestBody Long[] ids){
		purchaseService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}

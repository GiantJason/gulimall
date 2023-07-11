package com.giantjason.gulimall.warehouse.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.giantjason.common.utils.PageUtils;
import com.giantjason.gulimall.warehouse.entity.PurchaseEntity;
import com.giantjason.gulimall.warehouse.vo.MergeVO;
import com.giantjason.gulimall.warehouse.vo.PurchaseDoneVO;

import java.util.List;
import java.util.Map;

/**
 * 采购信息
 *
 * @author GiantJason
 * @email 1720003053@qq.com
 * @date 2023-05-02 19:16:27
 */
public interface PurchaseService extends IService<PurchaseEntity> {

    PageUtils queryPage(Map<String, Object> params);

    PageUtils queryUnreceivedPurchase(Map<String, Object> params);

    void received(List<Long> ids);

    void mergePurchase(MergeVO mergeVO);

    void finishPurchase(PurchaseDoneVO purchaseDoneVO);
}


package com.giantjason.gulimall.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.giantjason.common.utils.PageUtils;
import com.giantjason.gulimall.order.entity.OrderReturnApplyEntity;

import java.util.Map;

/**
 * 订单退货申请
 *
 * @author GiantJason
 * @email 1720003053@qq.com
 * @date 2023-05-02 19:10:54
 */
public interface OrderReturnApplyService extends IService<OrderReturnApplyEntity> {

    PageUtils queryPage(Map<String, Object> params);
}


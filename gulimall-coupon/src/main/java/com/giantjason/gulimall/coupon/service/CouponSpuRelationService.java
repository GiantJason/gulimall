package com.giantjason.gulimall.coupon.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.giantjason.common.utils.PageUtils;
import com.giantjason.gulimall.coupon.entity.CouponSpuRelationEntity;

import java.util.Map;

/**
 * 优惠券与产品关联
 *
 * @author GiantJason
 * @email 1720003053@qq.com
 * @date 2023-05-02 14:38:53
 */
public interface CouponSpuRelationService extends IService<CouponSpuRelationEntity> {

    PageUtils queryPage(Map<String, Object> params);
}


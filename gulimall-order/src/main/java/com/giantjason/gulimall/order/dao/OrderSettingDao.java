package com.giantjason.gulimall.order.dao;

import com.giantjason.gulimall.order.entity.OrderSettingEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 订单配置信息
 * 
 * @author GiantJason
 * @email 1720003053@qq.com
 * @date 2023-05-02 19:10:54
 */
@Mapper
public interface OrderSettingDao extends BaseMapper<OrderSettingEntity> {
	
}

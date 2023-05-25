package com.giantjason.gulimall.warehouse.dao;

import com.giantjason.gulimall.warehouse.entity.WareSkuEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 商品库存
 * 
 * @author GiantJason
 * @email 1720003053@qq.com
 * @date 2023-05-02 19:16:27
 */
@Mapper
public interface WareSkuDao extends BaseMapper<WareSkuEntity> {
	
}

package com.giantjason.gulimall.product.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.giantjason.gulimall.product.entity.SkuSaleAttrValueEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * sku销售属性&值
 * 
 * @author 夏沫止水
 * @email HeJieLin@gulimall.com
 * @date 2020-05-22 19:00:18
 */
@Mapper
public interface SkuSaleAttrValueDao extends BaseMapper<SkuSaleAttrValueEntity> {

//    List<SkuItemSaleAttrVo> getSaleAttrBySpuId(@Param("spuId") Long spuId);

//    List<String> getSkuSaleAttrValuesAsStringList(@Param("skuId") Long skuId);
}

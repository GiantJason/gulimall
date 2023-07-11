package com.giantjason.gulimall.product.dao;

import com.giantjason.gulimall.product.entity.CategoryEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 商品三级分类
 * 
 * @author GiantJason
 * @email 1720003053b@gmail.com
 * @date 2023-05-27 21:17:55
 */
@Mapper
public interface CategoryDao extends BaseMapper<CategoryEntity> {
	
}

package com.giantjason.gulimall.product.dao;

import com.giantjason.gulimall.product.entity.CommentReplayEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 商品评价回复关系
 * 
 * @author GiantJason
 * @email 1720003053b@gmail.com
 * @date 2023-05-22 23:41:00
 */
@Mapper
public interface CommentReplayDao extends BaseMapper<CommentReplayEntity> {
	
}

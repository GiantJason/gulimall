package com.giantjason.gulimall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.giantjason.common.utils.PageUtils;
import com.giantjason.gulimall.product.entity.AttrEntity;
import com.giantjason.gulimall.product.vo.AttrGroupRelationVO;
import com.giantjason.gulimall.product.vo.AttrRespVO;
import com.giantjason.gulimall.product.vo.AttrVO;

import java.util.List;
import java.util.Map;

/**
 * 商品属性
 *
 * @author GiantJason
 * @email 1720003053b@gmail.com
 * @date 2023-05-27 21:17:55
 */
public interface AttrService extends IService<AttrEntity> {

    PageUtils queryPage(Map<String, Object> params);

    void saveAttr(AttrVO attr);

    PageUtils queryBaseAttrPage(Map<String, Object> params, Long catalogId, String type);

    AttrRespVO getAttrInfo(Long attrId);

    void updateAttr(AttrVO attrVO);

    List<AttrEntity> getRelationAttr(Long attrGroupId);

    void deleteRelation(AttrGroupRelationVO[] vos);

    PageUtils getNoRelationAttr(Map<String, Object> params, Long attrgroupId);
}


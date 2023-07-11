package com.giantjason.gulimall.product.service.impl;

import com.giantjason.gulimall.product.entity.AttrAttrgroupRelationEntity;
import com.giantjason.gulimall.product.entity.AttrEntity;
import com.giantjason.gulimall.product.service.AttrAttrgroupRelationService;
import com.giantjason.gulimall.product.service.AttrService;
import com.giantjason.gulimall.product.vo.AttrGroupWithAttrsVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.giantjason.common.utils.PageUtils;
import com.giantjason.common.utils.Query;

import com.giantjason.gulimall.product.dao.AttrGroupDao;
import com.giantjason.gulimall.product.entity.AttrGroupEntity;
import com.giantjason.gulimall.product.service.AttrGroupService;
import org.springframework.util.StringUtils;


@Service("attrGroupService")
public class AttrGroupServiceImpl extends ServiceImpl<AttrGroupDao, AttrGroupEntity> implements AttrGroupService {

    @Autowired
    AttrGroupService attrGroupService;

    @Autowired
    AttrAttrgroupRelationService relationService;

    @Autowired
    AttrService attrService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<AttrGroupEntity> page = this.page(
                new Query<AttrGroupEntity>().getPage(params),
                new QueryWrapper<AttrGroupEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public PageUtils queryPage(Map<String, Object> params, Long catalogId) {
        String key = (String) params.get("key");
        //select * from pms_attr_group where catalog_id=? and (attr_group_id=key or attr_group_name like %key%)
        QueryWrapper<AttrGroupEntity> attrGroupEntityQueryWrapper = new QueryWrapper<AttrGroupEntity>();
        if (!StringUtils.isEmpty("key") && key != null) {
            attrGroupEntityQueryWrapper.and((obj) -> {
                obj.eq("attr_group_id", key).or().like("attr_group_name", key);
            });
        }
        if (catalogId == 0) {
            IPage<AttrGroupEntity> page = this.page(new Query<AttrGroupEntity>().getPage(params), attrGroupEntityQueryWrapper);
            return new PageUtils(page);
//            return queryPage(params);
        } else {
            attrGroupEntityQueryWrapper.eq("catalog_id", catalogId);

//            if( catalogId == 0){
//                IPage<AttrGroupEntity> page = this.page(new Query<AttrGroupEntity>().getPage(params),
//                        attrGroupEntityQueryWrapper);
//                return new PageUtils(page);
//            }else {
            IPage<AttrGroupEntity> page = this.page(new Query<AttrGroupEntity>().getPage(params),
                    attrGroupEntityQueryWrapper);
            return new PageUtils(page);
//            }
        }
    }

    @Override
    public List<AttrGroupWithAttrsVO> getAttrGroupWithAttrsByCatalogId(Long catalogId) {
        //查询所有分组
        List<AttrGroupEntity> attrGroupEntities = this.list(
                new QueryWrapper<AttrGroupEntity>().eq("catalog_id", catalogId));
        List<AttrGroupWithAttrsVO> attrGroupWithAttrsVOS = attrGroupEntities.stream().map(attrGroupEntity -> {
            AttrGroupWithAttrsVO attrGroupWithAttrsVO = new AttrGroupWithAttrsVO();
            BeanUtils.copyProperties(attrGroupEntity, attrGroupWithAttrsVO);
//            //获取属性分组和属性之间的关系
//            List<AttrAttrgroupRelationEntity> relationEntities = relationService.list(
//                    new QueryWrapper<AttrAttrgroupRelationEntity>()
//                            .eq("attr_group_id", attrGroupEntity.getAttrGroupId()));
//            //获取属性分组下的所有属性实体
//            List<AttrEntity> attrEntities = relationEntities.stream().map(relationEntity -> {
//                AttrEntity attrEntity = attrService.getById(relationEntity.getAttrId());
//                return attrEntity;
//            }).collect(Collectors.toList());
            List<AttrEntity> attrEntities = attrService.getRelationAttr(attrGroupEntity.getAttrGroupId());
            attrGroupWithAttrsVO.setAttrs(attrEntities);
            return attrGroupWithAttrsVO;
        }).collect(Collectors.toList());

        return attrGroupWithAttrsVOS;
    }

}
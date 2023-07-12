package com.giantjason.gulimall.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.giantjason.common.constant.ProductConstant;
import com.giantjason.gulimall.product.dao.AttrAttrgroupRelationDao;
import com.giantjason.gulimall.product.dao.AttrGroupDao;
import com.giantjason.gulimall.product.dao.CategoryDao;
import com.giantjason.gulimall.product.entity.AttrAttrgroupRelationEntity;
import com.giantjason.gulimall.product.entity.AttrGroupEntity;
import com.giantjason.gulimall.product.entity.CategoryEntity;
import com.giantjason.gulimall.product.service.CategoryService;
import com.giantjason.gulimall.product.vo.AttrGroupRelationVO;
import com.giantjason.gulimall.product.vo.AttrRespVO;
import com.giantjason.gulimall.product.vo.AttrVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.giantjason.common.utils.PageUtils;
import com.giantjason.common.utils.Query;

import com.giantjason.gulimall.product.dao.AttrDao;
import com.giantjason.gulimall.product.entity.AttrEntity;
import com.giantjason.gulimall.product.service.AttrService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;


@Service("attrService")
public class AttrServiceImpl extends ServiceImpl<AttrDao, AttrEntity> implements AttrService {

    @Autowired
    AttrAttrgroupRelationDao attrAttrgroupRelationDao;

    @Autowired
    AttrGroupDao attrGroupDao;

    @Autowired
    CategoryDao categoryDao;

    @Autowired
    CategoryService categoryService;

    /**
     * 查询所有的数据并以分页的形式展现出来
     */
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<AttrEntity> page = this.page(
                new Query<AttrEntity>().getPage(params),
                new QueryWrapper<AttrEntity>()
        );

        return new PageUtils(page);
    }

    @Transactional
    @Override
    public void saveAttr(AttrVO attr) {
        AttrEntity attrEntity = new AttrEntity();
        //1.save basic data(pms_attr)
        BeanUtils.copyProperties(attr, attrEntity);
        this.save(attrEntity);
        //2.save related data(pms_attr_attrGroup)
        if (attr.getAttrType() == ProductConstant.AttrEnum.ATTR_TYPE_BASE.getCode() && attr.getAttrGroupId() != null) {
            AttrAttrgroupRelationEntity attrAttrgroupRelationEntity = new AttrAttrgroupRelationEntity();
            attrAttrgroupRelationEntity.setAttrId(attrEntity.getAttrId());
            attrAttrgroupRelationEntity.setAttrGroupId(attr.getAttrGroupId());
            attrAttrgroupRelationDao.insert(attrAttrgroupRelationEntity);
        }
    }

    @Override
    public PageUtils queryBaseAttrPage(Map<String, Object> params, Long catalogId, String type) {

        QueryWrapper<AttrEntity> attrEntityQueryWrapper = new QueryWrapper<AttrEntity>()
                .eq("attr_type", "base"
                        .equalsIgnoreCase(type) ?
                        ProductConstant.AttrEnum.ATTR_TYPE_BASE.getCode() :
                        ProductConstant.AttrEnum.ATTR_TYPE_SALE.getCode());

        //catalogId != 0
        if (catalogId != 0) {
            attrEntityQueryWrapper.eq("catalog_id", catalogId);
        }
        //key fuzzy query
        String key = (String) params.get("key");
        if (!StringUtils.isEmpty(key) && key != null) {
            attrEntityQueryWrapper.and((wrapper) -> {
                wrapper.eq("attr_id", key).or().like("attr_name", key);
            });
        }

        IPage<AttrEntity> page = this.page(
                new Query<AttrEntity>().getPage(params),
                attrEntityQueryWrapper
        );

        PageUtils pageUtils = new PageUtils(page);
        List<AttrEntity> records = page.getRecords();
        List<AttrRespVO> attrRespVORecords = records.stream().map((attrEntity) -> {
            AttrRespVO attrRespVO = new AttrRespVO();
            BeanUtils.copyProperties(attrEntity, attrRespVO);
            //set the name of category & attribute group
            //set group name of AttrRespVO
            if ("base".equalsIgnoreCase(type)) {

                AttrAttrgroupRelationEntity attr_id = attrAttrgroupRelationDao
                        .selectOne(new QueryWrapper<AttrAttrgroupRelationEntity>()
                                .eq("attr_id", attrEntity.getAttrId()));
                if (attr_id != null) {
                    AttrGroupEntity attrGroupEntity = attrGroupDao.selectById(attr_id.getAttrGroupId());

                    if (attrGroupEntity != null) {
                        String attrGroupName = attrGroupEntity.getAttrGroupName();
                        attrRespVO.setGroupName(attrGroupName);
                    } else {
                        attrRespVO.setGroupName("有毒");
                    }
                }
            }
            //set category name of AttrRespVO
            CategoryEntity categoryEntity = categoryDao.selectById(attrEntity.getCatalogId());
            if (categoryEntity != null) {
                attrRespVO.setCatalogName(categoryEntity.getName());
            }
            return attrRespVO;
        }).collect(Collectors.toList());
        pageUtils.setList(attrRespVORecords);
        return pageUtils;
    }

    @Override
    public AttrRespVO getAttrInfo(Long attrId) {
        AttrEntity attrEntity = this.getById(attrId);
        AttrRespVO attrRespVO = new AttrRespVO();
        BeanUtils.copyProperties(attrEntity, attrRespVO);

        //enable group information when attrType is ATTR_TYPE_BASE.getCode()
        if (attrEntity.getAttrType() == ProductConstant.AttrEnum.ATTR_TYPE_BASE.getCode()) {
            //set attrGroupId & catalogPath
            //1.set attrGroupId
            AttrAttrgroupRelationEntity attrAttrgroupRelationEntity = attrAttrgroupRelationDao.selectOne(
                    new QueryWrapper<AttrAttrgroupRelationEntity>()
                            .eq("attr_id", attrEntity.getAttrId()));
            if (attrAttrgroupRelationEntity != null) {
                attrRespVO.setAttrGroupId(attrAttrgroupRelationEntity.getAttrGroupId());
                //1.1 set group name
                AttrGroupEntity attrGroupEntity = attrGroupDao
                        .selectById(attrAttrgroupRelationEntity.getAttrGroupId());
                if (attrGroupEntity != null) {
                    attrRespVO.setGroupName(attrGroupEntity.getAttrGroupName());
                }
            }
        }
        //2.set catalogPath
        Long[] catalogPath = categoryService.findCatalogPath(attrEntity.getCatalogId());
        attrRespVO.setCatalogPath(catalogPath);
        CategoryEntity categoryEntity = categoryDao.selectById(attrEntity.getCatalogId());
        if (categoryEntity != null) {
            attrRespVO.setCatalogName(categoryEntity.getName());
        }
        return attrRespVO;
    }

    @Transactional
    @Override
    public void updateAttr(AttrVO attrVO) {
        AttrEntity attrEntity = new AttrEntity();
        BeanUtils.copyProperties(attrVO, attrEntity);
        this.updateById(attrEntity);

        //如果是基本查询更新，则执行以下操作，设置分组等相关信息
        if (attrEntity.getAttrType() == ProductConstant.AttrEnum.ATTR_TYPE_BASE.getCode()) {

            if (!StringUtils.isEmpty(attrVO.getAttrGroupId())) {
                AttrAttrgroupRelationEntity relationEntity = new AttrAttrgroupRelationEntity();
                relationEntity.setAttrGroupId(attrVO.getAttrGroupId());
                relationEntity.setAttrId(attrVO.getAttrId());


                //check whether user`s action is add or update
                Integer count = attrAttrgroupRelationDao.selectCount(
                        new QueryWrapper<AttrAttrgroupRelationEntity>()
                                .eq("attr_id", attrEntity.getAttrId()));
                if (count > 0) {
                    //update data
                    attrAttrgroupRelationDao.update(relationEntity,
                            new UpdateWrapper<AttrAttrgroupRelationEntity>()
                                    .eq("attr_id", attrEntity.getAttrId()));
                } else {
                    attrAttrgroupRelationDao.insert(relationEntity);
                }
            }
        }

    }

    /*
     * @description: 根据分组Id查找所有属性
     * @author: GiantJason
     * @date: 6/26/2023-7:27 PM
     * @param: java.lang.Long attrGroupId
     * @return: List<AttrEntity>
     */
    @Override
    public List<AttrEntity> getRelationAttr(Long attrGroupId) {
        List<AttrAttrgroupRelationEntity> entities = attrAttrgroupRelationDao
                .selectList(new QueryWrapper<AttrAttrgroupRelationEntity>()
                        .eq("attr_group_id", attrGroupId));

        List<Long> attrIds = entities.stream().map((attr) -> attr.getAttrId()).collect(Collectors.toList());

        if (attrIds == null || attrIds.size() == 0) {
            return null;
        }
        Collection<AttrEntity> attrEntities = this.listByIds(attrIds);
        return (List<AttrEntity>) attrEntities;
    }

    @Override
    public void deleteRelation(AttrGroupRelationVO[] vos) {
//relationDao.delete(new QueryWrapper<>().eq("attr_id",1L).eq("attr_group_id",1L));
        //
        List<AttrAttrgroupRelationEntity> entities = Arrays.asList(vos).stream().map((item) -> {
            AttrAttrgroupRelationEntity relationEntity = new AttrAttrgroupRelationEntity();
            BeanUtils.copyProperties(item, relationEntity);
            return relationEntity;
        }).collect(Collectors.toList());
        attrAttrgroupRelationDao.deleteBatchRelation(entities);
    }

    @Override
    public PageUtils getNoRelationAttr(Map<String, Object> params, Long attrGroupId) {
        //1、当前分组只能关联自己所属的分类里面的所有属性
        AttrGroupEntity attrGroupEntity = attrGroupDao.selectById(attrGroupId);
        Long catalogId = attrGroupEntity.getCatalogId();
        //2、当前分组只能关联别的分组没有引用的属性
        //2.1)、当前分类下的其他分组
        List<AttrGroupEntity> group = attrGroupDao.selectList(new QueryWrapper<AttrGroupEntity>()
                .eq("catalog_id", catalogId));
        List<Long> collect = group.stream().map(item -> {
            return item.getAttrGroupId();
        }).collect(Collectors.toList());

        //2.2)、这些分组关联的属性
        List<AttrAttrgroupRelationEntity> groupId = attrAttrgroupRelationDao.selectList(
                new QueryWrapper<AttrAttrgroupRelationEntity>().in("attr_group_id", collect));
        List<Long> attrIds = groupId.stream().map(item -> {
            return item.getAttrId();
        }).collect(Collectors.toList());

        //2.3)、从当前分类的所有属性中移除这些属性；
        QueryWrapper<AttrEntity> wrapper = new QueryWrapper<AttrEntity>().eq("catalog_id", catalogId)
                .eq("attr_type", ProductConstant.AttrEnum.ATTR_TYPE_BASE.getCode());
        if (attrIds != null && attrIds.size() > 0) {
            wrapper.notIn("attr_id", attrIds);
        }
        String key = (String) params.get("key");
        if (!StringUtils.isEmpty(key)) {
            wrapper.and((w) -> {
                w.eq("attr_id", key).or().like("attr_name", key);
            });
        }
        IPage<AttrEntity> page = this.page(new Query<AttrEntity>().getPage(params), wrapper);

        PageUtils pageUtils = new PageUtils(page);

        return pageUtils;
    }
}
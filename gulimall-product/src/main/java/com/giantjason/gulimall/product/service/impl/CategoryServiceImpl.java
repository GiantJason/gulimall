package com.giantjason.gulimall.product.service.impl;

import com.giantjason.gulimall.product.service.CategoryBrandRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.giantjason.common.utils.PageUtils;
import com.giantjason.common.utils.Query;

import com.giantjason.gulimall.product.dao.CategoryDao;
import com.giantjason.gulimall.product.entity.CategoryEntity;
import com.giantjason.gulimall.product.service.CategoryService;
import org.springframework.transaction.annotation.Transactional;


@Service("categoryService")
public class CategoryServiceImpl extends ServiceImpl<CategoryDao, CategoryEntity> implements CategoryService {

    @Autowired
    CategoryBrandRelationService categoryBrandRelationService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CategoryEntity> page = this.page(new Query<CategoryEntity>().getPage(params),
                new QueryWrapper<CategoryEntity>());

        return new PageUtils(page);
    }

    @Override
    public List<CategoryEntity> listWithTree() {
        //1.get all categories and their sub-categories
        List<CategoryEntity> categoryEntities = baseMapper.selectList(null);

        //2.assemble it in tree structure

        List<CategoryEntity> level1Menus = categoryEntities
                //1)get the 1st level menus
                .stream().filter((categoryEntity) -> categoryEntity.getParentCid() == 0)
                .map((menu) -> {
                    menu.setChildren(getChildren(menu,categoryEntities));
                    return menu;
                }).sorted((menu1,menu2)->{
                    return (menu1.getSort()==null?0:menu1.getSort())-(menu2.getSort()==null?0:menu2.getSort());
                }).collect(Collectors.toList());


        return level1Menus;
    }

    @Override
    public void removeMenuByIds(List<Long> asList) {
        //TODO check current menu`s reference status
        baseMapper.deleteBatchIds(asList);
    }

    @Override
    public Long[] findCatalogPath(Long catalogId) {
        List<Long> path = new ArrayList<>();
        List<Long> parentPath = findParentPath(catalogId, path);
        //reverse the order of catalogPath
        Collections.reverse(parentPath);

        return parentPath.toArray(new Long[parentPath.size()]);
    }

    /*
     * @description: 更新所有级联数据
     * @author: GiantJason
     * @date: 6/16/2023-11:38 PM
     * @param: com.giantjason.gulimall.product.entity.CategoryEntity category
     * @return:
     */
    @Transactional
    @Override
    public void updateCascades(CategoryEntity category) {
        this.updateById(category);
        categoryBrandRelationService.updateCategory(category.getCatId(),category.getName());
    }

    private  List<Long> findParentPath(Long catalogId,List<Long> path){
        //1.收集当前节点Id
        path.add(catalogId);
        CategoryEntity byId = this.getById(catalogId);
        if(byId.getParentCid()!=0){
            //2.recursive method
            findParentPath(byId.getParentCid(), path);
        }
        return path;
    }

    private List<CategoryEntity> getChildren(CategoryEntity root,List<CategoryEntity> all) {
        List<CategoryEntity> children = all.stream().filter(categoryEntity -> {
            //2).get sub menus
                    return categoryEntity.getParentCid() == root.getCatId();
                }).map(categoryEntity ->{
                    //3)recursive method
                    categoryEntity.setChildren(getChildren(categoryEntity,all));
                    return categoryEntity;
                }).sorted((menu1,menu2)->{
                    return (menu1.getSort()==null?0:menu1.getSort())-(menu2.getSort()==null?0:menu2.getSort());
                }).collect(Collectors.toList());
        return  children;
    }
}
package com.giantjason.gulimall.product.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.giantjason.gulimall.product.entity.BrandEntity;
import com.giantjason.gulimall.product.vo.BrandVO;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.giantjason.gulimall.product.entity.CategoryBrandRelationEntity;
import com.giantjason.gulimall.product.service.CategoryBrandRelationService;
import com.giantjason.common.utils.PageUtils;
import com.giantjason.common.utils.R;


/**
 * 品牌分类关联
 *
 * @author GiantJason
 * @email 1720003053b@gmail.com
 * @date 2023-05-27 21:17:55
 */
@RestController
@RequestMapping("product/categorybrandrelation")
public class CategoryBrandRelationController {
    @Autowired
    private CategoryBrandRelationService categoryBrandRelationService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("product:categorybrandrelation:list")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = categoryBrandRelationService.queryPage(params);

        return R.ok().put("page", page);
    }

    /**
     * 获取当前品牌关联的所有分类
     */
//    @RequestMapping(value="/catalog/list",method = RequestMethod.GET)
    //@RequiresPermissions("product:categorybrandrelation:list")
    @GetMapping("/catalog/list")
    public R catalogList(@RequestParam("brandId") Long brandId) {

        List<CategoryBrandRelationEntity> data = categoryBrandRelationService.list(
                new QueryWrapper<CategoryBrandRelationEntity>()
                        .eq("brand_id", brandId));
        return R.ok().put("data", data);
    }

    /**
     * /product/categorybrandrelation/brands/list
     * 获取分类关联的所有品牌
     */
    @GetMapping("/brands/list")
    public R brandRelationList(@RequestParam(value = "catId",required = true) Long catId){
        List<BrandEntity> brandEntityList=categoryBrandRelationService.getBrandsByCatId(catId);
        List<BrandVO> brandVOS = brandEntityList.stream().map((brandEntity -> {
            BrandVO brandVO = new BrandVO();
            brandVO.setBrandId(brandEntity.getBrandId());
            brandVO.setBrandName(brandEntity.getName());
            return brandVO;
        })).collect(Collectors.toList());
        return R.ok().put("data", brandVOS);
    }
    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //@RequiresPermissions("product:categorybrandrelation:info")
    public R info(@PathVariable("id") Long id) {
        CategoryBrandRelationEntity categoryBrandRelation = categoryBrandRelationService.getById(id);

        return R.ok().put("categoryBrandRelation", categoryBrandRelation);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("product:categorybrandrelation:save")
    public R save(@RequestBody CategoryBrandRelationEntity categoryBrandRelation) {
        categoryBrandRelationService.saveDetails(categoryBrandRelation);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("product:categorybrandrelation:update")
    public R update(@RequestBody CategoryBrandRelationEntity categoryBrandRelation) {
        categoryBrandRelationService.updateById(categoryBrandRelation);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("product:categorybrandrelation:delete")
    public R delete(@RequestBody Long[] ids) {
        categoryBrandRelationService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}

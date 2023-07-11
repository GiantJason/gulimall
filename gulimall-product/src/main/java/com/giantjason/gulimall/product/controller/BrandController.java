package com.giantjason.gulimall.product.controller;

import com.giantjason.common.utils.PageUtils;
import com.giantjason.common.utils.R;
import com.giantjason.common.valid.AddGroup;
import com.giantjason.common.valid.UpdateGroup;
import com.giantjason.common.valid.UpdateStatusGroup;
import com.giantjason.gulimall.product.entity.BrandEntity;
import com.giantjason.gulimall.product.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;



/**
 * 品牌
 *
 * @author GiantJason
 * @email 1720003053b@gmail.com
 * @date 2023-05-27 21:17:55
 */
@RestController
@RequestMapping("product/brand")
public class BrandController {
    @Autowired
    private BrandService brandService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("product:brand:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = brandService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{brandId}")
    //@RequiresPermissions("product:brand:info")
    public R info(@PathVariable("brandId") Long brandId){
		BrandEntity brand = brandService.getById(brandId);

        return R.ok().put("brand", brand);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("product:brand:save")
    public R save(@Validated({AddGroup.class}) @RequestBody BrandEntity brand/*,BindingResult result*/){
        //以下方法不利于系统地组件化的对异常进行处理
//        if(result.hasErrors()){
//            Map<String,String> errorMap = new HashMap<>();
//            result.getFieldErrors().forEach((item)->{
//                //FieldError获取错误提示
//                String defaultMessage = item.getDefaultMessage();
//                //获取错误属性的名字
//                String field = item.getField();
//                errorMap.put(field, defaultMessage);
//            });
//            return R.error(400,"提交的数据不合法!").put("data", errorMap);
//        }else {
//        }
        brandService.save(brand);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("product:brand:update")
    public R update(@Validated({UpdateGroup.class}) @RequestBody BrandEntity brand){
		brandService.updateDetails(brand);

        return R.ok();
    }
    /**
     * 修改状态
     */
    @RequestMapping("/update/status")
    //@RequiresPermissions("product:brand:update")
    public R updateStatus(@Validated({UpdateStatusGroup.class}) @RequestBody BrandEntity brand){
        brandService.updateById(brand);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("product:brand:delete")
    public R delete(@RequestBody Long[] brandIds){
		brandService.removeByIds(Arrays.asList(brandIds));

        return R.ok();
    }

}

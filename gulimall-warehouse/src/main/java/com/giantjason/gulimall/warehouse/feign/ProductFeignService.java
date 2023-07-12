package com.giantjason.gulimall.warehouse.feign;

import com.giantjason.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @description: 调用Product微服务
 * @author GiantJason
 * @date 7/7/2023-3:44 PM
 * @version 1.0
 */
@FeignClient("gulimall-product")
public interface ProductFeignService {

    /**
     *      /product/skuinfo/info/{skuId}
     *
     *
     *   1)、让所有请求过网关；
     *          1、@FeignClient("gulimall-gateway")：给gulimall-gateway所在的机器发请求
     *          2、/api/product/skuinfo/info/{skuId}
     *   2）、直接让后台指定服务处理
     *          1、@FeignClient("gulimall-gateway")
     *          2、/product/skuinfo/info/{skuId}
     *
     */
    @RequestMapping("/product/skuinfo/info/{skuId}")
    public R info(@PathVariable("skuId") Long skuId);
}

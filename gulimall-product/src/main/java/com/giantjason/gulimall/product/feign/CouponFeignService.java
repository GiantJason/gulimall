package com.giantjason.gulimall.product.feign;

import com.giantjason.common.to.SkuReductionTO;
import com.giantjason.common.to.SpuBoundTO;
import com.giantjason.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient("gulimall-coupon")
public interface CouponFeignService {

    @PostMapping("coupon/spubounds/save")
    R saveSpuBounds(SpuBoundTO spuBoundTo);

    @PostMapping("coupon/skufullreduction/saveinfo")
    R saveSkuReduction(SkuReductionTO skuReductionTo);
}

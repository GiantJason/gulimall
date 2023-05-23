package com.giantjason.gulimall.product;

import com.giantjason.gulimall.product.entity.BrandEntity;
import com.giantjason.gulimall.product.service.BrandService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ProductApplicationTests {

    @Autowired
    BrandService brandService;

    @Test
    void contextLoads() {
        BrandEntity brandEntity = new BrandEntity();
        brandEntity.setDescript("comes handy");
        brandEntity.setName("Huawei");
        brandService.save(brandEntity);
        System.out.println("successfully saved!!!");
    }

}

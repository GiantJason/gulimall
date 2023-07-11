package com.giantjason.gulimall.product;

import com.giantjason.gulimall.product.entity.BrandEntity;
import com.giantjason.gulimall.product.service.BrandService;
import com.giantjason.gulimall.product.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductApplicationTests {

    @Autowired
    BrandService brandService;

    @Autowired
    CategoryService categoryService;

    @Test
    public void contextLoads() {
        BrandEntity brandEntity = new BrandEntity();
        brandEntity.setDescript("comes handy");
        brandEntity.setName("Huawei");
        brandService.save(brandEntity);
        System.out.println("successfully saved!!!");
    }

    @Test
    public void testFindPath(){
        Long[] catalogPath = categoryService.findCatalogPath(225L);
        log.info("完整路径：{}", Arrays.asList(catalogPath));
    }
}

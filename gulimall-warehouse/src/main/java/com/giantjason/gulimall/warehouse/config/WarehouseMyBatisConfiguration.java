package com.giantjason.gulimall.warehouse.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @description: MyBatis config
 * @author GiantJason
 * @date 7/7/2023-4:11 PM
 * @version 1.0
 */
@EnableTransactionManagement
@Configuration
@MapperScan("com.giantjason.gulimall.warehouse.dao")
public class WarehouseMyBatisConfiguration {
    //引入分页插件
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
        // 设置请求的页面大于最大页后操作， true调回到首页，false 继续请求  默认false
//        paginationInterceptor.setOverflow(true);
//        // 设置最大单页限制数量，默认 500 条，-1 不受限制
//        paginationInterceptor.setLimit(1000);
        return paginationInterceptor;
    }
}

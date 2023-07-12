package com.giantjason.gulimall.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

/**
 * @description: TODO
 * @author GiantJason
 * @date 6/5/2023-10:00 PM
 * @version 1.0
 */
@Configuration
public class GulimallCorsConfiguration {

    @Bean
   public CorsWebFilter corsWebFilter(){
       UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
      CorsConfiguration corsConfiguration = new CorsConfiguration();
      //1.Cors Configuration
       corsConfiguration.addAllowedHeader("*");
       corsConfiguration.addAllowedMethod("*");
       corsConfiguration.addAllowedOrigin("*");
       corsConfiguration.setAllowCredentials(true);
       source.registerCorsConfiguration("/**",corsConfiguration);
       return  new CorsWebFilter(source);
   }
}

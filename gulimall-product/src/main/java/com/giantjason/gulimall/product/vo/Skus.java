/**
  * Copyright 2019 bejson.com 
  */
package com.giantjason.gulimall.product.vo;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * Auto-generated: 2019-11-26 10:50:34
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
@Data
public class Skus {

    private List<Attr> attr;

    private String skuName;

    private BigDecimal price;

    private String skuTitle;

    private String skuSubtitle;

    private List<Images> images;

    private List<String> descar;

    /**
     * 满减信息
     */
    private int fullCount;

    /**
     * 打折信息
     */
    private BigDecimal discount;

    /**
     * 优惠情况(是否叠加)
     */
    private int countStatus;

    /**
     * 原件
     */
    private BigDecimal fullPrice;

    /**
     * 打折后价格
     */
    private BigDecimal reducePrice;

    /**
     * 价格状态(是否已经有折扣)
     */
    private int priceStatus;

    private List<MemberPrice> memberPrice;


}
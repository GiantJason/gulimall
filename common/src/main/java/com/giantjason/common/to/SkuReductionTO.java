package com.giantjason.common.to;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @description: none
 * @author GiantJason
 * @date 7/1/2023-11:35 PM
 * @version 1.0
 */
@Data
public class SkuReductionTO {

    private Long skuId;

    private int fullCount;

    private BigDecimal discount;

    private int countStatus;

    private BigDecimal fullPrice;

    private BigDecimal reducePrice;

    private int priceStatus;

    private List<MemberPrice> memberPrice;
}

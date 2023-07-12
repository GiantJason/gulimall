package com.giantjason.common.to;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @description: spu transfer object
 * @author GiantJason
 * @date 7/1/2023-11:26 PM
 * @version 1.0
 */
@Data
public class SpuBoundTO {

    private Long spuId;

    private BigDecimal buyBounds;

    private BigDecimal growBounds;
}

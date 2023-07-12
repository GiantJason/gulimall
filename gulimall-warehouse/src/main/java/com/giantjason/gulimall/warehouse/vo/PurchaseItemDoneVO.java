package com.giantjason.gulimall.warehouse.vo;

import lombok.Data;

/**
 * @description: 用于购买项完成的VO
 * @author GiantJason
 * @date 7/7/2023-3:29 PM
 * @version 1.0
 */
@Data
public class PurchaseItemDoneVO {

    /**
     * 商品Id
     */
    private Long itemId;

    /**
     * 商品状态
     */
    private Integer status;

    /**
     * 购买理由
     */
    private String reason;
}

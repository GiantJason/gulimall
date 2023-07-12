package com.giantjason.gulimall.warehouse.vo;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @description: 用于购买完成相关功能的VO
 * @author GiantJason
 * @date 7/7/2023-3:29 PM
 * @version 1.0
 */
@Data
public class PurchaseDoneVO {
    @NotNull
    private Long id;//采购单id

    private List<PurchaseItemDoneVO> items;
}

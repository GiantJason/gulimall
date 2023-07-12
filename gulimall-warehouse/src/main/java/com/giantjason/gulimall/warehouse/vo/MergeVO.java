package com.giantjason.gulimall.warehouse.vo;

import lombok.Data;

import java.util.List;

/**
 * @description: 用于合并的VO
 * @author GiantJason
 * @date 7/7/2023-3:18 PM
 * @version 1.0
 */
@Data
public class MergeVO {

    private Long purchaseId; //整单id
    private List<Long> items;//[1,2,3,4] //合并项集合

}

package com.giantjason.gulimall.product.vo;

import lombok.Data;

/**
 * @description: TODO
 * @author GiantJason
 * @date 6/22/2023-10:21 AM
 * @version 1.0
 */

@Data
public class AttrRespVO extends  AttrVO {
    /**
     * category name
     */
    private  String catalogName;

    /**
     * attribute group name
     */
    private String groupName;

    /**
     * catalog path of certain category
     */
    private Long[] catalogPath;
}

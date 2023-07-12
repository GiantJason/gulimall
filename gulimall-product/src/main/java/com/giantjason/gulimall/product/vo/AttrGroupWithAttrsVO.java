package com.giantjason.gulimall.product.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.giantjason.gulimall.product.entity.AttrEntity;
import lombok.Data;

import java.util.List;

/**
 * @description: 属性分组及其内部的所有属性
 * @author GiantJason
 * @date 6/28/2023-11:26 AM
 * @version 1.0
 */
@Data
public class AttrGroupWithAttrsVO {
    /**
     * 分组id
     */
    @TableId
    private Long attrGroupId;
    /**
     * 组名
     */
    private String attrGroupName;
    /**
     * 排序
     */
    private Integer sort;
    /**
     * 描述
     */
    private String descript;
    /**
     * 组图标
     */
    private String icon;
    /**
     * 所属分类id
     */
    private Long catalogId;

    /**
     * 该分组下的所有属性
     */
    private List<AttrEntity> attrs;
}

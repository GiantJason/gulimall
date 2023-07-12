package com.giantjason.common.constant;

import lombok.Data;

/**
 * @description: 与商品信息有关的常量
 * @author GiantJason
 * @date 6/26/2023-10:50 AM
 * @version 1.0
 */
public class ProductConstant {

    public enum AttrEnum{
        ATTR_TYPE_BASE(1,"基本属性"),
        ATTR_TYPE_SALE(0,"销售属性");

        private int code;
        private String msg;

        AttrEnum(int code,String msg){
            this.code = code;
            this.msg = msg;
        }

        public int getCode() {
            return code;
        }

        public String getMsg() {
            return msg;
        }
    }
}

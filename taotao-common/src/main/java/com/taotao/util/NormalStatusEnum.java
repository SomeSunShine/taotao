package com.taotao.util;

/**
 * Created by Peter on 15/3/31.
 */
public enum NormalStatusEnum {

    NORMAL(0),DELETED(1);

    private Integer value;

    NormalStatusEnum(Integer value) {
        this.value=value;
    }

    public Integer getValue(){
        return this.value;
    }

    public Byte getByte(){
        return this.value.byteValue();
    }
}

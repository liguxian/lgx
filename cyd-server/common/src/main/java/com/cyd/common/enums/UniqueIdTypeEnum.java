package com.cyd.common.enums;

import lombok.Getter;

@Getter
public enum UniqueIdTypeEnum {

    USER(1, "用户id"),
    ;

    private Integer code;

    private String description;

    UniqueIdTypeEnum(Integer code, String description) {
        this.code = code;
        this.description = description;
    }
}

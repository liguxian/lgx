package com.cyd.common.enums;

import lombok.Getter;

@Getter
public enum NumericEnum {

    MINUS_TWO(-2, "-2"),
    MINUS_ONE(-1, "-1"),
    ZERO(0, "0"),
    ONE(1, "1"),
    TWO(2, "2"),
    THREE(3, "3"),
    FOUR(4, "4"),
    FIVE(5, "5"),
    SIX(6, "6"),
    SEVEN(7, "7"),
    EIGHT(8, "8"),
    TEN(10, "10"),
    FOURTEEN(14,"14"),
    TEN_THOUSAND(10000, "10000"),
    ;

    private Integer code;

    private String description;

    NumericEnum(Integer code, String description) {
        this.code = code;
        this.description = description;
    }
}

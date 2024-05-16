package com.cyd.api.login.domain.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;

@Data
@Accessors(chain = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class InitDto {

    Long serverTime;
    String sourceVersion;
    String sourceUrl;
    String rechargeUrl;
    String tdAppId;
    Boolean isOpenTd;
    Integer payServerNo;

}

package com.cyd.common.entity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;

@Data
@Accessors(chain = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ChannelEntity {

    String channelId;
    String innerVersion;
    String sourceVersion;
    String sourceUrl;
    String rechargeUrl;
    Integer payServerNo;
    String tdAppId;
    Boolean isOpenTd;
}

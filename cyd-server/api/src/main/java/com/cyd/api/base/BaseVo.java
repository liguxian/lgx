package com.cyd.api.base;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BaseVo {

    String deviceIdentifier;
    String deviceModel;
    String sign;
    Long t;

}

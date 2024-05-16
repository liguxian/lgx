package com.cyd.api.login.domain.vo;

import com.cyd.api.base.BaseVo;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
public class InitVo extends BaseVo {

    String channelId;
    String innerVersion;
}

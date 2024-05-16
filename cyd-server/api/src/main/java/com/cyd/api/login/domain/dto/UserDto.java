package com.cyd.api.login.domain.dto;


import com.cyd.common.base.BaseEntity;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;

@Data
@Accessors(chain = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserDto extends BaseEntity {

    String name;

    String password;

    Short channelId;

    String deviceIdentifier;

    String deviceModel;
}

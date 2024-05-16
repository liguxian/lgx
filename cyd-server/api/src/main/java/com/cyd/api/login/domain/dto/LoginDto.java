package com.cyd.api.login.domain.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LoginDto {
    String userName;

    String password;

    Short channelId;

    String deviceIdentifier;

    String deviceModel;
}

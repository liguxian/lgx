package com.cyd.api.login.domain.vo;

import com.cyd.api.base.BaseVo;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
public class LoginVo extends BaseVo {

    /**
     * 0注册1登录
     */
    @NotNull(message = "操作类型不能为空")
    Integer type;

    @NotNull(message = "用户名不能为空")
    String userName;

    @NotNull(message = "密码不能为空")
    String password;

    @NotNull(message = "渠道不能为空")
    Short channelId;
}

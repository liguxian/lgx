package com.cyd.login.service;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.cyd.api.login.domain.dto.InitDto;
import com.cyd.api.login.domain.dto.UserDto;
import com.cyd.api.login.domain.vo.InitVo;
import com.cyd.cache.helper.RedisHelper;
import com.cyd.common.config.ChannelConfig;
import com.cyd.common.entity.ChannelEntity;
import com.cyd.common.utils.BeanConvert;
import com.cyd.login.service.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class InitService {

    @Autowired
    private UserRepository userRepository;

    public List<UserDto> selectAll() {
        RedisHelper.setValue("login_User", StrUtil.format("{}^{}", "cyd", "123456"));
        String loginUser = (String) RedisHelper.getValue("login_User");
        String[] split = loginUser.split("\\^");
        return Arrays.asList(new UserDto().setName(split[0]).setPassword(split[1]));

    }

    public InitDto init(InitVo vo) {
        ChannelEntity channelEntity = ChannelConfig.get(vo.getChannelId(), vo.getInnerVersion());
        if(ObjectUtil.isNotNull(channelEntity)) {
            return BeanConvert.convertObject(channelEntity, InitDto.class)
                    .setServerTime(System.currentTimeMillis());
        }
        return null;
    }
}































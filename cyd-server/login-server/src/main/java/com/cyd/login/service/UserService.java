package com.cyd.login.service;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.cyd.api.login.domain.dto.LoginDto;
import com.cyd.api.login.domain.vo.LoginVo;
import com.cyd.cache.helper.RedisHelper;
import com.cyd.common.enums.NumericEnum;
import com.cyd.common.enums.UniqueIdTypeEnum;
import com.cyd.common.utils.BeanConvert;
import com.cyd.common.utils.UniqueIdUtil;
import com.cyd.login.entity.UserEntity;
import com.cyd.login.service.repository.UserRepository;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Resource
    private UserRepository userRepository;

    /**
     * 登录
     * @param loginVo
     * @return
     */
    public LoginDto toLogin(LoginVo loginVo) {
        if (NumericEnum.ZERO.getCode().equals(loginVo.getType())) {
            return register(loginVo);
        } else if (NumericEnum.ONE.getCode().equals(loginVo.getType())) {
            return login(loginVo);
        }
        return null;
    }

    /**
     * 注册
     * @param vo
     * @return
     */
    public LoginDto register(LoginVo vo) {
        //1.把UserName写入UserName集合
        RedisHelper.setSet("register_userName", vo.getUserName());

        //2.去Mongodb 查询是否存在
        List<UserEntity> userEntities = userRepository.findByUserName(vo.getUserName());
        //如果DB存在 不能注册
        if (CollectionUtil.isNotEmpty(userEntities)) {
            return null;
        }

        //3.写入MongoDB
        UserEntity userEntity = BeanConvert.convertObject(vo, UserEntity.class);
        userEntity.setId(UniqueIdUtil.getIdByType(UniqueIdTypeEnum.USER.getCode()));
        userEntity.setIsDelete(NumericEnum.ZERO.getCode());
        userEntity.create();
        userRepository.insert(userEntity);

        return BeanConvert.convertObject(userEntity, LoginDto.class);
    }

    public LoginDto login(LoginVo vo) {
        UserEntity userEntity = RedisHelper.hashCacheShell("login_user", StrUtil.format("{}_{}", vo.getUserName(), vo.getPassword()), UserEntity.class, key -> {
            //去Mongodb 查询是否存在
            String[] split = key.split("_");
            if (NumericEnum.TWO.getCode().equals(split.length)) {
                List<UserEntity> userEntities = userRepository.findByUserName(split[0]);
                if (CollectionUtil.isNotEmpty(userEntities)) {
                    return userEntities.get(0);
                }
            }
            return null;
        });
        if (ObjectUtil.isNull(userEntity)) {
            return null;
        }
        //如果账号存在 并且换了设备 更新设备信息
        if (userEntity != null && (userEntity.getChannelId() != vo.getChannelId() || !userEntity.getDeviceIdentifier().equalsIgnoreCase(vo.getDeviceIdentifier()) || !userEntity.getDeviceModel().equalsIgnoreCase(vo.getDeviceModel()))) {
            userEntity.setChannelId(vo.getChannelId());
            userEntity.setDeviceIdentifier(vo.getDeviceIdentifier());
            userEntity.setDeviceModel(vo.getDeviceModel());

            userRepository.save(userEntity);
            RedisHelper.hashPut("login_user", StrUtil.format("{}_{}", vo.getUserName(), vo.getPassword()), userEntity);
        }

        return BeanConvert.convertObject(userEntity, LoginDto.class);
    }
}































package com.cyd.common.utils;

import cn.hutool.core.collection.CollectionUtil;
import com.cyd.cache.utils.SpringUtils;
import com.cyd.common.entity.UniqueIdEntity;
import com.cyd.common.entity.repository.UniqueIdRepository;
import com.cyd.common.enums.NumericEnum;

import java.util.List;

public class UniqueIdUtil {

    private static UniqueIdRepository uniqueIdRepository = SpringUtils.getBean("uniqueIdRepository", UniqueIdRepository.class);

    public static Long getIdByType(Integer type) {
        List<UniqueIdEntity> uniqueIdEntities = uniqueIdRepository.findByType(type);
        if (CollectionUtil.isEmpty(uniqueIdEntities)) {
            //插入
            UniqueIdEntity uniqueIdEntity = new UniqueIdEntity().setType(type);
            uniqueIdEntity.setId(1L);
            uniqueIdEntity.setIsDelete(NumericEnum.ZERO.getCode());
            uniqueIdEntity.create();
            uniqueIdRepository.insert(uniqueIdEntity);
            return uniqueIdEntity.getId();
        }
        //id+1
        UniqueIdEntity uniqueIdEntity = uniqueIdEntities.get(0);
        uniqueIdEntity.setId(uniqueIdEntity.getId() + 1L);
        uniqueIdRepository.save(uniqueIdEntity);
        return uniqueIdEntity.getId();
    }
}

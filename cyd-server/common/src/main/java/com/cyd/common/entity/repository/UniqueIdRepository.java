package com.cyd.common.entity.repository;

import com.cyd.common.entity.UniqueIdEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface UniqueIdRepository extends MongoRepository<UniqueIdEntity, String> {

    List<UniqueIdEntity> findByType(Integer type);
}

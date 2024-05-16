package com.cyd.login.service.repository;

import com.cyd.login.entity.UserEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface UserRepository extends MongoRepository<UserEntity, String> {

    List<UserEntity> findByUserName(String userName);
}

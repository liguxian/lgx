package com.cyd.common.base;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.time.LocalDateTime;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Accessors(chain = true)
@Data
public class BaseEntity implements Serializable {

    //主键
    @Id
    String _id;

    Long id;

    //状态
    Integer isDelete;

    //创建时间
    LocalDateTime createTime;

    //修改时间
    LocalDateTime updateTime;

    public void create(){
        createTime = LocalDateTime.now();
        updateTime = LocalDateTime.now();
    }

    public void update(){
        updateTime = LocalDateTime.now();
    }
}

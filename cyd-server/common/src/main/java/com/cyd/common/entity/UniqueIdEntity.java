package com.cyd.common.entity;

import com.cyd.common.base.BaseEntity;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "uniqueId")
@Data
@Accessors(chain = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UniqueIdEntity extends BaseEntity {

    Integer type;
}

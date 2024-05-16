package com.cyd.common.utils;

import cn.hutool.json.JSONUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author : [peng]
 * @version : [v1.0]
 * @description : [Bean转换]
 * @createTime : [2021/3/29 11:56]
 * @updateUser : [peng]
 * @updateTime : [2021/3/29 11:56]
 * @updateRemark : [说明本次修改内容]
 */
public class BeanConvert {
    
    private static Logger logger = LoggerFactory.getLogger(BeanConvert.class);
    
    public static <T> T convertObject(Object source, Class<T> targetClazz) {
        
        try {
            return convert(source, targetClazz);
        } catch (Exception e) {
            logger.error("convert object error :", e);
            return null;
        }
    }
    
    private static <T> T convert(Object source, Class<T> targetClazz) throws InstantiationException, IllegalAccessException, java.lang.reflect.InvocationTargetException, NoSuchMethodException {
        if (source == null) {
            return null;
        }
        Object targetObj = targetClazz.getConstructor().newInstance();
        BeanUtils.copyProperties(source, targetObj);
        
        return (T) targetObj;
    }
    
    public static <T> T convertObjectWithEx(Object source, Class<T> targetClazz) {
        try {
            return convert(source, targetClazz);
        } catch (Exception e) {
            throw new IllegalArgumentException("can't convert object from " + source.getClass().getName() + "to " + targetClazz.getName());
        }
    }
    
    public static <T> List<T> convertList(List source, Class<T> targetClazz) {
        
        if (CollectionUtils.isEmpty(source)) {
            return new ArrayList<>();
        }

        List<T> targetList = new ArrayList<>();

        for (Object sourceObj : source) {
            targetList.add(convertObject(sourceObj, targetClazz));
        }

        return targetList.stream().filter(Objects::nonNull).collect(Collectors.toList());
    }
    public static <T> List<T> convertListByJson(List source, Class<T> targetClazz) {

        if (CollectionUtils.isEmpty(source)) {
            return new ArrayList<>();
        }
        return JSONUtil.toList(JSONUtil.toJsonStr(source),targetClazz);
    }
}

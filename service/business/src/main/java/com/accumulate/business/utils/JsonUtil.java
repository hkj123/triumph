/**
 * Drore Confidential
 *
 * (c) Copyright Zhejiang Drore Technology Co.,Ltd. 2018
 *
 * All rights reserved.
 */

package com.accumulate.business.utils;

import com.accumulate.business.exception.SaasException;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.text.SimpleDateFormat;
import java.util.TimeZone;

public class JsonUtil {
    public static ObjectMapper objectMapper;
    public static ObjectMapper ignoreUnkownMapper;
    public static ObjectMapper nonNullMapper;

    /**
     * json string to java object
     * 
     * @param jsonStr
     * @param valueType
     * @return
     */
    public static <T> T readValue(String jsonStr, Class<T> valueType) {
        if (objectMapper == null) {
            objectMapper = new ObjectMapper();
            objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
            objectMapper.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
        }

        try {
            return objectMapper.readValue(jsonStr, valueType);
        } catch (Exception e) {
            throw new SaasException("解析JSON字符串 " + jsonStr + " 错误.原因：",e);
        }

    }

    /**
     * json string to java object,ignore unknown field
     * 
     * @param jsonStr
     * @param valueType
     * @return
     */
    public static <T> T readValueIgnoreUnkown(String jsonStr, Class<T> valueType) {
        if (ignoreUnkownMapper == null) {
            ignoreUnkownMapper = new ObjectMapper();
            ignoreUnkownMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
            ignoreUnkownMapper.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
            ignoreUnkownMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        }

        try {
            return ignoreUnkownMapper.readValue(jsonStr, valueType);
        } catch (Exception e) {
            throw new SaasException("解析JSON字符串 " + jsonStr + " 错误.原因：",e);
        }

    }

    /**
     * json string to List of object.
     * Example usage:
     * <List<Device> speakers=JsonUtil.readValue(responseJsonStr, new TypeReference<List<Device>>() {});
     * 
     * @param jsonStr
     * @param valueTypeRef
     * @return
     */
    public static <T> T readValue(String jsonStr, TypeReference<T> valueTypeRef) {
        if (objectMapper == null) {
            objectMapper = new ObjectMapper();
            objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
            objectMapper.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
        }

        try {
            return objectMapper.readValue(jsonStr, valueTypeRef);
        } catch (Exception e) {
            throw new SaasException("解析JSON字符串 " + jsonStr + " 错误.原因：",e);
        }

    }


    /**
     * json string to List of object, ignore unknown fields.
     * Example usage:
     * <List<Device> speakers=JsonUtil.readValue(responseJsonStr, new TypeReference<List<Device>>() {});
     *
     * @param jsonStr
     * @param valueTypeRef
     * @return
     */
    public static <T> T readValueIgnoreUnknown(String jsonStr, TypeReference<T> valueTypeRef) {
        if (ignoreUnkownMapper == null) {
            ignoreUnkownMapper = new ObjectMapper();
            ignoreUnkownMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
            ignoreUnkownMapper.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
            ignoreUnkownMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        }

        try {
            return ignoreUnkownMapper.readValue(jsonStr, valueTypeRef);
        } catch (Exception e) {
            throw new SaasException("解析JSON字符串 " + jsonStr + " 错误.原因：",e);
        }

    }

    /**
     * java object to json string
     * 
     * @param object
     * @return
     */
    public static String toJSon(Object object) {
        if (objectMapper == null) {
            objectMapper = new ObjectMapper();
            objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
            objectMapper.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
        }

        try {
            return objectMapper.writeValueAsString(object);
        } catch (Exception e) {
            throw new SaasException("将对象转化为JSON字符窜错误.原因：",e);
        }

    }

    /**
     * java object to json string,without null value
     * 
     * @param object
     * @return
     */
    public static String toNonNullJSon(Object object) {
        if (nonNullMapper == null) {
            nonNullMapper = new ObjectMapper();
            nonNullMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
            nonNullMapper.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
            nonNullMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

        }

        try {
            return nonNullMapper.writeValueAsString(object);
        } catch (Exception e) {
            throw new SaasException("将对象转化为JSON字符窜错误.原因：",e);
        }

    }

}

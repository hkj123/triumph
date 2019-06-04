package com.accumulate.business.utils;

import com.accumulate.business.exception.SaasException;
import lombok.extern.slf4j.Slf4j;

import java.io.*;

/**
 * 通过序列化方式实现深拷贝, 被拷贝对象以及其内容, 需要实现Serializable接口
 */
@Slf4j
public class CopyUtil {
//    public static <T> T deepClone(T src){
//        try {
//            Object obj = null;
//            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
//            ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
//            objectOutputStream.writeObject(src);
//            objectOutputStream.close();
//            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
//            ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
//            obj = objectInputStream.readObject();
//            objectInputStream.close();
//            return (T) obj;
//        } catch (IOException | ClassNotFoundException e) {
//            log.error("Deep copy object {} failed", src.toString(), e);
//            throw new SaasException("拷贝对象" + src.toString() + "失败", e);
//        }
//    }

    public static void main(String[] args) {
        String projectName = "123";
        String serviceName = "123";
        String jsonName = projectName+serviceName.concat(".json");
        System.out.println(jsonName);
    }
}

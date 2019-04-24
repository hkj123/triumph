package com.accumulate.business.utils;


import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * @author lilg
 */
@Slf4j
public class EntityUtil{
    /**
     * Set creator to the entity object
     *
     * @param entity - the given entity
     * @param creatorId - creator id
     * @return The entity with creator id added
     */
    public static <T> T addCreator(T entity, String creatorId) {
        Class<?> clazz = entity.getClass();
        try {
            Method mSetCreator = clazz.getMethod("setCreatorId", new Class[]{String.class});
            mSetCreator.invoke(entity, new Object[]{creatorId});
        } catch (NoSuchMethodException e) {
            log.error(e.toString());
        } catch (IllegalAccessException e) {
            log.error(e.toString());
        } catch (InvocationTargetException e) {
            log.error(e.toString());
        }

        return entity;
    }

    /**
     * Set create time to the entity object
     *
     * @param entity - the given entity
     * @return The entity with create time added
     */
    public static <T> T addCreateTime(T entity){
        Class<?> clazz = entity.getClass();

        Method mSetCreateTime = null;
        try {
            mSetCreateTime = clazz.getMethod("setCreateTime", new Class[] { Date.class });
            mSetCreateTime.invoke(entity, new Object[] { new Date() });
        } catch (NoSuchMethodException e) {
            log.error(e.toString());
        } catch (IllegalAccessException e) {
            log.error(e.toString());
        } catch (InvocationTargetException e) {
            log.error(e.toString());
        }
        return entity;
    }

    /**
     * Set modifier id to the entity
     *
     * @param entity - the given entity
     * @param modifierId - modifier id
     * @return the entity with modifier id added
     */
    public static <T> T addModifier(T entity, String modifierId) {
        Class<?> clazz = entity.getClass();
        try {
            Method mSetModifier = clazz.getMethod("setModifierId", new Class[]{String.class});
            mSetModifier.invoke(entity, new Object[]{modifierId});
        }catch (NoSuchMethodException e) {
            log.error(e.toString());
        } catch (IllegalAccessException e) {
            log.error(e.toString());
        } catch (InvocationTargetException e) {
            log.error(e.toString());
        }
        return entity;
    }


    /**
     * Set modify time to the entity
     *
     * @param entity - the given entity
     * @return the entity with modify time added
     */
    public static <T> T addModifyTime(T entity) {
        Class<?> clazz = entity.getClass();
        try {
            Method mSetModifyTime = clazz.getMethod("setModifyTime", new Class[]{Date.class});
            mSetModifyTime.invoke(entity, new Object[]{new Date()});
        } catch (NoSuchMethodException e) {
            log.error(e.toString());
        } catch (IllegalAccessException e) {
            log.error(e.toString());
        } catch (InvocationTargetException e) {
            log.error(e.toString());
        }
        return entity;
    }

    /**
     * Filter out the entities whose "isDeleted" field is true
     * @param entities The input entities
     * @return The output entities without deleted entities
     */
//    public static <T> List<T> filterDeleted(List<T> entities) throws NoSuchMethodException {
//        List<T> outEntities= new ArrayList<T>();
//        if (entities != null && !entities.isEmpty()){
//            Class<?> clazz = entities.get(0).getClass();
//            Method mGetIsDeleted = clazz.getMethod("getIsDeleted");
//            outEntities = entities.stream().filter(e -> {
//                try {
//                    return (Boolean)mGetIsDeleted.invoke(e) == false;
//                } catch (IllegalAccessException e1) {
//                    log.error("IllegalAccessException:" + e1);
//                } catch (InvocationTargetException e1) {
//                    log.error("InvocationTargetException:" + e1);
//                }
//                return false;
//            }).collect(Collectors.toList());
//
//        }
//        return outEntities;
//    }


}

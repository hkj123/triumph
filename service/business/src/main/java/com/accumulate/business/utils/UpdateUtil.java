package com.accumulate.business.utils;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.beans.PropertyDescriptor;
import java.util.HashSet;
import java.util.Set;

/**
 * 作用:用来对实体对象中的null赋值
 * @author :lilg
 */
public class UpdateUtil {
    /**
     * 将空值的属性从原实体中复制到目标实体中
     *
     * @param src    : 从数据库根据id查询出来的原实体
     * @param target : NUll值会被覆盖的目标实体
     */
    public static void supplementNullProperties(Object src, Object target) {
        BeanUtils.copyProperties(src, target, getNonNullProperties(target));
    }

    /**
     * 将非空的properties给找出来,然后返回出来
     *
     * @param src
     * @return
     */
    private static String[] getNonNullProperties(Object src) {
        BeanWrapper srcBean = new BeanWrapperImpl(src);
        PropertyDescriptor[] pds = srcBean.getPropertyDescriptors();
        Set<String> nonNullNames = new HashSet<>();
        for (PropertyDescriptor p : pds) {
            Object srcValue = srcBean.getPropertyValue(p.getName());
            if (srcValue != null) nonNullNames.add(p.getName());
        }
        String[] result = new String[nonNullNames.size()];
        return nonNullNames.toArray(result);
    }
}

package com.accumulate.business.mapper;

import com.accumulate.business.config.SuperMapper;
import com.accumulate.business.entity.Resource;

public interface ResourceMapper extends SuperMapper<Resource> {

//    /**
//     * 自定义注入方法
//     */
//    int deleteAll();
//
//    @Select("select  id, name from user")
//    List<User> selectListBySQL();
//
//    List<User> selectListByWrapper(@Param("ew") Wrapper wrapper);
//
//    /**
//     * 3.x 的 page 可以进行取值,多个入参记得加上注解
//     * 自定义 page 类必须放在入参第一位
//     * 返回值可以用 IPage<T> 接收 也可以使用入参的 MyPage<T> 接收
//     * <li> 3.1.0 之前的版本使用注解会报错,写在 xml 里就没事 </li>
//     * <li> 3.1.0 开始支持注解,但是返回值只支持 IPage ,不支持 IPage 的子类</li>
//     *
//     * @param myPage 自定义 page
//     * @return 分页数据
//     */
////    @Select("select * from user where (age = #{pg.selectInt} and name = #{pg.selectStr}) or (age = #{ps.yihao} and name = #{ps.erhao})")
//    MyPage<User> mySelectPage(@Param("pg") MyPage<User> myPage, @Param("ps") ParamSome paramSome);
//
//    Page<User> mySelectPage2(@Param("pg") Page myPage, @Param("ew") Wrapper wrapper);
}
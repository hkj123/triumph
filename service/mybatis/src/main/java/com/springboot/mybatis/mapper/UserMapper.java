package com.springboot.mybatis.mapper;

import com.github.pagehelper.Page;
import com.springboot.mybatis.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * User 表数据库控制层接口
 */
@Mapper
public interface UserMapper {

    List<User> findAll();

    Page<User> findByPaging();
}

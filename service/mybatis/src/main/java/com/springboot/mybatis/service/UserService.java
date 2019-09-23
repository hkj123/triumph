package com.springboot.mybatis.service;

import com.github.pagehelper.Page;
import com.springboot.mybatis.entity.User;

import java.util.List;

public interface UserService {
    List<User> findAll();

    Page<User> findByPaging();
}

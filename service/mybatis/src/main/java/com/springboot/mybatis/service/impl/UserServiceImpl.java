package com.springboot.mybatis.service.impl;

import com.github.pagehelper.Page;
import com.springboot.mybatis.entity.User;
import com.springboot.mybatis.mapper.UserMapper;
import com.springboot.mybatis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> findAll() {
        return userMapper.findAll();
    }

    @Override
    public Page<User> findByPaging(){
        return userMapper.findByPaging();
    }
}

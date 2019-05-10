package com.accumulate.business.service.impl;

import com.accumulate.business.entity.UserRole;
import com.accumulate.business.mapper.UserRoleMapper;
import com.accumulate.business.service.UserRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * User 表数据服务层接口实现类
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements UserRoleService {

}
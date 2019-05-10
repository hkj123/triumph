package com.accumulate.business.service;

import com.accumulate.business.entity.User;
import com.accumulate.business.model.JobAndTrigger;
import com.accumulate.business.model.MyPage;
import com.accumulate.business.model.ParamSome;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * User 表数据服务层接口
 */
public interface IUserService extends IService<User> {

    MyPage<User> findUserPage(MyPage<User> myPage);

//    Long create(User user);
//	boolean deleteAll();
//
//	public List<User> selectListBySQL();
//
//	public List<User> selectListByWrapper(Wrapper wrapper);
//
//	MyPage<User> pageFindUserModel(MyPage<User> myPage, ParamSome paramSome);
//
//	Page<User> pageFindUserModel2(Page myPage, Wrapper<User> wrapper);
}
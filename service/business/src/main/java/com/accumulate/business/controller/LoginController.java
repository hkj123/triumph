package com.accumulate.business.controller;

import com.accumulate.business.entity.User;
import com.accumulate.business.model.UserLoginModel;
import com.accumulate.business.service.IUserService;
import com.accumulate.business.utils.MD5;
import com.accumulate.business.utils.Result;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.ApiController;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@RestController
@RequestMapping("/login")
@Api("登录")
public class LoginController extends ApiController {

    @Autowired
    private IUserService userService;

    @PostMapping(value = "/login")
    @ApiOperation(value = "用户登录", notes = "用户登录")
    public Result login(@ApiParam("登陆属性") @RequestBody UserLoginModel userLoginModel) {
//        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        try {
            if (Objects.isNull(userLoginModel.getUsername()) || Objects.isNull(userLoginModel.getPassword())) {
                return new Result(Result.ReturnValue.FAILURE, "Missing parameters");
            }
            QueryWrapper<User> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("username", userLoginModel.getUsername());
            User user = userService.getOne(queryWrapper);
            if (Objects.isNull(user)) {
                return new Result(Result.ReturnValue.FAILURE, "User does not exist");
            }
//            if (!passwordEncoder.matches(userLoginModel.getPassword(), user.getPassword())) {
//                return new Result(Result.ReturnValue.FAILURE, "Wrong password, please re-enter");
//            }
            if(!Objects.equals(userLoginModel.getPassword(),user.getPassword())){
                return new Result(Result.ReturnValue.FAILURE, "Wrong password, please re-enter");
            }
            //生成一个token，保存用户登录状态
            String valiToken = userService.createToken(user.getId());
            Map loginMap = new HashMap();
            loginMap.put("token", valiToken);
            loginMap.put("user", user);
            loginMap.put("department", "");
            loginMap.put("roleList", "");
            return new Result(Result.ReturnValue.SUCCESS, "", loginMap);
        } catch (Exception e) {
            return new Result(Result.ReturnValue.FAILURE, e.getMessage());
        }
    }

    @PostMapping(value = "/developLogin")
    @ApiOperation(value = "开发登录", notes = "用户登录")
    public Result developLogin(@RequestBody UserLoginModel userLoginModel, HttpServletRequest request) {
        try {
            QueryWrapper<User> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("username", userLoginModel.getUsername());
            User user = userService.getOne(queryWrapper);
            if (Objects.isNull(user)) {
                return new Result(Result.ReturnValue.FAILURE, "User does not exist");
            }
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//            if (!passwordEncoder.matches(MD5.MD5Encode(userLoginModel.getPassword()), user.getPassword())) {
//                return new Result(Result.ReturnValue.FAILURE, "Wrong password, please re-enter");
//            }
            if(!Objects.equals(userLoginModel.getPassword(),user.getPassword())){
                return new Result(Result.ReturnValue.FAILURE, "Wrong password, please re-enter");
            }
            //生成一个token，保存用户登录状态
            String valiToken = userService.createToken(user.getId());
            Map loginMap = new HashMap();
            loginMap.put("token", valiToken);
            loginMap.put("user", user);
            loginMap.put("department", "");
            loginMap.put("roleList", "");
            return new Result(Result.ReturnValue.SUCCESS, "", loginMap);
        } catch (Exception e) {
            return new Result(Result.ReturnValue.FAILURE, e.getMessage());
        }
    }
}

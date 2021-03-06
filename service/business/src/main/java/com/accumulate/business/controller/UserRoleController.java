package com.accumulate.business.controller;

import com.accumulate.business.entity.UserRole;
import com.accumulate.business.model.UserAndListRoleModel;
import com.accumulate.business.service.UserRoleService;
import com.accumulate.business.utils.Result;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.ApiController;
import io.swagger.annotations.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/userRole")
@Api("用户角色管理")
public class UserRoleController extends ApiController {

    @Autowired
    private UserRoleService userRoleService;

    /**
     * user create
     */
    @PostMapping("/userAndRole")
    @ApiOperation(value = "userAndRole")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Correct response", response = UserRole.class)})
    public Result userAndRole(@ApiParam("用户角色实体") @RequestBody UserRole userRole) {
        try {
            UserRole userRole1 = new UserRole();
            BeanUtils.copyProperties(userRole, userRole1);
            userRoleService.save(userRole1);
            return new Result(Result.ReturnValue.SUCCESS, "operate success", userRole1.getId());
        } catch (Exception e) {
            return new Result(Result.ReturnValue.FAILURE, e.getMessage());
        }
    }

    /**
     * userAndRole
     */
    @PostMapping("/userAndListRole")
    @ApiOperation(value = "userAndListRole")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Correct response", response = UserAndListRoleModel.class)})
    public Result userAndListRole(@ApiParam("用户批量添加角色") @RequestBody UserAndListRoleModel userAndListRoleModel) {
        try {
            if (Objects.isNull(userAndListRoleModel.getUserid())) {
                return new Result(Result.ReturnValue.FAILURE, "user id is null", "user id is null");
            }
            //delete relation pre
            QueryWrapper<UserRole> queryWrapper = new QueryWrapper<>();
            if (Objects.nonNull(userAndListRoleModel.getUserid())) {
                queryWrapper.eq("userid",userAndListRoleModel.getUserid());
            }
            userRoleService.remove(queryWrapper);
            List<UserRole> userRoleList = new ArrayList<>();
            for (Long roleid : userAndListRoleModel.getRoleidList()) {
                UserRole userRole = new UserRole();
                userRole.setUserid(userAndListRoleModel.getUserid());
                userRole.setRoleid(roleid);
                userRoleList.add(userRole);
            }
            userRoleService.saveBatch(userRoleList);
            return new Result(Result.ReturnValue.SUCCESS, "operate success");
        } catch (Exception e) {
            return new Result(Result.ReturnValue.FAILURE,  e.getMessage());
        }
    }
//
//    /**
//     * http://localhost:8080/user/add
//     * 简单添加
//     */
//    @PostMapping("/add")
//    @ApiOperation(value = "add user")
////    @ApiResponses(value = {@ApiResponse(code = 200, message = "Correct response", response = User.class)})
//    public Object addUser(@RequestBody final User user
////            ,@RequestHeader(value = "token") String token
//    ) {
////        redis缓存使用示例
//        redisTemplate.opsForValue().set("user", user);
//        return userService.save(user);
//    }
//
//    /**
//     * http://localhost:8080/user/test
//     */
//    @GetMapping("/PageFindUser")
//    @ApiOperation(value = "PageFind user")
////    public IPage<User> PageFindUser() {
//    public Result PageFindUser() {
////        return userService.page(new Page<User>(0, 12), null);
//        return new Result(Result.ReturnValue.SUCCESS, "", userService.page(new Page<User>(0, 12), null));
//    }
//
//    /**
//     * http://localhost:8080/user/test
//     */
//    @GetMapping("/PageFindUserModel")
//    @ApiOperation(value = "PageFind user model")
//    public Object PageFindUserModel(@ApiParam(value = "分页页数", required = true) @RequestParam(value = "current", required = true) int current,
//                                    @ApiParam(value = "页数量", required = true) @RequestParam(value = "size", required = true) int size,
//                                    @ApiParam(value = "查询name", required = false) @RequestParam(value = "name", required = false) String name) {
//        MyPage<User> myPage = new MyPage<>(current, size);
//
//        ParamSome paramSome = new ParamSome();
//        if (Objects.nonNull(name)) {
//            paramSome.setErhao(name);
//        }
//        MyPage<User> userMyPage = userService.pageFindUserModel(myPage, paramSome);
//        return userMyPage;
//    }
//
//    /**
//     * http://localhost:8080/user/test
//     */
//    @PostMapping("/PageFindUserModel2")
//    @ApiOperation(value = "PageFind user model2")
//    public Object PageFindUserModel2(@RequestBody MyPage myPage) {
//        Page page = new Page();
//        BeanUtils.copyProperties(myPage, page);
//        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
//        if (Objects.nonNull(myPage.getSelectStr())) {
//            queryWrapper.eq("name", myPage.getSelectStr());
//        }
//        Page<User> userMyPage = userService.pageFindUserModel2(myPage, queryWrapper);
//        return userMyPage;
//    }
//
//    /**
//     * 增删改查 CRUD
//     * http://localhost:8080/user/crudModel
//     */
//    @GetMapping("/crudModel")
//    public Object crudModel(@ApiParam(value = "删除id", required = false) @RequestParam(value = "id", required = true) String id) {
//        System.err.println("删除一条数据");
//        userService.removeById(id);
//        System.err.println("deleteAll：");
//        userService.deleteAll();
//        System.err.println("插入一条数据");
//        User user = new User();
//        user.setId("测试添加");
//        user.setName("测试添加");
//        System.err.println("插入一条数据：" + user.toString());
//        boolean result = userService.save(user);
//        System.err.println("查询：" + userService.getById("测试添加").toString());
//        System.err.println("更新一条数据：" + userService.updateById(user));
//        IPage<User> userListPage = userService.page(new Page<User>(1, 5), new QueryWrapper<>());
//        System.err.println("total=" + userListPage.getTotal() + ", current list size=" + userListPage.getRecords().size());
//        userListPage = userService.page(new Page<User>(1, 5), new QueryWrapper<User>().orderByDesc("name"));
//        System.err.println("total=" + userListPage.getTotal() + ", current list size=" + userListPage.getRecords().size());
//        return userListPage;
//    }
//
//    /**
//     * http://localhost:8080/user/select_wrapper
//     */
//    @GetMapping("/select_wrapper")
//    public Result getUserByWrapper() {
//        List<User> userList = userService.selectListByWrapper(new QueryWrapper<User>()
//                .lambda().like(User::getName, "毛")
//                .or(e -> e.like(User::getName, "张")));
//        return new Result(Result.ReturnValue.SUCCESS, "查询", userList);
//    }
//
//    @GetMapping("/delete_wrapper")
//    public Result deleteByWrapper() {
//        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
//        queryWrapper.isNull("name")
//                .isNotNull("id")
//                .eq("name", "胡");
//        Boolean del = userService.remove(queryWrapper);
//        return new Result(Result.ReturnValue.SUCCESS, "删除", del);
//    }
//
//    @GetMapping("/selectWrapperOne")
//    public Result selectWrapperOne(@ApiParam(value = "查询name", required = false) @RequestParam(value = "name", required = false) String name) {
//        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
//        queryWrapper.eq("name", name);
//        User user = userService.getOne(queryWrapper);
//        return new Result(Result.ReturnValue.SUCCESS, "selectWrapperOne", user);
//    }
//
//    @GetMapping("/selectWrapperCount")
//    public Result selectWrapperCount(@ApiParam(value = "查询name", required = true) @RequestParam(value = "name", required = true) String name) {
//        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
//        queryWrapper.eq("name", name);
//        List<User> userList = userService.selectListByWrapper(queryWrapper);
//        return new Result(Result.ReturnValue.SUCCESS, "selectWrapperOne", userList);
//    }
//
//    @GetMapping("/getHealth")
//    @ApiOperation(value = "getHealth")
//    public ModelAndView getHealth() {
////        Health health = restTemplate.getForObject("http://business-service:9001/metrics/health",Health.class);
//        String url = "http://business-service:9001/metrics/prometheus";
//        return new ModelAndView(url);
//    }
//
//    @GetMapping("/noParamRedirect")
//    @ApiOperation(value = "noParamRedirect")
//    public RedirectView noParamTest() {
//        RedirectView redirectTarget = new RedirectView();
//        redirectTarget.setContextRelative(true);
//        redirectTarget.setUrl("http://localhost:9001/metrics/prometheus");
//        return redirectTarget;
//    }
/**
 * <p>
 * 测试通用 Api Controller 逻辑
 * </p>
 * 测试地址：
 * http://localhost:8080/user/api
 * http://localhost:8080/user/api?test=mybatisplus
 * <p>
 * 插入 OR 修改
 * http://localhost:8080/user/test3
 * <p>
 * http://localhost:8080/user/select_sql
 *
 * <p>
 * 参数模式分页
 * </p>
 * <p>
 * 7、分页 size 一页显示数量  current 当前页码
 * 方式一：http://localhost:8080/user/page?size=1&current=1<br>
 * <p>
 * 集合模式，不进行分页直接返回所有结果集：
 * http://localhost:8080/user/page?listMode=true
 * <p>
 * 测试事物
 * http://localhost:8080/user/test_transactional<br>
 * 访问如下并未发现插入数据说明事物可靠！！<br>
 * http://localhost:8080/user/test<br>
 * <br>
 * 启动  Application 加上 @EnableTransactionManagement 注解其实可无默认貌似就开启了<br>
 * 需要事物的方法加上 @Transactional 必须的哦！！
 */
//    @GetMapping("/api")
//    public R<String> testError(String test) {
//        return success(test);
//    }


//    /**
//     * AR 部分测试
//     * http://localhost:8080/user/test1
//     */
//    @GetMapping("/test1")
//    public IPage<User> test1() {
//        User user = new User();
//        user.setName("22222222222");
//        System.err.println("删除所有：" + user.delete(null));
//        user.insert();
//        System.err.println("查询插入结果：" + user.selectById().toString());
//        user.setName("mybatis-plus-ar");
//        System.err.println("更新：" + user.updateById());
//        return user.selectPage(new Page<User>(0, 12), null);
//    }

/**
 * 插入 OR 修改
 * http://localhost:8080/user/test3
 */
//    @GetMapping("/test3")
//    public User test3() {
//        User user = new User();
//        user.setId("2222222222222");
//        user.setName("22222222222");
//        userService.saveOrUpdate(user);
//        return userService.getById(1L);
//    }

/**
 * http://localhost:8080/user/select_sql
 */
//    @GetMapping("/select_sql")
//    public Object getUserBySql() {
//        return userService.selectListBySQL();
//    }


/**
 * <p>
 * 参数模式分页
 * </p>
 * <p>
 * 7、分页 size 一页显示数量  current 当前页码
 * 方式一：http://localhost:8080/user/page?size=1&current=1<br>
 * <p>
 * 集合模式，不进行分页直接返回所有结果集：
 * http://localhost:8080/user/page?listMode=true
 */
//    @GetMapping("/page")
//    public IPage page(Page page, boolean listMode) {
//        if (listMode) {
//            // size 小于 0 不在查询 total 及分页，自动调整为列表模式。
//            // 注意！！这个地方自己控制好！！
//            page.setSize(-1);
//        }
//        return userService.page(page, null);
//    }

/**
 * 测试事物
 * http://localhost:8080/user/test_transactional<br>
 * 访问如下并未发现插入数据说明事物可靠！！<br>
 * http://localhost:8080/user/test<br>
 * <br>
 * 启动  Application 加上 @EnableTransactionManagement 注解其实可无默认貌似就开启了<br>
 * 需要事物的方法加上 @Transactional 必须的哦！！
 */
//    @Transactional(rollbackFor = Exception.class)
//    @GetMapping("/test_transactional")
//    public void testTransactional() {
//        User user = new User();
//        user.setName("22222555222222");
//        userService.save(user);
//        System.out.println(" 这里手动抛出异常，自动回滚数据");
//        throw new RuntimeException();
//    }
}

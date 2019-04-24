package com.accumulate.test.web;

import com.accumulate.test.service.RoleService;
import com.accumulate.core.util.Constants;
import com.accumulate.core.web.util.EntityUtil;
import com.accumulate.core.web.util.Result;
import com.accumulate.entity.Role;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

/**
 * @System: 车贷金融
 * @Auther: hukaijia
 * @Description: 角色信息管理
 * @Modified By:
 */
@RestController
@RequestMapping("/role")
@Api(value = "", description = "角色资源管理")
public class RoleController extends BaseController {
    private static final String ENTITY_NAME = "Role";
    private final Logger logger = LoggerFactory.getLogger(RoleController.class);
    @Autowired
    private RoleService roleService;

    @Autowired
    RestTemplate restTemplate;

    // 服务调用的负载均衡
    @GetMapping("/ribbonConsumer")
    @ApiOperation(value = "服务调用的负载均衡", notes = "服务调用的负载均衡")
    public Result ribbonConsumer() {
        return restTemplate.getForEntity("http://test-service/role/index", Result.class).getBody();
    }


    @GetMapping("/index")
    @ApiOperation(value = "带条件的分页查询", notes = "带条件的分页查询")
    public Result greeting() {
        return new Result(Result.ReturnValue.SUCCESS, "");
    }

    /**
     * @System: 车贷金融
     * @Auther: hukaijia
     * @Description: 断路器的功能
     * @Modified By:
     */
    @GetMapping("/breaker")
    @ApiOperation(value = "断路器的功能", notes = "断路器的功能")
    public Result breaker() {
        String hello = roleService.circuitBreaker();
        return new Result(Result.ReturnValue.SUCCESS, "", hello);
    }

    /**
     * @Description : 带条件的分页查询
     */
    @GetMapping(value = "/getAllRolePage")
    @ApiOperation(value = "带条件的分页查询", notes = "带条件的分页查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", dataType = "int", paramType = "query",
                    value = "页数 (0..N)"),
            @ApiImplicitParam(name = "size", dataType = "int", paramType = "query",
                    value = "每页大小."),
            @ApiImplicitParam(name = "sort", allowMultiple = true, dataType = "string", paramType = "query",
                    value = "依据什么排序: 属性名(,asc|desc). ")
    })
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Correct response", response = Role.class)})
    public Result getAllRolePage(@RequestParam(required = false) String name,
                                 @ApiIgnore Pageable pageable) {
        try {
            logger.debug("REST request to get all Role");
            Page<Role> page = roleService.findAll(name, pageable);
            return new Result(Result.ReturnValue.SUCCESS, "", page);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new Result(Result.ReturnValue.FAILURE, Constants.ERROR_MESSAGE, e.getMessage());
        }
    }


    /**
     * @Description : 查找角色通过id
     */
    @GetMapping("/getRoleById")
    @ApiOperation(value = "查找角色通过id", notes = "查找角色通过id")
    public Result getRoleById(@ApiParam(value = "角色id", required = true) @RequestParam(value = "id") String id) {
        try {
            Role role = roleService.findOne(id);
            return new Result(Result.ReturnValue.SUCCESS, "", role);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new Result(Result.ReturnValue.FAILURE, Constants.ERROR_MESSAGE, e.getMessage());
        }
    }

    /**
     * @Description : 增加角色
     */
    @PostMapping("/createRole")
    @ApiOperation(value = "增加角色", notes = "增加角色")
    public Result createRole(@Validated @ApiParam("角色对象") @RequestBody Role role
//            ,@RequestHeader(value = "X-UserToken") String token
    ) {
        try {
            role = (Role) EntityUtil.emptyValueToNull(role);
            logger.debug("REST request to save caseInfo : {}", role);
            if (role.getId() != null) {
                return new Result(Result.ReturnValue.FAILURE, Constants.ERROR_MESSAGE);
            }
            Role role1 = roleService.save(role);
            return new Result(Result.ReturnValue.SUCCESS, "", role1);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new Result(Result.ReturnValue.FAILURE, Constants.ERROR_MESSAGE, e.getMessage());
        }
    }

    /**
     * @Description : 带条件的查询
     */
    @GetMapping(value = "/roleList")
    @ApiOperation(value = "带条件的查询", notes = "带条件的查询")
    public Result roleList(@RequestParam(required = false) String name) {
        logger.debug("REST request to get all Role");
        try {
            List<Role> list = roleService.findAll(name);
            return new Result(Result.ReturnValue.SUCCESS, "", list);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new Result(Result.ReturnValue.FAILURE, Constants.ERROR_MESSAGE, e.getMessage());
        }
    }
}

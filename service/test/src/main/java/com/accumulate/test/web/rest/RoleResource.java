package com.accumulate.test.web.rest;

import com.accumulate.test.service.RoleService;
import com.accumulate.core.web.util.PaginationUtil;
import com.accumulate.entity.Role;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import javax.inject.Inject;
import java.net.URISyntaxException;
import java.util.List;

/**
 * @author chenjiao
 * @version Id:RoleResource.java, v 0.1 2017/2/27 17:51
 * @function 角色
 */
@RestController
@ApiIgnore
@RequestMapping("/api/roleResource")
@Api(value = "", description = "角色")
public class RoleResource {
    private final Logger log = LoggerFactory.getLogger(RoleResource.class);
    @Inject
    private RoleService roleService;

    /**
     * @System: 车贷金融
     * @Auther: hukaijia
     * @Description: 服务内部调用
     * @Modified By:
     */
    @GetMapping
    @ApiOperation(value = "查询所有角色", notes = "查询所有角色")
    public ResponseEntity<List<Role>> getAllRole(@ApiParam Pageable pageable)
            throws URISyntaxException {
        log.debug("REST request to get all of Role");
        Page<Role> page = roleService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/userResource");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }
}

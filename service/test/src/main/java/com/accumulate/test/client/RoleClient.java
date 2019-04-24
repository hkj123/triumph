package com.accumulate.test.client;

import com.accumulate.entity.Role;
import io.swagger.annotations.ApiParam;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * @System: 车贷金融
 * @Auther: hukaijia
 * @Description: 提供微服务内部调用角色模块
 * @Modified By:
 */
@FeignClient("bussines-service")
public interface RoleClient {
    @RequestMapping(method = RequestMethod.GET, value = "/api/userResource")
    ResponseEntity<List<Role>> getAllRole(@ApiParam Pageable pageable);
}
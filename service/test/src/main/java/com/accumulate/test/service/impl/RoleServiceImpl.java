package com.accumulate.test.service.impl;


import com.accumulate.core.service.impl.GenericManagerImpl;
import com.accumulate.core.web.util.Result;
import com.accumulate.entity.Role;
import com.accumulate.test.repository.RoleRepository;
import com.accumulate.test.service.RoleService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.inject.Inject;
import javax.persistence.criteria.Predicate;
import java.util.List;
import java.util.Objects;

import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.contains;


@Service("roleService")
public class RoleServiceImpl extends GenericManagerImpl<Role, String> implements RoleService {
    RoleRepository roleRepository;
    final Logger log = LoggerFactory.getLogger(RoleServiceImpl.class);
    @Inject
    private RoleService roleService;
    @Autowired
    RestTemplate restTemplate;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        super(roleRepository);
        this.roleRepository = roleRepository;
    }

    /**
     * @System: 车贷金融
     * @Auther: hukaijia
     * @Description: 查询所有角色 缓存示例
     * @Modified By:
     */
    @Cacheable(value = "roleCache", key = "'petstore:role:all'")
    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    /**
     * @System: 车贷金融
     * @Auther: hukaijia
     * @Description: 保存角色 缓存示例
     * @Modified By:
     */

    @CacheEvict(value = "roleCache", key = "'petstore:role:all'")
    public Role save(Role role) {
        return roleRepository.save(role);
    }

    /**
     * @System: 车贷金融
     * @Auther: hukaijia
     * @Description: 根据参数查询角色
     * @Modified By:
     */
    public Page<Role> findAll(String name, Pageable pageable) {
        //这里用于拼装条件
        Specification<Role> specification = (root, query, cb) -> {
            Predicate predicate = cb.conjunction();
            if (Objects.isNull(name)) {
                return predicate;
            }
            if (Objects.nonNull(name)) {
                predicate.getExpressions().add(
                        cb.like(root.get("name"), "%" + StringUtils.trim(name) + "%"));
            }
            return predicate;

        };
        return roleRepository.findAll(Specifications.where(specification), pageable);

    }

    /**
     * @System: 车贷金融
     * @Auther: hukaijia
     * @Description: 根据参数查询角色不分页
     * @Modified By:
     */
    public List<Role> findAll(String name) {
        ExampleMatcher matcher = ExampleMatcher.matching().withMatcher("name", contains());
        Role role = new Role();
        role.setName(name);
        Example<Role> roleExample = Example.of(role, matcher);
        return roleService.findAll(roleExample);
    }

    /**
     * @System: 车贷金融
     * @Auther: hukaijia
     * @Description: 断路器功能
     * @Modified By:
     */
    @HystrixCommand(fallbackMethod = "breaker")
    public String circuitBreaker() {
        return restTemplate.getForEntity("http://test-service/roleid/index", String.class).toString();
    }

    /**
     * @System: 车贷金融
     * @Auther: hukaijia
     * @Description: 断路器功能
     * @Modified By:
     */
    public String breaker() {
        return "error";
    }

}

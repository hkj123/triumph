package com.accumulate.test.service;

import com.accumulate.core.service.GenericManager;
import com.accumulate.entity.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author : Administrator
 * @Description :
 * @Date : 17:43 2017/7/20
 */
public interface RoleService extends GenericManager<Role, String> {

    /**
     * @System: 车贷金融
     * @Auther: hukaijia
     * @Description: 查询所有角色
     * @Modified By:
     */

    List<Role> findAll();

    /**
     * @System: 车贷金融
     * @Auther: hukaijia
     * @Description: 保存角色
     * @Modified By:
     */
    Role save(Role role);

    /**
     * @System: 车贷金融
     * @Auther: hukaijia
     * @Description: 根据参数查询角色
     * @Modified By:
     */
    Page<Role> findAll(String name, Pageable pageable);

    /**
     * @System: 车贷金融
     * @Auther: hukaijia
     * @Description: 根据参数查询角色不分页
     * @Modified By:
     */
    List<Role> findAll(String name);

    /**
     *@System: 车贷金融
     *@Auther: hukaijia
     *@Description: 断路器功能
     *@Modified By:
    */

    String circuitBreaker();
}
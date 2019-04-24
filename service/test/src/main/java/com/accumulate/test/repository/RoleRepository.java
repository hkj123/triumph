package com.accumulate.test.repository;

import com.accumulate.core.repository.GenericRepository;
import com.accumulate.entity.Role;

/**
 * @System: 车贷金融
 * @Auther: hukaijia
 * @Description:
 * @Modified By:
 */
public interface RoleRepository extends GenericRepository<Role, String> {

//    @Modifying
//    @Transactional
//    @Query(value = "delete from role where id=:roleId", nativeQuery = true)
//    int deleteResoByRoleId(@Param("roleId") String roleId);
}

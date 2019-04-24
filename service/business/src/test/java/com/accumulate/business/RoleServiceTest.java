//package com.accumulate.business;
//
//import com.accumulate.report.service.RoleService;
//import com.accumulate.entity.Role;
//import org.junit.Assert;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import javax.inject.Inject;
//import java.util.List;
//
///**
// * @System: 车贷金融
// * @Auther: hukaijia
// * @Description: 角色测试
// * @Modified By:
// */
//@RunWith(SpringRunner.class)
//@SpringBootTest()
//@ContextConfiguration(classes = BusinessApplication.class)
//public class RoleServiceTest {
//    @Inject
//    private RoleService roleService;
//
//    @Before
//    public void setUp() {
//    }
//
//    //    @Test
////    public void createRole()throws Exception{
////        Role role = new Role();
////        role.setName("催收专员");
////        role.setStatus(0);
////        role.setRemark("测试数据");
////        role.setCreator("chenjiao");
////        Role saveRole = roleService.save(role);
////        Assert.assertEquals(saveRole,role);
////    }
////    @Test
////    public void updateRole()throws Exception{
////        Role roleOne = roleService.findOne("a1ca16bb-787f-4fb2-b28d-21d67a5f5a7b");
////        roleOne.setCreator("chenjiao");
////        Role updateRole = roleService.save(roleOne);
////        Assert.assertEquals(updateRole,roleOne);
////    }
//    @Test
//    public void getRole() {
//        List<Role> roleAll = roleService.findAll();
//        Assert.assertEquals(5, roleAll.size());
//    }
//}

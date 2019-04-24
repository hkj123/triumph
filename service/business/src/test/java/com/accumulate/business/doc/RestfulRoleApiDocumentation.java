//package com.accumulate.business.doc;
//
//import com.accumulate.business.SpringRestDocApplicationTest;
//import org.junit.Test;
//import org.springframework.http.MediaType;
//import org.springframework.util.LinkedMultiValueMap;
//import org.springframework.util.MultiValueMap;
//
//import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
//import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
//import static org.springframework.restdocs.payload.PayloadDocumentation.*;
//import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
//import static org.springframework.restdocs.request.RequestDocumentation.requestParameters;
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//public class RestfulRoleApiDocumentation extends SpringRestDocApplicationTest {
//
//    @Test
//    public void RoleIndex() throws Exception {
//        this.mockMvc.perform(
//                get("/role/index").accept(MediaType.APPLICATION_JSON))
//////                .andDo(print())
//                .andExpect(status().isOk())
////                .andExpect(content().string(containsString("Hello World")))
//                .andDo(document("role"
//                ));
//    }
//
//    @Test
//    public void RoleList() throws Exception {
//        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
//        params.add("name", "超级管理员");
//        this.mockMvc.perform(
//                get("/role/roleList").params(params))
//                .andDo(print())
//                .andExpect(status().isOk())
//                .andDo(document("role-list",
//                        requestParameters(
//                                parameterWithName("name").description("角色名")),
//                        relaxedResponseFields(
//                                fieldWithPath("object").type("对象数组").description("角色列表")
//                        )
//                ));
//    }
//
//}

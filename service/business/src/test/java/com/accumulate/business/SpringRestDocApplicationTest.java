//package com.accumulate.business;
//
//import org.junit.After;
//import org.junit.Before;
//import org.junit.Rule;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.MediaType;
//import org.springframework.restdocs.JUnitRestDocumentation;
//import org.springframework.restdocs.cli.CliDocumentation;
//import org.springframework.restdocs.http.HttpDocumentation;
//import org.springframework.restdocs.payload.PayloadDocumentation;
//import org.springframework.restdocs.snippet.Snippet;
//import org.springframework.restdocs.templates.TemplateFormats;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//import org.springframework.web.context.WebApplicationContext;
//
//import static org.springframework.restdocs.hypermedia.HypermediaDocumentation.linkWithRel;
//import static org.springframework.restdocs.hypermedia.HypermediaDocumentation.links;
//import static org.springframework.restdocs.hypermedia.HypermediaDocumentation.relaxedLinks;
//import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
//import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
//import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
//import static org.springframework.restdocs.payload.PayloadDocumentation.*;
//import static org.springframework.restdocs.payload.PayloadDocumentation.subsectionWithPath;
//import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
//import static org.springframework.restdocs.request.RequestDocumentation.requestParameters;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
///**
// * @System: 车贷金融
// * @Auther: hukaijia
// * @Description: restdoc父类
// * @Modified By:
// */
//@RunWith(SpringRunner.class)
//@AutoConfigureMockMvc
//@AutoConfigureRestDocs(outputDir = "target/generated-snippets")
//@ContextConfiguration(classes = BusinessApplication.class)
//@SpringBootTest
//public class SpringRestDocApplicationTest {
//
//    //    首先需要声明一个 public 的 JUnitRestDocumentation ，并且加上 @Rule 注解，
//// JUnitRestDocumentation 会按约定自动完成配置（约定大于配置），在 Maven 下默认文档片段输出目录为：target/generated-snippets
//    @Rule
//    public JUnitRestDocumentation restDocumentation = new JUnitRestDocumentation();
//
//
//    public MockMvc mockMvc;
//
//    @Autowired
//    private WebApplicationContext context;
//
//    @Before
//    public void setUp() throws Exception {
//        //默认生成的文档片段
//        Snippet[] defaultSnippets = new Snippet[]{CliDocumentation.curlRequest(), CliDocumentation.httpieRequest(), HttpDocumentation.httpRequest(), HttpDocumentation.httpResponse(), PayloadDocumentation.requestBody(), PayloadDocumentation.responseBody()};
//        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context)
//                .apply(documentationConfiguration(this.restDocumentation)
//                                .snippets().withTemplateFormat(TemplateFormats.asciidoctor()).withEncoding("UTF-8")
//                                .withDefaults(defaultSnippets)
//                                .and()
//                                .uris().withScheme("https").withHost("hukaijia.com").withPort(443)
//                                .and()
////                        默认为 Asciidoctor ，也可以配置生成 MarkDown 格式的文档：
////                        .snippets().withTemplateFormat(TemplateFormats.markdown())
//                )
//                .build();
//    }
//}

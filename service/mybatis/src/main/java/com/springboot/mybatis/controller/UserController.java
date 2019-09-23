package com.springboot.mybatis.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.springboot.mybatis.entity.User;
import com.springboot.mybatis.service.UserService;
import com.springboot.mybatis.utils.HttpClientUtil;
import com.springboot.mybatis.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController   //注意模板 需要这个
@RequestMapping("/user")

public class UserController extends BaseController {

    @Autowired
    private UserService userService;

    @GetMapping("/findUserList")
    public Result findUserList() {
        try {
            List<User> userList = userService.findAll();
            return new Result(Result.ReturnValue.SUCCESS, "", userList);
        } catch (
                Exception e) {
            return new Result(Result.ReturnValue.FAILURE, e.getMessage());
        }
    }

    @GetMapping("/findByPage")
    public Result findByPaging(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        Page<User> userList = userService.findByPaging();
        return new Result(Result.ReturnValue.SUCCESS, "", userList);
    }

//    public static void main(String[] args) {
//        try {
//
//            BufferedReader reader1  = new BufferedReader(new InputStreamReader(Thread.currentThread().getContextClassLoader().getResourceAsStream("claims.json"),"UTF-8"));
//            StringBuilder sb1 = new StringBuilder();
//            String s1; // 依次循环，至到读的值为空
//            while ((s1 = reader1.readLine()) != null) {
//                sb1.append(s1);
//            }
//            reader1.close();
//
//            String requestObjectClaims = sb1.toString();
//
//            BufferedReader reader  = new BufferedReader(new InputStreamReader(Thread.currentThread().getContextClassLoader().getResourceAsStream("jwk.json"),"UTF-8"));
//            StringBuilder sb = new StringBuilder();
//            String s; // 依次循环，至到读的值为空
//            while ((s = reader.readLine()) != null) {
//                sb.append(s);
//            }
//            reader.close();
//
//            String jwks = sb.toString();
//
//            JWTClaimsSet claimSet = JWTClaimsSet.parse(requestObjectClaims);
//            JWKSet jwkSet = JWKSet.parse(jwks);
//            if (jwkSet.getKeys().size() == 1) {
//                // figure out which algorithm to use
//                JWK jwk = jwkSet.getKeys().iterator().next();
//
//                JWSSigner signer = null;
//                if (jwk.getKeyType().equals(KeyType.RSA)) {
//                    signer = new RSASSASigner((RSAKey) jwk);
//                } else if (jwk.getKeyType().equals(KeyType.EC)) {
//                    signer = new ECDSASigner((ECKey) jwk);
//                } else if (jwk.getKeyType().equals(KeyType.OCT)) {
//                    signer = new MACSigner((OctetSequenceKey) jwk);
//                }
//                //
//                JWSHeader header = new JWSHeader(JWSAlgorithm.parse("RS256"), JOSEObjectType.JWT, null, null, null, null, null, null, null, null, "client1-RS256", null, null);
//                SignedJWT requestObject = new SignedJWT(header, claimSet);
//                requestObject.sign(signer);
//                System.out.println("**********claims" + claimSet.toString());
//                System.out.println("**********header" + header.toString());
//                System.out.println("**********requestObject" + requestObject.serialize());
//                System.out.println("**********key" + jwk.toJSONString());
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }


    public static void main(String[] args) {

//        String url = "http://localhost:8080/auth/realms/openbanking/protocol/openid-connect/token";
//        Map<String, String> headerBody = new HashMap<>();
//        headerBody.put("Content-Type", "application/x-www-form-urlencoded");
//
//        Map<String, String> entityBody = new HashMap<>();
//        entityBody.put("grant_type", "client_credentials");
//        entityBody.put("client_id", "resource-proxy");
//        entityBody.put("client_secret", "85a564ec-2824-4b2d-94f1-aa46f29f87a4");
//
//        String httpOrgCreateTestRtn = HttpClientUtil.doPost(url, headerBody, entityBody, "utf-8");

        String url = "https://qloudpdp.pditdap.service.sd/auth/realms/openbankingrs256/protocol/openid-connect/token";
        Map<String, String> headerBody = new HashMap<>();
        headerBody.put("Content-Type", "application/x-www-form-urlencoded");

        Map<String, String> entityBody = new HashMap<>();
        entityBody.put("grant_type", "client_credentials");
        entityBody.put("client_id", "client1-mtls-RS256-PS256");
        entityBody.put("scope", "accounts");

        String httpOrgCreateTestRtn = HttpClientUtil.doPost(url, headerBody, entityBody, "utf-8");
    }
}

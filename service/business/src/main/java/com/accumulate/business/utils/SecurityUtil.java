package com.accumulate.business.utils;//package com.accumulate.services.utils;
//
//import cn.hutool.core.util.StrUtil;
//import com.accumulate.services.constant.SecurityConstant;
//import com.accumulate.services.entity.Permission;
//import com.accumulate.services.entity.Role;
//import com.accumulate.services.entity.Tenant;
//import com.accumulate.services.entity.User;
//import com.accumulate.services.service.TenantService;
//import com.accumulate.services.service.UserService;
//import com.accumulate.services.vo.TokenUser;
//import com.google.gson.Gson;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.redis.core.StringRedisTemplate;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.stereotype.Component;
//
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//import java.util.concurrent.TimeUnit;
//
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
//import org.springframework.beans.factory.annotation.Value;
//
//
///**
// * @author lilg
// */
//@Component
//public class SecurityUtil {
//
//    @Value("${drore.saveLoginTime}")
//    private Integer saveLoginTime;
//
//    @Value("${drore.tokenExpireTime}")
//    private Integer tokenExpireTime;
//
//    @Value("${drore.token.redis}")
//    private Boolean tokenRedis;
//
//
////    @Autowired
////    private UserService userService;
////
////    @Autowired
////    private TenantService tenantService;
//
//
//    @Autowired
//    private StringRedisTemplate redisTemplate;
//
//
//
//    public String getUserToken(String username, Boolean saveLogin){
//
////        // 已绑定
////        User u = userService.findByName(username);
////        List<String> authorities = new ArrayList<>();
////
////        Role role = u.getRole();
////        if (role != null){
////            List<Permission> permisions = role.getPermissions();
////            if (permisions != null){
////                for (Permission perm: permisions){
////                    authorities.add(perm.getModule());
////                }
////            }
////            authorities.add(u.getRole().getName());
////        }
////        return getToken(saveLogin,u.getName(), authorities, true);
//    }
//
//    public String getTenantToken(String username, Boolean saveLogin){
//
//        // 已绑定
//        Tenant u = tenantService.findByName(username);
//        List<String> authorities = new ArrayList<>();
//        final List<GrantedAuthority> authorityList = new ArrayList<>();
//        // tenant不区分其他权限是系统管理员,直接填充系统管理员权限
//        authorityList.add(new SimpleGrantedAuthority(SecurityConstant.SYS_ADMIN));
//
//        if (authorityList.size()>0){
//            for(GrantedAuthority ga : authorityList) {
//                authorities.add(ga.getAuthority());
//            }
//        }
//
//        return getToken(saveLogin,u.getName(), authorities,false);
//    }
//
//    private String getToken(Boolean saved, String username, List<String> list, Boolean isUser) {
//        final String token;
//        String tokenPrefix;
//        String tokenPrePrefix;
//        if (isUser){
//            tokenPrefix = SecurityConstant.USER_TOKEN;
//            tokenPrePrefix = SecurityConstant.TOKEN_PRE;
//        }else{
//            tokenPrefix = SecurityConstant.TENANT_TOKEN;
//            tokenPrePrefix = SecurityConstant.TENANT_TOKEN_PRE;
//        }
//
//        if (tokenRedis) {
//            // redis
//            token = UUID.getId().toString().replace("-", "");
//            final TokenUser user = new TokenUser(username, list, saved);
//            // 单点登录 之前的token失效
//            final String oldToken = redisTemplate.opsForValue().get(tokenPrefix+ username);
//            if (StrUtil.isNotBlank(oldToken)) {
//                redisTemplate.delete(tokenPrePrefix + oldToken);
//            }
//            if (saved) {
//                redisTemplate.opsForValue().set(tokenPrefix + username, token, saveLoginTime,
//                        TimeUnit.DAYS);
//                redisTemplate.opsForValue().set(tokenPrePrefix + token, new Gson().toJson(user),
//                        saveLoginTime, TimeUnit.DAYS);
//            } else {
//                redisTemplate.opsForValue().set(tokenPrefix + username, token, tokenExpireTime,
//                        TimeUnit.MINUTES);
//                redisTemplate.opsForValue().set(tokenPrePrefix + token, new Gson().toJson(user),
//                        tokenExpireTime, TimeUnit.MINUTES);
//            }
//        } else {
//            // jwt
//            token = SecurityConstant.TOKEN_SPLIT + Jwts.builder()
//                    // 主题 放入用户名
//                    .setSubject(username)
//                    // 自定义属性 放入用户拥有请求权限
//                    .claim(SecurityConstant.AUTHORITIES, new Gson().toJson(list))
//                    // 失效时间
//                    .setExpiration(new Date(System.currentTimeMillis() + tokenExpireTime * 60 * 1000))
//                    // 签名算法和密钥
//                    .signWith(SignatureAlgorithm.HS512, SecurityConstant.JWT_SIGN_KEY).compact();
//        }
//        return token;
//    }
//
//    /**
//     * 获取当前登录用户
//     * @return
//     */
//    public User getCurrUser(){
//
//        UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        return userService.findByName(user.getUsername());
//    }
//
//    /**
//     * 获取当前登录租户
//     * @return
//     */
//    public Tenant getCurrTenant(){
//        UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        return tenantService.findByName(user.getUsername());
//    }
//
//}

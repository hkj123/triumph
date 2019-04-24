package com.accumulate.business.constant;

/**
 * @author lilg
 */
public interface SecurityConstant {

    /**
     * token分割
     */
    String TOKEN_SPLIT = "Bearer ";

    /**
     * JWT签名加密key
     */
    String JWT_SIGN_KEY = "drore";

    /**
     * token参数头
     */
    String HEADER = "accessToken";

    /**
     * 权限参数头
     */
    String AUTHORITIES = "authorities";

    /**
     * 用户选择JWT保存时间参数头
     */
    String SAVE_LOGIN = "saveLogin";

    /**
     * 交互token前缀key
     */
    String TOKEN_PRE = "DRORE_TOKEN_PRE:";

    /**
     * 用户token前缀key 单点登录使用
     */
    String USER_TOKEN = "DRORE_USER_TOKEN:";

    /**
     * 交互token前缀key
     */
    String TENANT_TOKEN_PRE = "DRORE_TENANT_TOKEN_PRE:";

    /**
     * 用户token前缀key 单点登录使用
     */
    String TENANT_TOKEN = "DRORE_TENANT_TOKEN:";

    /**
     * 系统管理员权限
     */
    String SYS_ADMIN = "SYS_ADMIN";
}

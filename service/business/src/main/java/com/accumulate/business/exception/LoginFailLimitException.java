package com.accumulate.business.exception;

import lombok.Data;
import org.springframework.security.authentication.InternalAuthenticationServiceException;

/**
 * @author lilg
 */
@Data
public class LoginFailLimitException extends InternalAuthenticationServiceException {

    private String msg;

    public LoginFailLimitException(final String msg) {
        super(msg);
        this.msg = msg;
    }

    public LoginFailLimitException(final String msg, final Throwable t) {
        super(msg, t);
        this.msg = msg;
    }
}

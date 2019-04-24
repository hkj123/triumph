package com.accumulate.business.exception;

import lombok.Data;

/**
 * @author lilg
 */
@Data
public class SaasException extends RuntimeException {

    public SaasException(final String msg) {
        super(msg);
    }

    public SaasException(String message, Throwable cause) {
        super(message, cause);
    }
}

package com.assionx.canal.client.exception;

/**
 * @author assionx
 * @date 2023-07-29
 **/
public class CanalSycException extends RuntimeException{

    public CanalSycException() {
    }

    public CanalSycException(String message) {
        super(message);
    }

    public CanalSycException(String message, Throwable cause) {
        super(message, cause);
    }

    public CanalSycException(Throwable cause) {
        super(cause);
    }

    public CanalSycException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

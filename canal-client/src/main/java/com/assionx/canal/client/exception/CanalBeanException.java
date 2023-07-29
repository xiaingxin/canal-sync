package com.assionx.canal.client.exception;

/**
 * @author assionx
 * @date 2023-07-29
 **/
public class CanalBeanException extends CanalSycException{
    public CanalBeanException() {
    }

    public CanalBeanException(String message) {
        super(message);
    }

    public CanalBeanException(String message, Throwable cause) {
        super(message, cause);
    }

    public CanalBeanException(Throwable cause) {
        super(cause);
    }

    public CanalBeanException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

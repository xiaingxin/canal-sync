package com.assionx.canal.client.exception;

/**
 * @author assionx
 * @date 2023-07-29
 **/
public class SycIOException extends CanalSycException{
    public SycIOException() {
    }

    public SycIOException(String message) {
        super(message);
    }

    public SycIOException(String message, Throwable cause) {
        super(message, cause);
    }

    public SycIOException(Throwable cause) {
        super(cause);
    }

    public SycIOException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

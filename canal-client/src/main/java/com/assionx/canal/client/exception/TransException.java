package com.assionx.canal.client.exception;

import com.assionx.canal.client.connect.SycCanalConnector;

/**
 * @author assionx
 * @date 2023-07-29
 **/
public class TransException extends CanalSycException {
    public TransException() {
    }

    public TransException(String message) {
        super(message);
    }

    public TransException(String message, Throwable cause) {
        super(message, cause);
    }

    public TransException(Throwable cause) {
        super(cause);
    }

    public TransException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

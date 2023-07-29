package com.assionx.canal.client.exception;

import com.alibaba.otter.canal.protocol.exception.CanalClientException;

/**
 * @author xiaingxin
 * @date 2023/7/11-12:08
 **/
public interface ExceptionHandler {

    /**
     * 错误处理
     * @param e
     */
    void sycIoException(SycIOException e);


    void lockExceptionHandler(LockException e);

    void canalClientExceptionHandler(CanalClientException e);


    void exceptionHandler(Exception e);

}

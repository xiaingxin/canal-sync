package com.assionx.canal.client.exception;



import com.alibaba.otter.canal.protocol.exception.CanalClientException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author xiaingxin
 * @date 2023/7/11-12:09
 **/

public class DefaultExceptionHandler implements ExceptionHandler {

    Logger log= LoggerFactory.getLogger(DefaultExceptionHandler.class);

    @Override
    public void sycIoException(SycIOException e) {
        log.info("Canal连接异常");
    }

    @Override
    public void lockExceptionHandler(LockException e) {
        log.info("{}",e);
    }

    @Override
    public void canalClientExceptionHandler(CanalClientException e) {

        log.info("{}",e);
    }

    @Override
    public void exceptionHandler(Exception e) {
        log.info("Canal连接异常");
        log.info("{}",e);
    }
}

package com.assionx.canal.client.client;


import com.alibaba.otter.canal.client.CanalConnector;
import com.alibaba.otter.canal.protocol.Message;
import com.alibaba.otter.canal.protocol.exception.CanalClientException;
import com.assionx.canal.client.config.AbstractCanalLock;
import com.assionx.canal.client.config.CanalLock;
import com.assionx.canal.client.exception.DefaultExceptionHandler;
import com.assionx.canal.client.exception.ExceptionHandler;
import com.assionx.canal.client.exception.LockException;
import com.assionx.canal.client.handler.MessageHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.net.ConnectException;
import java.util.concurrent.TimeUnit;

/**
 * @author xiangxin
 * @date
 */

public abstract class AbstractCanalClient implements CanalClient {

    Logger log= LoggerFactory.getLogger(AbstractCanalClient.class);

    protected volatile boolean flag;
    protected String lockId;
    protected TimeUnit unit;
    protected Long timeout;
    protected Thread workThread;
    protected String threadName;
    protected CanalConnector connector;
    protected String filter;
    protected Integer batchSize;
    protected Long sleepTime;
    protected long readSpeed;
    private MessageHandler messageHandler;
    protected ExceptionHandler exceptionHandler;
    private CanalLock canalLock;

    @Override
    public void start() {
        log.info("start canal client ");
        this.workThread = new Thread(this::process);
        this.workThread.setName(threadName);
        this.flag = true;
        this.unit=TimeUnit.SECONDS;
        this.workThread.start();

    }
    public AbstractCanalClient(){
          this.exceptionHandler=new DefaultExceptionHandler();
    }

    /**
     * 停止线程
     */
    @Override
    public void stop() {
        log.debug("stop canal client");
        this.flag = false;

        if (null != this.workThread) {
            this.workThread.interrupt();
        }

    }

    @Override
    public void process() {
        long batchId = 0;
        while (this.flag) {
            try {
                this.connector.connect();
                this.connector.subscribe(this.filter);

                while (this.flag && canalLock.lock(lockId, batchId)) {

                    Message message = connector.getWithoutAck(batchSize);
                    //获取批量ID
                    batchId = message.getId();
//                    System.out.println(message);
                    //获取批量的数量
                    int size = message.getEntries().size();
                    //如果没有数据
                    if (batchId == -1 || size == 0) {
                        sleep(sleepTime);
                    } else {
                        //如果有数据,处理数据
                        sleep(readSpeed);
                        messageHandler.handleMessage(message.getEntries());
                    }

                    //进行 batch id 的确认。确认之后，小于等于此 batchId 的 Message 都会被确认。
                    connector.ack(batchId);
                    canalLock.unlock(lockId);
                    sleep(sleepTime);
                }

            }catch (CanalClientException var7) {
                exceptionHandler.canalClientExceptionHandler(var7);
            }catch (LockException var8){
                var8.printStackTrace();
                exceptionHandler.lockExceptionHandler(var8);
            }catch (Exception var9){

                exceptionHandler.exceptionHandler(var9);
            }finally {
                sleep(sleepTime);
                this.connector.disconnect();
            }
        }

    }

    @Override
    public void sleep(long million) {
        try {
            //线程休眠2秒
            Thread.sleep(million);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public String getLockId() {
        return lockId;
    }

    public void setLockId(String lockId) {
        this.lockId = lockId;
    }

    public TimeUnit getUnit() {
        return unit;
    }

    public void setUnit(TimeUnit unit) {
        this.unit = unit;
    }

    public Long getTimeout() {
        return timeout;
    }

    public void setTimeout(Long timeout) {
        this.timeout = timeout;
    }

    public Thread getWorkThread() {
        return workThread;
    }

    public void setWorkThread(Thread workThread) {
        this.workThread = workThread;
    }

    public String getThreadName() {
        return threadName;
    }

    public void setThreadName(String threadName) {
        this.threadName = threadName;
    }

    public CanalConnector getConnector() {
        return connector;
    }

    public void setConnector(CanalConnector connector) {
        this.connector = connector;
    }

    public String getFilter() {
        return filter;
    }

    public void setFilter(String filter) {
        this.filter = filter;
    }

    public Integer getBatchSize() {
        return batchSize;
    }

    public void setBatchSize(Integer batchSize) {
        this.batchSize = batchSize;
    }

    public Long getSleepTime() {
        return sleepTime;
    }

    public void setSleepTime(Long sleepTime) {
        this.sleepTime = sleepTime;
    }

    public long getReadSpeed() {
        return readSpeed;
    }

    public void setReadSpeed(long readSpeed) {
        this.readSpeed = readSpeed;
    }

    public MessageHandler getMessageHandler() {
        return messageHandler;
    }

    public void setMessageHandler(MessageHandler messageHandler) {
        this.messageHandler = messageHandler;
    }

    public ExceptionHandler getExceptionHandler() {
        return exceptionHandler;
    }

    public void setExceptionHandler(ExceptionHandler exceptionHandler) {
        this.exceptionHandler = exceptionHandler;
    }

    public CanalLock getCanalLock() {
        return canalLock;
    }

    public void setCanalLock(CanalLock canalLock) {
        this.canalLock = canalLock;
    }
}
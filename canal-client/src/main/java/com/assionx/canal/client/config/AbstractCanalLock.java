package com.assionx.canal.client.config;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author xiaingxin
 * @date 2023/7/13-11:07
 **/

public abstract class AbstractCanalLock implements CanalLock{

    Logger log= LoggerFactory.getLogger(AbstractCanalLock.class);

    protected String nodeId;

    public AbstractCanalLock(String nodeId){

        this.nodeId=nodeId;

        log.info("nodeId:{}",nodeId);
        log.info("canal lock is inited....");
    }

    /**
     *  设置LockId
     * @param nodeId
     */
    public abstract void setLockId(String nodeId);

}

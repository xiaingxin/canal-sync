package com.assionx.canal.client.config;

/**
 * @author xiaingxin
 * @date 2023/7/11-19:48
 **/
public class LocalCanalLock extends AbstractCanalLock{




    /**
     * 是否上锁
     */
    private volatile static boolean isLock=false;

    public LocalCanalLock(String lockId) {
        super(lockId);
    }

    @Override
    public void setLockId(String nodeId) {
        this.nodeId=nodeId;
    }


    @Override
    public boolean lock(String lockId,long batchId) {
       return true;

    }

    @Override
    public boolean unlock(String lockId) {
       return true;
    }

    @Override
    public boolean isLocked(String lockId) {
       return isLock;
    }



}

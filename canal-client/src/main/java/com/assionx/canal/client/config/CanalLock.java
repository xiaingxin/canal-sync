package com.assionx.canal.client.config;


import com.assionx.canal.client.exception.LockException;

/**
 * @author xiaingxin
 * @date 2023/7/11-19:39
 **/
public interface CanalLock {


    /**
     * 锁
     * @param lockId
     * @param batchId
     * @throws LockException
     * @return
     */
     boolean lock(String lockId,long batchId) throws LockException;


    /**
     * 解锁
     * @param lockId
     * @throws LockException
     * @return
     */
     boolean unlock(String lockId) ;


    /**
     * 是否被锁
     * @return
     * @param lockedId
     * @throws LockException
     */
     default boolean isLocked(String lockedId){
         return false;
     }



}

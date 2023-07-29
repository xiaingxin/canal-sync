package com.assionx.canal.client.factory;


import com.assionx.canal.client.bean.CanalBean;


import java.util.Set;

/**
 * @author xiaingxin
 * @date 2023/7/11-17:42
 **/
public interface ICanalBeanFactory<T> {

    /**
     * 获取实例
     * @param entryHandler
     * @param t
     * @param <R>
     * @return
     * @throws Exception
     */
    <R> R newInstance(CanalBean canalBean, T t) throws Exception;



}

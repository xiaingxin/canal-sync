package com.assionx.canal.client.row;

import com.alibaba.otter.canal.protocol.CanalEntry.EventType;
import com.assionx.canal.client.bean.CanalBean;


/**
 * @author xiaingxin
 * @date 2023/7/11-18:01
 **/
public interface RowDataHandler<T> {



    /**
     * 行数据处理
     * @param t
     * @param entryHandler
     * @param eventType
     * @param <R>
     * @throws Exception
     */
    <R> void handlerRowData(T t, CanalBean<R> entryHandler, EventType eventType) throws Exception;
}

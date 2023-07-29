package com.assionx.canal.client.row;

import com.alibaba.otter.canal.protocol.CanalEntry;
import com.assionx.canal.client.bean.CanalBean;

/**
 * @author assionx
 * @date 2023-07-29
 **/
public class SycRowDataHandler implements RowDataHandler<CanalEntry.RowData>{
    @Override
    public <R> void handlerRowData(CanalEntry.RowData rowData, CanalBean<R> entryHandler,
                                   CanalEntry.EventType eventType) throws Exception {

        if(entryHandler==null){
            return;
        }
        switch (eventType){
            case INSERT:

        }
    }
}

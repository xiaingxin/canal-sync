package com.assionx.canal.client.handler;

import com.alibaba.otter.canal.protocol.CanalEntry;
import com.assionx.canal.client.bean.CanalBean;
import com.assionx.canal.client.context.CanalBeanContext;
import com.assionx.canal.client.row.RowDataHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * @author assionx
 * @date 2023-07-29
 **/

public class SycMessageHandler implements MessageHandler{

    Logger logger= LoggerFactory.getLogger(SycMessageHandler.class);

    private CanalBeanContext context;


    @Override
    public void handleMessage(List<CanalEntry.Entry> list) {
        for (CanalEntry.Entry entry : list) {
            if (entry.getEntryType() == CanalEntry.EntryType.TRANSACTIONBEGIN ||
                    entry.getEntryType() == CanalEntry.EntryType.TRANSACTIONEND) {
                //开启/关闭事务的实体类型，跳过
                continue;
            }
            if (entry.getEntryType().equals(CanalEntry.EntryType.ROWDATA)) {
                try {
                    String tableName = entry.getHeader().getTableName();
                    CanalBean<?> canalBean = context.getCanalBean(tableName);
                    if(canalBean!=null){
                        CanalEntry.RowChange rowChange = CanalEntry.RowChange.parseFrom(entry.getStoreValue());
                        List<CanalEntry.RowData> rowDataList = rowChange.getRowDatasList();
                        CanalEntry.EventType eventType = rowChange.getEventType();
                        switch (eventType){
                            case INSERT:
                        }
                    }
                } catch (Exception e) {
                    throw new RuntimeException("parse event has an error , data:" + entry.toString(), e);
                }finally {

                }

            }
        }
    }
}

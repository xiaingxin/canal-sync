package com.assionx.canal.client.handler;

import com.alibaba.otter.canal.protocol.CanalEntry;
import com.assionx.canal.client.bean.CanalBean;

import com.assionx.canal.client.context.CanalBeanContext;
import com.assionx.canal.client.factory.CanalBeanFactory;
import com.assionx.canal.client.factory.ICanalBeanFactory;
import com.assionx.canal.client.row.DefaultRowDataHandler;
import com.assionx.canal.client.row.RowDataHandler;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 * canal官方写法
 * @author xiaingxin
 * @date 2023/7/11-16:49
 **/
@Service
public class CustomMessageHandler implements MessageHandler{



    private RowDataHandler rowDataHandler=new DefaultRowDataHandler();
    private CanalBeanContext context= CanalBeanContext.getInstance();
    private ICanalBeanFactory factory=new CanalBeanFactory();

    @Override
    public void handleMessage(List<CanalEntry.Entry> list) {
        for (CanalEntry.Entry entry : list) {
            if (entry.getEntryType() == CanalEntry.EntryType.TRANSACTIONBEGIN || entry.getEntryType() == CanalEntry.EntryType.TRANSACTIONEND) {
                //开启/关闭事务的实体类型，跳过
                continue;
            }
            if (entry.getEntryType().equals(CanalEntry.EntryType.ROWDATA)) {
                try {
                    String tableName = entry.getHeader().getTableName();
                    CanalBean<?> entryHandler = context.getCanalBean(tableName);
                    if(entryHandler!=null){
                        CanalEntry.RowChange rowChange = CanalEntry.RowChange.parseFrom(entry.getStoreValue());
                        List<CanalEntry.RowData> rowDataList = rowChange.getRowDatasList();
                        CanalEntry.EventType eventType = rowChange.getEventType();
                        for (CanalEntry.RowData rowData : rowDataList) {
                            rowDataHandler.handlerRowData(rowData,entryHandler,eventType);
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

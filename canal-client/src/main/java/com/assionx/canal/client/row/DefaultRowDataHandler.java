package com.assionx.canal.client.row;

import com.alibaba.otter.canal.protocol.CanalEntry;
import com.assionx.canal.client.bean.CanalBean;
import com.assionx.canal.client.factory.CanalBeanFactory;
import com.assionx.canal.client.factory.ICanalBeanFactory;


import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author xiaingxin
 * @date 2023/7/11-20:35
 **/
public class DefaultRowDataHandler implements RowDataHandler<CanalEntry.RowData>{


    private ICanalBeanFactory<List<CanalEntry.Column>> canalBeanFactory;

    public DefaultRowDataHandler(){
        this.canalBeanFactory= new CanalBeanFactory();
    }


    @Override
    public <R> void handlerRowData(CanalEntry.RowData rowData, CanalBean<R> entryHandler, CanalEntry.EventType eventType) throws Exception {
        if (entryHandler != null) {
            switch (eventType) {
                case INSERT:
                    R object = canalBeanFactory.newInstance(entryHandler, rowData.getAfterColumnsList());
                    entryHandler.insert(object);
                    break;
                case UPDATE:
                    Set<String> updateColumnSet = rowData.getAfterColumnsList().stream().filter(CanalEntry.Column::getUpdated)
                            .map(CanalEntry.Column::getName).collect(Collectors.toSet());
                    R before = canalBeanFactory.newInstance(entryHandler, rowData.getBeforeColumnsList());
                    R after = canalBeanFactory.newInstance(entryHandler, rowData.getAfterColumnsList());
                    entryHandler.update(before, after);
                    break;
                case DELETE:
                    R o = canalBeanFactory.newInstance(entryHandler, rowData.getBeforeColumnsList());
                    entryHandler.delete(o);
                    break;
                default:
                    break;
            }
        }
    }

}

package com.assionx.canal.client.factory;

import com.alibaba.otter.canal.protocol.CanalEntry;
import com.assionx.canal.client.bean.CanalBean;
import com.assionx.canal.client.context.CanalBeanContext;

import com.assionx.canal.client.enums.TableNameEnum;
import com.assionx.canal.client.utils.CanalBeanUtils;
import com.assionx.canal.client.utils.FieldUtil;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author xiaingxin
 * @date 2023/7/12-10:13
 **/
public class CanalBeanFactory extends AbstractCanalBeanFactory<List<CanalEntry.Column>>{


    protected CanalBeanContext context=CanalBeanContext.getInstance();

    @Override
    <R> R newInstance(Class<R> c,
                      List<CanalEntry.Column> columns) throws Exception {
        R object = c.newInstance();
        Map<String, String> columnNames = CanalBeanUtils.getFieldName(object.getClass());
        for (CanalEntry.Column column : columns) {
            String fieldName = columnNames.get(column.getName());
            if (StringUtils.isNotEmpty(fieldName)) {
                FieldUtil.setFieldValue(object, fieldName, column.getValue());
            }
        }
        return object;
    }

    @Override
    public <R> R newInstance(CanalBean canalBean,
                             List<CanalEntry.Column> columns) throws Exception {
        String canalTableName = context.getTableNameByCanalBean(canalBean);
        if (TableNameEnum.ALL.name().toLowerCase().equals(canalTableName)) {
            Map<String, String> map = columns.stream().collect(Collectors.toMap(CanalEntry.Column::getName, CanalEntry.Column::getValue));
            return (R) map;
        }
        Class<R> tableClass = CanalBeanUtils.getTableClass(canalBean);
        if (tableClass != null) {
            return newInstance(tableClass, columns);
        }
        return null;
    }

}

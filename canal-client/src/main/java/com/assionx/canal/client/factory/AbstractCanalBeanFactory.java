package com.assionx.canal.client.factory;


import com.assionx.canal.client.bean.CanalBean;
import com.assionx.canal.client.context.CanalBeanContext;
import com.assionx.canal.client.enums.TableNameEnum;
import com.assionx.canal.client.utils.CanalBeanUtils;

/**
 * @author xiaingxin
 * @date 2023/7/12-9:39
 **/
public abstract class AbstractCanalBeanFactory<T>  implements ICanalBeanFactory<T>{

    protected CanalBeanContext context;


    @Override
    public <R> R newInstance(CanalBean canalBean, T t) throws Exception {
        String canalTableName = context.getTableNameByCanalBean(canalBean);
        if (TableNameEnum.ALL.name().toLowerCase().equals(canalTableName)) {
            return (R) t;
        }
        Class<R> tableClass = CanalBeanUtils.getTableClass(canalBean);
        if (tableClass != null) {
            return newInstance(tableClass, t);
        }
        return null;
    }

    /**
     * 新实例
     * @param c
     * @param t
     * @param <R>
     * @return
     * @throws Exception
     */
    abstract <R> R newInstance(Class<R> c, T t) throws Exception;
}

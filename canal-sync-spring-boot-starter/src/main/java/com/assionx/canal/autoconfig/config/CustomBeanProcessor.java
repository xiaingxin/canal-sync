package com.assionx.canal.autoconfig.config;

import com.assionx.canal.client.annotation.CanalBeanEntity;
import com.assionx.canal.client.bean.CanalBean;
import com.assionx.canal.client.context.CanalBeanContext;
import io.netty.util.internal.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 * @author assionx
 * @date 2023-07-29
 **/
public class CustomBeanProcessor implements BeanPostProcessor {


    Logger logger = LoggerFactory.getLogger(CustomBeanProcessor.class);

    private CanalBeanContext context=CanalBeanContext.getInstance();

    @Override
    public Object postProcessBeforeInitialization(Object canalBean, String s) throws BeansException {
        if(canalBean.getClass().isAnnotationPresent(CanalBeanEntity.class)){
            CanalBeanEntity annotation = canalBean.getClass().getAnnotation(CanalBeanEntity.class);
            String tableName = annotation.value();
            if(!StringUtil.isNullOrEmpty(tableName)&&tableName!=""){
                context.addCanal((CanalBean) canalBean,tableName.toLowerCase());
            }
            else{
                logger.warn("{}:未配置表名",canalBean.getClass().getName());
            }
        }
        return null;
    }

    @Override
    public Object postProcessAfterInitialization(Object o, String s) throws BeansException {

        return null;
    }
}

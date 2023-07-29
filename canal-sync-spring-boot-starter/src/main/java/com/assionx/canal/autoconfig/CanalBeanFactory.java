package com.assionx.canal.autoconfig;

import com.assionx.canal.autoconfig.config.CustomBeanProcessor;
import org.springframework.beans.BeanInfoFactory;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Bean;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;

/**
 * @author assionx
 * @date 2023-07-29
 **/
public class CanalBeanFactory  {


    @Bean
    public BeanPostProcessor customBeanProcessor(){
        return new CustomBeanProcessor();
    }
}

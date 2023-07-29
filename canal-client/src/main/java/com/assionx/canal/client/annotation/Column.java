package com.assionx.canal.client.annotation;

import com.assionx.canal.client.bean.CanalBeanField;
import com.assionx.canal.client.bean.ConventionCanalBeanField;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author xiangxin
 */
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface  Column {
    String name() default "";

    Class<? extends CanalBeanField> type() default ConventionCanalBeanField.class;

}
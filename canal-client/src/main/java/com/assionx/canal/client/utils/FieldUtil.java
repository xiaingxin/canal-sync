package com.assionx.canal.client.utils;

import com.assionx.canal.client.annotation.Column;
import com.assionx.canal.client.bean.CanalBeanField;
import com.assionx.canal.client.bean.ConventionCanalBeanField;

import java.lang.reflect.Field;

/**
 * @author xiaingxin
 * @date 2023-7-12
 */
public class FieldUtil {

   private static   ConventionCanalBeanField conventionCanalBeanField=new ConventionCanalBeanField();

    public static void setFieldValue(Object object,String fieldName,String value) throws NoSuchFieldException, IllegalAccessException, InstantiationException {
        Field field;
        try{
            field = object.getClass().getDeclaredField(fieldName);
        }catch (NoSuchFieldException e){
            field = object.getClass().getSuperclass().getDeclaredField(fieldName);
        }
        field.setAccessible(true);
        if(field.isAnnotationPresent(Column.class)){
            Column fieldAnnotation = field.getAnnotation(Column.class);
            Class<? extends CanalBeanField> type = fieldAnnotation.type();
            CanalBeanField canalBeanField = type.newInstance();
            Object result = canalBeanField.convertType(field.getType(), value);
            field.set(object,result);
        }else{
            Class<?> type = field.getType();
            Object result = conventionCanalBeanField.convertType(type, value);
            field.set(object,result);
        }
    }



}

package com.assionx.canal.client.utils;



import com.assionx.canal.client.annotation.Column;
import com.assionx.canal.client.bean.CanalBean;
import org.apache.commons.lang3.reflect.FieldUtils;

import java.beans.Transient;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * @author xiaingxin
 * @date 2023/7/11-18:12
 **/
public class CanalBeanUtils {

    private static Map<Class, Map<String, String>> cache = new ConcurrentHashMap<>();



    private static Map<Class<? extends CanalBean>, Class> cached = new ConcurrentHashMap<>();

    /**
     * 获取字段名称和实体属性的对应关系
     *
     * @param c class
     * @return map
     */
    public static Map<String, String> getFieldName(Class c) {
        Map<String, String> map = cache.get(c);
        if (map == null) {
            List<Field> fields = FieldUtils.getAllFieldsList(c);
            //如果实体类中存在column 注解，则使用column注解的名称为字段名
            map = fields.stream().filter(CanalBeanUtils::notTransient)
                    .filter(field -> !Modifier.isStatic(field.getModifiers()))
                    .collect(Collectors.toMap(CanalBeanUtils::getColumnName, Field::getName));
            cache.putIfAbsent(c, map);
        }
        return map;
    }


    private static String getColumnName(Field field) {
        Column annotation = field.getAnnotation(Column.class);
        if (annotation != null) {
            return annotation.name();
        } else {
            return field.getName();
        }
    }


    private static boolean notTransient(Field field) {
        Transient annotation = field.getAnnotation(Transient.class);
        return annotation == null;
    }


    public static <T> Class<T> getTableClass(CanalBean object) {
        Class<? extends CanalBean> handlerClass = object.getClass();
        Class tableClass = cached.get(handlerClass);
        if (tableClass == null) {
            Type[] interfacesTypes = handlerClass.getGenericInterfaces();
            for (Type t : interfacesTypes) {
                Class c = (Class) ((ParameterizedType) t).getRawType();
                if (c.equals(CanalBean.class)) {
                    tableClass = (Class<T>) ((ParameterizedType) t).getActualTypeArguments()[0];
                    cached.putIfAbsent(handlerClass, tableClass);
                    return tableClass;
                }
            }
        }
        return tableClass;
    }
}

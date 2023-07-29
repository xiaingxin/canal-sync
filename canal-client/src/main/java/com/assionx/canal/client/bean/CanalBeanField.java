package com.assionx.canal.client.bean;

import com.assionx.canal.client.exception.TransException;

/**
 * <em>常规的CanalBean</em>
 * @author assionx
 * @date 2023-07-29
 **/
public interface CanalBeanField {

     /**
      * Canal Bean字段
      * @param type
      * @param value
      * @return
      */
     Object convertType(Class<?> type, String value) throws TransException;

}

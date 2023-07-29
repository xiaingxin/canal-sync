package com.assionx.canal.client.context;



import com.assionx.canal.client.annotation.CanalBeanEntity;


import com.assionx.canal.client.bean.CanalBean;
import io.netty.util.internal.StringUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author xiaingxin
 * @date 2023/7/11-18:14
 **/
@Service
public class CanalBeanContext {


    Logger log= LoggerFactory.getLogger(CanalBeanContext.class);

    private  static Map<String,CanalBean> canalBeans=new ConcurrentHashMap<>(4);

    private static CanalBeanContext canalBeanContext;

    public void  addCanal(CanalBean canalBean,String tableName){
        log.info("canal context  add table:{}",tableName);
        canalBeans.put(tableName,canalBean);
    }


    public CanalBean getCanalBean(String tableName){
        return canalBeans.get(tableName.toLowerCase());
    }


    public  void  remove(String tableName){
          canalBeans.remove(tableName.toLowerCase());
    }

    public  Map<String, CanalBean> getCanalBeans(){
        return canalBeans;
    }


    public String getTableNameByCanalBean(CanalBean canalBean) {
        for(String key: canalBeans.keySet()){
            com.assionx.canal.client.bean.CanalBean canal = canalBeans.get(key);
            if(canal.equals(canalBean)){
                return key;
            }
        }
        return null;

    }

    public static CanalBeanContext getInstance(){
        if(canalBeanContext==null){
            canalBeanContext=new CanalBeanContext();
        }
        return canalBeanContext;
    }
}

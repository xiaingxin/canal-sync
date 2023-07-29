package com.assionx.canal.client.connect;

import java.net.SocketAddress;
import java.util.List;

import com.alibaba.otter.canal.client.CanalConnector;
import com.alibaba.otter.canal.client.impl.ClusterCanalConnector;
import com.alibaba.otter.canal.client.impl.ClusterNodeAccessStrategy;
import com.alibaba.otter.canal.client.impl.SimpleCanalConnector;
import com.alibaba.otter.canal.client.impl.SimpleNodeAccessStrategy;
import com.alibaba.otter.canal.common.zookeeper.ZkClientx;
import com.assionx.canal.client.exception.DefaultExceptionHandler;
import com.assionx.canal.client.exception.ExceptionHandler;

/**
 * canal connectors创建工具类
 * 
 * @author jianghang 2012-10-29 下午11:18:50
 * @version 1.0.0
 */
public class CanalConnectors {


    /**
     * 创建单链接的客户端链接
     *
     * @param address
     * @param destination
     * @param username
     * @param password
     * @return
     */

    private static CanalConnectors canalConnectors;

    public  CanalConnector newSingleConnector(SocketAddress address, String destination, String username,
                                                    String password,ExceptionHandler exceptionHandler) {
        SycCanalConnector canalConnector = new SycCanalConnector(address, username, password, destination);
        canalConnector.setSoTimeout(60 * 1000);
        canalConnector.setIdleTimeout(60 * 60 * 1000);
        canalConnector.setExceptionHandler(exceptionHandler);
        return canalConnector;
    }

    public static  CanalConnectors getInstance(){
        if(canalConnectors==null){
            canalConnectors=new CanalConnectors();
        }
        return canalConnectors;
    }





}

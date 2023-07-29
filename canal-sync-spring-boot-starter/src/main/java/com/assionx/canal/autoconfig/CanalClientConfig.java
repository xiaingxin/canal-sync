package com.assionx.canal.autoconfig;



import com.assionx.canal.autoconfig.config.CanalSettings;
import com.assionx.canal.client.bean.CanalBean;
import com.assionx.canal.client.client.CanalClient;
import com.assionx.canal.client.client.EsCanalClient;
import com.assionx.canal.client.config.CanalLock;
import com.assionx.canal.client.config.LocalCanalLock;
import com.assionx.canal.client.context.CanalBeanContext;
import com.assionx.canal.client.exception.DefaultExceptionHandler;
import com.assionx.canal.client.exception.ExceptionHandler;
import com.assionx.canal.client.handler.CustomMessageHandler;
import com.assionx.canal.client.handler.ShowMessageHandler;
import com.assionx.canal.client.handler.MessageHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.UUID;

/**
 * @author xiaingxin
 * @date 2023/7/12-11:17
 * 配置
 **/

public class CanalClientConfig {

    Logger logger= LoggerFactory.getLogger(CanalClientConfig.class);

    @Bean
    public CanalLock canalLock(){
        return new LocalCanalLock(UUID.randomUUID().toString());
    }


    @Bean
    public ExceptionHandler exceptionHandler(){
        return new DefaultExceptionHandler();
    }


    @Bean
    public MessageHandler messageHandler(){
        return new CustomMessageHandler();
    }

    @Bean
    @ConfigurationProperties(prefix = "canal.client")
    public CanalSettings canalSettings(){
        return new CanalSettings();
    }

    @Bean(initMethod = "start", destroyMethod = "stop")
    public CanalClient canalClient(MessageHandler messageHandler,
                                   CanalLock canalLock,
                                   CanalSettings canalSettings,
                                   ExceptionHandler exceptionHandler){
        EsCanalClient esCanalClient= EsCanalClient.Builder.builder()
                .batchSize(1)
                .sleep(1000L)
                .destination(canalSettings.getDestination())
                .hostName(canalSettings.getHost())
                .port(Integer.parseInt(canalSettings.getPort()))
                .userName(canalSettings.getUsername())
                .canalLock(canalLock)
                .lockId(canalSettings.getLockId())
                .password(canalSettings.getPassword())
                .filter(canalSettings.getFilter())
                .exceptionHandler(exceptionHandler)
                .messageHandler(messageHandler)
                .build();

        return esCanalClient;

    }
}

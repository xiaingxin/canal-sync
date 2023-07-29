package com.assionx.canal.client.client;




import com.alibaba.otter.canal.client.CanalConnector;
import com.assionx.canal.client.config.CanalLock;
import com.assionx.canal.client.connect.CanalConnectors;
import com.assionx.canal.client.connect.SycCanalConnector;
import com.assionx.canal.client.exception.ExceptionHandler;
import com.assionx.canal.client.handler.MessageHandler;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetSocketAddress;
import java.util.concurrent.TimeUnit;

/**
 * @author xiaingxin
 * @date 2023/7/11-12:17
 **/

public class EsCanalClient extends AbstractCanalClient{

    Logger logger= LoggerFactory.getLogger(EsCanalClient.class);
    public static final class Builder {
        Logger logger= LoggerFactory.getLogger(EsCanalClient.class);
        private Long sleep;
        private String lockId;
        private String filter = StringUtils.EMPTY;
        private Integer batchSize = 1;
        private Long timeout = 1L;
        private TimeUnit unit = TimeUnit.SECONDS;
        private String destination;
        private String hostname;
        private Integer port;
        private String userName;
        private String password;
        private MessageHandler messageHandler;
        private CanalLock canalLock;
        private ExceptionHandler exceptionHandler;


        private Builder() {
        }

        public static Builder builder() {
            return new Builder();
        }

        public Builder exceptionHandler(ExceptionHandler exceptionHandler) {
            this.exceptionHandler=exceptionHandler;
            return this;
        }

        public Builder lockId(String lockId) {
            this.lockId=lockId;
            return this;
        }

        public Builder canalLock(CanalLock canalLock) {
            this.canalLock=canalLock;
            return this;
        }
        public Builder destination(String destination) {
            this.destination = destination;
            return this;
        }
        public Builder port(Integer port) {
            this.port=port;
            return this;
        }
        public Builder sleep(Long sleep) {
            this.sleep=sleep;
            return this;
        }
        public Builder hostName(String hostname) {
            this.hostname=hostname;
            return this;
        }


        public Builder userName(String userName) {
            this.userName = userName;
            return this;
        }

        public Builder password(String password) {
            this.password = password;
            return this;
        }

        public Builder filter(String filter) {
            this.filter = filter;
            return this;
        }

        public Builder batchSize(Integer batchSize) {
            this.batchSize = batchSize;
            return this;
        }

        public Builder timeout(Long timeout) {
            this.timeout = timeout;
            return this;
        }

        public Builder unit(TimeUnit unit) {
            this.unit = unit;
            return this;
        }

        public Builder messageHandler(MessageHandler messageHandler) {
            this.messageHandler = messageHandler;
            return this;
        }

        public EsCanalClient build() {
            CanalConnector connector = CanalConnectors.getInstance().newSingleConnector(
                    new InetSocketAddress(hostname,port), destination, userName, password,this.exceptionHandler);
            EsCanalClient esCanalClient =new  EsCanalClient();
            esCanalClient.setMessageHandler(messageHandler);
            esCanalClient.setConnector(connector);
            esCanalClient.filter = this.filter;
            esCanalClient.unit = this.unit;
            esCanalClient.batchSize = this.batchSize;
            esCanalClient.timeout = this.timeout;
            esCanalClient.threadName="es-canal-client";
            esCanalClient.sleepTime=500L;
            esCanalClient.sleepTime=this.sleep;
            esCanalClient.lockId=this.lockId;
            esCanalClient.setCanalLock(this.canalLock);
            esCanalClient.setBatchSize(1);
            esCanalClient.readSpeed=500L;
            esCanalClient.exceptionHandler=this.exceptionHandler;
            logger.info("es canal is inited...");
            return esCanalClient;
        }
    }



}

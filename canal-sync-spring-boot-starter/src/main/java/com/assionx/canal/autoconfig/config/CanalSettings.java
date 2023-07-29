package com.assionx.canal.autoconfig.config;


import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author assionx
 * @date 2023-07-28
 **/
@Configuration
@EnableConfigurationProperties(CanalSettings.class)
public class CanalSettings {

    private Long sleep=500L;

    private String destination="example";

    private String host="127.0.0.1";

    private String port="11111";

    private String lockId="knowledge-base-service";

    private String username="";

    private String password="";

    private String filter=".*\\..*";


    public Long getSleep() {
        return sleep;
    }

    public void setSleep(Long sleep) {
        this.sleep = sleep;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getLockId() {
        return lockId;
    }

    public void setLockId(String lockId) {
        this.lockId = lockId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFilter() {
        return filter;
    }

    public void setFilter(String filter) {
        this.filter = filter;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}

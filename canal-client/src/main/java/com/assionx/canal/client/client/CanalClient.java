package com.assionx.canal.client.client;

/**
 * @author xiangxin
 */
public interface CanalClient {
    /**
     * 项目启动
     */
    void start();

    /**
     * 项目停止
     */
    void stop();

    /**
     * 客户端运行
     */
    void process();

    /**
     * 休眠
     * @param million
     */
    void sleep(long million);
}

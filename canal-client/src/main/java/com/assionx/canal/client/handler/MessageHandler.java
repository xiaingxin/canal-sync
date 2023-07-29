package com.assionx.canal.client.handler;

import com.alibaba.otter.canal.protocol.CanalEntry.Entry;

import java.util.List;

/**
 * @author xiaingxin
 * @date 2023/7/11-11:04
 **/
public interface MessageHandler {
      /**
       * 消息处理器
       * @param list
       */
      void  handleMessage(List<Entry> list);
}

package com.assionx.canal.client.transaction;

import com.alibaba.otter.canal.protocol.CanalEntry;

/**
 * @author assionx
 * @date 2023-07-29
 **/

public interface CanalTransactionHandler {

    /**
     *
     */
    void transactionMessageHandler(CanalEntry.Entry entry);
}

package com.gitee.xiaosongstudy.canal.core;

/**
 * canal同步处理器
 */
public interface CanalSyncHandler {

    void updateOpeartion();

    void deleteOperation();

    void insertOpeartion();

    void ddlOerpation();
}

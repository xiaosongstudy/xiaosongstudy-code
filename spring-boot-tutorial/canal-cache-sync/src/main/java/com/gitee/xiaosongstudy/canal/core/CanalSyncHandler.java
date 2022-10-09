package com.gitee.xiaosongstudy.canal.core;

/**
 * canal同步处理器
 */
public interface CanalSyncHandler {

    default void updateOperation(CanalBean canalBean) {
        System.out.println(canalBean);
    }

    default void deleteOperation(CanalBean canalBean) {
        System.out.println(canalBean);
    }

    default void insertOperation(CanalBean canalBean) {
        System.out.println(canalBean);
    }

    default void ddlOperation(CanalBean canalBean) {
        System.out.println(canalBean);
    }
}

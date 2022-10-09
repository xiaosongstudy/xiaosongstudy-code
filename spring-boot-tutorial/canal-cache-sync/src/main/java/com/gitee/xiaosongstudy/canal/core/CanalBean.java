package com.gitee.xiaosongstudy.canal.core;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Map;

/**
 * canal 缓存
 */
@Getter
@Setter
@ToString
public class CanalBean {

    /**
     * 当前修改数据库表
     */
    private String schema;

    /**
     * 数据库表名
     */
    private String tableName;

    /**
     * 修改的数据
     */
    private Map<String, String> data;

    /**
     * 待删除编号
     */
    private String deleteId;

    /**
     * ddl sql语句
     */
    private String ddlSql;
}

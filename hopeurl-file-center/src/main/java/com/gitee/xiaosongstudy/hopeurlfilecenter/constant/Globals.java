package com.gitee.xiaosongstudy.hopeurlfilecenter.constant;

/**
 * 全局常量类
 * @author hopeurl
 * @email myhopeurlife@foxmail
 * @since 2022/10/12 9:31
 */
public class Globals {

    private Globals(){}

    public static class File {
        /**
         * 以只读的方式打开，调用该对象的任何write（写）方法都会导致IOException异常
         */
        public static final String MODE_R = "r";
        /**
         * 以读、写方式打开，支持文件的读取或写入。若文件不存在，则创建之。
         */
        public static final String MODE_RW = "rw";
        /**
         * 以读、写方式打开，与“rw”不同的是，还要对文件内容的每次更新都同步更新到潜在的存储设备中去。这里的“s”表示synchronous（同步）的意思
         */
        public static final String MODE_RWS = "rws";
        /**
         * 以读、写方式打开，与“rw”不同的是，还要对文件内容的每次更新都同步更新到潜在的存储设备中去。使用“rwd”模式仅要求将文件的内容更新到存储设备中，
         * 而使用“rws”模式除了更新文件的内容，还要更新文件的元数据（metadata），因此至少要求1次低级别的I/O操作
         */
        public static final String MODE_RWD = "rwd";
    }
}

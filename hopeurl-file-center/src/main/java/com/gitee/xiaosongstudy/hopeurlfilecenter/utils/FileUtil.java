package com.gitee.xiaosongstudy.hopeurlfilecenter.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * 文件工具类
 *
 * @author hopeurl
 * @email myhopeurlife@foxmail
 * @since 2022/10/12 15:53
 */
public class FileUtil {

    private FileUtil() {
    }

    /**
     * 默认保留位数为2位
     */
    private static final int DEFAULT_SCALE = 2;

    /**
     * BIT 8位
     */
    public static final int BIT = 8;
    /**
     * 定义KB的计算常量
     */
    public static final BigDecimal KB = new BigDecimal("1024");
    /**
     * 定义MB的计算常量
     */
    public static final BigDecimal MB = KB.multiply(KB);

    /**
     * 定义GB的计算常量
     */
    public static final BigDecimal GB = MB.multiply(KB);


    /**
     * 获取文件扩展名
     *
     * @param name 原始文件名称
     * @return
     */
    public static String getExtensionName(String name) {
        if (StringUtil.isEmpty(name)) {
            throw new IllegalArgumentException(String.format("非法文件名：[%s]", name));
        } else {
            int lastDot = name.lastIndexOf(".");
            if (lastDot < 0) {
                throw new IllegalArgumentException(String.format("未知文件：[%s]", name));
            } else {
                return name.substring(lastDot);
            }
        }
    }

    /**
     * 文件大小转化
     *
     * @param size  大小
     * @param scale 保留位数
     * @param mode  小数处理策略
     * @return 转化结果
     */
    public static String getSize(BigDecimal size, int scale, RoundingMode mode) {
        String resultSize;
        BigDecimal gbResult = size.divide(GB, scale, mode);
        BigDecimal mbResult = size.divide(MB, scale, mode);
        BigDecimal kbResult = size.divide(KB, scale, mode);

        if (gbResult.compareTo(BigDecimal.ONE) >= 0) {
            //如果当前Byte的值大于等于1GB
            resultSize = gbResult + "GB   ";
        } else if (mbResult.compareTo(BigDecimal.ONE) >= 0) {
            //如果当前Byte的值大于等于1MB
            resultSize = mbResult + "MB   ";
        } else if (kbResult.compareTo(BigDecimal.ONE) >= 0) {
            //如果当前Byte的值大于等于1KB
            resultSize = kbResult + "KB    ";
        } else {
            resultSize = size + "B   ";
        }
        return resultSize;
    }


    /**
     * 文件大小转化
     *
     * @param size 文件大小
     * @return
     */
    public static String getSize(BigDecimal size) {
        return getSize(size, DEFAULT_SCALE, RoundingMode.UP);
    }

    /**
     * 根据文件的后缀获取文件的类型
     *
     * @param suffix 文件的后缀
     * @return 文件乐类型
     */
    public static String getFileType(String suffix) {
        if (FileType.TAG_IMAGE.getTypes().contains(suffix)) {
            return FileType.TAG_IMAGE.getDescription();
        } else if (FileType.TAG_DOCUMENTS.getTypes().contains(suffix)) {
            return FileType.TAG_DOCUMENTS.getDescription();
        } else if (FileType.TAG_MUSIC.getTypes().contains(suffix)) {
            return FileType.TAG_MUSIC.getDescription();
        } else if (FileType.TAG_VIDEO.getTypes().contains(suffix)) {
            return FileType.TAG_VIDEO.getDescription();
        } else {
            return FileType.TAG_OTHER.getDescription();
        }
    }

    public static void main(String[] args) {
        System.out.println(FileUtil.getSize(new BigDecimal("22502193")));
    }

    /**
     * 文件类型枚举
     */
    public enum FileType {
        /**
         * 文档
         */
        TAG_DOCUMENTS("txt pdf pps wps doc docx ppt pptx xls xlsx", "文档"),
        /**
         * 音频
         */
        TAG_MUSIC("mp3 wav wma mpa ram ra aac aif m4a", "音频"),
        /**
         * 视频
         */
        TAG_VIDEO("avi mpg mpe mpeg asf wmv mov qt rm mp4 flv m4v webm ogv ogg", "视频"),
        /**
         * 图片
         */
        TAG_IMAGE("bmp dib pcp dif wmf gif jpg tif eps psd cdr iff tga pcd mpt png jpeg", "图片"),
        /**
         * 其他
         */
        TAG_OTHER("", "其他");
        private final String types;
        private final String description;

        FileType(String types, String description) {
            this.types = types;
            this.description = description;
        }

        public String getTypes() {
            return types;
        }

        public String getDescription() {
            return description;
        }

        @Override
        public String toString() {
            return "FileType{" +
                    "types='" + types + '\'' +
                    ", description='" + description + '\'' +
                    '}';
        }
    }
}

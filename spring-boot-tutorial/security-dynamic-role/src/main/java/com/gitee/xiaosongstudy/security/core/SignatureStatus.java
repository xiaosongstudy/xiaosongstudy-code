package com.gitee.xiaosongstudy.security.core;

/**
 * 本类定义的是签名后的三种状态
 */
public enum SignatureStatus {
    /**
     * 验证签名后的三种状态:
     *   correct 正确
     *   expired 过期
     *   tamper  篡改了
     */
    correct, expired, tamper
}

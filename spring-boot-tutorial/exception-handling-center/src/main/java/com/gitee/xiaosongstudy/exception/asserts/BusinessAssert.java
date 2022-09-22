package com.gitee.xiaosongstudy.exception.asserts;


import com.gitee.xiaosongstudy.exception.core.BusinessException;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collection;

/**
 * <p>
 * 自定义断言，用以检验参数
 * </p>
 *
 * @author 宋世平 email:2453332538@qq.com 2022/3/28
 **/
public class BusinessAssert {

    private BusinessAssert() {
    }

    /**
     * 如果numOne小于0则抛异常
     *
     * @param numOne
     * @param message
     */
    public static void ltZero(BigInteger numOne, String message) {
        lt(numOne, BigInteger.ZERO, message);
    }

    /**
     * 如果numOne小于或者等于0则抛异常
     *
     * @param numOne
     * @param message
     */
    public static void leZero(BigInteger numOne, String message) {
        le(numOne, BigInteger.ZERO, message);
    }

    /**
     * 如果numOne小于numTwo则抛出异常
     *
     * @param numOne
     * @param numTwo
     * @param message
     */
    public static void lt(BigInteger numOne, BigInteger numTwo, String message) {
        doAssert(numOne.compareTo(numTwo) < 0, message);
    }

    /**
     * 如果numOne小于或者等于numTwo则抛出异常
     *
     * @param numOne
     * @param numTwo
     * @param message
     */
    public static void le(BigInteger numOne, BigInteger numTwo, String message) {
        doAssert(numOne.compareTo(numTwo) <= 0, message);
    }

    /**
     * 如果numOne大于numTwo则抛出异常
     *
     * @param numOne
     * @param numTwo
     * @param message
     */
    public static void gt(BigInteger numOne, BigInteger numTwo, String message) {
        doAssert(numOne.compareTo(numTwo) > 0, message);
    }

    /**
     * 如果numOne大于或者等于numTwo则抛出异常
     *
     * @param numOne
     * @param numTwo
     * @param message
     */
    public static void ge(BigInteger numOne, BigInteger numTwo, String message) {
        doAssert(numOne.compareTo(numTwo) >= 0, message);
    }


    /**
     * 如果numOne小于0则抛异常
     *
     * @param numOne
     * @param message
     */
    public static void ltZero(BigDecimal numOne, String message) {
        lt(numOne, BigDecimal.ZERO, message);
    }

    /**
     * 如果numOne小于等于0则抛异常
     *
     * @param numOne
     * @param message
     */
    public static void leZero(BigDecimal numOne, String message) {
        le(numOne, BigDecimal.ZERO, message);
    }

    /**
     * 如果numOne小于numTwo则抛异常
     *
     * @param numOne
     * @param numTwo
     * @param message
     */
    public static void lt(BigDecimal numOne, BigDecimal numTwo, String message) {
        doAssert(numOne.compareTo(numTwo) < 0, message);
    }

    /**
     * 如果numOne小于或者等于numTwo则抛异常
     *
     * @param numOne
     * @param numTwo
     * @param message
     */
    public static void le(BigDecimal numOne, BigDecimal numTwo, String message) {
        doAssert(numOne.compareTo(numTwo) <= 0, message);
    }

    /**
     * 如果numOne大于或者等于numTwo则抛异常
     *
     * @param numOne
     * @param numTwo
     * @param message
     */
    public static void ge(BigDecimal numOne, BigDecimal numTwo, String message) {
        doAssert(numOne.compareTo(numTwo) >= 0, message);
    }

    /**
     * 如果numOne大于numTwo则抛异常
     *
     * @param numOne
     * @param numTwo
     * @param message
     */
    public static void gt(BigDecimal numOne, BigDecimal numTwo, String message) {
        doAssert(numOne.compareTo(numTwo) > 0, message);
    }

    /**
     * 如果numOne小于numTwo则抛异常
     *
     * @param numOne  数字1
     * @param numTwo  数字2
     * @param message 错误信息
     */
    public static void lt(int numOne, int numTwo, String message) {
        doAssert(numOne < numTwo, message);
    }

    /**
     * numOne小于numTwo
     *
     * @param numOne  数字1
     * @param numTwo  数字2
     * @param message 错误信息
     */
    public static void le(int numOne, int numTwo, String message) {
        doAssert(numOne > numTwo, message);
    }

    /**
     * numOne 大于 numTwo
     *
     * @param numOne  数值1
     * @param numTwo  数值2
     * @param message 错误信息
     */
    public static void ge(Integer numOne, Integer numTwo, String message) {
        notNull(numOne, "数值一不能为空！");
        notNull(numTwo, "数值二不能为空！");
        doAssert(numOne < numTwo, message);
    }

    /**
     * 如果numOne大于numTwo则抛异常
     *
     * @param numOne  数字1
     * @param numTwo  数字2
     * @param message 错误信息
     */
    public static void gt(int numOne, int numTwo, String message) {
        doAssert(numOne > numTwo, message);
    }

    /**
     * 如果numOne大于或者等于numTwo则抛异常
     *
     * @param numOne  数字1
     * @param numTwo  数字2
     * @param message 错误信息
     */
    public static void ge(int numOne, int numTwo, String message) {
        doAssert(numOne >= numTwo, message);
    }


    /**
     * 校验引用对象是否为空
     *
     * @param validateObj 待校验的对象
     * @param message     校验信息
     */
    public static void notNull(Object validateObj, String message) {
        doAssert(validateObj == null, message);
    }


    /**
     * 检验字符串参数是否有内容
     *
     * @param validateStr 待校验的字符串
     */
    public static void hasText(String validateStr, String message) {
        doAssert(!StringUtils.hasText(validateStr), message);
    }

    /**
     * 校验集合类参数是否为空
     *
     * @param collection
     */
    public static void notEmpty(Collection<?> collection, String message) {
        doAssert(collection.isEmpty(), message);
    }

    /**
     * 执行断言操作
     *
     * @param flag    标识位 如果为true则抛出执行异常！
     * @param message 错误信息！
     */
    private static void doAssert(boolean flag, String message) {
        if (flag) {
            throw new BusinessException(message);
        }
    }

    /**
     * 字符串1和字符串2相等
     *
     * @param strOne
     * @param StrTwo
     * @param message
     */
    public static void eq(String strOne, String StrTwo, String message) {
        boolean flag = StringUtils.hasText(strOne) && StringUtils.hasText(StrTwo) && strOne.equals(StrTwo);
        doAssert(!flag, message);
    }

    /**
     * 断言为真
     * @param flag
     * @param message
     */
    public static void isTure(boolean flag, String message) {
        doAssert(!flag, message);
    }
}
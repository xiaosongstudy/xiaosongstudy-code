package com.gitee.xiaosongstudy.base.asserts;


import com.gitee.xiaosongstudy.base.core.exception.BusinessException;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collection;
import java.util.Map;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * <p>
 * 自定义断言，用以检验参数
 * </p>
 *
 * @author 宋世平 email:2453332538@qq.com 2022/3/28
 **/
public class Asserts {

    private Asserts() {
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
     * 将捕获到的异常转化为系统自带的异常
     *
     * @param cause   待包装异常
     * @param message 错误信息
     * @param model   辅助数据源
     * @date 2023/7/27 21:19
     */
    public static void err(Throwable cause, String message, Map<String, String> model) {
        throw new BusinessException(message, cause, model);
    }

    public static void err(Throwable cause, String message) {
        err(cause, message, null);
    }

    public static void err(String message) {
        throw new BusinessException(message);
    }

    /**
     * 执行断言操作
     *
     * @param flag    标识位 如果为true则抛出执行异常！
     * @param message 错误信息！
     */
    private static void doAssert(boolean flag, String message) {
        doAssert(flag, message, null);
    }

    /**
     * <p>断言flag为false，如果为真则抛出错误</p>
     * <p>message 支持${param}这种形式的占位符，如果使用占位符则与model联动，此时model作为数据源</p>
     *
     * @param flag    待校验标识
     * @param message 抛出的错误
     * @param model   辅助数据源
     * @date 2023/7/27 21:14
     */
    private static void doAssert(boolean flag, String message, Map<String, String> model) {
        if (flag) {
            if (Objects.nonNull(model)) {
                throw new BusinessException(message, model);
            } else {
                throw new BusinessException(message);
            }
        }
    }

    /**
     * 字符串1和字符串2相等，第一个参数请尽量使用一定不为空的值
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
     * 断言第一个数值和第二个数值不相等
     *
     * @param one     第一个数值
     * @param two     第二个数值
     * @param message 错误提示信息
     * @date 2023/9/9 09:34
     */
    public static void ne(Integer one, Integer two, String message) {
        doAssert(one.equals(two), message);
    }

    /**
     * 断言为真
     *
     * @param flag
     * @param message
     */
    public static void isTure(boolean flag, String message) {
        doAssert(!flag, message);
    }

    /**
     * 校验param是否为空值，如果为空值则抛出系统异常，如果不为空则返回原值
     *
     * @param param    待校验参数
     * @param errorMsg 异常信息
     * @param <T>      待校验参数范型
     * @date 2023/9/6 21:16
     */
    public static <T> T requireNonNull(T param, String errorMsg) {
        notNull(param, errorMsg);
        return param;
    }

    /**
     * 校验param是否为空值，如果为空值则抛出系统异常，如果不为空则返回原值
     *
     * @param paramSupplier 待校验值生成器
     * @param errorMsg      错误信息
     * @param <T>
     * @return 校验后的值
     * @date 2023/9/6 21:22
     */
    public static <T> T requireNonNull(Supplier<T> paramSupplier, String errorMsg) {
        // 提前获取处理，减少可能出现的二次io操作
        T param = paramSupplier.get();
        notNull(param, errorMsg);
        return param;
    }

    /**
     * 构建系统业务异常
     *
     * @param message 异常信息
     * @return 系统业务异常
     * @date 2023/9/6 21:24
     */
    public BusinessException buildBizException(String message) {
        return new BusinessException(message);
    }
}
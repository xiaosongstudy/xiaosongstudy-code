package life.hopeurl.application.mysql;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * mapper 调用处理器
 *
 * @author shiping.song
 * @date 2023/5/2 16:49
 */
public class MapperInvocationHandler implements InvocationHandler {

    private final Class<?> mapperClz;

    public MapperInvocationHandler(Class<?> mapperClz) {
        this.mapperClz = mapperClz;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (Object.class.equals(method.getDeclaringClass())) {
            // 走默认的方法处理
            return method.invoke(this, args);
        }
        // 解析xml然后执行sql返回结果
        return new MapperMethod().invoke(mapperClz, method, args);
    }
}

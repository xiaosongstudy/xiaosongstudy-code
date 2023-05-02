package life.hopeurl.application.mysql;

import java.lang.reflect.Proxy;

/**
 * mapper持久层工厂
 *
 * @author shiping.song
 * @date 2023/5/2 16:28
 */
public class MapperFactory {

    /**
     * 通过持久层字节码对象获取代理对象
     *
     * @param mapperClz 持久层字节码
     * @param <T>       被代理对象泛型
     * @return 代理对象
     * @date 2023/5/2 16:32
     */
    @SuppressWarnings("unchecked")
    public static <T> T getInstance(Class<T> mapperClz) {
        // 暂时在这初始化xml信息
        XmlCache.beginCache();
        MapperInvocationHandler mapperInvocationHandler = new MapperInvocationHandler(mapperClz);
        return (T) Proxy.newProxyInstance(mapperClz.getClassLoader(), new Class[]{mapperClz}, mapperInvocationHandler);
    }
}

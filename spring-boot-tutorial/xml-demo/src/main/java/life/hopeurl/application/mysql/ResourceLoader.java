package life.hopeurl.application.mysql;

import org.yaml.snakeyaml.Yaml;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

/**
 * 资源加载器
 *
 * @author shiping.song
 * @date 2023/5/2 01:45
 */
public class ResourceLoader {

    /**
     * 核心配置类文件
     */
    private static Configuration configuration;

    private ResourceLoader() {

    }

    /**
     * 从指定路径获取流资源
     *
     * @param path 指定路径
     * @return 流资源
     * @date 2023/5/2 01:47
     */
    public static InputStream getResourceAsStream(String path) {
        return ResourceLoader.class.getResourceAsStream(path);
    }

    /**
     * 解析核心配置类，只需要解析一次即可
     *
     * @return 核心配置类
     * @date 2023/5/2 13:42
     */
    public static Configuration resolveConfiguration() {
        if (Objects.isNull(configuration)) {
            synchronized (ResourceLoader.class) {
                if (Objects.isNull(configuration)) {
                    try (InputStream in = ResourceLoader.getResourceAsStream("/application.yml")) {
                        Yaml yaml = new Yaml();
                        configuration = yaml.loadAs(in, Configuration.class);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
        return configuration;
    }
}

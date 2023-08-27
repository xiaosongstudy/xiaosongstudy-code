import cn.hutool.core.util.RandomUtil;
import life.hopeurl.redistemplate.RedisTemplateApplication;
import life.hopeurl.redistemplate.business.common.RedisCache;
import life.hopeurl.redistemplate.business.common.RedisService;
import life.hopeurl.redistemplate.vo.UserInfoVo;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * redis服务类测试
 *
 * @author shiping.song
 * @date 2023/8/20 01:14
 * @email 2453332538@qq.com
 */
@SpringBootTest(classes = {RedisTemplateApplication.class})
public class RedisServiceTest {


    @Resource
    private RedisService redisService;


    @Test
    void testPutAll() {
        UserInfoVo userInfoVo = new UserInfoVo();
        userInfoVo.setUsername("zhangsan");
        userInfoVo.setMobilePhone(RandomUtil.randomNumbers(11));
        UserInfoVo testChild = new UserInfoVo();
        testChild.setUsername("zhangsan的儿子");
        testChild.setMobilePhone(RandomUtil.randomNumbers(11));
        userInfoVo.setTestChild(testChild);
        RedisCache redisCache = RedisCache.builder().businessPrefix("test:map:").businessKey(RandomUtil.randomNumbers(32)).value(userInfoVo).build();
        redisService.putAll(redisCache);
    }

    @Test
    void testNextLongId() throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(500);
        CountDownLatch countDownLatch = new CountDownLatch(500);
        long startTime = System.currentTimeMillis();
        Runnable task = () -> {
            for (int i = 0; i < 200; i++) {
                redisService.nextLongId("order_item");
            }
            countDownLatch.countDown();
        };
        for (int i = 0; i < 500; i++) {
            executorService.submit(task);
        }
        countDownLatch.await();
        long endTime = System.currentTimeMillis();
        System.out.println("time:" + (endTime - startTime));

    }
}

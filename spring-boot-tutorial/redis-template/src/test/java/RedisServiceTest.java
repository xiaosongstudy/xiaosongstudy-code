import cn.hutool.core.util.RandomUtil;
import life.hopeurl.redistemplate.RedisTemplateApplication;
import life.hopeurl.redistemplate.business.common.RedisCache;
import life.hopeurl.redistemplate.business.common.RedisService;
import life.hopeurl.redistemplate.vo.UserInfoVo;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

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
}

package life.hopeurl.redistemplate.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import life.hopeurl.redistemplate.business.common.RedisService;
import life.hopeurl.redistemplate.core.Stores;
import life.hopeurl.redistemplate.entity.UserKillVoucher;
import life.hopeurl.redistemplate.service.UserKillVoucherService;
import life.hopeurl.redistemplate.mapper.UserKillVoucherMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
* @author songshiping
* @description 针对表【user_kill_voucher(用户秒杀券中间表)】的数据库操作Service实现
* @createDate 2023-09-06 23:41:21
*/
@Service
public class UserKillVoucherServiceImpl extends ServiceImpl<UserKillVoucherMapper, UserKillVoucher>
    implements UserKillVoucherService{

    @Resource(type = RedisService.class)
    private RedisService redisService;

    @Override
    public void saveVoucher(Long voucherId) {
        UserKillVoucher userKillVoucher = new UserKillVoucher();
        userKillVoucher.setVoucherId(voucherId);
        userKillVoucher.setUserId(Stores.getUserInfo().getId());
        long id = redisService.nextLongId("voucher:kill");
        userKillVoucher.setId(id);
        super.save(userKillVoucher);
    }
}





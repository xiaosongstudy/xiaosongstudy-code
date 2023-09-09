package life.hopeurl.redistemplate.service;

import life.hopeurl.redistemplate.entity.UserKillVoucher;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author songshiping
* @description 针对表【user_kill_voucher(用户秒杀券中间表)】的数据库操作Service
* @createDate 2023-09-06 23:41:21
*/
public interface UserKillVoucherService extends IService<UserKillVoucher> {


    /**
     * 保存用户优惠券信息
     *
     * @param voucherId 优惠券编号
     * @date 2023/9/9 09:51
     */
    void saveVoucher(Long voucherId);

}

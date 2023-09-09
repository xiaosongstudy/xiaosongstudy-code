package life.hopeurl.redistemplate.service;

import com.baomidou.mybatisplus.extension.service.IService;
import life.hopeurl.redistemplate.entity.SeckillVoucher;
import life.hopeurl.redistemplate.vo.SkillVoucherVo;

/**
 * @author songshiping
 * @description 针对表【seckill_voucher(秒杀优惠券表，与优惠券是一对一关系)】的数据库操作Service
 * @createDate 2023-09-06 23:26:10
 */
public interface SeckillVoucherService extends IService<SeckillVoucher> {

    /**
     * 通过voucherId查询抢票券信息
     *
     * @param voucherId 优惠券编号
     * @date 2023/9/9 09:38
     */
    SeckillVoucher queryByVoucherId(Long voucherId);

    /**
     * 抢优惠券-基于纯数据库版实现
     *
     * @param skillVoucherVo 请求数据
     * @date 2023/9/7 00:16
     */
    void robVoucherByDbFirst(SkillVoucherVo skillVoucherVo);

    void robVoucherByDbSynchronize(SkillVoucherVo skillVoucherVo);


}

package life.hopeurl.redistemplate.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gitee.xiaosongstudy.base.asserts.Asserts;
import life.hopeurl.redistemplate.business.common.RedisService;
import life.hopeurl.redistemplate.entity.SeckillVoucher;
import life.hopeurl.redistemplate.entity.Voucher;
import life.hopeurl.redistemplate.mapper.SeckillVoucherMapper;
import life.hopeurl.redistemplate.service.SeckillVoucherService;
import life.hopeurl.redistemplate.service.UserKillVoucherService;
import life.hopeurl.redistemplate.service.VoucherService;
import life.hopeurl.redistemplate.vo.SkillVoucherVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.framework.AopContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author songshiping
 * @description 针对表【seckill_voucher(秒杀优惠券表，与优惠券是一对一关系)】的数据库操作Service实现
 * @createDate 2023-09-06 23:26:10
 */
@Service
@Slf4j
public class SeckillVoucherServiceImpl extends ServiceImpl<SeckillVoucherMapper, SeckillVoucher>
        implements SeckillVoucherService {

    @Resource(type = VoucherService.class)
    private VoucherService voucherService;

    @Resource(type = UserKillVoucherService.class)
    private UserKillVoucherService userKillVoucherService;

    @Resource(type = RedisService.class)
    private RedisService redisService;

    public static final String VOUCHER_NOT_EXIST = "抢购的秒杀优惠券不存在";
    public static final String VOUCHER_OFF = "该优惠券已经被下架了";
    public static final String VOUCHER_EXPIRED = "该优惠券已经过期了";
    public static final String VOUCHER_NOT_SKILL_SERVICE = "该优惠券没有秒杀服务";
    public static final String VOUCHER_SKILL_FINISH = "该优惠券已经被抢完了";
    public static final String VOUCHER_SKILL_AGAIN = "优惠券暂时没抢到，请稍后再试";

    @Override
    public void robVoucherByDbFirst(SkillVoucherVo skillVoucherVo) {
        synchronized (skillVoucherVo.getVoucherId().toString().intern()) {
            SeckillVoucherService proxy = (SeckillVoucherService) AopContext.currentProxy();
            Asserts.notNull(proxy, "代理对象获取失败");
            proxy.robVoucherByDbSynchronize(skillVoucherVo);
        }
    }

    @Override
    public SeckillVoucher queryByVoucherId(Long voucherId) {
        LambdaQueryWrapper<SeckillVoucher> voucherWr = Wrappers.lambdaQuery(SeckillVoucher.class).eq(SeckillVoucher::getVoucherId, voucherId);
        return super.getOne(voucherWr, true);
    }

    /**
     * 基于数据库进行秒杀抢购，使用cas乐观锁思想解决并发问题
     *
     * @param skillVoucherVo 目标优惠券数据
     * @date 2023/9/9 09:02
     */
    @Transactional
    public void robVoucherByDbWithCAS(SkillVoucherVo skillVoucherVo) {
        // 1. 查询优惠券有没有同时有没有到抢的时间
        Voucher voucher = voucherService.queryOneByCondition(this.buildQueryVoucherParams(skillVoucherVo.getVoucherId()));
        Asserts.notNull(voucher, VOUCHER_NOT_EXIST);
        Asserts.notNull(voucher.getStatus(), "优惠券状态为空");
        // 这里简单处理，直接使用自带断言进行空值校验
        Asserts.ne(voucher.getStatus(), 2, VOUCHER_OFF);
        Asserts.ne(voucher.getStatus(), 3, VOUCHER_EXPIRED);
        // 2. 查询优惠券库存是否足够，如果够则抢成功，开始扣库存，扣库存时需要考虑并发问题
        SeckillVoucher oldVoucher = this.queryByVoucherId(skillVoucherVo.getVoucherId());
        Asserts.notNull(oldVoucher, VOUCHER_NOT_SKILL_SERVICE);
        Asserts.notNull(oldVoucher.getStock(), "优惠券库存信息异常");
        Asserts.isTure(oldVoucher.getStock() <= 0, VOUCHER_SKILL_FINISH);
        // 2.1 如何控制数据不被超卖？ 加版本号字段，或者使用 CAS乐观锁(弊端是失败率高)
        boolean robResult = this.updateStockByVoucherId(skillVoucherVo, true);
        Asserts.isTure(robResult, VOUCHER_SKILL_AGAIN);
        // 抢到则新增用户与秒杀券的关系表
        userKillVoucherService.saveVoucher(skillVoucherVo.getVoucherId());
    }


    /**
     * 基于数据库进行秒杀抢购，使用synchronized阻塞锁解决并发问题
     *
     * @param skillVoucherVo 目标优惠券数据
     * @date 2023/9/9 09:06
     */
    @Transactional
    public void robVoucherByDbSynchronize(SkillVoucherVo skillVoucherVo) {

    }


    /**
     * 通过优惠券编号更新库存
     *
     * @param openCas        是否开启cas
     * @param skillVoucherVo 更新条件
     * @return 是否更新成功
     * @date 2023/9/9 09:48
     */
    private boolean updateStockByVoucherId(SkillVoucherVo skillVoucherVo, boolean openCas) {
        LambdaUpdateWrapper<SeckillVoucher> updateWrapper = Wrappers.lambdaUpdate(SeckillVoucher.class)
                .eq(SeckillVoucher::getVoucherId, skillVoucherVo.getVoucherId())
                .eq(openCas, SeckillVoucher::getStock, skillVoucherVo.getStock())
                .set(SeckillVoucher::getStock, skillVoucherVo.getStock() - 1);
        return super.update(updateWrapper);
    }

    /**
     * 构建优惠券查询条件
     *
     * @param voucherId 优惠券编号
     * @return 查询条件
     * @date 2023/9/9 09:19
     */
    private Voucher buildQueryVoucherParams(Long voucherId) {
        Voucher condition = new Voucher();
        condition.setId(voucherId);
        condition.setType(1);
        return condition;
    }
}





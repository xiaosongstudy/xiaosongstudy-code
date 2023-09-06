package life.hopeurl.redistemplate.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import life.hopeurl.redistemplate.core.Result;
import life.hopeurl.redistemplate.entity.SeckillVoucher;
import life.hopeurl.redistemplate.entity.Voucher;
import life.hopeurl.redistemplate.mapper.SeckillVoucherMapper;
import life.hopeurl.redistemplate.service.SeckillVoucherService;
import life.hopeurl.redistemplate.service.VoucherService;
import life.hopeurl.redistemplate.vo.SkillVoucherVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

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

    @Override
    public Result<SkillVoucherVo> robVoucherByDb(Result<SkillVoucherVo> resultModel, SkillVoucherVo skillVoucherVo) {
        String msg = StrUtil.EMPTY;
        // 1. 查询优惠券有没有同时有没有到抢的时间
        LambdaQueryWrapper<Voucher> wr = Wrappers.lambdaQuery(Voucher.class).eq(Voucher::getId, skillVoucherVo.getVoucherId());
        Voucher voucher = voucherService.getOne(wr, true);
        // 这里简单处理，直接使用自带断言进行空值校验
        assert voucher != null;
        Integer status = voucher.getStatus();
        if (2 == status) {
            msg = "该优惠券已经被下架了";
        } else if (3 == status) {
            msg = "该优惠券已经过期了";
        } else {
            // 2. 查询优惠券库存是否足够，如果够则抢成功，开始扣库存，扣库存时需要考虑并发问题
            LambdaQueryWrapper<SeckillVoucher> voucherWr = Wrappers.lambdaQuery(SeckillVoucher.class).eq(SeckillVoucher::getVoucherId, skillVoucherVo.getVoucherId());
            SeckillVoucher seckillVoucher = super.getOne(voucherWr, true);
            assert seckillVoucher != null;
            Integer stock = seckillVoucher.getStock();
            if (stock < 0) {
                msg = "该优惠券已经被抢完了";
            } else {
                // 2.1 如何控制数据不被超卖？ 加版本号字段，或者使用 CAS乐观锁(弊端是失败率高)
                skillVoucherVo.setStock(stock);
                handleRobVoucherByDb(skillVoucherVo, "1");
            }
        }
        resultModel.setFlag(true);
        resultModel.setMsg(msg);
        return resultModel;
    }

    /**
     * 执行抢优惠券的操作 -- 主流程数据库实现
     *
     * @param skillVoucherVo 目标优惠券数据
     * @param handleType     处理类型，1=使用cas乐观锁
     * @date 2023/9/7 00:40
     */
    private void handleRobVoucherByDb(SkillVoucherVo skillVoucherVo, String handleType) {
        long start = System.currentTimeMillis();
        if ("1".equals(handleType)) {
            LambdaUpdateWrapper<SeckillVoucher> updateWrapper = Wrappers.lambdaUpdate(SeckillVoucher.class)
                    .eq(SeckillVoucher::getVoucherId, skillVoucherVo.getVoucherId())
                    .eq(SeckillVoucher::getStock, skillVoucherVo.getStock())
                    .set(SeckillVoucher::getStock, skillVoucherVo.getStock() - 1);
            super.update(updateWrapper);
        }
        long end = System.currentTimeMillis();
        log.info(String.format("time: %s, handleType: %s", (end-start), handleType));
    }
}





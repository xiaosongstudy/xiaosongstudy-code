package life.hopeurl.redistemplate.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import life.hopeurl.redistemplate.entity.Voucher;
import life.hopeurl.redistemplate.mapper.VoucherMapper;
import life.hopeurl.redistemplate.service.VoucherService;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @author songshiping
 * @description 针对表【voucher(优惠券表)】的数据库操作Service实现
 * @createDate 2023-09-06 23:24:08
 */
@Service
public class VoucherServiceImpl extends ServiceImpl<VoucherMapper, Voucher>
        implements VoucherService {

    @Override
    public Voucher queryOneByCondition(Voucher condition) {
        assert null != condition;
        LambdaQueryWrapper<Voucher> wr = Wrappers.lambdaQuery(Voucher.class)
                .eq(Objects.nonNull(condition.getType()), Voucher::getType, condition.getType())
                .eq(Objects.nonNull(condition.getId()), Voucher::getId, condition.getId());
        return super.getOne(wr, true);
    }
}





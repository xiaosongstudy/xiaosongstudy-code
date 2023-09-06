package life.hopeurl.redistemplate.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import life.hopeurl.redistemplate.entity.SeckillVoucher;
import life.hopeurl.redistemplate.service.SeckillVoucherService;
import life.hopeurl.redistemplate.mapper.SeckillVoucherMapper;
import org.springframework.stereotype.Service;

/**
* @author songshiping
* @description 针对表【seckill_voucher(秒杀优惠券表，与优惠券是一对一关系)】的数据库操作Service实现
* @createDate 2023-09-06 23:26:10
*/
@Service
public class SeckillVoucherServiceImpl extends ServiceImpl<SeckillVoucherMapper, SeckillVoucher>
    implements SeckillVoucherService{

}





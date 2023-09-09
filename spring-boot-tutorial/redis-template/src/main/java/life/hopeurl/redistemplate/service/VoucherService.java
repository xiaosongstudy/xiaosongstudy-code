package life.hopeurl.redistemplate.service;

import life.hopeurl.redistemplate.entity.Voucher;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @author songshiping
 * @description 针对表【voucher(优惠券表)】的数据库操作Service
 * @createDate 2023-09-06 23:24:08
 */
public interface VoucherService extends IService<Voucher> {

    /**
     * 通过条件获取一个目标对象
     *
     * @param condition 查询对象
     * @return 目标数据
     * @date 2023/9/9 09:13
     */
    Voucher queryOneByCondition(Voucher condition);

}

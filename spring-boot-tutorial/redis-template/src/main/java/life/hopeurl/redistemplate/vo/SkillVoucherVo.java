package life.hopeurl.redistemplate.vo;

import life.hopeurl.redistemplate.entity.SeckillVoucher;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 秒杀优惠券视图
 *
 * @author shiping.song
 * @date 2023/9/7 00:08
 * @email 2453332538@qq.com
 */
@Getter
@Setter
@ToString
public class SkillVoucherVo extends SeckillVoucher {

    private static final long serialVersionUID = 6219829928339704709L;

    /**
     * 用户编号
     */
    private Long userId;
}

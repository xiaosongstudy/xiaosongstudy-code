package life.hopeurl.redistemplate.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 用户秒杀券中间表
 *
 * @TableName user_kill_voucher
 */
@TableName(value = "user_kill_voucher")
@Data
public class UserKillVoucher implements Serializable {
    /**
     * 交易记录主键
     */
    private Long id;

    /**
     * 用户编号
     */
    private Long userId;

    /**
     * 优惠券编号
     */
    private Long voucherId;

    /**
     * 优惠券状态[0=未使用/1=已使用/2=已过期]
     */
    private String voucherStatus;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}
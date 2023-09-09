package life.hopeurl.redistemplate.api;

import com.gitee.xiaosongstudy.base.asserts.Asserts;
import com.gitee.xiaosongstudy.base.core.Result;
import life.hopeurl.redistemplate.service.SeckillVoucherService;
import life.hopeurl.redistemplate.vo.SkillVoucherVo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 优惠券控制器
 *
 * @author shiping.song
 * @date 2023/9/6 23:56
 * @email 2453332538@qq.com
 */
@RequestMapping("/vouchers")
@RestController
public class VoucherController {

    @Resource(type = SeckillVoucherService.class)
    private SeckillVoucherService seckillVoucherService;

    /**
     * 抢优惠券-基于纯数据库版实现
     *
     * @param skillVoucherVo 请求数据
     * @return 优惠券结果
     * @date 2023/9/7 00:10
     */
    @PostMapping("/robVoucherByDb")
    public Result robVoucherByDb(@RequestBody SkillVoucherVo skillVoucherVo) {
        Asserts.notNull(skillVoucherVo, "优惠券信息不为空");
        Asserts.notNull(skillVoucherVo.getVoucherId(), "待抢优惠券为空");
        seckillVoucherService.robVoucherByDbFirst(skillVoucherVo);
        return Result.success("抢券成功");
    }
}

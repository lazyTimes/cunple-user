import com.myapp.cunpleapp.CunpleAppApplication;
import com.myapp.cunpleapp.mapper.TCouponMapper;
import com.myapp.cunpleapp.model.TCoupon;
import com.myapp.cunpleapp.model.TCouponExample;
import com.myapp.cunpleapp.service.MyTestService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * 测试使用
 * @author zhaoxudong
 * @title: TestController
 * @projectName cunple
 * @description: 测试使用
 * @date 2019/8/19 15:15
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = CunpleAppApplication.class)
public class TestController {

    @Resource
    private MyTestService myTestService;

    @Resource
    private TCouponMapper couponMapper;

    @Test
    public void run(){
        myTestService.run();
        System.err.println("hello world");
    }

    @Test
    public void insert(){
        for (int i = 0; i < 100000; i++) {
            TCoupon tCoupon = new TCoupon();
            tCoupon.setAchieveAmount(500);
            tCoupon.setReduceAmount(20);
            tCoupon.setCreatetime(new Date());
            tCoupon.setCode(UUID.randomUUID().toString());
            tCoupon.setPicUrl("1.jpg");
            tCoupon.setStatus(0);
            tCoupon.setStock(100L);
            tCoupon.setTitle("测试coupon"+i);
            couponMapper.insert(tCoupon);
        }

    }

    @Test
    public void select(){
        List<TCoupon> tCoupons = couponMapper.selectByExample(new TCouponExample());
        tCoupons.stream().forEach(System.out::println);
    }

    @Test
    public void update(){
        TCoupon tCoupon = couponMapper.selectByPrimaryKey(1);
        tCoupon.setTitle("测试数据");
        couponMapper.updateByPrimaryKey(tCoupon);
    }

    @Test
    public void delete(){
//        couponMapper.deleteByPrimaryKey(2);
    }

}

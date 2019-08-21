package com.myapp.cunpleuserapp.service.dubbo;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.collect.Lists;
import com.myapp.cunpleuserapp.VO.UserVO;
import com.myapp.cunpleuserapp.constant.Constant;
import com.myapp.cunpleuserapp.mapper.TCouponMapper;
import com.myapp.cunpleuserapp.mapper.TUserMapper;
import com.myapp.cunpleuserapp.model.TCoupon;
import com.myapp.cunpleuserapp.model.TCouponExample;
import com.myapp.cunpleuserapp.model.TUser;
import com.myapp.cunpleuserapp.service.IUserService;
import org.springframework.beans.BeanUtils;
import org.apache.dubbo.config.annotation.Service;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * dubbo rpc 接口
 * @Description dubbo rpc 接口
 * @author zhaoxudong
 */
@Service
public class UserService implements IUserService {

    @Resource
    private TUserMapper userMapper;

    @Resource
    private TCouponMapper couponMapper;

    // 引入谷歌的guava 缓存
    private LoadingCache<Integer, List<TCoupon>> cache = CacheBuilder.newBuilder()
            // 缓存失效时间
            .expireAfterWrite(10, TimeUnit.MINUTES)
            // 隔多久获取缓存
            .refreshAfterWrite(5, TimeUnit.MINUTES)
            .build(new CacheLoader<Integer, List<TCoupon>>() {
                @Override
                public List<TCoupon> load(Integer key) throws Exception {
                    return loadCoupons();
                }
            });

    private List<TCoupon> loadCoupons(){
        TCouponExample example = new TCouponExample();
        example.createCriteria().andStatusEqualTo(Constant.USERFUL)
                .andStartTimeLessThan(new Date()).andEndTimeGreaterThan(new Date());
        return couponMapper.selectByExample(example);
    }

    @Override
    public void run() {
        System.err.println("提供对外接口");
    }

    @Override
    public Object getUserById(int id) {
        TUser tUser = userMapper.selectByPrimaryKey(id);
        UserVO userVo = new UserVO();
        BeanUtils.copyProperties(tUser, userVo);
        return userVo;
    }

    @Override
    public List<TCoupon> getCouponList() {
        List<TCoupon> result = null;
        try {
            result = cache.get(1);
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        if(result == null)
            result = Collections.emptyList();
        return result;
    }

    @Override
    public String query() {
        TCouponExample example = new TCouponExample();
        example.createCriteria().andCodeEqualTo("0057da3c-f2ad-42bd-b6d2-8bb58b6dbc90");
        List<TCoupon> tCoupon =  couponMapper.selectByExample(example);
        return tCoupon.get(0).toString();
    }
}

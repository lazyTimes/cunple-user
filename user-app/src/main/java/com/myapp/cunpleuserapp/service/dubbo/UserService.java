package com.myapp.cunpleuserapp.service.dubbo;

import com.alibaba.druid.support.json.JSONUtils;
import com.google.common.base.Splitter;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.apache.dubbo.config.annotation.Service;
import javax.annotation.Resource;
import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

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

    /**
     * 日志
     */
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    /**
     * 分割器
      */
    private static final Splitter SPLITTER = Splitter.on(",").trimResults().omitEmptyStrings();

    /**
     * 引入谷歌的guava 缓存
     */
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


    /**
     * 引入谷歌的guava 缓存,基于ids的缓存策略
     */
    private LoadingCache<Integer, TCoupon> cacheByIds = CacheBuilder.newBuilder()
            // 缓存失效时间
            .expireAfterWrite(10, TimeUnit.MINUTES)
            // 隔多久获取缓存
            .refreshAfterWrite(5, TimeUnit.MINUTES)
            .build(new CacheLoader<Integer, TCoupon>() {
                @Override
                public TCoupon load(Integer key) throws Exception {
                    return loadCouponsbyIds(key);
                }
            });

    /**
     * 根据主键查询内容的缓存查询策略
     * @param id
     * @return
     */
    private TCoupon loadCouponsbyIds(int id) {

        return couponMapper.selectByPrimaryKey(id);
    }

    /**
     * 缓存的更新策略：
     * 查询数据库
     * @return
     */
    private List<TCoupon> loadCoupons(){
        TCouponExample example = new TCouponExample();
        example.createCriteria().andStatusEqualTo(Constant.USERFUL)
                .andStartTimeLessThan(new Date()).andEndTimeGreaterThan(new Date());
        List<TCoupon> tCoupons = couponMapper.selectByExample(example);
        return tCoupons;
    }


    private List<TCoupon> LoadCoupons(String ids){
        List<String> strings = Lists.newArrayList(ids.split(","));
        // 从数据库读出来的
        List<Integer> loadFromDb = new ArrayList<>();
        // 返回结果
        List<TCoupon> result = new ArrayList<>();
        for (String string : strings) {
            TCoupon ifPresent = cacheByIds.getIfPresent(string);
            if(ifPresent==null){
                // 收集缓存中没有的id
                loadFromDb.add(Integer.parseInt(string));
            }else{
                // 缓存当中的数据
                result.add(ifPresent);

            }
        }
        List<TCoupon> tCoupons = couponByIds(loadFromDb);
        Map<Integer, TCoupon> collect = tCoupons.stream().collect(Collectors.toMap(TCoupon::getId, TCoupon -> TCoupon));
        // 合并缓存的数据和数据库查询的数据
        result.addAll(tCoupons);
        cacheByIds.putAll(collect);
        return result;
    }

    /**
     * 根据主键列表查询记录
     * @param ids
     * @return
     */
    private List<TCoupon> couponByIds(List<Integer> ids) {
        TCouponExample example = new TCouponExample();
        example.createCriteria().andIdIn(ids);
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
            logger.error(e.getMessage());
            e.printStackTrace();
        }
        if(result == null) {
            result = Collections.emptyList();
        }
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

package com.myapp.cunpleuserapp.service.impl;

import com.myapp.cunpleuserapp.VO.UserVO;
import com.myapp.cunpleuserapp.mapper.TUserMapper;
import com.myapp.cunpleuserapp.model.TCoupon;
import com.myapp.cunpleuserapp.model.TUser;
import com.myapp.cunpleuserapp.service.IUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 业务层测试
 * @author zhaoxudong
 * @title: MyTestService
 * @projectName cunple
 * @description: 业务层测试
 * @date 2019/8/19 15:18
 */
//@Service
//@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl implements IUserService {

    @Autowired
    private TUserMapper userMapper;

    @Override
    public void run() {
        System.err.println("my run is test Service");
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
        return null;
    }

    @Override
    public String query() {
        return null;
    }
}

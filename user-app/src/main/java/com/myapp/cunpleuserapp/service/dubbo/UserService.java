package com.myapp.cunpleuserapp.service.dubbo;

import com.myapp.cunpleuserapp.VO.UserVO;
import com.myapp.cunpleuserapp.mapper.TUserMapper;
import com.myapp.cunpleuserapp.model.TUser;
import com.myapp.cunpleuserapp.service.IUserService;
import org.springframework.beans.BeanUtils;
import org.apache.dubbo.config.annotation.Service;
import javax.annotation.Resource;

/**
 * dubbo rpc 接口
 * @Description dubbo rpc 接口
 * @author zhaoxudong
 */
@Service
public class UserService implements IUserService {

    @Resource
    private TUserMapper userMapper;


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
}

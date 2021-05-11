package com.xcyf.springcloud.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xcyf.springcloud.entity.XcyfUser;
import com.xcyf.springcloud.mapper.XcyfUserMapper;
import com.xcyf.springcloud.request.LoginRequest;
import com.xcyf.springcloud.request.RegisterRequest;
import com.xcyf.springcloud.response.LoginResponse;
import com.xcyf.springcloud.response.errorcode.UserError;
import com.xcyf.springcloud.service.ILoginService;
import com.xcyf.springcloud.util.BusinessAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author liaojiamin
 * @Date:Created in 15:40 2021/5/11
 */
@Service
public class LoginServiceImpl extends ServiceImpl<XcyfUserMapper, XcyfUser> implements ILoginService {
    @Autowired
    private XcyfUserMapper userMapper;

    @Override
    public LoginResponse login(LoginRequest request) {
        if(request == null || StringUtils.isBlank(request.getPhone()) || StringUtils.isBlank(request.getPassword())){
            BusinessAssert.error(UserError.LOGIN_PARAM_ERROR);
        }
        QueryWrapper<XcyfUser> wrapper = new QueryWrapper();
        wrapper.eq("phone", request.getPhone());
        XcyfUser user = userMapper.selectOne(wrapper);
        if(!user.getPassword().equals(request.getPassword())){
            BusinessAssert.error(UserError.LOGIN_PARAM_ERROR);
        }
        LoginResponse loginRespons = new LoginResponse();
        loginRespons.setUserInfo(user);
        return loginRespons;
    }

    @Override
    public Boolean register(RegisterRequest request) {
        if(request == null || StringUtils.isBlank(request.getPhone()) || StringUtils.isBlank(request.getPassword())){
            BusinessAssert.error(UserError.REGISTER_PARAM_ERROR);
        }
        return userMapper.insert(buildUserEntity(request.getPhone(), request.getPassword())) > 0;
    }
    public XcyfUser buildUserEntity(String phone, String password){
        Date date = new Date();
        XcyfUser user = new XcyfUser();
        user.setBirthday(date);
        user.setCreateTime(date);
        user.setLastRegisterTime(date);
        user.setPhone(phone);
        user.setPassword(password);
        user.setUserType(1);
        user.setStatus(1);
        user.setUpdateTime(date);
        return user;
    }
}

package com.xcyf.springcloud.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xcyf.springcloud.entity.XcyfUser;
import com.xcyf.springcloud.request.PageListUserRequest;
import com.xcyf.springcloud.request.UserRequest;
import com.xcyf.springcloud.response.BaseResponse;
import com.xcyf.springcloud.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * 代码生成器，参考源码测试用例：
 * <p>
 * /mybatis-plus/src/test/java/com/baomidou/mybatisplus/test/generator/MysqlGenerator.java
 */
@RestController
@RequestMapping("/user")
public class UserController extends ApiController {

    @Autowired
    private IUserService userService;

    @RequestMapping("/addUser")
    public BaseResponse addUser(UserRequest request){
        return userService.addUser(request);
    }

    @RequestMapping("/delUser")
    public BaseResponse delUser(Long userID){
       return userService.delUser(userID);
    }

    @RequestMapping("/updateUser")
    public BaseResponse updateUser(UserRequest request){
        return userService.updateUser(request);
    }

    @RequestMapping("/pageList")
    public IPage<XcyfUser> pageList(PageListUserRequest request){
        return userService.pageList(request);
    }
}

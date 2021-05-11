package com.xcyf.springcloud.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xcyf.springcloud.entity.XcyfUser;
import com.xcyf.springcloud.request.PageListUserRequest;
import com.xcyf.springcloud.request.UserRequest;
import com.xcyf.springcloud.response.BaseResponse;

/**
 *
 * User 表数据服务层接口
 *
 */
public interface IUserService extends IService<XcyfUser> {

	BaseResponse addUser(UserRequest request);

	BaseResponse delUser(Long userID);

	BaseResponse updateUser(UserRequest request);

	IPage<XcyfUser> pageList(PageListUserRequest request);
}
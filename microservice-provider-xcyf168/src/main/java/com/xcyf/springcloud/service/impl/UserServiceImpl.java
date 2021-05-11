package com.xcyf.springcloud.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xcyf.springcloud.Enums.UserStatusEnum;
import com.xcyf.springcloud.entity.XcyfUser;
import com.xcyf.springcloud.mapper.XcyfUserMapper;
import com.xcyf.springcloud.request.PageListUserRequest;
import com.xcyf.springcloud.request.UserRequest;
import com.xcyf.springcloud.response.BaseResponse;
import com.xcyf.springcloud.response.errorcode.HeadCode;
import com.xcyf.springcloud.response.errorcode.UserError;
import com.xcyf.springcloud.service.IUserService;
import com.xcyf.springcloud.util.BusinessAssert;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 *
 * User 表数据服务层接口实现类
 *
 */
@Service
public class UserServiceImpl extends ServiceImpl<XcyfUserMapper, XcyfUser> implements IUserService {

	@Autowired
	private XcyfUserMapper userMapper;

	@Override
	public BaseResponse addUser(UserRequest request) {
		if(request == null || StringUtils.isBlank(request.getPhone()) || StringUtils.isBlank(request.getPassword())){
			BusinessAssert.error(UserError.PARAM_ERROR);
		}
		Date date = new Date();
		XcyfUser xcyfUser = new XcyfUser();
		BeanUtils.copyProperties(request, xcyfUser);
		xcyfUser.setCreateTime(date);
		xcyfUser.setStatus(1);
		xcyfUser.setUpdateTime(date);
		xcyfUser.setLastRegisterTime(date);
		boolean insert = userMapper.insert(xcyfUser) > 0;
		if(insert){
			BusinessAssert.error(UserError.ADDUSER_PARAM_ERROR);
		}
		BaseResponse response = new BaseResponse();
		response.setCode(HeadCode.SUCCESS);
		response.setMessage("SUCCESS");
		return response;
	}

	@Override
	public BaseResponse delUser(Long userID) {
		if(userID == null){
			BusinessAssert.error(UserError.PARAM_ERROR);
		}
		userMapper.deleteById(userID);
		BaseResponse response = new BaseResponse();
		response.setCode(HeadCode.SUCCESS);
		response.setMessage("SUCCESS");
		return response;
	}

	@Override
	public BaseResponse updateUser(UserRequest request) {
		if(request == null || request.getUserID() == null){
			BusinessAssert.error(UserError.UPDATE_FAIL);
		}
		Date date = new Date();
		XcyfUser xcyfUser = new XcyfUser();
		BeanUtils.copyProperties(request, xcyfUser);
		xcyfUser.setUpdateTime(date);
		userMapper.updateById(xcyfUser);
		BaseResponse response = new BaseResponse();
		response.setCode(HeadCode.SUCCESS);
		response.setMessage("SUCCESS");
		return response;
	}

	@Override
	public IPage<XcyfUser> pageList(PageListUserRequest request) {
		if(request == null || request.getUserID() == null){
			BusinessAssert.error(UserError.PARAM_ERROR);
		}
		Integer page = request.getPage() > 0 ? request.getPage() : 1;
		Integer pageSize = request.getPageSize() > 0 && request.getPageSize() < 500 ? request.getPageSize() : 20;
		XcyfUser user = userMapper.selectById(request.getUserID());
		QueryWrapper<XcyfUser> wrapper = new QueryWrapper<>();
		if(UserStatusEnum.ORDINARY.getValue() == user.getUserType()){
			wrapper.eq("userID", user.getUserID());
		}
		IPage<XcyfUser>  pageLIst = userMapper.selectPage(new Page<XcyfUser>(page, pageSize), wrapper );
		return pageLIst;
	}
}
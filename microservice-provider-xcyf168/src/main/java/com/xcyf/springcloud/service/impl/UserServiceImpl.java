package com.xcyf.springcloud.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xcyf.springcloud.entity.XcyfUser;
import com.xcyf.springcloud.mapper.XcyfUserMapper;
import com.xcyf.springcloud.service.IUserService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 *
 * User 表数据服务层接口实现类
 *
 */
@Service
public class UserServiceImpl extends ServiceImpl<XcyfUserMapper, XcyfUser> implements IUserService {


	@Override
	public boolean deleteById(Long userID) {
		return retBool(baseMapper.deleteById(userID));
	}

	@Override
	public List<XcyfUser> selectByIDs(List<Long> userIDs) {
		return baseMapper.selectBatchIds(userIDs);
	}

	@Override
	public List<XcyfUser> selectListByWrapper() {
		LambdaQueryWrapper<XcyfUser> queryWrapper = new QueryWrapper<XcyfUser>()
				.lambda().like(XcyfUser::getUserID, 1)
				.or(e -> e.like(XcyfUser::getStatus, 1));
		return baseMapper.selectList(queryWrapper);
	}

	@Override
	public void insertUser(Long userID) {
		XcyfUser xcyfUser = buildUserEntity();
		baseMapper.insert(xcyfUser);
	}

	public XcyfUser buildUserEntity(){
		Date date = new Date();
		XcyfUser user = new XcyfUser();
		user.setAddress("1111");
		user.setBirthday(date);
		user.setCreateTime(date);
		user.setEmail("645121107@qq.com");
		user.setGender(1);
		user.setLastRegisterTime(date);
		user.setNickName("ljmadmin");
		user.setPhone("18594208134");
		user.setUserType(1);
		user.setStatus(1);
		return user;
	}
}
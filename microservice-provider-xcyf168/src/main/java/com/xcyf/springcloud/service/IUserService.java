package com.xcyf.springcloud.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xcyf.springcloud.entity.XcyfUser;

import java.util.List;

/**
 *
 * User 表数据服务层接口
 *
 */
public interface IUserService extends IService<XcyfUser> {

	boolean deleteById(Long userID);

	public List<XcyfUser> selectByIDs(List<Long> userIDs);

	public List<XcyfUser> selectListByWrapper();

	public void insertUser(Long userID);
}
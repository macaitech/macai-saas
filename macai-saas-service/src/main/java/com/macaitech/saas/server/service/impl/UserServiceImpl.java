package com.macaitech.saas.server.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.macaitech.saas.server.entity.User;
import com.macaitech.saas.server.mapper.UserMapper;
import com.macaitech.saas.server.service.IUserService;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Mht
 * @since 2019-08-07
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
	
	@Autowired
	private UserMapper userMapper ;
	
	@Override
	public List<User> findDeptUsers() {
		return userMapper.findDeptUsers();
	}
	
}

package com.macaitech.saas.server.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.macaitech.saas.server.annotation.SaasDataSource;
import com.macaitech.saas.server.entity.User;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Mht
 * @since 2019-08-07
 */
@SaasDataSource
public interface IUserService extends IService<User> {
	
	public List<User> findDeptUsers();
}

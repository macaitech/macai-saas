package com.macaitech.saas.server.mapper;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.macaitech.saas.server.annotation.SaasDataSource;
import com.macaitech.saas.server.datasource.DataSourceType;
import com.macaitech.saas.server.entity.User;

/**
 * 
 * @author yuhui.tang
 *
 */
@SaasDataSource(type=DataSourceType.Biz_Tenant)
public interface UserMapper extends BaseMapper<User> {

	public List<User> findDeptUsers();
}

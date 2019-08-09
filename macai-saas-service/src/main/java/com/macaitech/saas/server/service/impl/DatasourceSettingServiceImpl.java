package com.macaitech.saas.server.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.macaitech.saas.server.entity.DatasourceSetting;
import com.macaitech.saas.server.mapper.DatasourceSettingMapper;
import com.macaitech.saas.server.service.IDatasourceSettingService;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author Mht
 * @since 2019-08-09
 */
@Service
public class DatasourceSettingServiceImpl extends ServiceImpl<DatasourceSettingMapper, DatasourceSetting>
		implements IDatasourceSettingService {

	@Override
	public List<DatasourceSetting> findAll() {
		DatasourceSetting datasourceSetting = new DatasourceSetting();
		QueryWrapper<DatasourceSetting> queryWrapper = new QueryWrapper<>(datasourceSetting);
		List<DatasourceSetting> list = super.list(queryWrapper);
		return list;
	}
	
}

package com.macaitech.saas.server.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.macaitech.saas.server.entity.DatasourceSetting;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Mht
 * @since 2019-08-09
 */
public interface IDatasourceSettingService extends IService<DatasourceSetting> {

	public List<DatasourceSetting> findAll();
}

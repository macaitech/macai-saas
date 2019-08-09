package com.macaitech.saas.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.macaitech.saas.server.annotation.SaasDataSource;
import com.macaitech.saas.server.datasource.DataSourceType;
import com.macaitech.saas.server.entity.DatasourceSetting;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Mht
 * @since 2019-08-09
 */
@SaasDataSource(type=DataSourceType.Main)
public interface DatasourceSettingMapper extends BaseMapper<DatasourceSetting> {

}

/**
 * 
 */
package com.macaitech.saas.server.datasource;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * @author yuhui.tang
 *
 */
public class DynamicDataSource extends AbstractRoutingDataSource {

	protected Logger logger = LoggerFactory.getLogger(getClass());

    private static Map<Object, Object> datasourceMap = new ConcurrentHashMap<>();

    @Override
    protected Object determineCurrentLookupKey() {
    	Object object =DynamicDatasourceHolder.get(); 
    	System.out.println("current dataSourceId:" + object.toString());
        return DynamicDatasourceHolder.get();
    }

    public DataSource createDatasource(String id, String url, String username, String password, String driverClassName) {
        DataSource dataSource = DataSourceBuilder.create()
                .url(url)
                .username(username)
                .password(password)
                .driverClassName(driverClassName)
                .build();
        if (dataSource != null) {
            DataSourceUtil.put(id, id);
            datasourceMap.put(id, dataSource);
            //调用父类方法赋值Map<Object, DataSource> resolvedDataSources
            super.setTargetDataSources(datasourceMap);
            super.afterPropertiesSet();
            return dataSource;
        }
        return null;
    }
    
    public boolean containsDataSource(String key) {
    	return datasourceMap.containsKey(key);
    }
    
    public DataSource getDataSource(String key) {
    	return (DataSource) datasourceMap.get(key);
    }
}


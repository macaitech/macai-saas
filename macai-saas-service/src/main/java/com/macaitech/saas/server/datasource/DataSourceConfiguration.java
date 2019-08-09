/**
 * 
 */
package com.macaitech.saas.server.datasource;
import javax.sql.DataSource;

import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.MybatisConfiguration;
import com.baomidou.mybatisplus.core.config.GlobalConfig;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;


@Configuration
@MapperScan("com.macaitech.saas.server.mapper")
public class DataSourceConfiguration implements ApplicationContextAware {

	protected Logger logger = LoggerFactory.getLogger(getClass());

    private ApplicationContext applicationContext;
    
    @Bean( "dataSource")
    public  DataSource dataSource() {
        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        
        DataSourceType[] dataSourceTypeList =   DataSourceType.values();
        for (DataSourceType dataSourceType : dataSourceTypeList) {
       	 	if(dataSourceType==DataSourceType.Main) {
       	 		DataSource dataSource =dynamicDataSource.createDatasource(dataSourceType.Main.value(), 
             		"jdbc:mysql://localhost:3306/saas_main?useUnicode=true&characterEncoding=UTF-8&useSSL=false&autoReconnect=true&failOverReadOnly=false",
                     "root", "123456", "com.mysql.cj.jdbc.Driver");
        		 dynamicDataSource.setDefaultTargetDataSource(dataSource);
        	 }
		}
        return dynamicDataSource;
    }

    @Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}


	/**
     * Mybatis 配置
     *
     * @param metaObjectHandler 填充器
     * @return SqlSessionFactory
     * @throws Exception 异常
     */
    @Bean("sqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(PaginationInterceptor paginationInterceptor) throws Exception {
        MybatisSqlSessionFactoryBean sqlSessionFactory = new MybatisSqlSessionFactoryBean();
        // 配置多数据源
        DataSource dataSource = (DataSource) this.applicationContext.getBean("dataSource");
        sqlSessionFactory.setDataSource(dataSource);
        sqlSessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath*:mapper/*.xml"));
        // 实体扫描，多个package用逗号或者分号分隔
        sqlSessionFactory.setTypeAliasesPackage("com.macaitech.saas.server.entity");
        GlobalConfig.DbConfig dbConfig = new GlobalConfig.DbConfig();
        // 主键类型  AUTO:"数据库ID自增", INPUT:"用户输入ID",ID_WORKER:"全局唯一ID (数字类型唯一ID)", UUID:"全局唯一ID UUID";
        dbConfig.setIdType(IdType.AUTO);
        // 字段策略 IGNORED:"忽略判断",NOT_NULL:"非 NULL 判断"),NOT_EMPTY:"非空判断"
        dbConfig.setFieldStrategy(FieldStrategy.NOT_NULL);
        // 数据库大写下划线转换
        dbConfig.setCapitalMode(true);
        // 逻辑删除配置
        dbConfig.setLogicDeleteValue("1");
        dbConfig.setLogicNotDeleteValue("0");
        dbConfig.setDbType(DbType.MYSQL);

        GlobalConfig globalConfig = new GlobalConfig();
        //#刷新mapper 调试神器
        globalConfig.setRefresh(true);
        globalConfig.setDbConfig(dbConfig);
        //globalConfig.setMetaObjectHandler(metaObjectHandler);
        sqlSessionFactory.setGlobalConfig(globalConfig);

        MybatisConfiguration configuration = new MybatisConfiguration();
        configuration.setMapUnderscoreToCamelCase(true);
        configuration.setCacheEnabled(false);
        configuration.setCallSettersOnNulls(true);
        sqlSessionFactory.setConfiguration(configuration);

        sqlSessionFactory.setPlugins(new Interceptor[]{
                //添加分页功能
                paginationInterceptor
        });
        return sqlSessionFactory.getObject();
    }


    @Bean
    DataSourceTransactionManager dataSourceTransactionManager(DataSource dataSource) {
        DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager();
        dataSourceTransactionManager.setDataSource(dataSource);
        return dataSourceTransactionManager;
    }

}

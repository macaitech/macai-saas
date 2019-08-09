package com.macaitech.saas.server.datasource;

import javax.sql.DataSource;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.macaitech.saas.server.annotation.SaasDataSource;
import com.macaitech.saas.server.context.TenantContext;

@Component
@Aspect
@Order(-1)
public class DynamicDataSourceAOP implements ApplicationContextAware {

	protected Logger logger = LoggerFactory.getLogger(getClass());

	private ApplicationContext applicationContext;

	@Pointcut("execution(* com.macaitech.saas.server.mapper..*.*(..))")
	public void pointCut() {
		logger.info("-----test-------");
	}

	/**
	 * 执行方法前更换数据源
	 *
	 * @param joinPoint
	 *            切点
	 */
	@Before("pointCut()")
	public void doBefore(JoinPoint joinPoint) {
		DynamicDataSource dynamicDataSource = this.applicationContext.getBean(DynamicDataSource.class);
		SaasDataSource saasDataSource = null;//
		Class<?> target = joinPoint.getTarget().getClass();
		MethodSignature signature = (MethodSignature) joinPoint.getSignature();
		for (Class<?> clazz : target.getInterfaces()) {
			saasDataSource = resolveDataSource(clazz);
		}
		if(saasDataSource==null) {//主库
			DynamicDatasourceHolder.set(DataSourceType.Main.value());
		}
		Long tenantId = TenantContext.getTenantId();
		String datasouceType = saasDataSource.type().value();
		String dataSourceKey = null;
		String dataBaseName = "test";
		if(tenantId==null || tenantId.longValue()==1L) {
			dataSourceKey = datasouceType;
		}
		else {
			dataSourceKey = datasouceType+"_"+tenantId.longValue();
			dataBaseName = "test_2";
		}
		if(!dynamicDataSource.containsDataSource(dataSourceKey)) {
			// 创建DataSource
			dynamicDataSource.createDatasource(dataSourceKey,
				"jdbc:mysql://localhost:3306/"+dataBaseName+"?useUnicode=true&characterEncoding=UTF-8&useSSL=false&autoReconnect=true&failOverReadOnly=false",
				"root", "123456", "com.mysql.cj.jdbc.Driver");
		}
		DynamicDatasourceHolder.set(dataSourceKey);
	}

	private SaasDataSource resolveDataSource(Class<?> clazz) {
		try {
			if (clazz.isAnnotationPresent(SaasDataSource.class)) {
				SaasDataSource saasDataSource = clazz.getAnnotation(SaasDataSource.class);
				return saasDataSource;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 执行方法后清除数据源设置
	 *
	 * @param joinPoint
	 *            切点
	 */
	@After(value = "pointCut() && args(dataSource)")
	public void doAfter(JoinPoint joinPoint, DataSource dataSource) {
		DynamicDatasourceHolder.clear();
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}
}
package com.macaitech.saas.server.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.macaitech.saas.server.datasource.DataSourceType;

@Inherited
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface SaasDataSource {
	DataSourceType type() default DataSourceType.Main;
}

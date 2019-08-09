package com.macaitech.saas.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * 
 * @author yuhui.tang
 *
 */
//@SpringBootApplication
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class MacaiSaasApp {
	private static Logger logger = LoggerFactory.getLogger(MacaiSaasApp.class);
	
	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(MacaiSaasApp.class);
		app.run(args);
		logger.info("MacaiSaasApp 启动");
	}
	
}

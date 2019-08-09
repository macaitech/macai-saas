/**
 * 
 */
package com.macaitech.saas.server.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.macaitech.saas.server.handler.SaasTenantInterceptor;

/**
 * @author yuhui.tang
 *
 */

@Configuration
public class SpringWebConfig implements  WebMvcConfigurer  {

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new SaasTenantInterceptor());
		WebMvcConfigurer.super.addInterceptors(registry);
	}


}

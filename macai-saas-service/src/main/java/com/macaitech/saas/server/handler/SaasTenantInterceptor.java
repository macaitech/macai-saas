/**
 * 
 */
package com.macaitech.saas.server.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.macaitech.saas.server.context.TenantContext;

/**
 * @author yuhui.tang
 * 多租户Id拦截
 */
@Component
public class SaasTenantInterceptor implements HandlerInterceptor {

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		TenantContext.removeTenant();
		HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		Long tenantId = this.getTenantId(request);
		TenantContext.setTenatnId(tenantId);
		return HandlerInterceptor.super.preHandle(request, response, handler);
	}

	private Long getTenantId(HttpServletRequest request) {
		String id = null;
		String[] values = request.getParameterValues("tenantId");
		if(values!=null && values.length>0) {
			id = values[0];
		}
		if(!StringUtils.isEmpty(id)) {
			try {
				return Long.parseLong(id);
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
}

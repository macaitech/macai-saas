/**
 * 
 */
package com.macaitech.saas.server.handler;

import org.springframework.stereotype.Component;

import com.baomidou.mybatisplus.extension.plugins.tenant.TenantHandler;
import com.macaitech.saas.server.context.TenantContext;
import com.macaitech.saas.server.datasource.DataSourceType;
import com.macaitech.saas.server.datasource.DynamicDatasourceHolder;

import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.LongValue;

/**
 * @author yuhui.tang
 *
 */
@Component
public class SaasTenantHandler implements TenantHandler {

	/* (non-Javadoc)
	 * @see com.baomidou.mybatisplus.extension.plugins.tenant.TenantHandler#getTenantId()
	 */
	@Override
	public Expression getTenantId() {
		Expression expression = new LongValue(TenantContext.getTenantId());
		return expression;
	}

	/* (non-Javadoc)
	 * @see com.baomidou.mybatisplus.extension.plugins.tenant.TenantHandler#getTenantIdColumn()
	 */
	@Override
	public String getTenantIdColumn() {
		 return "tenantId";
	}

	/* (non-Javadoc)
	 * @see com.baomidou.mybatisplus.extension.plugins.tenant.TenantHandler#doTableFilter(java.lang.String)
	 */
	@Override
	public boolean doTableFilter(String tableName) {
		Object object =DynamicDatasourceHolder.get(); 
		if(object==null) {
			return false;
		}
		else {
			String datasouceType = object.toString();
			if(datasouceType.equals(DataSourceType.Main.value())) {
				return true;
			}
		}
		return false;
	}

}

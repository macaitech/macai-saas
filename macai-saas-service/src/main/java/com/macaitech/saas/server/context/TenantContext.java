/**
 * 
 */
package com.macaitech.saas.server.context;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yuhui.tang 多租户上下文
 */
public class TenantContext {
	public static final String Key_Tenant_Id = "tenantId";
	private static final ThreadLocal<Map<String, Object>> contextData = new ThreadLocal<>();

	public static void setTenatnId(Long tenantId) {
		Map<String, Object> map = contextData.get();
		if (map == null) {
			map = new HashMap<>();
		}
		map.put(Key_Tenant_Id, tenantId);
		contextData.set(map);
	}

	public static Long getTenantId() {
		if (!contextData.get().containsKey(Key_Tenant_Id))
			return null;
		Object object = contextData.get().get(Key_Tenant_Id);
		if (object == null)
			return null;
		return Long.parseLong(object.toString());
	}

	public static void removeTenant() {
		if (contextData.get().containsKey(Key_Tenant_Id)) {
			contextData.get().remove(Key_Tenant_Id);
		}
	}
}

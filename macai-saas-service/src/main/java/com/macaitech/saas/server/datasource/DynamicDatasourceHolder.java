/**
 * 
 */
package com.macaitech.saas.server.datasource;

/**
 * @author yuhui.tang
 *
 */
public class DynamicDatasourceHolder {
	
	private static final ThreadLocal<String> DATASOURCE_HOLDER = new ThreadLocal<>();


    public static void set(String datasource) {
        DATASOURCE_HOLDER.set(datasource);
    }

    public static String get() {
        return DATASOURCE_HOLDER.get();
    }

    public static void clear() {
        DATASOURCE_HOLDER.remove();
    }
    
}

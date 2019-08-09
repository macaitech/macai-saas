/**
 * 
 */
package com.macaitech.saas.server.datasource;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class DataSourceUtil {

	private static final Map<String, String> dataSourceMap = new ConcurrentHashMap<>();

	private DataSourceUtil() {
	}

	public static void put(String projectId, String dataSource) {
		dataSourceMap.put(projectId, dataSource);
	}

	public static boolean contains(String projectId) {
		return dataSourceMap.get(projectId) != null;
	}

	public static String getDataSourceId(String projectId) {
		return dataSourceMap.get(projectId);
	}
}
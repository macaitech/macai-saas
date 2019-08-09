/**
 * 
 */
package com.macaitech.saas.server.datasource;

/**
 * 
 * @author yuhui.tang
 *
 */
public enum DataSourceType {
	Main("main"),
	Biz_Tenant("biz_tenant");
	
	
	 private String type;

    /**
     * 私有构造,防止被外部调用
     * @param desc
     */
    private DataSourceType(String type){
        this.type=type;
    }

    public String value() {
		return this.type;
    }
    /**
     * 覆盖
     * @return
     */
    @Override
    public String toString() {
        return type;
    }
}

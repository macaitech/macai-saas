package com.macaitech.saas.server.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author Mht
 * @since 2019-08-09
 */
@TableName("saas_datasource_setting")
public class DatasourceSetting extends Model<DatasourceSetting> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键Id
     */
    @TableId(value = "Id", type = IdType.AUTO)
    private Integer Id;

    /**
     * 租户Id,如果有值，则lazy加载
     */
    private String tenantId;

    /**
     * 数据源类型
     */
    private String dataSourceType;

    private String jdbcUrl;

    private String driverClassName;

    private String userName;

    private String password;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    public Integer getId() {
        return Id;
    }

    public void setId(Integer Id) {
        this.Id = Id;
    }
    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }
    public String getDataSourceType() {
        return dataSourceType;
    }

    public void setDataSourceType(String dataSourceType) {
        this.dataSourceType = dataSourceType;
    }
    public String getJdbcUrl() {
        return jdbcUrl;
    }

    public void setJdbcUrl(String jdbcUrl) {
        this.jdbcUrl = jdbcUrl;
    }
    public String getDriverClassName() {
        return driverClassName;
    }

    public void setDriverClassName(String driverClassName) {
        this.driverClassName = driverClassName;
    }
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }
    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    protected Serializable pkVal() {
        return this.Id;
    }

    @Override
    public String toString() {
        return "DatasourceSetting{" +
        "Id=" + Id +
        ", tenantId=" + tenantId +
        ", dataSourceType=" + dataSourceType +
        ", jdbcUrl=" + jdbcUrl +
        ", driverClassName=" + driverClassName +
        ", userName=" + userName +
        ", password=" + password +
        ", createTime=" + createTime +
        ", updateTime=" + updateTime +
        "}";
    }
}

package com.macaitech.saas.server.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;

/**
 * <p>
 * 
 * </p>
 *
 * @author Mht
 * @since 2019-08-07
 */
@TableName("test_user")
public class User extends Model<User> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "Id", type = IdType.AUTO)
    private Integer id;

    private String userName;

    private Date userTime;

    private Date createTime;
    
    private String deptName;
    
    private int tenantId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer Id) {
        this.id = Id;
    }

	public String getUserName() {
        return userName;
    }

	public void setUserName(String userName) {
        this.userName = userName;
    }

	public Date getUserTime() {
        return userTime;
    }

    public void setUserTime(Date UserTime) {
        this.userTime = UserTime;
    }

	public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date CreateTime) {
        this.createTime = CreateTime;
    }
    
    public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	@Override
    protected Serializable pkVal() {
        return this.id;
    }

	
	
    public int getTenantId() {
		return tenantId;
	}

	public void setTenantId(int tenantId) {
		this.tenantId = tenantId;
	}

	@Override
    public String toString() {
        return "User{" +
            "Id=" + id +
            ", UserName=" + userName +
            ", UserTime=" + userTime +
            ", CreateTime=" + createTime +
        "}";
    }
}

package com.revolver.survey.admin.entity;

import java.util.Set;

/**
 * 
 * @author REVOLVER2016年2月26日
 *
 */
public class Admin {
	private Integer adminId;
	private String adminName;
	private String adminPwd;
	private Set<Role> roleSet;
	private String authorityStr;
	
	public Admin() {

	}

	/**
	 * @param adminName
	 * @param adminPwd
	 */
	public Admin(String adminName, String adminPwd) {
		super();
		this.adminName = adminName;
		this.adminPwd = adminPwd;
	}


	public Integer getAdminId() {
		return adminId;
	}

	public void setAdminId(Integer adminId) {
		this.adminId = adminId;
	}

	public String getAdminName() {
		return adminName;
	}

	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}

	public String getAdminPwd() {
		return adminPwd;
	}

	public void setAdminPwd(String adminPwd) {
		this.adminPwd = adminPwd;
	}

	public String getAuthorityStr() {
		return authorityStr;
	}

	public void setAuthorityStr(String authorityStr) {
		this.authorityStr = authorityStr;
	}

	public Set<Role> getRoleSet() {
		return roleSet;
	}

	public void setRoleSet(Set<Role> roleSet) {
		this.roleSet = roleSet;
	}

	@Override
	public String toString() {
		return "Admin [adminName=" + adminName + ", authorityStr=" + authorityStr + "]";
	}
	
}

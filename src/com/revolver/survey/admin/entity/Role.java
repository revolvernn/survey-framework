/**
 * 
 */
package com.revolver.survey.admin.entity;

import java.util.Set;

/**
 * @author REVOLVER2016年2月29日
 *
 */
public class Role {
	private Integer roleId;
	private String roleName;
	
	private Set<Authority> authSet;
	/**
	 * 
	 */
	public Role() {
		// TODO Auto-generated constructor stub
	}
	/**
	 * @param roleId
	 * @param roleName
	 */
	public Role(Integer roleId, String roleName) {
		super();
		this.roleId = roleId;
		this.roleName = roleName;
	}
	public Integer getRoleId() {
		return roleId;
	}
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public Set<Authority> getAuthSet() {
		return authSet;
	}
	public void setAuthSet(Set<Authority> authSet) {
		this.authSet = authSet;
	}
	@Override
	public String toString() {
		return "Role [roleId=" + roleId + ", roleName=" + roleName + "]";
	}
}

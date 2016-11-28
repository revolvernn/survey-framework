/**
 * 
 */
package com.revolver.survey.admin.entity;

import java.util.Set;

/**
 * @author REVOLVER2016年2月29日
 *
 */
public class Authority {
	private Integer authorityId;
	private String authorityName;
	private Set<Resource> resourceSet;
	
	public Integer getAuthorityId() {
		return authorityId;
	}
	public void setAuthorityId(Integer authorityId) {
		this.authorityId = authorityId;
	}
	public String getAuthorityName() {
		return authorityName;
	}
	public void setAuthorityName(String authorityName) {
		this.authorityName = authorityName;
	}
	public Authority() {
		// TODO Auto-generated constructor stub
	}
	public Set<Resource> getResourceSet() {
		return resourceSet;
	}
	public void setResourceSet(Set<Resource> resourceSet) {
		this.resourceSet = resourceSet;
	}
	@Override
	public String toString() {
		return "Authority [authorityId=" + authorityId + ", authorityName=" + authorityName + "]";
	}
	
}

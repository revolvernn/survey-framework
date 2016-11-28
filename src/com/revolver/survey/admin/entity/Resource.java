/**
 * 
 */
package com.revolver.survey.admin.entity;

/**
 * @author REVOLVER2016年2月27日 资源实体类
 */
public class Resource {
	// OID
	private Integer resourceId;

	// action名字
	private String actionName;

	// actionName 翻译后的对应资源名字
	private String resourceName;

	// 资源点
	private Integer resPos;

	// 资源码
	private Long resCode;

	public Resource() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param actionName
	 * @param resourceName
	 * @param resPos
	 * @param resCode
	 */
	public Resource(String actionName, String resourceName, int resPos, Long resCode) {
		super();
		this.actionName = actionName;
		this.resourceName = resourceName;
		this.resPos = resPos;
		this.resCode = resCode;
	}

	public Integer getResourceId() {
		return resourceId;
	}

	public void setResourceId(Integer resourceId) {
		this.resourceId = resourceId;
	}

	public String getActionName() {
		return actionName;
	}

	public void setActionName(String actionName) {
		this.actionName = actionName;
	}

	public String getResourceName() {
		return resourceName;
	}

	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}

	public Integer getResPos() {
		return resPos;
	}

	public void setResPos(Integer resPos) {
		this.resPos = resPos;
	}

	public Long getResCode() {
		return resCode;
	}

	public void setResCode(Long resCode) {
		this.resCode = resCode;
	}

	@Override
	public String toString() {
		return "Resource [resourceName=" + resourceName + ", resPos=" + resPos + ", resCode=" + resCode + "]";
	}
}

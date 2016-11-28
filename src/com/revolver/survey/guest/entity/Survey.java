package com.revolver.survey.guest.entity;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

/**
 * 调查类
 * 
 * @author REVOLVER
 * 
 */
public class Survey implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer surveyId;
	private String title;
	private String logoPath = "/resources_static/logo.gif";
	private User user;

	private Set<Bag> bagSet;

	private boolean completed;
	private Date completedTime;

	private Integer minOrder;
	private Integer maxOrder;

	public Survey() {
	}

	/**
	 * @param surveyId
	 * @param title
	 * @param logoPath
	 * @param completedTime
	 */
	public Survey(Integer surveyId, String title, String logoPath, Date completedTime) {
		super();
		this.surveyId = surveyId;
		this.title = title;
		this.logoPath = logoPath;
		this.completedTime = completedTime;
	}

	public Integer getSurveyId() {
		return surveyId;
	}

	public void setSurveyId(Integer surveyId) {
		this.surveyId = surveyId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getLogoPath() {
		return logoPath;
	}

	public void setLogoPath(String logoPath) {
		this.logoPath = logoPath;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public boolean isCompleted() {
		return completed;
	}

	public void setCompleted(boolean completed) {
		this.completed = completed;
	}

	public Set<Bag> getBagSet() {
		return bagSet;
	}

	public void setBagSet(Set<Bag> bagSet) {
		this.bagSet = bagSet;
	}

	public Date getCompletedTime() {
		return completedTime;
	}

	public void setCompletedTime(Date completedTime) {
		this.completedTime = completedTime;
	}

	public String getFormatedTime() {

		SimpleDateFormat format = new SimpleDateFormat("yyy年MM月dd HH:mm:ss");
		String format2 = "";
		if (completedTime != null) {
			format2 = format.format(completedTime);
		}

		return format2;
	}

	public Integer getMinOrder() {
		return minOrder;
	}

	public void setMinOrder(Integer minOrder) {
		this.minOrder = minOrder;
	}

	public Integer getMaxOrder() {
		return maxOrder;
	}

	public void setMaxOrder(Integer maxOrder) {
		this.maxOrder = maxOrder;
	}

	@Override
	public String toString() {
		return "Survey [surveyId=" + surveyId + ", title=" + title + ", completed=" + completed + ", completedTime=" + completedTime + "]";
	}
}

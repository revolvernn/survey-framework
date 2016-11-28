package com.revolver.survey.guest.entity;

import java.io.Serializable;
import java.util.Set;

/**
 * 包裹类
 * 
 * @author REVOLVER
 * 
 */
public class Bag implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer bagId;
	private String bagName;
	private transient Survey survey;
	private Set<Question> questionSet;
	private int bagOrder;

	public Bag() {
	}

	public Integer getBagId() {
		return bagId;
	}

	public void setBagId(Integer bagId) {
		this.bagId = bagId;
	}

	public String getBagName() {
		return bagName;
	}

	public void setBagName(String bagName) {
		this.bagName = bagName;
	}

	public Survey getSurvey() {
		return survey;
	}

	public void setSurvey(Survey survey) {
		this.survey = survey;
	}

	public Set<Question> getQuestionSet() {
		return questionSet;
	}

	public void setQuestionSet(Set<Question> questionSet) {
		this.questionSet = questionSet;
	}

	public int getBagOrder() {
		return bagOrder;
	}

	public void setBagOrder(int bagOrder) {
		this.bagOrder = bagOrder;
	}

	@Override
	public String toString() {
		return "Bag [ bagName=" + bagName + "]";
	}
}

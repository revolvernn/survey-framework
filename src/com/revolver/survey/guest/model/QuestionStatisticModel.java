package com.revolver.survey.guest.model;

import java.util.List;

/**
 * 
 * @author REVOLVER
 * 
 */
public class QuestionStatisticModel {
	private String questionName;
	private int totalCount;
	private List<OptionStatisticModel> osmList;

	public QuestionStatisticModel() {
	}

	public String getQuestionName() {
		return questionName;
	}

	public void setQuestionName(String questionName) {
		this.questionName = questionName;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public List<OptionStatisticModel> getOsmList() {
		return osmList;
	}

	public void setOsmList(List<OptionStatisticModel> osmList) {
		this.osmList = osmList;
	}
}

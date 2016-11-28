package com.revolver.survey.guest.entity;

import java.util.Date;

/**
 * 
 * @author REVOLVER
 * 
 */
public class Answer {
	private Integer answerId;
	private Integer surveyId;
	private Integer questionId;

	private Date answerTime;

	private String uuid;

	private String mainAnswers;

	private String otherAnswers;

	public Answer() {

	}

	/**
	 * @param surveyId
	 * @param questionId
	 * @param answerTime
	 * @param uuid
	 * @param mainAnswers
	 * @param otherAnswers
	 */
	public Answer(Integer surveyId, Integer questionId, Date answerTime, String uuid, String mainAnswers, String otherAnswers) {
		super();
		this.surveyId = surveyId;
		this.questionId = questionId;
		this.answerTime = answerTime;
		this.uuid = uuid;
		this.mainAnswers = mainAnswers;
		this.otherAnswers = otherAnswers;
	}

	public Integer getAnswerId() {
		return answerId;
	}

	public void setAnswerId(Integer answerId) {
		this.answerId = answerId;
	}

	public Integer getQuestionId() {
		return questionId;
	}

	public void setQuestionId(Integer questionId) {
		this.questionId = questionId;
	}

	public Date getAnswerTime() {
		return answerTime;
	}

	public void setAnswerTime(Date answerTime) {
		this.answerTime = answerTime;
	}

	public String getMainAnswers() {
		return mainAnswers;
	}

	public void setMainAnswers(String mainAnswers) {
		this.mainAnswers = mainAnswers;
	}

	public String getOtherAnswers() {
		return otherAnswers;
	}

	public void setOtherAnswers(String otherAnswers) {
		this.otherAnswers = otherAnswers;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public Integer getSurveyId() {
		return surveyId;
	}

	public void setSurveyId(Integer surveyId) {
		this.surveyId = surveyId;
	}

	@Override
	public String toString() {
		return "Answer [answerId=" + answerId + ", surveyId=" + surveyId + ", questionId=" + questionId + ", answerTime=" + answerTime + "]";
	}
}

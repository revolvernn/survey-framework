/**
 * 
 */
package com.revolver.survey.token;


/**
 * @author REVOLVER2016年3月4日
 *	线程本地化
 */
public class SurveyToken {
	private static ThreadLocal<SurveyToken> local = new ThreadLocal<SurveyToken>();
	
	private Integer surveyId;
	
	/**
	 * @param surveyId the surveyId to set
	 */
	public void setSurveyId(Integer surveyId) {
		this.surveyId = surveyId;
	}
	/**
	 * @return the surveyId
	 */
	public Integer getSurveyId() {
		return surveyId;
	}
	
	public static SurveyToken getSurveyToken(){
		
		return local.get();
	}
	
	//绑定线程
	public void bind(SurveyToken surveyToken){
		local.set(surveyToken);
	}
	
	//解除绑定
	public void remove(){
		local.remove();
	}
}

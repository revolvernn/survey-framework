/**
 * 
 */
package com.revolver.survey.router;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import com.revolver.survey.token.SurveyToken;

/**
 * @author REVOLVER2016年3月4日
 *	数据源路由
 */
public class SurveyRouter extends AbstractRoutingDataSource {

	/* (non-Javadoc)
	 * @see org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource#determineCurrentLookupKey()
	 */
	@Override
	protected Object determineCurrentLookupKey() {
		SurveyToken token = SurveyToken.getSurveyToken();
		
		if(token != null){
			Integer surveyId = token.getSurveyId();
			
			token.remove();
			
			return (surveyId % 2 == 0)?"even":"odd";
		}
		
		return null;
	}
}

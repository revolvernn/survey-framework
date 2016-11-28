package com.revolver.survey.guest.component.dao.i;

import java.util.List;

import com.revolver.survey.base.i.BaseDao;
import com.revolver.survey.guest.entity.Answer;
import com.revolver.survey.guest.entity.Question;
import com.revolver.survey.guest.entity.Survey;
import com.revolver.survey.guest.entity.User;

/**
 * 
 * @author REVOLVER
 * 
 */
public interface SurveyDao extends BaseDao<Survey> {

	// 查询未完成的调查总记录数
	int getUncompletedCount(User user);

	// 查证未完成的调查list
	List<Survey> getUncompletedList(int pageNo, int pageSize, User user);

	// 查询完成的调查总记录数
	int getCompletedCount(User user);

	// 查证完成的调查list
	List<Survey> getCompletedList(int pageNo, int pageSize, User user);

	int getSurveyCount(Integer userId, boolean completed);

	int getSurveyCompletedCount();

	List<Survey> getSurveyList(Integer pageNo, int surveyListPageSize, Integer userId, boolean completed);

	List<Survey> getSurveyList(Integer pageNo, int surveyListPageSize);

	List<Survey> getTenNewSurveyList();

	List<Survey> getTenHotSurveyList();
	
	void saveEngage(Integer userId, Integer surveyId);

	int getEngageCount(Integer userId);

	List<Survey> getEngageList(Integer userId, Integer pageNo, int surveyListPageSize);

	int getCompletedCount();

	List<Survey> getCompletedList(Integer pageNo, int pageSize);

	int getEngageSurveyCount(Integer surveyId);

	List<Question> getQuestionListBySurveyId(Integer surveyId);
	
	List<Answer> getAnswerBySurveyId(Integer surveyId);

}

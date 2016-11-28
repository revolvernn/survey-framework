package com.revolver.survey.guest.component.service.i;

import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.revolver.survey.base.i.BaseService;
import com.revolver.survey.guest.entity.Survey;
import com.revolver.survey.guest.entity.User;
import com.revolver.survey.guest.model.Page;

/**
 * 
 * @author REVOLVER
 * 
 */
public interface SurveyService extends BaseService<Survey> {
	
	Page<Survey> getUncompletedPage(String pageNoStr, int pageSize, User user);

	Page<Survey> getCompletedPage(String pageNoStr, int pageSize, User user);

	boolean completed(Integer surveyId);

	Page<Survey> getSurveyPage(Integer userId, String pageNoStr, int surveyListPageSize, boolean completed);

	List<Survey> getTenNewSurveyList();

	List<Survey> getTenHotSurveyList();

	Page<Survey> findAllAvailableSurvey(String pageNoStr, int allSurveyPageSize);

	void saveEngage(Integer userId, Integer surveyId);

	Page<Survey> getMyEngageSurvey(String pageNoStr, int surveyListPageSize, User user);

	Page<Survey> getCompletedList(String pageNoStr, int pageSize);

	HSSFWorkbook generateWorkBook(Integer surveyId);
}

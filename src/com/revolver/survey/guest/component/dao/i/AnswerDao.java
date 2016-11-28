package com.revolver.survey.guest.component.dao.i;

import java.util.List;

import com.revolver.survey.base.i.BaseDao;
import com.revolver.survey.guest.entity.Answer;
import com.revolver.survey.guest.model.OptionStatisticModel;

/**
 * 
 * @author REVOLVER
 * 
 */
public interface AnswerDao extends BaseDao<Answer> {

	void batchSaveAnswerList(List<Answer> answerList);

	int getTotalEngageCount(Integer questionId);

	List<OptionStatisticModel> getosmList(String[] optionsArray, Integer questionId);

	int getTotalOtherCount(Integer questionId);

	List<String> getOtherTextList(Integer questionId);

	List<String> getTextList(Integer questionId);

	int getOptionEngageCount(Integer questionId, String currentValue);
}

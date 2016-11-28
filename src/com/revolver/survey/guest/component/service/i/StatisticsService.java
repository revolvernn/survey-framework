package com.revolver.survey.guest.component.service.i;

import java.util.List;

import com.revolver.survey.base.i.BaseService;
import com.revolver.survey.guest.entity.Survey;
import com.revolver.survey.guest.model.OptionStatisticModel;
import com.revolver.survey.guest.model.QuestionStatisticModel;
/**
 * 
 * @author REVOLVER
 *
 */
public interface StatisticsService extends BaseService<Survey> {

	QuestionStatisticModel getQsm(Integer questionId);

	List<String> getOtherTextList(Integer questionId);

	List<String> getTextList(Integer questionId);

	List<OptionStatisticModel> getOsmList(Integer questionId, Integer rowNo);

	List<OptionStatisticModel> getOsmList(Integer questionId, Integer rowNo, Integer colNo);


}

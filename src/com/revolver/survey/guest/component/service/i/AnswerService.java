package com.revolver.survey.guest.component.service.i;

import java.util.Map;

import com.revolver.survey.base.i.BaseService;
import com.revolver.survey.guest.entity.Answer;
/**
 * 
 * @author REVOLVER
 *
 */
public interface AnswerService extends BaseService<Answer> {

	void parseAndSave(Integer surveyId, Map<Integer, Map<String, String[]>> allBagMap);

}

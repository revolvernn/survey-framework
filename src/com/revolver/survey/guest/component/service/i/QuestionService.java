package com.revolver.survey.guest.component.service.i;

import com.revolver.survey.base.i.BaseService;
import com.revolver.survey.guest.entity.Question;
/**
 * 
 * @author REVOLVER
 *
 */
public interface QuestionService extends BaseService<Question> {


	void saveOrUpdateQuestion(Question t);

}

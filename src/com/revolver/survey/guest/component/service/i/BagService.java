package com.revolver.survey.guest.component.service.i;

import java.util.List;

import com.revolver.survey.base.i.BaseService;
import com.revolver.survey.guest.entity.Bag;
/**
 * 
 * @author REVOLVER
 *
 */
public interface BagService extends BaseService<Bag> {

	void batchUpdateBagOrder(List<Bag> bagList);

	void moveBagToSurvey(Integer bagId, Integer surveyId);

	void copyBagToSurvey(Integer bagId, Integer surveyId);

	Bag getFirstBag(Integer surveyId);

	Bag getNextBag(Integer surveyId,Integer bagId);

	Bag getPrevBag(Integer surveyId,Integer bagId);

}

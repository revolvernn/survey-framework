package com.revolver.survey.guest.component.dao.i;

import java.util.List;

import com.revolver.survey.base.i.BaseDao;
import com.revolver.survey.guest.entity.Bag;
/**
 * 
 * @author REVOLVER
 *
 */
public interface BagDao extends BaseDao<Bag> {

	void batchUpdateBagOrder(List<Bag> bagList);

	void moveBagToSurvey(Integer bagId, Integer surveyId);

	Bag getFirstBag(Integer surveyId);

	Bag getNextBag(Integer surveyId,Integer bagId);

	Bag getPrevBag(Integer surveyId,Integer bagId);

}

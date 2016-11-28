package com.revolver.survey.guest.component.service.m;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revolver.survey.base.m.BaseServiceImpl;
import com.revolver.survey.guest.component.dao.i.BagDao;
import com.revolver.survey.guest.component.dao.i.QuestionDao;
import com.revolver.survey.guest.component.service.i.BagService;
import com.revolver.survey.guest.entity.Bag;
import com.revolver.survey.guest.entity.Survey;
import com.revolver.survey.utils.DataProcessUtils;
/**
 * 
 * @author REVOLVER
 *
 */
@Service
public class BagServiceImpl extends BaseServiceImpl<Bag> implements BagService {
	@Autowired
	private BagDao bagDao;
	@Autowired
	private QuestionDao questionDao;

	@Override
	public void batchUpdateBagOrder(List<Bag> bagList) {
		bagDao.batchUpdateBagOrder(bagList);
	}

	@Override
	public void moveBagToSurvey(Integer bagId, Integer surveyId) {
		bagDao.moveBagToSurvey(bagId,surveyId);
		
	}

	@Override
	public void copyBagToSurvey(Integer bagId, Integer surveyId) {
		try {
			Bag bag = bagDao.getEntityById(bagId);
			
			Bag bag2 = (Bag) DataProcessUtils.copyObject(bag);
			
			Survey survey = new Survey();
			survey.setSurveyId(surveyId);
			bag2.setSurvey(survey);
			bag2.setBagOrder(bag2.getBagId());
			bagDao.saveEntity(bag2);
			questionDao.batchSavePuestion(bag2.getQuestionSet());
			
		} catch (Exception e) {e.printStackTrace();}
	}

	@Override
	public Bag getFirstBag(Integer surveyId) {
		return bagDao.getFirstBag(surveyId);
	}

	@Override
	public Bag getNextBag(Integer surveyId,Integer bagId) {
		return bagDao.getNextBag(surveyId,bagId);
	}

	@Override
	public Bag getPrevBag(Integer surveyId,Integer bagId) {
		return bagDao.getPrevBag(surveyId,bagId);
	}

	
}

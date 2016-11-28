package com.revolver.survey.guest.component.service.m;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revolver.survey.base.m.BaseServiceImpl;
import com.revolver.survey.guest.component.dao.i.QuestionDao;
import com.revolver.survey.guest.component.service.i.QuestionService;
import com.revolver.survey.guest.entity.Question;
/**
 * 
 * @author REVOLVER
 *
 */
@Service
public class QuestionServiceImpl extends BaseServiceImpl<Question> implements
		QuestionService {
	@Autowired
	private QuestionDao questionDao;
	

	@Override
	public void saveOrUpdateQuestion(Question t) {
		// TODO Auto-generated method stub
		try {
			questionDao.saveOrUpdateEntity(t);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
}

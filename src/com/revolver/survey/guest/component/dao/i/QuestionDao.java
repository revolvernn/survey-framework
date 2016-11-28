package com.revolver.survey.guest.component.dao.i;

import java.util.Set;

import com.revolver.survey.base.i.BaseDao;
import com.revolver.survey.guest.entity.Question;
/**
 * 
 * @author REVOLVER
 *
 */
public interface QuestionDao extends BaseDao<Question> {

	void batchSavePuestion(Set<Question> questionSet);

}

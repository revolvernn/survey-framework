package com.revolver.survey.guest.component.dao.m;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.revolver.survey.base.m.BaseDaoImpl;
import com.revolver.survey.guest.component.dao.i.SurveyDao;
import com.revolver.survey.guest.entity.Answer;
import com.revolver.survey.guest.entity.Question;
import com.revolver.survey.guest.entity.Survey;
import com.revolver.survey.guest.entity.User;

/**
 * 
 * @author REVOLVER
 * 
 */
@SuppressWarnings("unchecked")
@Repository
public class SurveyDaoImpl extends BaseDaoImpl<Survey> implements SurveyDao {
	
	
	//---------------------获取count------------------------------
	@Override
	public int getUncompletedCount(User user) {
		String hql = "SELECT COUNT(*) From Survey s WHERE s.completed=false AND s.user=?";
		Long totalRecord = (Long) getSession().createQuery(hql)
											  .setEntity(0, user)
											  .uniqueResult();
		return totalRecord.intValue();
	}
	
	@Override
	public int getCompletedCount(User user) {
		String hql = "SELECT COUNT(*) From Survey s WHERE s.completed=true AND s.user=?";
		Long totalRecord = (Long) getSession().createQuery(hql)
											  .setEntity(0, user)
											  .uniqueResult();
		return totalRecord.intValue();
	}
	
	@Override
	public int getCompletedCount() {
		String hql = "SELECT COUNT(s.completed) From Survey s WHERE s.completed=true";
		Long totalRecord = (Long) getSession().createQuery(hql)
											  .uniqueResult();
		return totalRecord.intValue();
	}
	

	@Override
	public int getSurveyCount(Integer userId, boolean completed) {
		String hql = "SELECT COUNT(*) From Survey s WHERE s.completed=? AND s.user.userId=?";
		Long totalRecord = (Long) this.getSession().createQuery(hql)
												   .setParameter(0, completed)
												   .setParameter(1, userId)
												   .uniqueResult();
		return totalRecord.intValue();
	}
	

	@Override
	public int getSurveyCompletedCount() {
		String hql = "SELECT COUNT(s.completed) From Survey s WHERE s.completed=true";
		Long totalRecord = (Long) this.getSession().createQuery(hql)
												   .uniqueResult();
		return totalRecord.intValue();
	}
	

	@Override
	public int getEngageCount(Integer userId) {
		String sql = "SELECT COUNT(survey_id) FROM engage WHERE user_id=?";
		
		BigInteger result = (BigInteger) this.getSession().createSQLQuery(sql).setInteger(0, userId).uniqueResult();
		return result.intValue();
	}
	
	
	@Override
	public int getEngageSurveyCount(Integer surveyId) {
		
		//SELECT COUNT(DISTINCT UUID) FROM answers a WHERE a.survey_Id=1
		
		String sql = "SELECT COUNT(DISTINCT UUID) FROM answers a WHERE a.survey_Id=?";
		BigInteger totalRecord = (BigInteger) this.getSession().createSQLQuery(sql).setInteger(0, surveyId)
				.uniqueResult();
		return totalRecord.intValue();
	}
	//---------------------获取List-------------------------------

	@Override
	public List<Survey> getUncompletedList(int pageNo, int pageSize, User user) {
		String hql = "From Survey s WHERE s.completed=false AND s.user=?";
		int index = (pageNo - 1) * pageSize;
		List<Survey> list = getSession().createQuery(hql)
										.setEntity(0, user)
										.setFirstResult(index)
										.setMaxResults(pageSize)
										.list();
		return list;
	}


	@Override
	public List<Survey> getCompletedList(int pageNo, int pageSize, User user) {
		String hql = "From Survey s WHERE s.completed=true AND s.user=?";
		int index = (pageNo - 1) * pageSize;
		List<Survey> list = getSession().createQuery(hql)
										.setEntity(0, user).setFirstResult(index)
										.setMaxResults(pageSize)
										.list();
		return list;
	}

	
	@Override
	public List<Survey> getSurveyList(Integer pageNo, int surveyListPageSize, Integer userId, boolean completed) {
		String hql = "From Survey s WHERE s.completed=? AND s.user.userId=?";
		int index = (pageNo - 1) * surveyListPageSize;
		List<Survey> list = getSession().createQuery(hql)
										.setParameter(0, completed)
										.setParameter(1, userId)
										.setFirstResult(index)
										.setMaxResults(surveyListPageSize)
										.list();
		return list;
	}
	@Override
	public List<Survey> getSurveyList(Integer pageNo, int surveyListPageSize) {
		String hql = "From Survey s WHERE s.completed=true";
		int index = (pageNo - 1) * surveyListPageSize;
		List<Survey> list = getSession().createQuery(hql)
										.setFirstResult(index)
										.setMaxResults(surveyListPageSize)
										.list();
		return list;
	}

	@Override
	public List<Survey> getTenNewSurveyList() {
		String hql = "From Survey s WHERE s.completed=true ORDER BY s.completedTime DESC";
		return this.getSession().createQuery(hql).setMaxResults(10).list();
	}

	@Override
	public List<Survey> getTenHotSurveyList() {
		
		//SELECT SURVEY_ID,TITLE FROM surveys WHERE SURVEY_ID IN (SELECT SURVEY_ID FROM answers a GROUP BY a.`SURVEY_ID` ORDER BY COUNT(a.`SURVEY_ID`))
		
		//String sql = "SELECT * FROM surveys WHERE SURVEY_ID IN (SELECT SURVEY_ID FROM answers a GROUP BY a.`SURVEY_ID` ORDER BY COUNT(a.`SURVEY_ID`))";
		String hql = "FROM Survey s WHERE s.surveyId IN "
													+ "(select a.surveyId "
													+ "FROM Answer a GROUP BY "
													+ "a.surveyId ORDER BY "
													+ "COUNT(a.surveyId))";
		
		return this.getSession().createQuery(hql).setMaxResults(10).list();
		
	}

	@Override
	public void saveEngage(Integer userId, Integer surveyId) {
		String sql = "INSERT INTO engage(user_id,survey_id) VALUES(?,?)";
		
		this.getSession().createSQLQuery(sql).setInteger(0, userId).setInteger(1, surveyId).executeUpdate();
	}

	@Override
	public List<Survey> getEngageList(Integer userId, Integer pageNo, int surveyListPageSize) {
		String sql = "SELECT `SURVEY_ID`,"
					+ "`LOGO_PATH`,"
					+ "`TITLE`,"
					+ "`COMPLETED_TIME` "
					+ "FROM surveys WHERE survey_id "
					+ "IN (SELECT survey_id FROM engage WHERE user_id =?) LIMIT ?,?";
		int index = (pageNo - 1) * surveyListPageSize;
		List<Object[]> list = getSession().createSQLQuery(sql).setInteger(0, userId)
															  .setInteger(1, index)
															  .setInteger(2, surveyListPageSize)
															  .list();
		
		List<Survey> surveyList = new ArrayList<Survey>();
		
		for (int i = 0; i < list.size(); i++) {
			Object[] objects = list.get(i);
			
			Integer surveyId = (Integer) objects[0];
			String logoPath = (String) objects[1];
			String title = (String) objects[2];
			Date completedTime = (Date) objects[3];
			
			Survey survey = new Survey(surveyId,title,logoPath,completedTime);
			surveyList.add(survey);
		}
		
		return surveyList;
	}

	@Override
	public List<Survey> getCompletedList(Integer pageNo, int pageSize) {
		String hql = "From Survey s WHERE s.completed=true";
		int index = (pageNo - 1) * pageSize;
		List<Survey> list = getSession().createQuery(hql)
										.setFirstResult(index)
										.setMaxResults(pageSize)
										.list();
		return list;
	}
	
	@Override
	public List<Question> getQuestionListBySurveyId(Integer surveyId) {
		String hql = "From Question q WHERE q.bag.survey.surveyId=?";
		
		return this.getSession().createQuery(hql).setInteger(0, surveyId).list();
	}

	/* (non-Javadoc)
	 * @see com.revolver.survey.guest.component.dao.i.SurveyDao#getAnswerBySurveyId(java.lang.Integer)
	 */
	@Override
	public List<Answer> getAnswerBySurveyId(Integer surveyId) {
		
		String hql = "From Answer a WHERE a.surveyId=?";
		
		return this.getSession().createQuery(hql).setInteger(0, surveyId).list();
	}

	
}

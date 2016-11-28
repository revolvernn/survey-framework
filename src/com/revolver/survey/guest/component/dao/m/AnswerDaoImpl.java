package com.revolver.survey.guest.component.dao.m;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.jdbc.Work;
import org.springframework.stereotype.Repository;

import com.revolver.survey.base.m.BaseDaoImpl;
import com.revolver.survey.guest.component.dao.i.AnswerDao;
import com.revolver.survey.guest.entity.Answer;
import com.revolver.survey.guest.model.OptionStatisticModel;
/**
 * 
 * @author REVOLVER
 *
 */
@Repository
public class AnswerDaoImpl extends BaseDaoImpl<Answer> implements AnswerDao {

	@Override
	public void batchSaveAnswerList(final List<Answer> answerList) {

		this.getSession().doWork(new Work() {

			@Override
			public void execute(Connection conn) throws SQLException {

				String sql = "INSERT INTO answers(`SURVEY_ID`," + "`QUESTION_ID`," + "`ANSWER_TIME`," + "`UUID`," + "`MAIN_ANSWERS`," + "`OTHER_ANSWERS`) VALUES(?,?,?,?,?,?)";

				PreparedStatement ps = conn.prepareStatement(sql);

				for (Answer answer : answerList) {
					ps.setInt(1, answer.getSurveyId());
					ps.setInt(2, answer.getQuestionId());
					ps.setObject(3, answer.getAnswerTime());
					ps.setString(4, answer.getUuid());
					ps.setString(5, answer.getMainAnswers());
					ps.setString(6, answer.getOtherAnswers());
					ps.addBatch();
				}
				ps.executeBatch();

				ps.close();
			}
		});
	}

	@Override
	public int getTotalEngageCount(Integer questionId) {
		String sql = "SELECT COUNT(DISTINCT UUID) FROM answers WHERE question_id=?";

		BigInteger uniqueResult = (BigInteger) this.getSession().createSQLQuery(sql).setInteger(0, questionId).uniqueResult();

		return uniqueResult.intValue();
	}

	@Override
	public List<OptionStatisticModel> getosmList(String[] optionsArray, Integer questionId) {
		String sql = "SELECT COUNT(*) FROM answers WHERE question_id = ? AND concat(',',main_answers,',') like ?";

		SQLQuery sqlQuery = this.getSession().createSQLQuery(sql);

		List<OptionStatisticModel> osmList = new ArrayList<OptionStatisticModel>();

		for (int i = 0; i < optionsArray.length; i++) {
			OptionStatisticModel osm = new OptionStatisticModel();
			osm.setLable(optionsArray[i]);

			String name = "%," + i + ",%";

			BigInteger result = (BigInteger) sqlQuery.setInteger(0, questionId).setString(1, name).uniqueResult();
			int count = result.intValue();

			if (count == 0)
				continue;

			osm.setCount(count);
			osmList.add(osm);
		}

		return osmList;
	}

	@Override
	public int getTotalOtherCount(Integer questionId) {
		String sql = "SELECT COUNT(*) FROM answers WHERE question_id = ? AND concat(',',main_answers,',') like '%,other,%'";

		BigInteger result = (BigInteger) this.getSession().createSQLQuery(sql).setInteger(0, questionId).uniqueResult();
		return result.intValue();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> getOtherTextList(Integer questionId) {
		String sql = "SELECT OTHER_ANSWERS FROM answers WHERE question_id=? AND OTHER_ANSWERS IS NOT NULL AND OTHER_ANSWERS != ''";

		return this.getSession().createSQLQuery(sql).setInteger(0, questionId).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> getTextList(Integer questionId) {

		String sql = "SELECT MAIN_ANSWERS FROM answers WHERE question_id=? AND MAIN_ANSWERS IS NOT NULL AND MAIN_ANSWERS != ''";

		return this.getSession().createSQLQuery(sql).setInteger(0, questionId).list();
	}

	@Override
	public int getOptionEngageCount(Integer questionId, String currentValue) {
		String sql = "SELECT COUNT(*) FROM answers WHERE question_id = ? AND concat(',',main_answers,',') like ?";

		String name = "%," + currentValue + ",%";

		BigInteger result = (BigInteger) this.getSession().createSQLQuery(sql).setInteger(0, questionId).setString(1, name).uniqueResult();

		return result.intValue();
	}

}

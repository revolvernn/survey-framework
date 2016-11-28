package com.revolver.survey.guest.component.dao.m;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Set;

import org.hibernate.jdbc.Work;
import org.springframework.stereotype.Repository;

import com.revolver.survey.base.m.BaseDaoImpl;
import com.revolver.survey.guest.component.dao.i.QuestionDao;
import com.revolver.survey.guest.entity.Question;
/**
 * 
 * @author REVOLVER
 *
 */
@Repository
public class QuestionDaoImpl extends BaseDaoImpl<Question> implements
		QuestionDao {
	
	@Override
	public void batchSavePuestion(final Set<Question> questionSet) {
		this.getSession().doWork(new Work() {
			
			@Override
			public void execute(Connection conn) throws SQLException {
				String sql = "INSERT INTO QUESTIONS(`QUESTION_NAME`,"
												 + "`QUESTION_TYPE`,"
												 + "`OPTIONS`,"
												 + "`BR`,"
												 + "`HAS_OTHER`,"
												 + "`OTHER_TYPE`,"
												 + "`MATRIX_ROW_TITLES`,"
												 + "`MATRIX_COL_TITLES`,"
												 + "`MATRIX_OPTIONS`,"
												 + "`BAG_ID`) VALUES(?,?,?,?,?,?,?,?,?,?)";
				
				PreparedStatement ps = conn.prepareStatement(sql);
				
				for (Question question : questionSet) {
					
						ps.setString(1, question.getQuestionName());
						ps.setInt(2, question.getQuestionType());
						ps.setString(3, question.getOptions());
						ps.setBoolean(4, question.isBr());
						ps.setBoolean(5, question.isHasOther());
						ps.setInt(6, question.getOtherType());
						ps.setString(7, question.getMatrixRowTitles());
						ps.setString(8, question.getMatrixColTitles());
						ps.setString(9, question.getMatrixOptions());
						ps.setInt(10, question.getBag().getBagId());
						
						ps.addBatch();
				}
				
				ps.executeBatch();
				ps.close();
			}
		});
	}

}

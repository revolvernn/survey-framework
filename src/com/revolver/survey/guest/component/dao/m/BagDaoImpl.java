package com.revolver.survey.guest.component.dao.m;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.hibernate.jdbc.Work;
import org.springframework.stereotype.Repository;

import com.revolver.survey.base.m.BaseDaoImpl;
import com.revolver.survey.guest.component.dao.i.BagDao;
import com.revolver.survey.guest.entity.Bag;
/**
 * 
 * @author REVOLVER
 *
 */
@Repository
public class BagDaoImpl extends BaseDaoImpl<Bag> implements BagDao {

	@Override
	public void batchUpdateBagOrder(final List<Bag> bagList) {
		getSession().doWork(new Work() {
			
			@Override
			public void execute(Connection conn) throws SQLException {
				String sql="UPDATE BAGS SET BAG_ORDER=? WHERE BAG_ID=?";
				
				PreparedStatement ps = conn.prepareStatement(sql);
				
				for (Bag bag : bagList) {
					ps.setInt(1, bag.getBagOrder());
					ps.setInt(2, bag.getBagId());
					ps.addBatch();
				}
				
				ps.executeBatch();
				
				ps.close();
			}
		});
	}

	@Override
	public void moveBagToSurvey(Integer bagId, Integer surveyId) {
		
		String sql="UPDATE Bags SET SURVEY_ID=? WHERE BAG_ID=?";
		this.getSession().createSQLQuery(sql).setInteger(0,surveyId)
											 .setInteger(1, bagId)
											 .executeUpdate();
		
	}

	@Override
	public Bag getFirstBag(Integer surveyId) {
		String hql="FROM Bag b LEFT JOIN FETCH b.questionSet WHERE b.survey.surveyId=? ORDER BY b.bagOrder ASC";
		return (Bag) this.getSession().createQuery(hql).setParameter(0, surveyId).setMaxResults(1).uniqueResult();
	}
	@Override
	public Bag getPrevBag(Integer surveyId, Integer bagId) {
		
		//要查询的目标包裹：bagOrder<bagId对应的包裹的bagOrder
		
		String hql = "From Bag b left join fetch b.questionSet where b.survey.surveyId=? and "
				+ "b.bagOrder<(select innerBag.bagOrder from Bag innerBag where innerBag.bagId=?) order by b.bagOrder desc";
		
		return (Bag) this.getSession().createQuery(hql).setInteger(0, surveyId).setInteger(1, bagId).setMaxResults(1).uniqueResult();
	}
	
	@Override
	public Bag getNextBag(Integer surveyId, Integer bagId) {
		String hql = "From Bag b left join fetch b.questionSet where b.survey.surveyId=? and "
				+ "b.bagOrder>(select innerBag.bagOrder from Bag innerBag where innerBag.bagId=?) order by b.bagOrder asc";
		
		return (Bag) this.getSession().createQuery(hql).setInteger(0, surveyId).setInteger(1, bagId).setMaxResults(1).uniqueResult();
	}

	
}

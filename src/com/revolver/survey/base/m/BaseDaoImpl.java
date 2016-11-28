package com.revolver.survey.base.m;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.jdbc.Work;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.revolver.survey.base.i.BaseDao;
import com.revolver.survey.utils.ValidateUtils;

/**
 * 
 * @author REVOLVER
 * 
 * @param <T>
 */
@SuppressWarnings("unchecked")
@Repository
public class BaseDaoImpl<T> implements BaseDao<T> {
	@Autowired
	private SessionFactory sessionFactory;

	private Class<T> entityType;

	public BaseDaoImpl() {
		Type type = this.getClass().getGenericSuperclass();
		this.entityType = (Class<T>) (((ParameterizedType) type).getActualTypeArguments())[0];
	}

	public Session getSession() {
//		 return sessionFactory.openSession();
		return sessionFactory.getCurrentSession();
	}

	@Override
	public void saveEntity(T t) {
		getSession().save(t);
	}

	@Override
	public void deleteEntity(T t) throws Exception {
		getSession().delete(t);
	}

	@Override
	public void updateEntity(T t) throws Exception {
		getSession().update(t);
	}

	@Override
	public T getEntityById(Integer entId) {
		return (T) getSession().get(entityType, entId);
	}

	@Override
	public List<T> getEntityList(){

		/**
		 * //注：criteria.setResultTransformer设成了其它的值。 
		 * criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY); 
		 * //设置ENTITY级的DISTINCT模式，根实体
		 */
		Criteria criteria = getSession().createCriteria(entityType);
		return criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
	}

	@Override
	public void saveOrUpdateEntity(T t) {
		this.getSession().saveOrUpdate(t);
	}
	
	/**
	 * @param sql
	 * @param authIdList
	 */
	public void doBatchWork(final String sql, final List<Integer> objIdList) {
		this.getSession().doWork(new Work() {
			
			@Override
			public void execute(Connection conn) throws SQLException {
				PreparedStatement ps = conn.prepareStatement(sql);
				
				for (int i = 0; i < objIdList.size(); i++) {
					ps.setInt(1, objIdList.get(i));
					ps.addBatch();
				}
				ps.executeBatch();
				ps.close();
			}
		});
	}
	
	public void batchSave(final Integer authorityId, final List<Integer> resIdList, final String sql) {
		this.getSession().doWork(new Work() {
			
			@Override
			public void execute(Connection conn) throws SQLException {
				
				PreparedStatement ps = conn.prepareStatement(sql);
				
				for (Integer resourceId : resIdList) {
					ps.setInt(1, authorityId);
					ps.setInt(2, resourceId);
					ps.addBatch();
				}
				ps.executeBatch();
				ps.close();
			}
		});
	}
	
	public void doBatchWork2(final String sql, final List<Object[]> params) {
		this.getSession().doWork(new Work() {
			
			@Override
			public void execute(Connection conn) throws SQLException {
				
			if (!ValidateUtils.validateCollection(params)) return;
			
			PreparedStatement ps = conn.prepareStatement(sql);
				// 遍历List得到的对象对应SQL语句的一次执行
			for (int i = 0; i < params.size(); i++) {
				Object[] paramArr = params.get(i);
					
					for (int j = 0; j < paramArr.length; j++) {

						// 给每一条SQL语句设置占位符参数
						ps.setObject(j + 1, paramArr[j]);
					}
					ps.addBatch();
				}
			ps.executeBatch();
			ps.close();
		}});
	}

	/* (non-Javadoc)
	 * @see com.revolver.survey.base.i.BaseDao#checkId(java.lang.Integer)
	 */
	@Override
	public boolean checkId(String sql, Integer oId) {
		
		BigInteger result = (BigInteger)this.getSession().createSQLQuery(sql).setInteger(0, oId).uniqueResult();
		return result.intValue() > 0;
	}
}

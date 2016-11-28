/**
 * 
 */
package com.revolver.survey.admin.component.dao.m;

import java.math.BigInteger;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.revolver.survey.admin.component.dao.i.ResourceDao;
import com.revolver.survey.admin.entity.Resource;
import com.revolver.survey.base.m.BaseDaoImpl;

/**
 * @author REVOLVER2016年2月27日
 *
 */
@Repository
public class ResourceDaoImpl extends BaseDaoImpl<Resource> implements ResourceDao {


	/* (non-Javadoc)
	 * @see com.revolver.survey.admin.component.dao.i.ResourceDao#getResByName(java.lang.String)
	 */
	@Override
	public boolean getResByName(String actionName) {
		String sql = "SELECT COUNT(*) FROM resources WHERE action_name=?";
		BigInteger result = (BigInteger) this.getSession().createSQLQuery(sql).setString(0, actionName).uniqueResult();
		
		return result.intValue() > 0;
	}

	/* (non-Javadoc)
	 * @see com.revolver.survey.admin.component.dao.i.ResourceDao#getMaxResPos()
	 */
	@Override
	public Integer getMaxResPos() {
		String sql = "SELECT MAX(RES_POS) FROM resources";
		Integer result =  (Integer) this.getSession().createSQLQuery(sql).uniqueResult();
		
		return result;
	}

	/* (non-Javadoc)
	 * @see com.revolver.survey.admin.component.dao.i.ResourceDao#getCurrentMaxResCode(java.lang.Integer)
	 */
	@Override
	public Long getCurrentMaxResCode(Integer maxResPos) {
		String sql = "SELECT MAX(RES_CODE) FROM resources WHERE RES_POS=?";
		BigInteger result = (BigInteger) this.getSession().createSQLQuery(sql).setInteger(0, maxResPos).uniqueResult();
		return result.longValue();
	}

	/* (non-Javadoc)
	 * @see com.revolver.survey.admin.component.dao.i.ResourceDao#getResCount()
	 */
	@Override
	public int getResCount() {
		String sql = "SELECT COUNT(*) FROM resources";
		
		BigInteger result = (BigInteger) this.getSession().createSQLQuery(sql).uniqueResult();
	
		return result.intValue();
	}

	/* (non-Javadoc)
	 * @see com.revolver.survey.admin.component.dao.i.ResourceDao#getAllResList(java.lang.Integer, int)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Resource> getAllResList(Integer pageNo, int pageSize) {
		String hql = "From Resource r order by r.resourceName";
		return this.getSession().createQuery(hql).setFirstResult((pageNo-1)*pageSize)
												 .setMaxResults(pageSize)
												 .list();
	}

	/* (non-Javadoc)
	 * @see com.revolver.survey.admin.component.dao.i.ResourceDao#batchRemove(java.util.List)
	 */
	@Override
	public void batchRemove(final List<Integer> resIdList) {
		
		final String sql = "DELETE FROM resources WHERE resource_id=?";
		this.doBatchWork(sql, resIdList);
	}

	/* (non-Javadoc)
	 * @see com.revolver.survey.admin.component.dao.i.ResourceDao#getResourceByResName(java.lang.String)
	 */
	@Override
	public Resource getResourceByResName(String actionName) {
		String hql = "From Resource r where r.actionName=?";
		return (Resource) this.getSession().createQuery(hql).setString(0, actionName).uniqueResult();
	}

}

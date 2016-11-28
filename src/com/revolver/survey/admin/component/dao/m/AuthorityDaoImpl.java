/**
 * 
 */
package com.revolver.survey.admin.component.dao.m;

import java.math.BigInteger;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.revolver.survey.admin.component.dao.i.AuthorityDao;
import com.revolver.survey.admin.entity.Authority;
import com.revolver.survey.base.m.BaseDaoImpl;

/**
 * @author REVOLVER2016年2月29日
 *
 */
@Repository
public class AuthorityDaoImpl extends BaseDaoImpl<Authority> implements AuthorityDao {

	/* (non-Javadoc)
	 * @see com.revolver.survey.admin.component.dao.i.AuthorityDao#getAuthorityCount()
	 */
	@Override
	public int getAuthorityCount() {
		String sql = "SELECT COUNT(*) FROM authoritys";
		BigInteger result = (BigInteger) this.getSession().createSQLQuery(sql)
														  .uniqueResult();
		return result.intValue();
	}

	/* (non-Javadoc)
	 * @see com.revolver.survey.admin.component.dao.i.AuthorityDao#getAllAuthList(java.lang.Integer, int)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Authority> getAllAuthList(Integer pageNo, int authPageSize) {
		String hql = "From Authority a order by a.authorityId";
		return this.getSession().createQuery(hql).setFirstResult((pageNo-1)*authPageSize)
												 .setMaxResults(authPageSize)
												 .list();
	}

	/* (non-Javadoc)
	 * @see com.revolver.survey.admin.component.dao.i.AuthorityDao#bathRemove(java.util.List)
	 */
	@Override
	public void bathRemove(final List<Integer> authIdList) {
		String sql = "DELETE FROM authoritys WHERE authority_id=?";	
		
		this.doBatchWork(sql, authIdList);
	}

	/* (non-Javadoc)
	 * @see com.revolver.survey.admin.component.dao.i.AuthorityDao#batchSaveRes(java.lang.Integer, java.util.List)
	 */
	@Override
	public void batchSaveRes(final Integer authorityId, final List<Integer> resIdList) {
		final String sql = "INSERT INTO RES_AUTH_INNER(AUTH_ID,RES_ID) VALUES(?,?)";
		
		this.batchSave(authorityId, resIdList, sql);
	}

	/* (non-Javadoc)
	 * @see com.revolver.survey.admin.component.dao.i.AuthorityDao#getCurrentResIdList(java.lang.Integer)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Integer> getCurrentResIdList(Integer authorityId) {
		String sql = "SELECT RES_ID FROM RES_AUTH_INNER WHERE AUTH_ID=?";
		
		return this.getSession().createSQLQuery(sql).setInteger(0, authorityId).list();
	}

	/* (non-Javadoc)
	 * @see com.revolver.survey.admin.component.dao.i.AuthorityDao#deleteAllResByAuthId(java.lang.Integer)
	 */
	@Override
	public void deleteAllResByAuthId(Integer authorityId) {
		String sql = "DELETE FROM RES_AUTH_INNER WHERE AUTH_ID=?";
		
		this.getSession().createSQLQuery(sql).setInteger(0, authorityId).executeUpdate();
	}

	/* (non-Javadoc)
	 * @see com.revolver.survey.admin.component.dao.i.AuthorityDao#checkId(java.lang.Integer)
	 */
	@Override
	public boolean checkId(Integer authorityId) {
		String sql = "SELECT COUNT(*) FROM RES_AUTH_INNER WHERE AUTH_ID=?";
		return this.checkId(sql, authorityId);
	}

}

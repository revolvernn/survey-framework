/**
 * 
 */
package com.revolver.survey.admin.component.dao.m;

import java.math.BigInteger;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.revolver.survey.admin.component.dao.i.RoleDao;
import com.revolver.survey.admin.entity.Role;
import com.revolver.survey.base.m.BaseDaoImpl;

/**
 * @author REVOLVER2016年2月29日
 *
 */
@Repository
public class RoleDaoImpl extends BaseDaoImpl<Role> implements RoleDao {

	/* (non-Javadoc)
	 * @see com.revolver.survey.admin.component.dao.i.RoleDao#batchRemove(java.util.List)
	 */
	@Override
	public void batchRemove(List<Integer> roleIdList) {
		String sql = "DELETE FROM ROLES WHERE ROLE_ID=?";
		this.doBatchWork(sql, roleIdList);
		
	}

	/* (non-Javadoc)
	 * @see com.revolver.survey.admin.component.dao.i.RoleDao#getCurrentAuthIdList(java.lang.Integer)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Integer> getCurrentAuthIdList(Integer roleId) {
		String sql = "SELECT AUTH_ID FROM AUTH_ROLE_INNER WHERE ROLE_ID=?";
		
		return this.getSession().createSQLQuery(sql).setInteger(0, roleId).list();
	}

	/* (non-Javadoc)
	 * @see com.revolver.survey.admin.component.dao.i.RoleDao#batchSaveAuth(java.lang.Integer, java.util.List)
	 */
	@Override
	public void batchSaveAuth(Integer roleId, List<Integer> authIdList) {
		String sql = "INSERT INTO AUTH_ROLE_INNER(ROLE_ID,AUTH_ID) VALUES(?,?)";
		
		this.batchSave(roleId, authIdList, sql);
	}

	/* (non-Javadoc)
	 * @see com.revolver.survey.admin.component.dao.i.RoleDao#deleteAllAuthByRoleId(java.lang.Integer)
	 */
	@Override
	public void deleteAllAuthByRoleId(Integer roleId) {
		String sql = "DELETE FROM AUTH_ROLE_INNER WHERE ROLE_ID=?";
		this.getSession().createSQLQuery(sql).setInteger(0, roleId).executeUpdate();
	}

	/* (non-Javadoc)
	 * @see com.revolver.survey.admin.component.dao.i.RoleDao#checkId(java.lang.Integer)
	 */
	@Override
	public boolean checkId(Integer roleId) {
		String sql = "SELECT COUNT(*) FROM AUTH_ROLE_INNER WHERE ROLE_ID=?";
		BigInteger result = (BigInteger)this.getSession().createSQLQuery(sql).setInteger(0, roleId).uniqueResult();
		return result.intValue() > 0;
	}

	/* (non-Javadoc)
	 * @see com.revolver.survey.admin.component.dao.i.RoleDao#getRoleList()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Role> getRoleList() {
		String hql = "FROM Role r order by r.roleId";
		return this.getSession().createQuery(hql).list();
	}

	/* (non-Javadoc)
	 * @see com.revolver.survey.admin.component.dao.i.RoleDao#checkIdExists(java.lang.Integer)
	 */
	@Override
	public boolean checkIdExists(Integer roleId) {
		String sql = "SELECT COUNT(*) FROM AUTH_ROLE_INNER WHERE ROLE_ID=?";
		BigInteger result = (BigInteger)this.getSession().createSQLQuery(sql).setInteger(0, roleId).uniqueResult();
		return result.intValue() > 0;
	}

}

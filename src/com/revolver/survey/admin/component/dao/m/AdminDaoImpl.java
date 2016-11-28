/**
 * 
 */
package com.revolver.survey.admin.component.dao.m;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.revolver.survey.admin.component.dao.i.AdminDao;
import com.revolver.survey.admin.entity.Admin;
import com.revolver.survey.base.m.BaseDaoImpl;

/**
 * @author REVOLVER2016年2月26日
 *
 */
@Repository
public class AdminDaoImpl extends BaseDaoImpl<Admin> implements AdminDao {

	/* (non-Javadoc)
	 * @see com.revolver.survey.admin.component.dao.i.AdminDao#checkLogin(java.lang.String, java.lang.String)
	 */
	@Override
	public Admin checkLogin(String adminName, String adminPwd) {
		
		String hql = "From Admin a where a.adminName=? AND a.adminPwd=?";
		
		return (Admin) this.getSession().createQuery(hql).setString(0, adminName)
										  .setString(1, adminPwd)
										  .uniqueResult();
	}

	/* (non-Javadoc)
	 * @see com.revolver.survey.admin.component.dao.i.AdminDao#getAdminCount()
	 */
	@Override
	public int getAdminCount() {
		String hql = "SELECT COUNT(*) FROM Admin";
		Long result = (Long) this.getSession().createQuery(hql).uniqueResult();
		return result.intValue();
	}

	/* (non-Javadoc)
	 * @see com.revolver.survey.admin.component.dao.i.AdminDao#getAdminList(java.lang.Integer, int)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Admin> getAdminList(Integer pageNo, int adminPageSize) {
		String hql = "From Admin";
		return this.getSession().createQuery(hql).setFirstResult((pageNo - 1)*adminPageSize)
												 .setMaxResults(adminPageSize)
												 .list();
	}

	/* (non-Javadoc)
	 * @see com.revolver.survey.admin.component.dao.i.AdminDao#bathcRemove(java.util.List)
	 */
	@Override
	public void bathcRemove(List<Integer> adminIdList) {
		String sql = "DELETE FROM ADMINS WHERE ADMIN_ID=?";
		
		this.doBatchWork(sql, adminIdList);
	}

	/* (non-Javadoc)
	 * @see com.revolver.survey.admin.component.dao.i.AdminDao#batchGeenrateAdmin(java.util.List)
	 */
	@Override
	public void batchGeenrateAdmin(List<Admin> adminList) {
		String sql = "INSERT INTO ADMINS(ADMIN_NAME,ADMIN_PWD) VALUES(?,?)";
		
		List<Object[]> params = new ArrayList<Object[]>();
		for (Admin admin : adminList) {
			Object[] param = new Object[2];
			param[0] = admin.getAdminName();
			param[1] = admin.getAdminPwd();
			params.add(param);
 		}
		
		this.doBatchWork2(sql, params );
	}

	/* (non-Javadoc)
	 * @see com.revolver.survey.admin.component.dao.i.AdminDao#getCurrentRoleIdList(java.lang.Integer)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Integer> getCurrentRoleIdList(Integer adminId) {
		String sql = "SELECT `ROLE_ID` FROM `admin_role_inner` WHERE `ADMIN_ID`=?";
		return this.getSession().createSQLQuery(sql).setInteger(0, adminId).list();
	}

	/* (non-Javadoc)
	 * @see com.revolver.survey.admin.component.dao.i.AdminDao#deleteAllRolesByAdminId(java.lang.Integer)
	 */
	@Override
	public void deleteAllRolesByAdminId(Integer adminId) {
		String sql = "DELETE FROM `admin_role_inner` WHERE `ADMIN_ID`=?";
		this.getSession().createSQLQuery(sql).setInteger(0, adminId).executeUpdate();
	}

	/* (non-Javadoc)
	 * @see com.revolver.survey.admin.component.dao.i.AdminDao#batchSaveRole(java.lang.Integer, java.util.List)
	 */
	@Override
	public void batchSaveRole(Integer adminId, List<Integer> roleIdList) {
		String sql = "INSERT INTO `admin_role_inner`(`ADMIN_ID`,`ROLE_ID`) VALUES(?,?)";
		
		this.batchSave(adminId, roleIdList, sql);
		
	}

	/* (non-Javadoc)
	 * @see com.revolver.survey.admin.component.dao.i.AdminDao#checkId(java.lang.Integer)
	 */
	@Override
	public boolean checkId(Integer adminId) {
		String sql = "SELECT COUNT(*) FROM ADMIN_ROLE_INNER WHERE ADMIN_ID=?";
		return this.checkId(sql, adminId);
	}

}

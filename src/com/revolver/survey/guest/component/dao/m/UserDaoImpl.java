package com.revolver.survey.guest.component.dao.m;

import org.springframework.stereotype.Repository;

import com.revolver.survey.admin.entity.Role;
import com.revolver.survey.base.m.BaseDaoImpl;
import com.revolver.survey.guest.component.dao.i.UserDao;
import com.revolver.survey.guest.entity.User;

/**
 * 
 * @author REVOLVER
 * 
 */
@Repository("userDao")
public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao {

	@Override
	public boolean checkName(String userName) {
		String hpl = "SELECT COUNT(*) FROM User u WHERE u.userName=?";
		Long count = (Long) getSession()
								.createQuery(hpl)
								.setString(0, userName)
								.uniqueResult();
		return (count > 0);
	}

	@Override
	public User checkLogin(String userName, String userPwd) {
		String hql = "FROM User u WHERE u.userName=? AND u.userPwd=?";
		return (User) getSession().createQuery(hql).setParameter(0, userName)
				.setParameter(1, userPwd).uniqueResult();
	}

	/* (non-Javadoc)
	 * @see com.revolver.survey.guest.component.dao.i.UserDao#findRoleName(java.lang.String)
	 */
	@Override
	public Role findRoleName(String roleName) {
		String hql = "From Role r WHERE r.roleName=?";
		return (Role) this.getSession().createQuery(hql).setString(0, roleName).uniqueResult();
	}

	/* (non-Javadoc)
	 * @see com.revolver.survey.guest.component.dao.i.UserDao#saveUserRole(java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public void saveUserRole(Integer userId, Integer roleId) {
		String sql = "INSERT INTO USER_ROLE_INNER(USER_ID,ROLE_ID) VALUES(?,?)";
		
		this.getSession().createSQLQuery(sql).setInteger(0, userId).setInteger(1, roleId).executeUpdate();
	}

}

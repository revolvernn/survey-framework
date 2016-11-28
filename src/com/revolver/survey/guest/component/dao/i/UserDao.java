package com.revolver.survey.guest.component.dao.i;

import com.revolver.survey.admin.entity.Role;
import com.revolver.survey.base.i.BaseDao;
import com.revolver.survey.guest.entity.User;
/**
 * 
 * @author REVOLVER
 *
 */
public interface UserDao extends BaseDao<User> {


	boolean checkName(String userName);

	User checkLogin(String userName, String userPwd);

	/**
	 * @param roleName
	 * @return
	 */
	Role findRoleName(String roleName);

	/**
	 * @param userId
	 * @param roleId
	 */
	void saveUserRole(Integer userId, Integer roleId);

}

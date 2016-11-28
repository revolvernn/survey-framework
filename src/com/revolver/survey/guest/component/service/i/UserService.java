package com.revolver.survey.guest.component.service.i;

import java.util.Set;

import com.revolver.survey.admin.entity.Role;
import com.revolver.survey.base.i.BaseService;
import com.revolver.survey.guest.entity.User;

/**
 * 
 * @author REVOLVER
 * 
 */
public interface UserService extends BaseService<User> {

	boolean regist(User t);

	User login(User t);

	boolean vip(int vipDays, User t);

	/**
	 * @param roles
	 * @return
	 */
	String calculatAuthorityValue(Set<Role> roles);
	
	void resetAuthority(String roleName, User user);

	/**
	 * @param user
	 */
	void addVipRole(User user);

}

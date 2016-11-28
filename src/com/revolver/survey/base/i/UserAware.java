package com.revolver.survey.base.i;

import com.revolver.survey.guest.entity.User;

/**
 * 后期User注入
 * 
 * @author REVOLVER
 * 
 */
public interface UserAware<T> {
	
	void setUser(User user);
}

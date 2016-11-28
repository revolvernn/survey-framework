package com.revolver.survey.admin.component.interceptor;

import java.util.Date;
import java.util.Set;

import org.apache.struts2.ServletActionContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.revolver.survey.admin.entity.Role;
import com.revolver.survey.guest.component.service.i.UserService;
import com.revolver.survey.guest.entity.User;
import com.revolver.survey.utils.GlobalNames;

/**
 * 
 * @author REVOLVER2016年3月2日
 * 
 */
public class VIPInterceptor extends AbstractInterceptor {

	private static final long serialVersionUID = 1L;

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {

		// 获取用户
		User user = (User) invocation.getInvocationContext().getSession().get(GlobalNames.LOGIN_USER);

		if (user == null || !user.isPayStatus())
			return invocation.invoke();

		Long endTime = user.getEndTime();
		long time = new Date().getTime();
		if (endTime <= time) {
			user.setPayStatus(false);
			Set<Role> roles = user.getRoleSet();
			for (Role role : roles) {
				String roleName = role.getRoleName();
				if ("付费登录用户".equals(roleName)) {
					roles.remove(role);
				}
			}

			WebApplicationContext ioc = WebApplicationContextUtils.getWebApplicationContext(ServletActionContext.getServletContext());
			UserService userService = ioc.getBean(UserService.class);
			String authorityStr = userService.calculatAuthorityValue(roles);
			user.setAuthorityStr(authorityStr);
			userService.updateEntity(user);
		}

		return invocation.invoke();
	}

}

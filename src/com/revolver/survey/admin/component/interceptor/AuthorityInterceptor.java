/**
 * 
 */
package com.revolver.survey.admin.component.interceptor;

import java.util.HashSet;
import java.util.Set;

import org.apache.struts2.ServletActionContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionProxy;
import com.opensymphony.xwork2.ValidationAware;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.revolver.survey.admin.component.service.i.ResourceService;
import com.revolver.survey.admin.entity.Admin;
import com.revolver.survey.admin.entity.Resource;
import com.revolver.survey.base.i.UserAware;
import com.revolver.survey.guest.entity.User;
import com.revolver.survey.utils.DataProcessUtils;
import com.revolver.survey.utils.GlobalNames;

/**
 * @author REVOLVER2016年3月3日
 *
 */
public class AuthorityInterceptor extends AbstractInterceptor {

	private static final long serialVersionUID = 1L;

	/* (non-Javadoc)
	 * @see com.opensymphony.xwork2.interceptor.AbstractInterceptor#intercept(com.opensymphony.xwork2.ActionInvocation)
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		Object action = invocation.getAction();
		WebApplicationContext ioc = WebApplicationContextUtils.getWebApplicationContext(ServletActionContext.getServletContext());
		
		Admin admin = (Admin) invocation.getInvocationContext().getSession().get(GlobalNames.LOGIN_ADMIN);
		User user = (User) invocation.getInvocationContext().getSession().get(GlobalNames.LOGIN_USER);
		
		
		if(admin != null && "SuperAdmin".equals(admin.getAdminName())){
			return invocation.invoke();
		}
		
		//游客
		Set<String> visitorAllowedAction = new HashSet<String>();
		
		visitorAllowedAction.add("UserAction_login");
		visitorAllowedAction.add("UserAction_regist");
		visitorAllowedAction.add("SurveyAction_top10");
		visitorAllowedAction.add("AdminAction_toAdminMain");
		visitorAllowedAction.add("AdminAction_login");
		visitorAllowedAction.add("EngageAction_findAllAvailableSurvey");
		
		//访问name = "ToPageAction_*"
		visitorAllowedAction.add("ToPageAction_user_login");
		visitorAllowedAction.add("ToPageAction_user_regist");
		
		ActionProxy proxy = invocation.getProxy();
		String actionName = proxy.getActionName();
		if(visitorAllowedAction.contains(actionName)) {
			return invocation.invoke();
		}
				
		String namespace = proxy.getNamespace();
		//前台
		if("/Guest".equals(namespace)) {
			//注入User
			if(action instanceof UserAware){
				UserAware ua = (UserAware)action;
				
				ua.setUser(user);
			}
			
			if(user == null) {
				
				if(action instanceof ValidationAware) {
					ValidationAware va = (ValidationAware) action;
					va.addActionError("请登录后再操作！");
				}
				
				return "loginError";
			}else{
				
				//检查是否具备访问目标资源的权限
				//1.查询得到目标资源对象
				ResourceService resourceService = ioc.getBean(ResourceService.class);
				Resource resource = resourceService.getResourceByResName(actionName);
				
				//2.检查是否具备资源
				String resCode = user.getAuthorityStr();
				boolean checkResult = DataProcessUtils.checkAuthority(resource, resCode);
				
				if(checkResult) {
					return invocation.invoke();
				}else{
					
					if(action instanceof ValidationAware) {
						ValidationAware va = (ValidationAware) action;
						va.addActionError("您没有访问【"+resource.getResourceName()+"】这个资源的权限");
					}
					
					return "globalErrMsg";
				}
				
			}
			
		}
		
		//后台
		if("/Admin".equals(namespace)) {
			
			if(admin == null) {
				
				if(action instanceof ValidationAware) {
					ValidationAware va = (ValidationAware) action;
					va.addActionError("请登录后再操作！");
				}
				
				return "toAdminLoginPage";
			}else{
				
				//检查是否具备访问目标资源的权限
				//1.查询得到目标资源对象
				ResourceService resourceService = ioc.getBean(ResourceService.class);
				Resource resource = resourceService.getResourceByResName(actionName);
				
				//2.检查是否具备资源
				String resCode = admin.getAuthorityStr();
				boolean checkResult = DataProcessUtils.checkAuthority(resource, resCode);
				
				if(checkResult) {
					return invocation.invoke();
				}else{
					
					if(action instanceof ValidationAware) {
						ValidationAware va = (ValidationAware) action;
						va.addActionError("您没有访问【"+resource.getResourceName()+"】这个资源的权限");
					}
					
					return "globalErrMsg";
				}
				
			}
			
		}

		return invocation.invoke();
	}

}

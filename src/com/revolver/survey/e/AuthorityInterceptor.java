package com.revolver.survey.e;

import java.util.HashSet;
import java.util.Set;

import org.apache.struts2.ServletActionContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.opensymphony.xwork2.ActionContext;
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
 * 
 * @author REVOLVER2016年3月2日
 * 
 */
public class AuthorityInterceptor extends AbstractInterceptor {

	private static final long serialVersionUID = 1L;

	@SuppressWarnings("rawtypes")
	@Override
	public String intercept(ActionInvocation invocation) throws Exception {

		//检查超级管理员，如果是超级管理员登录则直接放行
				Object maybeSuperAdmin = ActionContext.getContext().getSession().get(GlobalNames.LOGIN_ADMIN);
				if("SuperAdmin".equals(maybeSuperAdmin)) return invocation.invoke();
				
				//0.准备工作
				Object action = invocation.getAction();
				WebApplicationContext ioc = WebApplicationContextUtils.getWebApplicationContext(ServletActionContext.getServletContext());
				
				//1.声明游客可访问的资源集合：公共资源
				Set<String> visitorAllowedAction = new HashSet<String>();
				visitorAllowedAction.add("UserAction_login");
				visitorAllowedAction.add("UserAction_regist");
				visitorAllowedAction.add("SurveyAction_top10");
				visitorAllowedAction.add("EngageAction_findAllAvailableSurvey");
				visitorAllowedAction.add("AdminAction_toAdminMain");
				visitorAllowedAction.add("AdminAction_login");
				
				//访问name = "ToPageAction_*"
				visitorAllowedAction.add("ToPageAction_user_login");
				visitorAllowedAction.add("ToPageAction_user_regist");
				
				//2.获取当前请求的目标ActionName
				ActionProxy proxy = invocation.getProxy();
				String actionName = proxy.getActionName();

				//3.判断目标ActionName是不是公共资源
				if(visitorAllowedAction.contains(actionName)) {
					//4.如果是公共资源，则直接放行
					return invocation.invoke();
				}else{
					//5.如果不是公共资源，则检查权限
					//6.获取当前请求的目标资源的名称空间
					String namespace = proxy.getNamespace();
					
					//7.检查名称空间
					if("/Admin".equals(namespace)) {
						//8.如果是Admin，则尝试获取Admin对象
						Admin admin = (Admin) ActionContext.getContext().getSession().get(GlobalNames.LOGIN_ADMIN);
						
						//9.检查Admin对象是否为null，如果是，则跳转到管理员登录页面
						if(admin == null) {
							if(action instanceof ValidationAware) {
								ValidationAware va = (ValidationAware) action;
								va.addActionError("这个资源需要管理员才可以使用的！");
							}
							return "toAdminLoginPage";
						}
						
						//10.检查Admin是否是超级管理员，如果是，则直接放行
						if("SuperAdmin".equals(admin.getAdminName())) {
							return invocation.invoke();
						}
						
						//11.根据目标资源的URL地址查询对应的权限位和权限码
						ResourceService resourceService = ioc.getBean(ResourceService.class);
						Resource resource = resourceService.getResourceByResName(actionName);
						
						//12.检查当前管理员是否具备访问目标资源的权限
						String authorityStr = admin.getAuthorityStr();
						
						if(DataProcessUtils.checkAuthority(resource, authorityStr)) {
							if(action instanceof ValidationAware) {
								ValidationAware va = (ValidationAware) action;
								va.addActionError("对不起，您没有访问【"+resource.getResourceName()+"】这个资源的权限！");
							}
							return "globalErrMsg";
						}else{
							return invocation.invoke();
						}
						
					}
					
					if("/Guest".equals(namespace)) {
						//9.如果是Guest，则尝试获取User对象
						User user = (User) ActionContext.getContext().getSession().get(GlobalNames.LOGIN_USER);
						
						if(user == null) {
							if(action instanceof ValidationAware) {
								ValidationAware va = (ValidationAware) action;
								va.addActionError("这个资源登录才可以使用的！");
							}
							return "loginError";
						}
						
						ResourceService resourceService = ioc.getBean(ResourceService.class);
						Resource resource = resourceService.getResourceByResName(actionName);
						
						String authorityStr = user.getAuthorityStr();
						
						if(DataProcessUtils.checkAuthority(resource, authorityStr)) {
							if(action instanceof ValidationAware) {
								ValidationAware va = (ValidationAware) action;
								va.addActionError("对不起，您没有访问【"+resource.getResourceName()+"】这个资源的权限！");
							}
							return "globalErrMsg";
						}else{
							
							if(action instanceof UserAware) {
								UserAware ua = (UserAware) action;
								ua.setUser(user);
							}
							
							return invocation.invoke();
						}
					}
				}
		return invocation.invoke();
	}

}

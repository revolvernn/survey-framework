package com.revolver.survey.guest.component.interceptor;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionProxy;
import com.opensymphony.xwork2.ValidationAware;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.revolver.survey.base.i.UserAware;
import com.revolver.survey.guest.component.service.i.UserService;
import com.revolver.survey.guest.entity.User;
import com.revolver.survey.utils.GlobalNames;
/**
 * 
 * @author REVOLVER
 *
 */
//		⑤自定义拦截器：LoginInterceptor
public class LoginInterceptor extends AbstractInterceptor {

	private static final long serialVersionUID = 1L;

	@SuppressWarnings("rawtypes")
	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		//未登录用户可以访问登录页面\注册页面
		Set<String> visitorAllowedAction = new HashSet<String>();
		//访问name = "UserAction_*"
		visitorAllowedAction.add("UserAction_login");
		visitorAllowedAction.add("UserAction_regist");
		visitorAllowedAction.add("SurveyAction_top10");
		visitorAllowedAction.add("AdminAction_toAdminMain");
		visitorAllowedAction.add("AdminAction_login");
		visitorAllowedAction.add("EngageAction_findAllAvailableSurvey");
		
		//访问name = "ToPageAction_*"
		visitorAllowedAction.add("ToPageAction_user_login");
		visitorAllowedAction.add("ToPageAction_user_regist");
		
		//登录用户可访问的页面
		Set<String> loginUserAllowedAction = new HashSet<String>();
		//访问name = "UserAction_*"
		loginUserAllowedAction.add("UserAction_logout");
		loginUserAllowedAction.add("UserAction_update");
		loginUserAllowedAction.add("UserAction_vip");
		loginUserAllowedAction.add("UserAction_pay");
		
		//访问name = "SurveyAction_*"
		loginUserAllowedAction.add("EngageAction_findAllAvailableSurvey");
		loginUserAllowedAction.add("SurveyAction_top10");
		
		//访问name = "ToPageAction_*"
		loginUserAllowedAction.add("ToPageAction_user_myCenter");
		loginUserAllowedAction.add("ToPageAction_user_pay");
		loginUserAllowedAction.add("ToPageAction_user_vip");
		
		
		//1.获取当前访问目标资源
		ActionProxy proxy = invocation.getProxy();
		
		//获取当前访问Action名称
		String actionName = proxy.getActionName();
		
		//2.获取session对象
		HttpSession session = ServletActionContext.getRequest().getSession();
		//从session域中获取User对象
		User user = (User) session.getAttribute(GlobalNames.LOGIN_USER);
		
		//如果访问其他相关页面,则转到登录页面
		Object action = invocation.getAction();
		
		if(user==null){
			//未登录用户可以访问登录页面\注册页面
			if(visitorAllowedAction.contains(actionName)){
				//放行
				return invocation.invoke();
			}else {
				if(action instanceof ValidationAware) {
					ValidationAware va = (ValidationAware) action;
					va.addActionError("这个操作需要登录后才可以使用！");
				}
				
				return "loginError";
			}
			
		}else{
			
			//注入User
			if(action instanceof UserAware){
				UserAware ua = (UserAware)action;
				
				ua.setUser(user);
			}
			
			//判断是否是会员
			if(user.isPayStatus()){
				
				//获取用户会员截止日期
				long endTime = user.getEndTime();
				
				//获取当前日期
				long time = new Date().getTime();
				
				if(endTime <= time){
					//会员已到期设置状态为普通用户
					user.setPayStatus(false);
					
					
					ServletContext sc = ServletActionContext.getServletContext();
					
					//获取IOC
					WebApplicationContext ioc = WebApplicationContextUtils.getWebApplicationContext(sc);
					
					//获取userService实例
					UserService userService = ioc.getBean(UserService.class);
					
					//更新用户设置
					userService.updateEntity(user);
					
					//去vip续费页面
					return "toRefill_VIP";
				}
				//vip未到期可放行
				return invocation.invoke();
				
			}else{
				
				//检查是否是普通用户可以访问的资源
				if(loginUserAllowedAction.contains(actionName)) {
					return invocation.invoke();
				}else{
					
					//Object action = invocation.getAction();
					if(action instanceof ValidationAware) {
						ValidationAware va = (ValidationAware) action;
						va.addActionError("这个操作需要开通VIP后才可以使用！");
					}
					//去vip续费页面
					return "toRefill_VIP";
				}
			}
		}
	}

}

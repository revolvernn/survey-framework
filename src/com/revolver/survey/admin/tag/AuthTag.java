/**
 * 
 */
package com.revolver.survey.admin.tag;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import org.apache.struts2.ServletActionContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.opensymphony.xwork2.ActionContext;
import com.revolver.survey.admin.component.service.i.ResourceService;
import com.revolver.survey.admin.entity.Admin;
import com.revolver.survey.admin.entity.Resource;
import com.revolver.survey.guest.entity.User;
import com.revolver.survey.utils.DataProcessUtils;
import com.revolver.survey.utils.GlobalNames;

/**
 * @author REVOLVER2016年3月3日
 * 
 */
public class AuthTag extends SimpleTagSupport {
	
	private String targetRes;
	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.jsp.tagext.SimpleTagSupport#doTag()
	 */
	@Override
	public void doTag() throws JspException, IOException {
		// getJspBody().invoke(null);
		WebApplicationContext ioc = WebApplicationContextUtils.getWebApplicationContext(ServletActionContext.getServletContext());

		Admin admin = (Admin) ActionContext.getContext().getSession().get(GlobalNames.LOGIN_ADMIN);
		User user = (User) ActionContext.getContext().getSession().get(GlobalNames.LOGIN_USER);

		if (admin != null && "SuperAdmin".equals(admin.getAdminName())) {
			 getJspBody().invoke(null);
			 return;
		}
		
		//游客
		Set<String> visitorAllowedAction = new HashSet<String>();

		visitorAllowedAction.add("UserAction_login");
		visitorAllowedAction.add("UserAction_regist");
		visitorAllowedAction.add("SurveyAction_top10");
		visitorAllowedAction.add("AdminAction_toAdminMain");
		visitorAllowedAction.add("AdminAction_login");
		visitorAllowedAction.add("EngageAction_findAllAvailableSurvey");

		// 访问name = "ToPageAction_*"
		visitorAllowedAction.add("ToPageAction_user_login");
		visitorAllowedAction.add("ToPageAction_user_regist");

		if (visitorAllowedAction.contains(targetRes)) {
			getJspBody().invoke(null);
			return;
		}
				
		if(user != null){
			
		//检查是否具备访问目标资源的权限
		//1.查询得到目标资源对象
		ResourceService resourceService = ioc.getBean(ResourceService.class);
		Resource resource = resourceService.getResourceByResName(targetRes);
		
		//2.检查是否具备资源
		String resCode = user.getAuthorityStr();
		boolean checkResult = DataProcessUtils.checkAuthority(resource, resCode);
		
		if(checkResult) {
			 getJspBody().invoke(null);
			 return;
		}
		}
		if(admin != null){
			
			ResourceService resourceService = ioc.getBean(ResourceService.class);
			Resource resource = resourceService.getResourceByResName(targetRes);
			
			//2.检查是否具备资源
			String resCode = admin.getAuthorityStr();
			boolean checkResult = DataProcessUtils.checkAuthority(resource, resCode);
			
			if(checkResult) {
				 getJspBody().invoke(null);
				 return;
			}
		}
	}
	
	/**
	 * @param targetRes the targetRes to set
	 */
	public void setTargetRes(String targetRes) {
		this.targetRes = targetRes;
	}
}

/**
 * 
 */
package com.revolver.survey.admin.component.interceptor;

import org.apache.struts2.ServletActionContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionProxy;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.revolver.survey.admin.component.service.i.ResourceService;
import com.revolver.survey.admin.entity.Resource;
import com.revolver.survey.utils.DataProcessUtils;

/**
 * ActionName拦截器
 * 
 * @author REVOLVER2016年2月27日
 * 
 */
public class GenerateResourceInterceptor extends AbstractInterceptor {

	private static final long serialVersionUID = 1L;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.opensymphony.xwork2.interceptor.AbstractInterceptor#intercept(com.opensymphony.xwork2.ActionInvocation)
	 */
	@Override
	public String intercept(ActionInvocation invocation) throws Exception {

		ActionProxy proxy = invocation.getProxy();

		String actionName = proxy.getActionName();

		String resourceName = DataProcessUtils.translateName(actionName);

		// 获取ioc容器
		WebApplicationContext ioc = WebApplicationContextUtils.getWebApplicationContext(ServletActionContext.getServletContext());

		// 获取ResourceService
		ResourceService resourceService = ioc.getBean(ResourceService.class);

		boolean exists = resourceService.getResByName(actionName);

		if (exists)
			return invocation.invoke();

		// 设置允许最大资源码
		long allowedMaxCode = 1 << 60;

		Integer resPos = null;
		Long resCode = null;
		Long maxResCode = null;
		
		Integer maxResPos = resourceService.getMaxResPos();
		if(maxResPos != null){
			maxResCode = resourceService.getCurrentMaxResCode(maxResPos);
		}
		
		
		if (maxResPos == null) {
			resPos = 0;
		} else {
			if (maxResCode == allowedMaxCode) {
				maxResPos++;
			}

			resPos = maxResPos;
		}

		// 计算资源码
		if (maxResCode == null) {
			resCode = 1L;
		} else if (maxResCode == allowedMaxCode) {
			resCode = 1L;
		} else {
			resCode = maxResCode << 1;
		}

		Resource resource = new Resource(actionName, resourceName, resPos, resCode);

		resourceService.saveEntity(resource);

		return invocation.invoke();
	}

}

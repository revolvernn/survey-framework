/**
 * 
 */
package com.revolver.survey.admin.advisory;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import org.aspectj.lang.ProceedingJoinPoint;

import com.opensymphony.xwork2.ActionContext;
import com.revolver.survey.admin.component.service.i.LogService;
import com.revolver.survey.admin.entity.Admin;
import com.revolver.survey.admin.entity.Log;
import com.revolver.survey.guest.entity.User;
import com.revolver.survey.utils.GlobalNames;

/**
 * @author REVOLVER2016年3月1日
 * 
 */
public class LogAspect {
	
	private LogService logService;
	
	public Object record(ProceedingJoinPoint joinPoint) {

		String operator = getOperator();
		String operateTime = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss").format(new Date());
		String methodName = joinPoint.getSignature().getName();
		String methodType = joinPoint.getSignature().getDeclaringTypeName();
		
		Object[] args = joinPoint.getArgs();
		String methodArgs = Arrays.asList(args).toString();
		String methodReturnValue = null;
		String methodResultMsg = null;
		Object result = null;
		try {
			result = joinPoint.proceed(args);
			methodReturnValue = (result == null)?"无":result.toString(); 
			methodResultMsg = "方法执行成功!";
			
		} catch (Throwable e) {
			methodResultMsg = "方法执行失败: "+e.getMessage();
		} finally {
			Log log = new Log(operator, operateTime, methodName, methodType, methodArgs, methodReturnValue, methodResultMsg);
			logService.saveLogs(log);
		}

		return result;
	}

	/**
	 * @return
	 */
	private String getOperator() {
		Admin admin = (Admin) ActionContext.getContext().getSession().get(GlobalNames.LOGIN_ADMIN);
		User user = (User) ActionContext.getContext().getSession().get(GlobalNames.LOGIN_USER);
		String login = null;
		if(admin!= null){
			login = "[管理员]["+admin.getAdminName()+"]";
		}
		if(user != null){
			login = "[用户]["+user.getUserName()+"]";
		}
		
		return (login != null)?login:"[游客]";
	}
	
	/**
	 * @param logService the logService to set
	 */
	public void setLogService(LogService logService) {
		this.logService = logService;
	}
}

package com.revolver.survey.base.m;

import java.io.File;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ApplicationAware;
import org.apache.struts2.interceptor.ParameterAware;
import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.web.context.ServletContextAware;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;
import com.revolver.survey.utils.ValidateUtils;

/**
 * 
 * @author REVOLVER
 *
 * @param <T>
 */
public class BaseAction<T> extends ActionSupport implements RequestAware, SessionAware, ApplicationAware, ServletRequestAware, ServletContextAware, ParameterAware, ModelDriven<T>, Preparable {

	private static final long serialVersionUID = 1L;
	protected T t;
	private Class<T> entityType;
	protected ServletContext application;
	protected HttpServletRequest request;
	protected Map<String, Object> appMap;

	protected Map<String, Object> sessionMap;
	protected Map<String, Object> reqMap;
	protected String inputPath;

	protected Integer surveyId;
	protected Integer bagId;
	protected Map<String, String[]> parameters;
	
	@SuppressWarnings("unchecked")
	public BaseAction() {
		Type type = this.getClass().getGenericSuperclass();
		this.entityType = (Class<T>) (((ParameterizedType) type).getActualTypeArguments())[0];
		try {
			t = entityType.newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// **************************************

	@Override
	public T getModel() {
		return t;
	}

	@Override
	public void prepare() throws Exception {
	}

	// **************************************

	/**
	 * 判断路径是否有效
	 * 
	 * @param logoPath
	 * @return
	 */
	public boolean isLogoExists(String logoPath) {

		if (ValidateUtils.stringValidate(logoPath)) {
			String realPath = application.getRealPath(logoPath);
			return new File(realPath).exists();
		}

		return false;
	}

	// **************************************
	@Override
	public void setServletContext(ServletContext arg0) {
		this.application = arg0;
	}

	@Override
	public void setServletRequest(HttpServletRequest arg0) {
		this.request = arg0;
	}

	@Override
	public void setApplication(Map<String, Object> arg0) {
		this.appMap = arg0;
	}

	@Override
	public void setSession(Map<String, Object> arg0) {
		this.sessionMap = arg0;
	}

	@Override
	public void setRequest(Map<String, Object> arg0) {
		this.reqMap = arg0;
	}

	public void setInputPath(String inputPath) {
		this.inputPath = inputPath;
	}

	public String getInputPath() {
		return inputPath;
	}

	public void setSurveyId(Integer surveyId) {
		this.surveyId = surveyId;
	}

	public Integer getSurveyId() {
		return surveyId;
	}

	public void setBagId(Integer bagId) {
		this.bagId = bagId;
	}

	public Integer getBagId() {
		return bagId;
	}

	@Override
	public void setParameters(Map<String, String[]> parameters) {
		this.parameters = parameters;
	}
}

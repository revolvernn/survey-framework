/**
 * 
 */
package com.revolver.survey.admin.component.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.revolver.survey.admin.component.service.i.LogService;
import com.revolver.survey.admin.entity.Log;
import com.revolver.survey.base.m.BaseAction;
import com.revolver.survey.guest.model.Page;
import com.revolver.survey.utils.GlobalNames;
import com.revolver.survey.utils.GlobalValues;

/**
 * @author REVOLVER2016年3月1日
 *
 */
@Controller
@Scope("prototype")
public class LogAction extends BaseAction<Log> {

	private static final long serialVersionUID = 1L;
	
	@Autowired
	private LogService logService;

	private String pageNoStr;

	public String showLogs(){
		
		Page<Log> page =logService.getLogPage(pageNoStr,GlobalValues.LOG_PAGE_SIZE);
		reqMap.put(GlobalNames.PAGE, page);
		
		return "toShowLogs";
	}
	
	/**
	 * @param pageNoStr the pageNoStr to set
	 */
	public void setPageNoStr(String pageNoStr) {
		this.pageNoStr = pageNoStr;
	}
	
}

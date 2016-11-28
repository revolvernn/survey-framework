/**
 * 
 */
package com.revolver.survey.admin.listener;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import com.revolver.survey.admin.component.service.i.LogService;
import com.revolver.survey.utils.DataProcessUtils;

/**
 * @author REVOLVER2016年3月4日
 *
 */
public class LogInitListener implements ApplicationListener<ApplicationEvent>{
	private LogService logService;
	/* (non-Javadoc)
	 * @see org.springframework.context.ApplicationListener#onApplicationEvent(org.springframework.context.ApplicationEvent)
	 */
	@Override
	public void onApplicationEvent(ApplicationEvent event) {
			if(event instanceof ContextRefreshedEvent){
				String tableName = DataProcessUtils.generateTableName(0);
				logService.createTable(tableName);
				
				tableName = DataProcessUtils.generateTableName(1);
				logService.createTable(tableName);
				
				tableName = DataProcessUtils.generateTableName(2);
				logService.createTable(tableName);
			}
	}
	
	/**
	 * @param logService the logService to set
	 */
	public void setLogService(LogService logService) {
		this.logService = logService;
	}
}

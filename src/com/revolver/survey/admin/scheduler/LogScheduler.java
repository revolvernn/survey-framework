/**
 * 
 */
package com.revolver.survey.admin.scheduler;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.revolver.survey.admin.component.service.i.LogService;
import com.revolver.survey.utils.DataProcessUtils;

/**
 * @author REVOLVER2016年3月4日
 *	定时自成生成为数库表
 */
public class LogScheduler extends QuartzJobBean {

	private LogService logService;
	
	/* (non-Javadoc)
	 * @see org.springframework.scheduling.quartz.QuartzJobBean#executeInternal(org.quartz.JobExecutionContext)
	 */
	@Override
	protected void executeInternal(JobExecutionContext arg0) throws JobExecutionException {
		String tableName = DataProcessUtils.generateTableName(1);
		logService.createTable(tableName);
		
		tableName = DataProcessUtils.generateTableName(2);
		logService.createTable(tableName);
		
		tableName = DataProcessUtils.generateTableName(3);
		logService.createTable(tableName);
	}
	
	/**
	 * @param logService the logService to set
	 */
	public void setLogService(LogService logService) {
		this.logService = logService;
	}
}

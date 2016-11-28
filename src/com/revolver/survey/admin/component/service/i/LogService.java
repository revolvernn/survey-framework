/**
 * 
 */
package com.revolver.survey.admin.component.service.i;

import com.revolver.survey.admin.entity.Log;
import com.revolver.survey.base.i.BaseService;
import com.revolver.survey.guest.model.Page;

/**
 * @author REVOLVER2016年3月1日
 *
 */
public interface LogService extends BaseService<Log> {

	/**
	 * @param tableName
	 */
	void createTable(String tableName);

	/**
	 * @param log
	 */
	void saveLogs(Log log);

	/**
	 * @param pageNoStr
	 * @param logPageSize
	 * @return
	 */
	Page<Log> getLogPage(String pageNoStr, int logPageSize);

}

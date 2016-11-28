/**
 * 
 */
package com.revolver.survey.admin.component.dao.i;

import java.util.List;

import com.revolver.survey.admin.entity.Log;
import com.revolver.survey.base.i.BaseDao;

/**
 * @author REVOLVER2016年3月1日
 *
 */
public interface LogDao extends BaseDao<Log> {

	/**
	 * @param tableName
	 */
	void createTable(String tableName);

	/**
	 * @param log
	 */
	void saveLogs(Log log);
	
	/**
	 * 
	 * @return
	 */
	List<String> getAllTableNames();
	
	/**
	 * @param pageNoStr
	 * @param logPageSize
	 * @return
	 */
	List<Log> getLogList(int pageNo, int pageSize);

	/**
	 * @return
	 */
	int getLogTotalCount();

}

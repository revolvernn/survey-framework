/**
 * 
 */
package com.revolver.survey.admin.component.service.m;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revolver.survey.admin.component.dao.i.LogDao;
import com.revolver.survey.admin.component.service.i.LogService;
import com.revolver.survey.admin.entity.Log;
import com.revolver.survey.base.m.BaseServiceImpl;
import com.revolver.survey.guest.model.Page;

/**
 * @author REVOLVER2016年3月1日
 *
 */
@Service
public class LogServiceImpl extends BaseServiceImpl<Log> implements LogService{
	@Autowired
	private LogDao logDao;

	/* (non-Javadoc)
	 * @see com.revolver.survey.admin.component.service.i.LogService#createTable(java.lang.String)
	 */
	@Override
	public void createTable(String tableName) {
		logDao.createTable( tableName);
		
	}

	/* (non-Javadoc)
	 * @see com.revolver.survey.admin.component.service.i.LogService#saveLogs(com.revolver.survey.admin.entity.Log)
	 */
	@Override
	public void saveLogs(Log log) {
		logDao.saveLogs(log);
	}

	/* (non-Javadoc)
	 * @see com.revolver.survey.admin.component.service.i.LogService#getLogPage(java.lang.String, int)
	 */
	@Override
	public Page<Log> getLogPage(String pageNoStr, int logPageSize) {
		
		int totalRecordNo = logDao.getLogTotalCount();
		Page<Log> page = new Page<Log>(pageNoStr, totalRecordNo, logPageSize);
		
		Integer pageNo = page.getPageNo();
		
		List<Log> list = logDao.getLogList(pageNo, logPageSize);
		
		page.setList(list);
		
		return page;
	}
}

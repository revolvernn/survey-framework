/**
 * 
 */
package com.revolver.survey.admin.component.dao.m;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.revolver.survey.admin.component.dao.i.LogDao;
import com.revolver.survey.admin.entity.Log;
import com.revolver.survey.base.m.BaseDaoImpl;
import com.revolver.survey.utils.DataProcessUtils;

/**
 * @author REVOLVER2016年3月1日
 *
 */
@Repository
public class LogDaoImpl extends BaseDaoImpl<Log> implements LogDao {

	/* (non-Javadoc)
	 * @see com.revolver.survey.admin.component.dao.i.LogDao#createTable(java.lang.String)
	 */
	@Override
	public void createTable(String tableName) {
		String sql = "CREATE TABLE IF NOT EXISTS "+tableName+" LIKE `logs`";
		
		this.getSession().createSQLQuery(sql).executeUpdate();
	}

	/* (non-Javadoc)
	 * @see com.revolver.survey.admin.component.dao.i.LogDao#saveLogs(com.revolver.survey.admin.entity.Log)
	 */
	@Override
	public void saveLogs(Log log) {
		String tableName = DataProcessUtils.generateTableName(0);
		
		String sql = "INSERT INTO "+tableName+"(`OPERATOR`,"
											 + "`OPERATE_TIME`,"
											 + "`METHOD_NAME`,"
											 + "`METHOD_TYPE`,"
											 + "`METHOD_ARGS`,"
											 + "`METHOD_RETURN_VALUE`,"
											 + "`METHOD_RESULT_MSG`) "
											 + "VALUES(?,?,?,?,?,?,?)";
		
		this.getSession().createSQLQuery(sql).setString(0, log.getOperator())
										     .setString(1, log.getOperateTime())
										     .setString(2, log.getMethodName())
										     .setString(3, log.getMethodType())
										     .setString(4, log.getMethodArgs())
										     .setString(5, log.getMethodReturnValue())
										     .setString(6, log.getMethodResultMsg())
										     .executeUpdate();
	}

	/* (non-Javadoc)
	 * @see com.revolver.survey.admin.component.dao.i.LogDao#getAllTableNames()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<String> getAllTableNames() {
		String sql = "SELECT table_name FROM information_schema.`TABLES` WHERE table_name LIKE 'logs_%' AND table_schema='survey'";
		return this.getSession().createSQLQuery(sql).list();
	}

	/* (non-Javadoc)
	 * @see com.revolver.survey.admin.component.dao.i.LogDao#getLogList(java.lang.String, int)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Log> getLogList(int pageNo, int pageSize) {
		List<String> tableNames = getAllTableNames();
		
		String subSelect = DataProcessUtils.generateSubSelect(tableNames);
		
		//String sql = "SELECT `LOG_ID`,`OPERATOR`,`OPERATE_TIME`,`METHOD_NAME`,`METHOD_TYPE`,`METHOD_ARGS`,`METHOD_RETURN_VALUE`,`METHOD_RESULT_MSG` FROM (SELECT * FROM logs_2016_3 UNION SELECT * FROM logs_2016_4 UNION SELECT * FROM logs_2016_5) log_unin LIMIT ?,?"
		
		String sql = "SELECT log_unin.`LOG_ID`,"
						  + "log_unin.`OPERATOR`,"
						  + "log_unin.`OPERATE_TIME`,"
						  + "log_unin.`METHOD_NAME`,"
						  + "log_unin.`METHOD_TYPE`,"
						  + "log_unin.`METHOD_ARGS`,"
						  + "log_unin.`METHOD_RETURN_VALUE`,"
						  + "log_unin.`METHOD_RESULT_MSG` "
						  + "FROM ("+subSelect+") log_unin";
		
		List<Object[]> result = this.getSession().createSQLQuery(sql).setFirstResult((pageNo-1)*pageSize)
																	 .setMaxResults(pageSize)
																	 .list();
		
		List<Log> list = new ArrayList<Log>();
		for (Object[] objects : result) {
			Integer  logId = (Integer) objects[0];
			String operator = (String) objects[1];
			String operateTime = (String) objects[2];
			String methodName = (String) objects[3];
			String methodType = (String) objects[4];
			String methodArgs = (String) objects[5];
			String methodReturnValue = (String) objects[6];
			String methodResultMsg = (String) objects[7];
			
			Log log = new Log(logId, operator, operateTime, methodName, methodType, methodArgs, methodReturnValue, methodResultMsg);
			
			list.add(log);
		}
		
		
		return list;
	}


	/* (non-Javadoc)
	 * @see com.revolver.survey.admin.component.dao.i.LogDao#getLogTotalCount()
	 */
	@Override
	public int getLogTotalCount() {
		List<String> tableNames = getAllTableNames();
		
		String subSelect = DataProcessUtils.generateSubSelect(tableNames);
		
		String sql = "SELECT COUNT(log_unin.log_id) FROM ("+subSelect+") log_unin";
		BigInteger result = (BigInteger) this.getSession().createSQLQuery(sql).uniqueResult();
		
		return result.intValue();
	}

}

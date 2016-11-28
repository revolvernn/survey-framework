package com.revolver.survey.base.i;

import java.util.List;

/**
 * 
 * @author REVOLVER
 * 
 * @param <T>
 */
public interface BaseDao<T> {

	void saveOrUpdateEntity(T t);

	void saveEntity(T t) throws Exception;

	void deleteEntity(T t) throws Exception;

	void updateEntity(T t) throws Exception;
	
	boolean checkId(String sql, Integer oId);

	List<T> getEntityList();

	/**
	 * @param entId
	 * @return
	 */
	T getEntityById(Integer entId);
	
	void doBatchWork(final String sql, final List<Integer> objIdList);

}

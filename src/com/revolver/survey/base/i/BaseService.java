package com.revolver.survey.base.i;

import java.util.List;

/**
 * 
 * @author REVOLVER
 * 
 * @param <T>
 */
public interface BaseService<T> {
	void saveOrUpdateEntity(T t);

	void saveEntity(T t);

	void deleteEntity(T t) throws Exception;

	void updateEntity(T t) throws Exception;

	T getEntityById(Integer entId) throws Exception;

	List<T> getEntityList() throws Exception;
}

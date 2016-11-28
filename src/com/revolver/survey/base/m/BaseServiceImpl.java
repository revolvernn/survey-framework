package com.revolver.survey.base.m;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.revolver.survey.base.i.BaseDao;
import com.revolver.survey.base.i.BaseService;

/**
 * 
 * @author REVOLVER
 * 
 * @param <T>
 */
@Transactional
@Service
public class BaseServiceImpl<T> implements BaseService<T> {
	@Autowired
	private BaseDao<T> baseDao;

	@Override
	public void saveEntity(T t) {
		try {
			baseDao.saveEntity(t);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteEntity(T t) throws Exception {
		baseDao.deleteEntity(t);
	}

	@Override
	public void updateEntity(T t) throws Exception {
		baseDao.updateEntity(t);
	}

	@Override
	public T getEntityById(Integer entId) throws Exception {
		return baseDao.getEntityById(entId);
	}

	@Override
	public List<T> getEntityList() throws Exception {
		return baseDao.getEntityList();
	}

	@Override
	public void saveOrUpdateEntity(T t) {
		baseDao.saveOrUpdateEntity(t);
	}

}

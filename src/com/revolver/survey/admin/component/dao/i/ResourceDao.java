/**
 * 
 */
package com.revolver.survey.admin.component.dao.i;

import java.util.List;

import com.revolver.survey.admin.entity.Resource;
import com.revolver.survey.base.i.BaseDao;

/**
 * @author REVOLVER2016年2月27日
 *
 */
public interface ResourceDao extends BaseDao<Resource>{

	/**
	 * @param actionName
	 * @return
	 */
	boolean getResByName(String actionName);

	/**
	 * @return
	 */
	Integer getMaxResPos();

	/**
	 * @param maxResPos
	 * @return
	 */
	Long getCurrentMaxResCode(Integer maxResPos);

	/**
	 * @return
	 */
	int getResCount();

	/**
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	List<Resource> getAllResList(Integer pageNo, int pageSize);

	/**
	 * @param resIdList
	 */
	void batchRemove(List<Integer> resIdList);

	/**
	 * @param actionName
	 * @return
	 */
	Resource getResourceByResName(String actionName);

}

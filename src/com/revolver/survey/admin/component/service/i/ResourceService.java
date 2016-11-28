/**
 * 
 */
package com.revolver.survey.admin.component.service.i;

import java.util.List;

import com.revolver.survey.admin.entity.Resource;
import com.revolver.survey.base.i.BaseService;
import com.revolver.survey.guest.model.Page;

/**
 * @author REVOLVER2016年2月27日
 *
 */
public interface ResourceService extends BaseService<Resource> {
	
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
	 * @param pageNoStr
	 * @param i
	 * @return
	 */
	Page<Resource> getAllResList(String pageNoStr, int pageSize);

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

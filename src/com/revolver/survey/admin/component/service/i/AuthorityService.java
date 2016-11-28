/**
 * 
 */
package com.revolver.survey.admin.component.service.i;

import java.util.List;

import com.revolver.survey.admin.entity.Authority;
import com.revolver.survey.base.i.BaseService;
import com.revolver.survey.guest.model.Page;

/**
 * @author REVOLVER2016年2月29日
 *
 */
public interface AuthorityService extends BaseService<Authority>{

	/**
	 * @param pageNoStr
	 * @param authPageSize
	 * @return
	 */
	Page<Authority> getAuthList(String pageNoStr, int authPageSize);

	/**
	 * @param authIdList
	 */
	void bathRemove(List<Integer> authIdList);

	/**
	 * @param authorityId
	 * @param resIdList
	 */
	void batchSaveRes(Integer authorityId, List<Integer> resIdList);

	/**
	 * @param authorityId
	 * @return
	 */
	List<Integer> getCurrentResIdList(Integer authorityId);

	/**
	 * @return
	 */
	List<Authority> getAllAuthList() throws Exception;

}

/**
 * 
 */
package com.revolver.survey.admin.component.dao.i;

import java.util.List;

import com.revolver.survey.admin.entity.Authority;
import com.revolver.survey.base.i.BaseDao;

/**
 * @author REVOLVER2016年2月29日
 *
 */
public interface AuthorityDao extends BaseDao<Authority> {

	/**
	 * @return
	 */
	int getAuthorityCount();

	/**
	 * @param pageNo
	 * @param authPageSize
	 * @return
	 */
	List<Authority> getAllAuthList(Integer pageNo, int authPageSize);

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
	 * @param authorityId
	 */
	void deleteAllResByAuthId(Integer authorityId);

	/**
	 * @param authorityId
	 * @return
	 */
	boolean checkId(Integer authorityId);


}

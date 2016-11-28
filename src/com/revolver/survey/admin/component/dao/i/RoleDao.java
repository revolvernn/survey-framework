/**
 * 
 */
package com.revolver.survey.admin.component.dao.i;

import java.util.List;

import com.revolver.survey.admin.entity.Role;
import com.revolver.survey.base.i.BaseDao;

/**
 * @author REVOLVER2016年2月29日
 *
 */
public interface RoleDao extends BaseDao<Role> {

	/**
	 * @param roleIdList
	 */
	void batchRemove(List<Integer> roleIdList);

	/**
	 * @param roleId
	 * @return
	 */
	List<Integer> getCurrentAuthIdList(Integer roleId);

	/**
	 * @param roleId
	 * @param authIdList
	 */
	void batchSaveAuth(Integer roleId, List<Integer> authIdList);

	/**
	 * @param roleId
	 */
	void deleteAllAuthByRoleId(Integer roleId);

	/**
	 * @param roleId
	 * @return
	 */
	boolean checkId(Integer roleId);

	/**
	 * @return
	 */
	List<Role> getRoleList();

	/**
	 * @param roleId
	 * @return
	 */
	boolean checkIdExists(Integer roleId);

}

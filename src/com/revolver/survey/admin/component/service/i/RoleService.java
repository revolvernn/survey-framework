/**
 * 
 */
package com.revolver.survey.admin.component.service.i;

import java.util.List;

import com.revolver.survey.admin.entity.Role;
import com.revolver.survey.base.i.BaseService;

/**
 * @author REVOLVER2016年2月29日
 *
 */
public interface RoleService extends BaseService<Role>{

	/**
	 * @return
	 */
	List<Role> getRoleList() throws Exception;

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

}

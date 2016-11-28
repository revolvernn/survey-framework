/**
 * 
 */
package com.revolver.survey.admin.component.service.i;

import java.util.List;

import com.revolver.survey.admin.entity.Admin;
import com.revolver.survey.base.i.BaseService;
import com.revolver.survey.guest.model.Page;

/**
 * @author REVOLVER2016年2月26日
 *
 */
public interface AdminService extends BaseService<Admin> {

	/**
	 * @param t
	 * @return
	 */
	Admin login(Admin t);

	/**
	 * @param pageNoStr
	 * @param adminPageSize
	 * @return
	 */
	Page<Admin> getAdminList(String pageNoStr, int adminPageSize);

	/**
	 * @param adminIdList
	 */
	void batchRemove(List<Integer> adminIdList);

	/**
	 * 
	 */
	void batchGeenrateAdmin() throws Exception;

	/**
	 * 
	 */
	void batchCalculateAuth();

	/**
	 * @param adminId
	 * @return
	 */
	List<Integer> getCurrentRoleIdList(Integer adminId);

	/**
	 * @param adminId
	 * @param roleIdList
	 */
	void batchSaveRole(Integer adminId, List<Integer> roleIdList);

}

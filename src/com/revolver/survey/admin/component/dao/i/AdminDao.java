
package com.revolver.survey.admin.component.dao.i;

import java.util.List;

import com.revolver.survey.admin.entity.Admin;
import com.revolver.survey.base.i.BaseDao;

/**
 * @author REVOLVER2016年2月26日
 *
 */
public interface AdminDao extends BaseDao<Admin> {

	/**
	 * @param adminName
	 * @param adminPwd
	 * @return
	 */
	Admin checkLogin(String adminName, String adminPwd);

	/**
	 * @return
	 */
	int getAdminCount();

	/**
	 * @param pageNo
	 * @param adminPageSize
	 * @return
	 */
	List<Admin> getAdminList(Integer pageNo, int adminPageSize);

	/**
	 * @param adminIdList
	 */
	void bathcRemove(List<Integer> adminIdList);

	/**
	 * @param adminList
	 */
	void batchGeenrateAdmin(List<Admin> adminList);

	/**
	 * @param adminId
	 * @return
	 */
	List<Integer> getCurrentRoleIdList(Integer adminId);

	/**
	 * @param adminId
	 */
	void deleteAllRolesByAdminId(Integer adminId);

	/**
	 * @param adminId
	 * @param roleIdList
	 */
	void batchSaveRole(Integer adminId, List<Integer> roleIdList);

	/**
	 * @param adminId
	 * @return
	 */
	boolean checkId(Integer adminId);

}

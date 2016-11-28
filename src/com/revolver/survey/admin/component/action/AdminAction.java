/**
 * 
 */
package com.revolver.survey.admin.component.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.revolver.survey.admin.component.service.i.AdminService;
import com.revolver.survey.admin.component.service.i.RoleService;
import com.revolver.survey.admin.entity.Admin;
import com.revolver.survey.admin.entity.Role;
import com.revolver.survey.base.m.BaseAction;
import com.revolver.survey.e.CatchDelAdminException;
import com.revolver.survey.guest.model.Page;
import com.revolver.survey.utils.GlobalNames;
import com.revolver.survey.utils.GlobalValues;

/**
 * @author REVOLVER2016年2月26日
 * 
 */
@Controller
@Scope("prototype")
public class AdminAction extends BaseAction<Admin> {

	private static final long serialVersionUID = 1L;
	@Autowired
	private AdminService adminService;
	@Autowired
	private RoleService roleService;
	
	private String pageNoStr;
	private List<Integer> adminIdList;
	private Integer adminId;
	private List<Integer> roleIdList;
	
	public String batchCalculateAuth(){
		
		adminService.batchCalculateAuth();
		
		return "toAdminMainPage";
	}
	
	public String batchSaveRole(){
			adminService.batchSaveRole(adminId,roleIdList);
		return "toShowAdminsAction";
	}
	
	public String roleManager() throws Exception{
		List<Role> roleList = roleService.getEntityList();
		reqMap.put(GlobalNames.ROLE_LIST, roleList);
		
		List<Integer> currentRoleIdList = adminService.getCurrentRoleIdList(adminId);
		reqMap.put(GlobalNames.CURRENT_ROLEID_LIST, currentRoleIdList);
		
		return "toRoleManagerPage";
	}
	
	public String generateAdmin() throws Exception{
		
		adminService.batchGeenrateAdmin();
		
		return "toShowAdminsAction";
	}
	
	public void prepareUpdate() throws Exception{
		this.t = adminService.getEntityById(adminId);
	}
	
	public String update() throws Exception{
		adminService.updateEntity(t);
		return "updateSuccess";
	}
	
	public String batchRemove(){
		
		try {
			adminService.batchRemove(adminIdList);
		} catch (Exception e) {
			throw new CatchDelAdminException();
		}
		return "toShowAdminsAction";
	}
	
	public String showAdmins() throws Exception{
		
		Page<Admin> page = adminService.getAdminList(pageNoStr,GlobalValues.ADMIN_PAGE_SIZE);
		
		reqMap.put(GlobalNames.PAGE, page);
		
		return "toShowAdminPage";
	}

	public String toAdminMain() {
		return "toAdminLoginPage";
	}

	public String logout() {
		
		sessionMap.remove((GlobalNames.LOGIN_ADMIN));
		
		return "toIndexPage";
	}
	public String login() {

		Admin admin = adminService.login(t);

		if (admin != null) {
			System.out.println(t);
			sessionMap.put(GlobalNames.LOGIN_ADMIN, admin);
			return "toAdminMainPage";
		} else {
			addActionError("用户名或密码不正确!!!");
			return "toAdminLoginPage";
		}
	}
	
	/**
	 * @param pageNoStr the pageNoStr to set
	 */
	public void setPageNoStr(String pageNoStr) {
		this.pageNoStr = pageNoStr;
	}
	
	/**
	 * @param adminIdList the adminIdList to set
	 */
	public void setAdminIdList(List<Integer> adminIdList) {
		this.adminIdList = adminIdList;
	}
	
	/**
	 * @param adminId the adminId to set
	 */
	public void setAdminId(Integer adminId) {
		this.adminId = adminId;
	}
	
	public Map<String,String> getMessage(){
		Map<String , String> map = new HashMap<String , String>();
			map.put(GlobalNames.MESSAGE, "操作成功!!!");
		return map;
	}
	
	/**
	 * @param roleIdList the roleIdList to set
	 */
	public void setRoleIdList(List<Integer> roleIdList) {
		this.roleIdList = roleIdList;
	}
}

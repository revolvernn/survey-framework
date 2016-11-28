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

import com.revolver.survey.admin.component.service.i.AuthorityService;
import com.revolver.survey.admin.component.service.i.RoleService;
import com.revolver.survey.admin.entity.Authority;
import com.revolver.survey.admin.entity.Role;
import com.revolver.survey.base.m.BaseAction;
import com.revolver.survey.e.CatchDelRoleException;
import com.revolver.survey.utils.GlobalNames;

/**
 * @author REVOLVER2016年2月29日
 *
 */
@Controller
@Scope("prototype")
public class RoleAction extends BaseAction<Role>{
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private RoleService roleService;
	@Autowired
	private AuthorityService authorityService;

	private List<Integer> roleIdList;

	private Integer roleId;

	private List<Integer> authIdList;
	
	public String batchSaveAuth(){
		
		roleService.batchSaveAuth(roleId,authIdList);
		
		return "toShowRolesAction";
	}
	
	public String authManager() throws Exception{
		List<Authority> authList = authorityService.getAllAuthList();
		reqMap.put(GlobalNames.AUTH_LIST, authList);
		
		List<Integer> currentAuthIdList = roleService.getCurrentAuthIdList(roleId);
		
		reqMap.put(GlobalNames.CURRENT_AUTHID_LIST, currentAuthIdList);
		
		return "toAuthManagerPage";
	}
	
	public void prepareUpdate() throws Exception{
		this.t = roleService.getEntityById(roleId);
	}
	public String update() throws Exception{
		
		roleService.updateEntity(t);
		
		return "updateSuccess";
	}
	
	public String batchRemove(){
		
		try {
			roleService.batchRemove(roleIdList);
		} catch (Exception e) {
			throw new CatchDelRoleException();
		}
		
		return "toShowRolesAction";
	}
	
	public String showRoles() throws Exception{
		List<Role> roleList = roleService.getRoleList();
		
		reqMap.put(GlobalNames.ROLE_LIST, roleList);
		
		return "toShowRolesPage";
	}
	
	public String create(){
		
		try {
			roleService.saveEntity(t);
		} catch (Exception e) {
			addActionMessage("创建失败,角色已经存在!!!");
			return "toCreatePage";
		}
		addActionMessage("创建成功!!!");
		return "toCreatePage";
	}
	
	public String toCreatePage(){
		return "toCreatePage";
	}
	
	/**
	 * @param roleIdList the roleIdList to set
	 */
	public void setRoleIdList(List<Integer> roleIdList) {
		this.roleIdList = roleIdList;
	}
	/**
	 * 
	 * @return map
	 */
	public Map<String, String> getMessage(){
		Map<String, String> map = new HashMap<String, String>();
		
		map.put(GlobalNames.MESSAGE, "操作成功!!!");
		map.put(GlobalNames.ERROR_MSG, "删除失败,先取消分配资源,在操作!!!");
		
		return map;
	}
	
	/**
	 * @param roleId the roleId to set
	 */
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	
	/**
	 * @param authIdList the authIdList to set
	 */
	public void setAuthIdList(List<Integer> authIdList) {
		this.authIdList = authIdList;
	}
}

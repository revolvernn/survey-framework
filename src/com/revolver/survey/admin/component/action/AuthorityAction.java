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
import com.revolver.survey.admin.component.service.i.ResourceService;
import com.revolver.survey.admin.entity.Authority;
import com.revolver.survey.admin.entity.Resource;
import com.revolver.survey.base.m.BaseAction;
import com.revolver.survey.e.CatchDelAuthorityException;
import com.revolver.survey.guest.model.Page;
import com.revolver.survey.utils.GlobalNames;
import com.revolver.survey.utils.GlobalValues;

/**
 * @author REVOLVER2016年2月29日
 *
 */
@Controller
@Scope("prototype")
public class AuthorityAction extends BaseAction<Authority>{

	private static final long serialVersionUID = 1L;
	
	@Autowired
	private AuthorityService authorityService;
	@Autowired
	private ResourceService resourceService;

	private String pageNoStr;

	private List<Integer> authIdList;

	private Integer authorityId;

	private List<Integer> resIdList;
	
	public String batchSaveRes(){
		
		authorityService.batchSaveRes(authorityId,resIdList);
		
		return "toshowAuthAction";
	}
	
	public String resManager(){
		
		Page<Resource> page = resourceService.getAllResList(pageNoStr, GlobalValues.RES_PAGE_SIZE);
		
		List<Integer> currentResIdList = authorityService.getCurrentResIdList(authorityId);
		reqMap.put(GlobalNames.PAGE, page);
		reqMap.put(GlobalNames.CURRENT_RESID_LIST, currentResIdList);
		
		return "toResMngPage";
	}
	
	
	public void prepareUpdate() throws Exception{
		this.t = authorityService.getEntityById(authorityId);
	}
	public String update() throws Exception{
		authorityService.updateEntity(t);
		
		return "updateSuccess";
	}
	
	public String batchRemove(){
		
		try {
			authorityService.bathRemove(authIdList);
		} catch (Exception e) {
			throw new CatchDelAuthorityException();
		}
		
		return "toshowAuthAction";
	}
	
	public String showAuthorities(){
		
		Page<Authority> page = authorityService.getAuthList(pageNoStr,GlobalValues.AUTH_PAGE_SIZE);
		
		reqMap.put(GlobalNames.PAGE, page);
		
		return "toAuthListPage";
	}
	
	public String create(){
		
		try {
			authorityService.saveEntity(t);
		} catch (Exception e) {
			addActionMessage("创建失败,该权限名称已经存在!!!");
			return "toCreatePage";
		}
		addActionMessage("创建成功!!!");
		return "toCreatePage";
	}
	
	public String toCreatePage(){
		return "toCreatePage";
	}
	
	/**
	 * @param pageNoStr the pageNoStr to set
	 */
	public void setPageNoStr(String pageNoStr) {
		this.pageNoStr = pageNoStr;
	}
	
	/**
	 * @param authIdList the authIdList to set
	 */
	public void setAuthIdList(List<Integer> authIdList) {
		this.authIdList = authIdList;
	}
	
	/**
	 * @param authorityId the authorityId to set
	 */
	public void setAuthorityId(Integer authorityId) {
		this.authorityId = authorityId;
	}
	
	public Map<String,String> getMessage(){
		Map<String , String> map = new HashMap<String , String>();
			map.put(GlobalNames.MESSAGE, "操作成功!!!");
		return map;
	}
	
	/**
	 * @param resIdList the resIdList to set
	 */
	public void setResIdList(List<Integer> resIdList) {
		this.resIdList = resIdList;
	}
}

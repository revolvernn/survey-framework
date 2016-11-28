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

import com.revolver.survey.admin.component.service.i.ResourceService;
import com.revolver.survey.admin.entity.Resource;
import com.revolver.survey.base.m.BaseAction;
import com.revolver.survey.guest.model.Page;
import com.revolver.survey.utils.GlobalNames;
import com.revolver.survey.utils.GlobalValues;

/**
 * @author REVOLVER2016年2月27日
 *
 */
@Controller
@Scope("prototype")
public class ResourceAction extends BaseAction<Resource> {

	private static final long serialVersionUID = 1L;
	
	@Autowired
	private ResourceService resourceService;

	private String pageNoStr;

	private Integer resourceId;
	
	private List<Integer> resIdList;
	
	public String batchRemove(){
		
		resourceService.batchRemove(resIdList);
		
		return "toShowAllResourcesAction";
	}
	
	public void prepareUpdate() throws Exception{
		this.t =resourceService.getEntityById(resourceId);
	}
	
	public String update() throws Exception{
		resourceService.updateEntity(t);
		return "updateSuccess";
	}
	
	public String showAllResources(){
		Page<Resource> page = resourceService.getAllResList(pageNoStr,GlobalValues.RES_PAGE_SIZE);
		
		reqMap.put(GlobalNames.PAGE, page);
		
		return "toAllResListPage";
	}
	
	/**
	 * @param pageNoStr the pageNoStr to set
	 */
	public void setPageNoStr(String pageNoStr) {
		this.pageNoStr = pageNoStr;
	}
	
	/**
	 * @param resourceId the resourceId to set
	 */
	public void setResourceId(Integer resourceId) {
		this.resourceId = resourceId;
	}
	
	public Map<String, String> getMessage(){
		Map<String, String> map = new HashMap<String, String>();
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

/**
 * 
 */
package com.revolver.survey.admin.component.service.m;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revolver.survey.admin.component.dao.i.ResourceDao;
import com.revolver.survey.admin.component.service.i.ResourceService;
import com.revolver.survey.admin.entity.Resource;
import com.revolver.survey.base.m.BaseServiceImpl;
import com.revolver.survey.guest.model.Page;

/**
 * @author REVOLVER2016年2月27日
 *
 */
@Service
public class ResourceServiceImpl extends BaseServiceImpl<Resource> implements ResourceService{
	@Autowired
	private ResourceDao resourceDao;

	@Override
	public boolean getResByName(String actionName) {
		
		return resourceDao.getResByName(actionName);
	}


	/* (non-Javadoc)
	 * @see com.revolver.survey.admin.component.service.i.ResourceService#getMaxResPos()
	 */
	@Override
	public Integer getMaxResPos() {
		// TODO Auto-generated method stub
		return resourceDao.getMaxResPos();
	}

	/* (non-Javadoc)
	 * @see com.revolver.survey.admin.component.service.i.ResourceService#getCurrentMaxResCode(java.lang.Integer)
	 */
	@Override
	public Long getCurrentMaxResCode(Integer maxResPos) {
		// TODO Auto-generated method stub
		return resourceDao.getCurrentMaxResCode(maxResPos);
	}


	/* (non-Javadoc)
	 * @see com.revolver.survey.admin.component.service.i.ResourceService#getAllResList(java.lang.String, int)
	 */
	@Override
	public Page<Resource> getAllResList(String pageNoStr, int pageSize) {
		// 获取总记录数
				int totalRecordNo = resourceDao.getResCount();

				// 创建page对象
				Page<Resource> page = new Page<Resource>(pageNoStr, totalRecordNo, pageSize);

				Integer pageNo = page.getPageNo();

				List<Resource> list = resourceDao.getAllResList(pageNo, pageSize);

				page.setList(list);

				return page;
	}


	/* (non-Javadoc)
	 * @see com.revolver.survey.admin.component.service.i.ResourceService#batchRemove(java.util.List)
	 */
	@Override
	public void batchRemove(List<Integer> resIdList) {
		resourceDao.batchRemove(resIdList);
	}


	/* (non-Javadoc)
	 * @see com.revolver.survey.admin.component.service.i.ResourceService#getResourceByResName(java.lang.String)
	 */
	@Override
	public Resource getResourceByResName(String actionName) {
		
		return resourceDao.getResourceByResName(actionName);
	}
}

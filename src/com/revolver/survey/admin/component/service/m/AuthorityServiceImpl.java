/**
 * 
 */
package com.revolver.survey.admin.component.service.m;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revolver.survey.admin.component.dao.i.AuthorityDao;
import com.revolver.survey.admin.component.service.i.AuthorityService;
import com.revolver.survey.admin.entity.Authority;
import com.revolver.survey.base.m.BaseServiceImpl;
import com.revolver.survey.guest.model.Page;
import com.revolver.survey.utils.ValidateUtils;

/**
 * @author REVOLVER2016年2月29日
 *
 */
@Service
public class AuthorityServiceImpl extends BaseServiceImpl<Authority> implements AuthorityService {
	@Autowired
	private AuthorityDao authorityDao;

	/* (non-Javadoc)
	 * @see com.revolver.survey.admin.component.service.i.AuthorityService#getAuthList(java.lang.String, int)
	 */
	@Override
	public Page<Authority> getAuthList(String pageNoStr, int authPageSize) {
		int totalRecordNo = authorityDao.getAuthorityCount();
		
		Page<Authority> page = new Page<Authority>(pageNoStr, totalRecordNo, authPageSize);
		
		Integer pageNo = page.getPageNo();
		
		List<Authority> list = authorityDao.getAllAuthList(pageNo,authPageSize);
		
		page.setList(list);
		
		return page;
	}

	/* (non-Javadoc)
	 * @see com.revolver.survey.admin.component.service.i.AuthorityService#bathRemove(java.util.List)
	 */
	@Override
	public void bathRemove(List<Integer> authIdList) {
		authorityDao.bathRemove(authIdList);
	}

	/* (non-Javadoc)
	 * @see com.revolver.survey.admin.component.service.i.AuthorityService#batchSaveRes(java.lang.Integer, java.util.List)
	 */
	@Override
	public void batchSaveRes(Integer authorityId, List<Integer> resIdList) {
		boolean exists = authorityDao.checkId(authorityId);
		if(exists){
			//先将数据库中的数据删除
			authorityDao.deleteAllResByAuthId(authorityId);
		}
		
		if(ValidateUtils.validateCollection(resIdList)){
			//在重新保存
			authorityDao.batchSaveRes(authorityId,resIdList);
		}
	}

	/* (non-Javadoc)
	 * @see com.revolver.survey.admin.component.service.i.AuthorityService#getCurrentResIdList(java.lang.Integer)
	 */
	@Override
	public List<Integer> getCurrentResIdList(Integer authorityId) {
		
		return authorityDao.getCurrentResIdList(authorityId);
	}

	/* (non-Javadoc)
	 * @see com.revolver.survey.admin.component.service.i.AuthorityService#getAllAuthList()
	 */
	@Override
	public List<Authority> getAllAuthList() throws Exception {
		return authorityDao.getEntityList();
	}

}

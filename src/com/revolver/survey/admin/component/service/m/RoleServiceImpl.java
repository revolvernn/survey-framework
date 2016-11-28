/**
 * 
 */
package com.revolver.survey.admin.component.service.m;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revolver.survey.admin.component.dao.i.RoleDao;
import com.revolver.survey.admin.component.service.i.RoleService;
import com.revolver.survey.admin.entity.Role;
import com.revolver.survey.base.m.BaseServiceImpl;
import com.revolver.survey.utils.ValidateUtils;

/**
 * @author REVOLVER2016年2月29日
 * 
 */
@Service
public class RoleServiceImpl extends BaseServiceImpl<Role> implements RoleService {
	@Autowired
	private RoleDao roleDao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.revolver.survey.admin.component.service.i.RoleService#getRoleList()
	 */
	@Override
	public List<Role> getRoleList() throws Exception {
		return roleDao.getRoleList();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.revolver.survey.admin.component.service.i.RoleService#batchRemove(java.util.List)
	 */
	@Override
	public void batchRemove(List<Integer> roleIdList) {
		roleDao.batchRemove(roleIdList);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.revolver.survey.admin.component.service.i.RoleService#getCurrentAuthIdList(java.lang.Integer)
	 */
	@Override
	public List<Integer> getCurrentAuthIdList(Integer roleId) {
		// TODO Auto-generated method stub
		return roleDao.getCurrentAuthIdList(roleId);
	}

	/* (non-Javadoc)
	 * @see com.revolver.survey.admin.component.service.i.RoleService#batchSaveAuth(java.lang.Integer, java.util.List)
	 */
	@Override
	public void batchSaveAuth(Integer roleId, List<Integer> authIdList) {
		
		boolean exists = roleDao.checkIdExists(roleId);
		if(exists){
			//先将已存在的数据删除
			roleDao.deleteAllAuthByRoleId(roleId);
		}
		
		if(ValidateUtils.validateCollection(authIdList)){
			//在重新保存新在数据
			roleDao.batchSaveAuth(roleId, authIdList);
		}
	}

}

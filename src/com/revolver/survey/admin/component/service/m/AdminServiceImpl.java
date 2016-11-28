/**
 * 
 */
package com.revolver.survey.admin.component.service.m;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revolver.survey.admin.component.dao.i.AdminDao;
import com.revolver.survey.admin.component.dao.i.ResourceDao;
import com.revolver.survey.admin.component.service.i.AdminService;
import com.revolver.survey.admin.entity.Admin;
import com.revolver.survey.admin.entity.Authority;
import com.revolver.survey.admin.entity.Resource;
import com.revolver.survey.admin.entity.Role;
import com.revolver.survey.base.m.BaseServiceImpl;
import com.revolver.survey.guest.component.dao.i.UserDao;
import com.revolver.survey.guest.entity.User;
import com.revolver.survey.guest.model.Page;
import com.revolver.survey.utils.DataProcessUtils;
import com.revolver.survey.utils.GlobalValues;
import com.revolver.survey.utils.ValidateUtils;

/**
 * @author REVOLVER2016年2月26日
 * 
 */
@Service
public class AdminServiceImpl extends BaseServiceImpl<Admin> implements AdminService {
	
	@Autowired
	private AdminDao adminDao;
	@Autowired
	private ResourceDao resourceDao;
	@Autowired
	private UserDao userDao;
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.revolver.survey.admin.component.service.i.AdminService#login(com.revolver.survey.admin.entity.Admin)
	 */
	@Override
	public Admin login(Admin t) {
		String adminName = t.getAdminName();

		String adminPwd = t.getAdminPwd();

		if ("SuperAdmin".equals(adminName) && "admin".equals(adminPwd)) {
			return t;
		}
		return adminDao.checkLogin(adminName,adminPwd);
	}
	/* (non-Javadoc)
	 * @see com.revolver.survey.admin.component.service.i.AdminService#getAdminList(java.lang.String, int)
	 */
	@Override
	public Page<Admin> getAdminList(String pageNoStr, int adminPageSize) {
		
		int totalRecordNo = adminDao.getAdminCount();
		Page<Admin> page = new Page<Admin>(pageNoStr, totalRecordNo , adminPageSize);
		
		Integer pageNo = page.getPageNo();
		
		List<Admin> list = adminDao.getAdminList(pageNo,adminPageSize);
		page.setList(list);
		
		return page;
	}
	/* (non-Javadoc)
	 * @see com.revolver.survey.admin.component.service.i.AdminService#batchRemove(java.util.List)
	 */
	@Override
	public void batchRemove(List<Integer> adminIdList) {
		adminDao.bathcRemove(adminIdList);
	}
	/* (non-Javadoc)
	 * @see com.revolver.survey.admin.component.service.i.AdminService#batchGeenrateAdmin()
	 */
	@Override
	public void batchGeenrateAdmin() throws Exception {
		List<Admin> adminList = new ArrayList<Admin>();
		
		String adminPwd = null;
		String adminName = null;
		
		for (int i = 0; i < GlobalValues.GENERATE_SIZE; i++) {
			adminName = DataProcessUtils.generateRandomString(5)+DataProcessUtils.getRangedRandomNumber();
			adminPwd = DataProcessUtils.generateRandomString(6)+DataProcessUtils.getRangedRandomNumber();
			Admin admin = new Admin(adminName,adminPwd);
			
			adminList.add(admin);
		}
		
		adminDao.batchGeenrateAdmin(adminList);
	}
	/* (non-Javadoc)
	 * @see com.revolver.survey.admin.component.service.i.AdminService#batchCalculateAuth()
	 */
	@Override
	public void batchCalculateAuth() {
		List<Admin> adminList = adminDao.getEntityList();
		
		for (Admin admin : adminList) {
			Set<Role> roleSet = admin.getRoleSet();
			if(!ValidateUtils.validateCollection(roleSet)) continue;
			
			admin.setAuthorityStr(calculateAuthByRoleSet(roleSet));
		}
		
		List<User> userList = userDao.getEntityList();
		
		for (User user : userList) {
			Set<Role> roleSet = user.getRoleSet();
			if(!ValidateUtils.validateCollection(roleSet)) continue;
			
			user.setAuthorityStr(calculateAuthByRoleSet(roleSet));
		}
	}
	/**
	 * @param roleSet
	 * @return
	 */
	private String calculateAuthByRoleSet(Set<Role> roleSet) {
		//获取最大权限位
		Integer maxResPos = resourceDao.getMaxResPos();
		
		Long[] codeArr = new Long[maxResPos+1];
		
		for (Role role : roleSet) {
			Set<Authority> authSet = role.getAuthSet();
			if(!ValidateUtils.validateCollection(authSet)) continue;
			
			for (Authority authority : authSet) {
				Set<Resource> resourceSet = authority.getResourceSet();
				
				if(!ValidateUtils.validateCollection(resourceSet)) continue;
				for (Resource resource : resourceSet) {
					
					Integer resPos = resource.getResPos();
					
					Long resCode = resource.getResCode();
					
					Long oldCode = codeArr[resPos];
					
					if(oldCode == null){
						oldCode = 0L;
					}
					
					Long newCode = oldCode | resCode;
					
					codeArr[resPos] = newCode;
				}
			}
		}
		
		return DataProcessUtils.generateAuthStr(codeArr);
	}
	/* (non-Javadoc)
	 * @see com.revolver.survey.admin.component.service.i.AdminService#getCurrentRoleIdList(java.lang.Integer)
	 */
	@Override
	public List<Integer> getCurrentRoleIdList(Integer adminId) {
		
		return this.adminDao.getCurrentRoleIdList(adminId);
	}
	/* (non-Javadoc)
	 * @see com.revolver.survey.admin.component.service.i.AdminService#batchSaveRole(java.lang.Integer, java.util.List)
	 */
	@Override
	public void batchSaveRole(Integer adminId, List<Integer> roleIdList) {
		boolean exists = adminDao.checkId(adminId);
		if(exists){
			adminDao.deleteAllRolesByAdminId(adminId);
		}
		if(ValidateUtils.validateCollection(roleIdList)){
			adminDao.batchSaveRole(adminId,roleIdList);
		}
	}

}

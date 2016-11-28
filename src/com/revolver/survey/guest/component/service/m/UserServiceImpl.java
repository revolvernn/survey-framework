package com.revolver.survey.guest.component.service.m;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revolver.survey.admin.component.dao.i.ResourceDao;
import com.revolver.survey.admin.entity.Authority;
import com.revolver.survey.admin.entity.Resource;
import com.revolver.survey.admin.entity.Role;
import com.revolver.survey.base.m.BaseServiceImpl;
import com.revolver.survey.e.UserRoleNotExistsException;
import com.revolver.survey.guest.component.dao.i.UserDao;
import com.revolver.survey.guest.component.service.i.UserService;
import com.revolver.survey.guest.entity.User;
import com.revolver.survey.utils.DataProcessUtils;

/**
 * 
 * @author REVOLVER
 * 
 */
@Service("userService")
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService {
	@Autowired
	private UserDao userDao;

	@Autowired
	private ResourceDao resourceDao;

	@Override
	public boolean regist(User user) {

		boolean user1 = userDao.checkName(user.getUserName());

		if (!user1) {
			String userPwd = user.getUserPwd();

			String password = DataProcessUtils.md5(userPwd);
			user.setUserPwd(password);

			// 设置用户权限
			resetAuthority("普通登录用户", user);
			try {
				userDao.saveEntity(user);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return true;
		}
		return false;
	}

	/**
	 * @param string
	 * @param user
	 */
	@Override
	public void resetAuthority(String roleName, User user) {
		Role role = userDao.findRoleName(roleName);

		if (role == null)
			throw new UserRoleNotExistsException();
		
		Set<Role> roleSet = user.getRoleSet();

		if (roleSet == null) {
			roleSet = new HashSet<Role>();
			user.setRoleSet(roleSet);
		}

		roleSet.add(role);
		
		String authorityStr = calculatAuthorityValue(roleSet);
		user.setAuthorityStr(authorityStr);
	}

	@Override
	public User login(User t) {

		String userPwd = t.getUserPwd();
		userPwd = DataProcessUtils.md5(userPwd);

		return userDao.checkLogin(t.getUserName(), userPwd);

	}

	/**
	 * vip业务处理
	 */
	@Override
	public boolean vip(int vipDays, User t) {
		User user = userDao.getEntityById(t.getUserId());
		// 支付金额
		int payAmount = 0;

		// 会员套餐
		switch (vipDays) {
		case 30:
			payAmount = vipDays * 10; // 10元每天
			break;
		case 90:
			payAmount = vipDays * 8; // 8元每天
			break;
		case 180:
			payAmount = vipDays * 5; // 5元每天
			break;
		case 360:
			payAmount = vipDays * 3; // 3元每天
			break;
		}

		int balance = user.getBalance();

		if (balance < payAmount) {

			return false;

		} else {

			// 更新用户余额
			user.setBalance(balance - payAmount);

			long endTime = 0;

			// 计算vip截止日期
			long vipEndTime = (long) vipDays * 24 * 60 * 60 * 1000;

			// 判断是否为会员
			if (user.isPayStatus()) {
				// true 获取数据库中的日期
				endTime = user.getEndTime();
			} else {
				// false 创建当前日期
				endTime = new Date().getTime();
			}

			// 追加
			endTime += vipEndTime;

			// 设置vip截止日期
			user.setEndTime(endTime);

			// 更新用户
			user.setPayStatus(true);
			
			// 设置用户权限
			resetAuthority("付费登录用户", user);
			//addVipRole(user);
			try {
				userDao.updateEntity(user);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return true;
		}
	}
	
	@Override
	public void addVipRole(User user) {
		Role role = userDao.findRoleName("付费登录用户");
		if(role == null) throw new UserRoleNotExistsException();
		Integer userId = user.getUserId();
		Integer roleId = role.getRoleId();
		userDao.saveUserRole(userId,roleId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.revolver.survey.guest.component.service.i.UserService#calculatAuthorityValue(java.util.Set)
	 */
	@Override
	public String calculatAuthorityValue(Set<Role> roles) {
		Integer maxResPos = resourceDao.getMaxResPos();
		Long[] codeArr = new Long[maxResPos + 1];
		for (Role role : roles) {
			Set<Authority> authorities = role.getAuthSet();
			for (Authority authority : authorities) {
				Set<Resource> resources = authority.getResourceSet();
				for (Resource resource : resources) {
					Long resCode = resource.getResCode();
					Integer resPos = resource.getResPos();
					Long oldCode = codeArr[resPos];

					if (oldCode == null) {
						oldCode = 0L;
					}

					Long newCode = oldCode | resCode;
					codeArr[resPos] = newCode;
				}
			}
		}
		return DataProcessUtils.generateAuthStr(codeArr);
	}
}

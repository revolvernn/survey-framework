package com.revolver.survey.guest.component.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.revolver.survey.base.m.BaseAction;
import com.revolver.survey.guest.component.service.i.UserService;
import com.revolver.survey.guest.entity.User;
import com.revolver.survey.utils.GlobalNames;

/**
 * 
 * @author REVOLVER
 * 
 */
@Controller
@Scope("prototype")
public class UserAction extends BaseAction<User> {
	// ***************成员变量区***********************
	private static final long serialVersionUID = 1L;

	@Autowired
	private UserService userService;
	private int vipDays;
	private Integer amount;
	private Integer userId;

	// ***************Action方法区***********************

	// -----------------pay vip---------------------
	public void prepareUpdate() throws Exception {
		this.t = userService.getEntityById(userId);
	}

	public String update() throws Exception {

		userService.updateEntity(t);

		this.sessionMap.put(GlobalNames.LOGIN_USER, t);
		return SUCCESS;
	}

	/**
	 * VIP充值模块
	 * 
	 * @return
	 * @throws Exception 
	 */
	public String vip() throws Exception {
		this.t = (User) this.sessionMap.get(GlobalNames.LOGIN_USER);
		boolean flag = userService.vip(vipDays, t);
		t = userService.getEntityById(t.getUserId());
		if (flag) {
			this.sessionMap.put(GlobalNames.LOGIN_USER, t);
			return SUCCESS;
		} else {
			this.addActionError("您的余额不足,请先充值!");
			return "doVIPError";
		}
	}

	/**
	 * 充值模块
	 * 
	 * @return
	 * @throws Exception
	 */
	public String pay() throws Exception {

		User user = (User) this.sessionMap.get(GlobalNames.LOGIN_USER);

		user.setBalance(user.getBalance() + amount);

		userService.updateEntity(user);

		return SUCCESS;
	}

	// -----------------login regist logout---------------------
	/**
	 * 注册模块
	 * 
	 * @return
	 */
	public String regist() {
		boolean flag = userService.regist(t);
		if (flag) {
			return "registSuccess";
		} else {
			this.addActionError("该用户已经注册!");
			return "registError";
		}
	}

	/**
	 * 登录模块
	 * 
	 * @return
	 */
	public String login() {
		User user = userService.login(t);

		if (user != null) {
			this.sessionMap.put(GlobalNames.LOGIN_USER, user);
			return "loginSuccess";
		} else {
			this.addActionError("用户名或密码错误!");
			return "loginError";
		}
	}

	/**
	 * 登出模块
	 * 
	 * @return
	 */
	public String logout() {

		this.sessionMap.remove(GlobalNames.LOGIN_USER);

		return "logout";
	}

	// *************setXxx()/getXxx()方法区**********************

	public int getVipDays() {
		return vipDays;
	}

	public void setVipDays(int vipDays) {
		this.vipDays = vipDays;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}
}

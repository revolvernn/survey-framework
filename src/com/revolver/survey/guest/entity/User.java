package com.revolver.survey.guest.entity;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

import com.revolver.survey.admin.entity.Role;

/**
 * 用户类
 * 
 * @author REVOLVER
 * 
 */
public class User implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer userId;
	private String userName;
	private String userPwd;
	private String nickName;
	private String email;
	private int balance;
	private boolean payStatus;
	private long endTime;
	private Set<Role> roleSet;
	private String authorityStr;
	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPwd() {
		return userPwd;
	}

	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isPayStatus() {
		return payStatus;
	}

	public void setPayStatus(boolean payStatus) {
		this.payStatus = payStatus;
	}

	public long getEndTime() {
		return endTime;
	}

	public void setEndTime(long endTime) {
		this.endTime = endTime;
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	public String getVIPEndDate() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd HH:mm:ss");
		Date date = new Date(endTime);

		return sdf.format(date);
	}
	public String getAuthorityStr() {
		return authorityStr;
	}

	public void setAuthorityStr(String authorityStr) {
		this.authorityStr = authorityStr;
	}

	public Set<Role> getRoleSet() {
		return roleSet;
	}

	public void setRoleSet(Set<Role> roleSet) {
		this.roleSet = roleSet;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", userName=" + userName + ", nickName=" + nickName + ", email=" + email + ", payStatus=" + payStatus + ", authorityStr=" + authorityStr + "]";
	}
}

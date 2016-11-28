<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<div id="guestInfoBlock">
	<s:if test="#session.loginUser == null">
		<div id="guestInfoContent">
			<s:a action="ToPageAction_user_login" namespace="/Guest">登录</s:a>&nbsp;
			|
			<s:a action="ToPageAction_user_regist" namespace="/Guest">注册</s:a>&nbsp;
			|
			<s:a value="index.jsp">首页</s:a>
		</div>
	</s:if>
	<s:else>
		<div id="guestInfoContent">
			欢迎您 :&nbsp;<b style="color: red">
			<s:property value="#session.loginUser.nickName"/>
			<s:property value="#session.loginUser.payStatus==1?'付费会员':'普通会员 '"/>
			</b>&nbsp;|
			<s:a action="ToPageAction_user_myCenter" namespace="/Guest" >个人中心</s:a>&nbsp;
			
			|&nbsp;
			<s:if test="#session.loginUser.payStatus">
				<s:a action="ToPageAction_survey_create" namespace="/Guest">创建一个新的调查 &nbsp;|</s:a>
			</s:if>
			<s:a action="UserAction_logout" namespace="/Guest">退出登录</s:a>&nbsp; |
			&nbsp;<s:a value="index.jsp">首页</s:a>
		</div>
	</s:else>
</div>
<div class="block-div" id="headTitle">
	<img src="<s:url value="resources_static/surveyLogo.png"/>"/>
</div>
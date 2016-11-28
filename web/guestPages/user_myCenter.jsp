<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<s:include value="/resources_jsp/head.jsp"></s:include>
<script type="text/javascript">
$(function() {
		
	$("#updateUser").hide();
	
	$("#update_btn").click(function() {
		$("#updateUser").toggle();
	});
});
</script>
</head>
<body>
	<s:include value="/resources_jsp/top.jsp"></s:include>
	<div class="block-div navigatorDiv">
		<div class="locationDiv">我的基本信息</div>
		<div class="backToIndexDiv">
			<s:a value="index.jsp">回首页</s:a>
		</div>
	</div>

	<div class="block-div">
		<s:push value="#session.loginUser">
		<table class="dashedTable">
			<tr>
				<td class="leftTd">用户名</td>
				<td class="rightTd"><s:property value="userName"/></td>
			</tr>
			<tr>
				<td class="leftTd">昵称</td>
				<td class="rightTd"><s:property value="nickName"/></td>
			</tr>
			<tr>
				<td class="leftTd">我的余额</td>
				<td class="rightTd">￥：<b style="color: red"><s:property value="balance" /></b>&nbsp;元&nbsp;
				<s:a action="ToPageAction_user_pay" namespace="/Guest" >我要充值</s:a>
				</td>
			</tr>
			<tr>
				<td class="leftTd">用户状态</td>
				<td class="rightTd">
				<s:if test="!isPayStatus()">
					普通会员 
				</s:if>
				<s:else>
					<b style="color: red">VIP:付费会员&nbsp;有效期 : <s:property value="getVIPEndDate()"/>&nbsp; 到期</b>
				</s:else>
				 	&nbsp;
					<s:a action="ToPageAction_user_vip" namespace="/Guest">我要续费</s:a>
				</td>
			</tr>
			<tr>
				<td class="leftTd">Email</td>
				<td class="rightTd"><s:property value="email"/></td>
			</tr>
			<tr>
				<td class="leftTd" align="center" colspan="2"> <input id="update_btn" type="button" value="修改个人资料"/></td>
			</tr>
			
		</table>
		<s:form namespace="/Guest" action="UserAction_update">
		<s:hidden name="userId" />
		<s:token />
		<table class="dashedTable" id="updateUser">
			<tr>
				<td class="leftTd">用户名</td>
				<td class="rightTd"><s:textfield name="userName" disabled="true"/></td>
			</tr>
			<tr>
				<td class="leftTd">昵称</td>
				<td class="rightTd"><s:textfield name="nickName" value="%{nickName}"/> </td>
			</tr>
			<tr>
				<td class="leftTd">Email</td>
				<td class="rightTd"><s:textfield name="email" value="%{email}"/></td>
			</tr>
			<tr>
				<td class="leftTd" align="center" colspan="2"> <s:submit value="修改"/></td>
			</tr>
		</table>
		</s:form>
		</s:push>
	</div>

	<div class="block-div navigatorDiv">
		<div class="locationDiv">我的调查</div>
	</div>

	<div class="block-div">
		<table class="dashedTable">
			<tr>
				<td class="leftTd">我发起的调查</td>
				<td class="rightTd"><s:a namespace="/Guest" action="SurveyAction_myCompletedSurvey">查看详情</s:a></td>
			</tr>
			<tr>
				<td class="leftTd">我参与的调查</td>
				<td class="rightTd"><s:a namespace="/Guest" action="SurveyAction_myEngageSurvey">查看详情</s:a></td>
			</tr>
			<tr>
				<td class="leftTd">未完成的调查</td>
				<td class="rightTd"><s:a action="SurveyAction_myUncompleted" namespace="/Guest" >查看详情</s:a></td>
			</tr>
		</table>
	</div>
	<s:include value="/resources_jsp/bottom.jsp"></s:include>


</body>
</html>
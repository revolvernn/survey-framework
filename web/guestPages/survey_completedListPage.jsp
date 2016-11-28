<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<s:include value="/resources_jsp/head.jsp"></s:include>
</head>
<body>
	<s:include value="/resources_jsp/top.jsp"></s:include>
	<div class="block-div navigatorDiv">
		<div class="locationDiv">我发起的调查</div>
	</div>
	
	<s:push value="#request.page">
	<div class="block-div">
		<table class="dashedTable">
			<s:if test="list == null || list.empty">
			<tr>
				<td>您还没有创建任何调查！</td>
			</tr>
			</s:if>
			<s:else>
			<tr>
				<td>Logo</td>
				<td>调查标题</td>
				<td>调查状态</td>
				<td>调查结果</td>
			</tr>
			<s:iterator value="list">
			<tr>
				<td>
					<s:if test="isLogoExists(logoPath)">
						<img src="<s:url value="%{logoPath}"/>"/>
					</s:if>
					<s:else>
						<img src="<s:url value="resources_static/logo.gif"/>"/>
					</s:else>
				</td>
				<td>
					<s:property value="title"/>
				</td>
				<td>
					<s:if test="completed">已于<s:property value="formatedTime"/>完成</s:if>
				</td>
				<td>
					<s:a namespace="/Guest" action="StatisticsAction_showSummary?surveyId=%{surveyId}">查看统计结果</s:a>
				</td>
			</tr>
			</s:iterator>
			<tr>
				<td colspan="5" align="center">
					<s:set var="pageActionName" value="'SurveyAction_myUncompleted'"/>
					<s:set var="pageNamespace" value="'/Guest'"/>
					<s:include value="/resources_jsp/pageNavigator.jsp"/>
				</td>
			</tr>
			</s:else>
			
		</table>
	</div>
	</s:push>
	<s:include value="/resources_jsp/bottom.jsp"></s:include>


</body>
</html>
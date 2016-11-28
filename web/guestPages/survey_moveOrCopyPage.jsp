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
		<div class="locationDiv">将包裹: [ <span style="color:red"><s:property value="#session.currentBag.bagName"/></span> ] 移动或复制到调查</div>
		<div class="backToIndexDiv">
			<s:a namespace="/Guest" action="SurveyAction_myUncompleted">返回我的调查列表</s:a>
		</div>
	</div>
	<s:push value="#request.page">
	<div class="block-div">
		<table class="dashedTable">
			<s:if test="list == null || list.empty">
				<tr>
					<td>您目前只有一个调查!!!</td>
				</tr>
			</s:if>
			<s:else>
				<tr>
					<td>LOGO</td>
					<td>调查名称:</td>
					<td>操作</td>
				</tr>
				<s:iterator value="#request.page.list" var="survey">
				<tr>
					<td>
						<s:if test="isLogoExists(logoPath)"><img src="<s:url value="%{logoPath}"/>" /></s:if> 
						<s:else><img src="<s:url value="resources_static/logo.gif"/>" /></s:else>
					</td>
					<td>[<s:property value="title" />]</td>
					<td>
						<s:if test="surveyId== #session.currentSurvey.surveyId">当前调查</s:if>
						<s:else>
							<s:a namespace="/Guest" action="BagAction_copyBagToSurvey?surveyId=%{surveyId}&bagId=%{#session.currentBag.bagId}">复制到这个调查</s:a> |
							<s:a namespace="/Guest" action="BagAction_moveBagToSurvey?surveyId=%{surveyId}&bagId=%{#session.currentBag.bagId}">移动到这个调查</s:a>
						</s:else>
					</td>
				</tr>
				</s:iterator>
				<tr>
				<td colspan="5" align="center">
					<s:set var="pageActionName" value="'SurveyAction_toMoveOrCopyPage'"/>
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
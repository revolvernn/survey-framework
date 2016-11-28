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
		<div class="locationDiv">调整调查中包裹的顺序</div>
		<div class="backToIndexDiv">
			<s:a namespace="/Guest" action="SurveyAction_myUncompleted">返回我的调查列表</s:a>
		</div>
	</div>

	<div class="block-div navigatorDiv">
		<div class="locationDiv">

			<s:if test="isLogoExists(logoPath)">
				<img src="<s:url value="%{logoPath}"/>" />
			</s:if>
			<s:else>
				<img src="<s:url value="resources_static/logo.gif"/>" />
			</s:else>
			调查标题: 【
			<s:property value="title" />
			】
		</div>
	</div>
	<div class="block-div">
		<s:form namespace="/Guest" action="SurveyAction_bagOrderUpdate">
			<s:hidden name="surveyId" />
			<table class="dashedTable">
				<s:if test="bagSet == null || bagSet.size() == 0">
					<tr>
						<td>您目前没有创建包裹!!!</td>
					</tr>
				</s:if>
				<s:else>
					<tr>
						<td>包裹名称:</td>
						<td>旧序列号:</td>
						<td>新序列号:</td>
					</tr>
					<s:iterator value="bagSet" status="status">
 						<tr>
							<td class="leftTd">包裹名: <s:property value="bagName" /></td>
							<td ><s:textfield value="%{bagOrder}" disabled="true" cssStyle="width:40px; text-align: center;" /></td>
							<s:hidden name="bagList[%{#status.index}].bagId" cssStyle="width:50px; " value="%{bagId}" />
							<td><s:textfield name="bagList[%{#status.index}].bagOrder"
									cssStyle="width:50px; text-align: center;"/></td>
						</tr>
					</s:iterator>
					<tr>
						<td></td>
						<td></td>
						<td><s:submit value="OK" cssStyle="width:53px; "/> <s:if test="hasActionErrors() != null"><s:actionerror /></s:if></td>
					</tr>
				</s:else>
			</table>
		</s:form>
	</div>
	<s:include value="/resources_jsp/bottom.jsp"></s:include>
</body>
</html>
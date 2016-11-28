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
	<div class="block-div">
		<table class="dashedTable">
			<s:if
				test="#request.page == null || #request.page.list == null || #request.page.list.empty">
				<tr>
					<td>现在还没有调查可以参与，赶快开通会员去创建你的专属调查吧！！</td>
				</tr>
			</s:if>
			<s:else>
				<s:set var="cells" value="5" />
				<s:iterator value="#request.page.list" status="myStatus">
					<s:if
						test="(#myStatus.count-1 % #cells==0) && (#myStatus.count < #request.page.list.size())">
						<tr>
					</s:if>
					<td align="center"><s:if test="isLogoExists(logoPath)">
							<img src="<s:url value="%{logoPath}"/>" />
						</s:if> <s:else>
							<img src="<s:url value="/resources_static/logo.gif"/>" />
						</s:else> <br /> <s:a namespace="/Guest"
							action="EngageAction_entry?surveyId=%{surveyId}">
							<s:property value="title" />
						</s:a></td>
					<s:if test="#myStatus.count % #cells==0">
						</tr>
					</s:if>
				</s:iterator>
				<tr>
					<td colspan="5" align="center"><s:set var="pageActionName"
							value="'EngageAction_findAllAvailableSurvey'" /> <s:set
							var="pageNamespace" value="'/Guest'" /> <s:include
							value="/resources_jsp/pageNavigator.jsp" /></td>
				</tr>
			</s:else>
		</table>
	</div>
	<s:include value="/resources_jsp/bottom.jsp"></s:include>

</body>
</html>
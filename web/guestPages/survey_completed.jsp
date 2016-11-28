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
		<div class="block-div navigatorDiv" align="center">
		<s:if test="hasActionErrors()">
			<s:actionerror/>
			<a href="${header.referer }">返回继续完善</a>
		</s:if>
		<s:else>
			您的调查创建成功！
		</s:else>
		</div>
		<div align="center">
			<s:a namespace="/Guest" action="SurveyAction_myUncompleted">返回未完成调查列表</s:a>
		</div>
	<s:include value="/resources_jsp/bottom.jsp"></s:include>
</body>
</html>
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
	<div>
		<s:form action="SurveyAction_save" namespace="/Guest" enctype="multipart/form-data">
			<table class="formTable">
				<tr>
					<td colspan="2" align="center">调查列表</td>
				</tr>
				<tr>
					<td colspan="2" align="center">
					暂时没有列表!
					</td>
				</tr>
			</table>
		</s:form>
	</div>

	<s:include value="/resources_jsp/bottom.jsp"></s:include>


</body>
</html>
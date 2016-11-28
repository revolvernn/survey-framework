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
		<s:form action="BagAction_save" namespace="/Guest">
			<s:hidden name="surveyId" value="%{#parameters.surveyId}"/>
			<table class="formTable">
				<tr>
					<td colspan="2" align="center">创建包裹</td>
				</tr>
				<tr>
					<td>包裹名称</td>
					<td><s:textfield name="bagName" cssClass="longInput"/>
					</td>
				</tr>
				<tr>
					<td colspan="2" align="center"><s:submit value="创建"/>
					</td>
				</tr>
			</table>
		</s:form>
	</div>

	<s:include value="/resources_jsp/bottom.jsp"></s:include>


</body>
</html>
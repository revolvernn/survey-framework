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
		<s:form action="SurveyAction_update" namespace="/Guest" enctype="multipart/form-data" method="post">
			<s:hidden name="surveyId"/>
			<table class="formTable">
				<tr>
					<td colspan="2" align="center">更新调查</td>
				</tr>
				<s:if test="hasFieldErrors()">
					<tr>
						<td colspan="2" align="center"><s:fielderror name="logo"></s:fielderror></td>
					</tr>
				</s:if>
				<tr>
					<td>标题</td>
					<td><s:textfield name="title" cssClass="longInput"/>
					</td>
				</tr>
				<tr>
					<td>原LOGO</td>
					<td>
						<s:if test="isLogoExists(logoPath)">
							<img src="<s:url value="%{logoPath}"/>"/>
						</s:if>
						<s:else>
							<img src="<s:url value="resources_static/logo.gif"/>"/>
						</s:else>
					</td>
				</tr>
				<tr>
					<td>LOGO</td>
					<td><s:file name="logo" ></s:file>
					</td>
				</tr>
				
				<tr>
					<td colspan="2" align="center"><s:submit value="更新"/>
					</td>
				</tr>
			</table>
		</s:form>
	</div>

	<s:include value="/resources_jsp/bottom.jsp"></s:include>


</body>
</html>
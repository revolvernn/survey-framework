<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<s:include value="/resources_jsp/head.jsp"/>
</head>
<body>
	<s:include value="/resources_jsp/admin_top.jsp"/>
	<div>
		<s:form action="RoleAction_create" namespace="/Admin">
			<table class="formTable">
				<tr>
					<td colspan="2" align="center">创建角色</td>
				</tr>
				<s:if test="hasActionMessages()">
					<tr>
						<td colspan="2" align="center">
							<s:actionmessage/>
						</td>
					</tr>
				</s:if>
				<tr>
					<td>角色名称</td>
					<td>
						<s:textfield name="roleName" cssClass="longInput"/>
					</td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<s:submit value="创建"/>
					</td>
				</tr>
			</table>
		</s:form>
	</div>
	<s:debug />
	<s:include value="/resources_jsp/admin_bottom.jsp"/>

</body>
</html>
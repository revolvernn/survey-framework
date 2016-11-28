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
	<s:include value="/resources_jsp/admin_top.jsp"/>
	<div class="block-div navigatorDiv">
		<div class="locationDiv">所有可分配的角色列表</div>
	</div>
	
	<div class="block-div">
		<s:form namespace="/Admin" action="AdminAction_batchSaveRole">
		<s:hidden name="adminId"/>
		<table class="dashedTable">
			<s:if test="#request.roleList == null || #request.roleList.empty">
			<tr>
				<td>没有可分配的角色！</td>
			</tr>
			</s:if>
			<s:else>
			<tr>
				<td>角色名称</td>
			</tr>
			<s:iterator value="#request.roleList">
			<tr>
				<td>
					<input id="CheckBox<s:property  
						   value="roleId"/>" 
						   type="checkbox" 
						   name="roleIdList" 
						   value="<s:property value="roleId"/>"
						    <s:if test="#request.currentRoleIdList.contains(roleId)">checked='checked'</s:if>/>
					<label for="CheckBox<s:property  value="roleId"/>"><s:property value="roleName"/></label>
				</td>
			</tr>
			</s:iterator>
			<tr>
				<td align="center"><s:submit value="保存"/></td>
			</tr>
			</s:else>
		</table>
		</s:form>
	</div>
	<s:include value="/resources_jsp/admin_bottom.jsp"/>

</body>
</html>
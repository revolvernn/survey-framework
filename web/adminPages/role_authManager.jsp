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
		<div class="locationDiv">所有可分配的权限列表</div>
	</div>
	
	<div class="block-div">
		<s:form namespace="/Admin" action="RoleAction_batchSaveAuth">
		<s:hidden name="roleId"/>
		<table class="dashedTable">
			<s:if test="#request.authList == null || #request.authList.empty">
			<tr>
				<td>您还没有创建权限！</td>
			</tr>
			</s:if>
			<s:else>
			<tr>
				<td>权限名称</td>
			</tr>
			<s:iterator value="#request.authList">
			<tr>
				<td>
					<input id="CheckBox<s:property  
						   value="authorityId"/>" 
						   type="checkbox" 
						   name="authIdList" 
						   value="<s:property value="authorityId"/>"
						    <s:if test="#request.currentAuthIdList.contains(authorityId)">checked='checked'</s:if>/>
					<label for="CheckBox<s:property  value="authorityId"/>"><s:property value="authorityName"/></label>
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
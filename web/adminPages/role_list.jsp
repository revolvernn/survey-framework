<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<s:include value="/resources_jsp/head.jsp"></s:include>
<script type="text/javascript">
	$(function() {
		$(".roleNameInput").change(function() {
			var roleName = $.trim(this.value);
			//alert(resourceName);
			if(roleName == ""){
				this.value = this.defaultValue;
				return;
			}
			var roleId = this.title;
			
			var url = "${pageContext.request.contextPath}/Admin/RoleAction_update";
			
			var paramData = {"roleId":roleId,"roleName":roleName,"time":new Date()};
			
			var type = "json";
			
			var callBack = function(respData) {
				alert(respData.message);
			};
			
			$.post(url,paramData,callBack,type);
			
		});
	});
</script>
</head>
<body>
	<s:include value="/resources_jsp/admin_top.jsp"/>
	<div class="block-div navigatorDiv">
		<div class="locationDiv">角色列表</div>
	</div>
	
	<div class="block-div">
		<s:form namespace="/Admin" action="RoleAction_batchRemove">
		<table class="dashedTable">
			<s:if test="#request.roleList == null || #request.roleList.empty">
			<tr>
				<td>您还没有创建角色！</td>
			</tr>
			</s:if>
			<s:else>
			<tr>
				<td>角色ID</td>
				<td>角色名称</td>
				<td>分配权限</td>
				<td>删除</td>
			</tr>
			<s:iterator value="#request.roleList">
			<tr>
				<td>
					<s:property value="roleId"/>
				</td>
				<td>
					<input  title="<s:property value="roleId"/>" name="roleName" class="roleNameInput" value="<s:property value="roleName"/>" />
				</td>
				<td>
					<s:a namespace="/Admin" action="RoleAction_authManager?roleId=%{roleId}">分配权限</s:a>
				</td>
				<td>
					<input id="CheckBox<s:property  value="roleId"/>" type="checkbox" name="roleIdList" value="<s:property  value="roleId"/>"/>
					<label for="CheckBox<s:property  value="roleId"/>">选择</label>
				</td>
			</tr>
			</s:iterator>
			<tr>
				<td colspan="3"></td>
				<td><s:submit value="批量删除"/></td>
			</tr>
			</s:else>
		</table>
		</s:form>
	</div>
	<s:include value="/resources_jsp/admin_bottom.jsp"/>

</body>
</html>
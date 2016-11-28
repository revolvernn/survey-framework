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
		/* $(".admin_btn").click(function() {
			
			var $td = $(this).parents("tr").children("td:eq(3)");
			
			$td.children().removeAttr("disabled");
			//$(".adminNameInput").removeAttr("disabled");
		});
		 */
		$(".adminNameInput").change(function() {
			var adminPwd = $.trim(this.value);
			//alert(resourceName);
			if(adminPwd == ""){
				this.value = this.defaultValue;
				return;
			}
			var adminId = this.title;
			
			var url = "${pageContext.request.contextPath}/Admin/AdminAction_update";
			
			//alert(resourceName +" "+resourceId);
			
			var paramData = {"adminId":adminId,"adminPwd":adminPwd,"time":new Date()};
			
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
		<div class="locationDiv">管理员列表</div>
	</div>
	
	<s:push value="#request.page">
	<div class="block-div">
		<s:form namespace="/Admin" action="AdminAction_batchRemove">
		<table class="dashedTable">
			<s:if test="list == null || list.empty">
			<tr>
				<td>您还没有创建管理员！</td>
			</tr>
			</s:if>
			<s:else>
			<tr>
				<td>管理员ID</td>
				<td>管理员名称</td>
				<td>管理员密码</td>
				<td>分配角色</td>
				<td>删除</td>
			</tr>
			<s:iterator value="list">
			<tr>
				<td>
					<s:property value="adminId"/>
				</td>
				<td>
					 <s:property value="adminName"/>
				</td>
				<td>
					<input  title="<s:property value="adminId"/>" name="adminPwd" class="adminNameInput" disabled="disabled" value="<s:property value="adminPwd"/>" />
<!-- 				<input class="admin_btn" type="button" value="修改"/> -->
				</td>
				<td>
					<s:a namespace="/Admin" action="AdminAction_roleManager?adminId=%{adminId}">分配角色</s:a>
				</td>
				<td>
					<input id="CheckBox<s:property  value="adminId"/>" type="checkbox" name="adminIdList" value="<s:property  value="adminId"/>"/>
					<label for="CheckBox<s:property  value="adminId"/>">选择</label>
				</td>
			</tr>
			</s:iterator>
			<tr>
				<td colspan="3"/>
				<td><s:submit value="批量删除"/></td>
			</tr>
			<tr>
				<td colspan="5" align="center">
					<s:set var="pageActionName" value="'AdminAction_showAdmins'"/>
					<s:set var="pageNamespace" value="'/Admin'"/>
					<s:include value="/resources_jsp/pageNavigator.jsp"/>
				</td>
			</tr>
			</s:else>
		</table>
		</s:form>
	</div>
	</s:push>
	<s:include value="/resources_jsp/admin_bottom.jsp"/>

</body>
</html>
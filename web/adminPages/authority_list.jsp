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
		$(".authNameInput").change(function() {
			var authorityName = $.trim(this.value);
			//alert(resourceName);
			if(authorityName == ""){
				this.value = this.defaultValue;
				return;
			}
			var authorityId = this.title;
			
			var url = "${pageContext.request.contextPath}/Admin/AuthorityAction_update";
			
			//alert(resourceName +" "+resourceId);
			
			var paramData = {"authorityId":authorityId,"authorityName":authorityName,"time":new Date()};
			
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
		<div class="locationDiv">权限列表</div>
	</div>
	
	<s:push value="#request.page">
	<div class="block-div">
		<s:form namespace="/Admin" action="AuthorityAction_batchRemove">
		<table class="dashedTable">
			<s:if test="list == null || list.empty">
			<tr>
				<td>您还没有创建权限！</td>
			</tr>
			</s:if>
			<s:else>
			<tr>
				<td>权限ID</td>
				<td>权限名称</td>
				<td>分配资源</td>
				<td>删除</td>
			</tr>
			<s:iterator value="list">
			<tr>
				<td>
					<s:property value="authorityId"/>
				</td>
				<td>
					<input  title="<s:property value="authorityId"/>" name="AuthorityName" class="authNameInput" value="<s:property value="authorityName"/>" />
				</td>
				<td><s:a namespace="/Admin" action="AuthorityAction_resManager?authorityId=%{authorityId}">分配资源</s:a></td>
				<td>
					<input id="CheckBox<s:property  value="authorityId"/>" type="checkbox" name="authIdList" value="<s:property  value="authorityId"/>"/>
					<label for="CheckBox<s:property  value="authorityId"/>">选择</label>
				</td>
			</tr>
			</s:iterator>
			<tr>
				<td colspan="3"></td>
				<td><s:submit value="ok"/></td>
			</tr>
			<tr>
				<td colspan="5" align="center">
					<s:set var="pageActionName" value="'AuthorityAction_showAuthorities'"/>
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
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
		$(".resNameInput").change(function() {
			var resourceName = $.trim(this.value);
			//alert(resourceName);
			if(resourceName == ""){
				this.value = this.defaultValue;
				return;
			}
			var resourceId = this.title;
			
			var url = "${pageContext.request.contextPath}/Admin/ResourceAction_update";
			
			//alert(resourceName +" "+resourceId);
			
			var paramData = {"resourceId":resourceId,"resourceName":resourceName,"time":new Date()};
			
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
	<div class="block-div navigatorDiv" align="center">
		<div  class="locationDiv">资源数据列表</div>
	</div>
	
	<s:push value="#request.page">
	<div class="block-div">
		<s:form namespace="/Admin" action="ResourceAction_batchRemove">
		<table class="dashedTable">
			<s:if test="list == null || list.empty">
			<tr>
				<td>您还没有任何资源！</td>
			</tr>
			</s:if>
			<s:else>
			<tr>
				<td>资源ID</td>
				<td>资源地址</td>
				<td>资源名称</td>
				<td>删除</td>
			</tr>
			<s:iterator value="list">
			<tr>
				<td>
					<s:property value="resourceId"/>
				</td>
				<td>
					 <s:property value="actionName"/>
				</td>
				<td>
					<input  title="<s:property value="resourceId"/>" name="resourceName" class="resNameInput" value="<s:property value="resourceName"/>" />
				</td>
				<td>
						<input id="CheckBox<s:property  value="resourceId"/>" type="checkbox" name="resIdList" value="<s:property  value="resourceId"/>"/>
						<label for="CheckBox<s:property  value="resourceId"/>">选择</label>
				</td>
			</tr>
			</s:iterator>
			<tr>
				<td colspan="3"></td>
				<td ><s:submit value="批量删除"/></td>
			</tr>
			<tr>
				<td colspan="5" align="center">
					<s:set var="pageActionName" value="'ResourceAction_showAllResources'"/>
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
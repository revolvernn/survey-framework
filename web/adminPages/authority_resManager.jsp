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
		<div class="locationDiv">所有可分配资源数据列表</div>
	</div>
	
	<s:push value="#request.page">
	<div class="block-div">
		<s:form namespace="/Admin" action="AuthorityAction_batchSaveRes">
		<s:hidden name="authorityId"/>
		<table class="dashedTable">
			<s:if test="list == null || list.empty">
			<tr>
				<td>您还没有可分配任何资源！</td>
			</tr>
			</s:if>
			<s:else>
			<tr> 
				<td>资源名称</td>
			</tr>
			<s:set var="cells" value="2"/>
			<s:iterator value="list" status="myStatus">
					<tr>
				<td>
					<input id="CheckBox<s:property  value="resourceId"/>" 
						   type="checkbox" name="resIdList" 
						   value="<s:property  value="resourceId"/>"
						   <s:if test="#request.currentResIdList.contains(resourceId)">checked='checked'</s:if>/>
					<label for="CheckBox<s:property  value="resourceId"/>"><s:property value="resourceName"/></label>
				</td>
			</s:iterator>
			<tr>
				<td colspan="2" align="center"><s:submit value="保存"/></td>
			</tr>
			<tr>
				<td colspan="5" align="center">
					<s:set var="pageActionName" value="'AuthorityAction_resManager'" />
					<s:set var="pageNamespace" value="'/Admin'"/>
					<s:set var="extraParams" value="'&authorityId='"/>
					<s:set var="extraParam" value="authorityId"/>
					<s:include value="/resources_jsp/pageNavigator.jsp"/>
				</td>
			</tr>
			</s:else>
		</table>
		</s:form>
	</div>
	</s:push>
	<s:debug></s:debug>
	<s:include value="/resources_jsp/admin_bottom.jsp"/>

</body>
</html>
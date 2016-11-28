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
		<div class="locationDiv">日志列表</div>
	</div>
	
	<s:push value="#request.page">
	<div class="block-div">
		<table class="dashedTable">
			<s:if test="list == null || list.empty">
			<tr>
				<td>您还没有任何日志！</td>
			</tr>
			</s:if>
			<s:else>
			<tr>
				<td>日志Id</td>
				<td>操作人</td>
				<td>操作时间</td>
				<td>方法名</td>
				<!-- <td>方法类型</td> -->
				<!-- <td>方法参数</td> -->
				<!-- <td>结果值</td> -->
				<td>结果信息</td>
			</tr>
			<s:iterator value="list">
			<tr>
				<td><s:property value="logId"/></td>
				<td><s:property value="operator"/></td>
				<td><s:property value="operateTime"/></td>
				<td><s:property value="methodName"/></td>
				<%-- <td><s:property value="methodType"/></td> --%>
				<%-- <td><s:property value="methodArgs"/></td> --%>
				<%-- <td><s:property value="methodReturnValue"/></td> --%>
				<td><s:property value="methodResultMsg"/></td>
				
			</tr>
			</s:iterator>
			<tr>
				<td colspan="5" align="center">
					<s:set var="pageActionName" value="'LogAction_showLogs'"/>
					<s:set var="pageNamespace" value="'/Admin'"/>
					<s:include value="/resources_jsp/pageNavigator.jsp"/>
				</td>
			</tr>
			</s:else>
		</table>
	</div>
	</s:push>
	<s:include value="/resources_jsp/admin_bottom.jsp"/>

</body>
</html>
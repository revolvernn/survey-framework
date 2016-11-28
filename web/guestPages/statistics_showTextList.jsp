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
	<div class="block-div navigatorDiv">
		<div class="locationDiv">显示其它答案数据</div>
	</div>
	
	<div class="block-div">
		<table class="dashedTable">
			<s:if test="#request.textList == null || #request.textList.empty">
			<tr>
				<td>目前没有数据！</td>
			</tr>
			</s:if>
			<s:else>
			<s:iterator value="#request.textList">
			<tr>
				<td><s:property/></td>
			</tr>
			</s:iterator>
			</s:else>
		</table>
	</div>
	<s:include value="/resources_jsp/bottom.jsp"></s:include>

</body>
</html>
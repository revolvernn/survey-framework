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
		$("#backId").click(function() {
			window.history.back();
		});
	});
</script>
</head>
<body>
	<s:if test="#session.loginUser != null">
		<s:include value="/resources_jsp/top.jsp"></s:include>
	</s:if>
	<s:else>
		<s:include value="/resources_jsp/admin_top.jsp"/>
	</s:else>
	
	<div class="block-div navigatorDiv" align="center">
		<s:if test="hasActionErrors()">
			<s:actionerror/>
		</s:if>
		<s:property value="getText(exception.class.name)" /><br>
		<input type="button" id="backId" value="返回">
	</div>
	
	<s:debug />

	<s:if test="#session.loginUser != null">
		<s:include value="/resources_jsp/bottom.jsp"></s:include>
	</s:if>
	<s:else>
		<s:include value="/resources_jsp/admin_bottom.jsp"/>
	</s:else>

</body>
</html>
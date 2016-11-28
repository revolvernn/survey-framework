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
	<s:include value="/resources_jsp/top.jsp"></s:include>
	<div class="block-div navigatorDiv">
		<div align="center"><s:property value="actionMessages"/> |
			<s:a namespace="/Guest" action="SurveyAction_design?surveyId=%{surveyId}">返回我的调查列表</s:a>
		</div>
	</div>
	<s:include value="/resources_jsp/bottom.jsp"></s:include>

</body>
</html>
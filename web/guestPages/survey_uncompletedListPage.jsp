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
		$(".removeSurvey").click(function() {
			var title = $(this).parents("tr").children("td:eq(1)").text();
			
			title = $.trim(title);
			
			var flag = confirm("你真的要删除【 "+title+" 】这个调查吗？");
			
			if(!flag) {
				//取消默认行为
				return false;
			}
		});
	});
</script>
</head>
<body>

	<s:include value="/resources_jsp/top.jsp"></s:include>
	<div class="block-div navigatorDiv">
		<div class="locationDiv">未完成的调查</div>
	</div>
	
	<s:push value="#request.page">
	<div class="block-div">
		<table class="dashedTable">
			<s:if test="list == null || list.empty">
			<tr>
				<td>您还没有创建任何调查！</td>
			</tr>
			</s:if>
			<s:else>
			<tr>
				<td>Logo</td>
				<td>调查标题</td>
				<td>调查状态</td>
				<td>删除</td>
				<td>编辑</td>
			</tr>
			<s:iterator value="list">
			<tr>
				<td>
					<s:if test="isLogoExists(logoPath)">
						<img src="<s:url value="%{logoPath}"/>"/>
					</s:if>
					<s:else>
						<img src="<s:url value="resources_static/logo.gif"/>"/>
					</s:else>
				</td>
				<td>
					<s:property value="title"/>
				</td>
				<td>
					<s:if test="completed">已于<s:property value="formatedTime"/>完成</s:if>
					<s:else>
						<s:a namespace="/Guest" action="SurveyAction_design?surveyId=%{surveyId}">继续完善</s:a>
					</s:else>
				</td>
				<td>
					<s:a cssClass="removeSurvey" action="SurveyAction_remove?surveyId=%{surveyId}" namespace="/Guest">删除</s:a>
				</td>
				<td>
					<s:a action="SurveyAction_editSurvey?surveyId=%{surveyId}" namespace="/Guest">编辑调查基本信息</s:a>
				</td>
			</tr>
			</s:iterator>
			<tr>
				<td colspan="5" align="center">
					<s:set var="pageActionName" value="'SurveyAction_myUncompleted'"/>
					<s:set var="pageNamespace" value="'/Guest'"/>
					<s:include value="/resources_jsp/pageNavigator.jsp"/>
				</td>
			</tr>
			</s:else>
			
		</table>
	</div>
	</s:push>

	<s:include value="/resources_jsp/bottom.jsp"></s:include>


</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<s:include value="/resources_jsp/head.jsp"></s:include>
</head>
<script type="text/javascript">
	$(function() {
		$(".removeBag").click(function() {
			var bagName = $(this).parents("tr").children("td:eq(0)").text();

			bagName = $.trim(bagName);

			var flag = confirm("你真的要删除【 " + bagName + " 】这个包裹吗？");

			if (!flag) {
				//取消默认行为
				return false;
			}
		});
	});
</script>
<body>

	<s:include value="/resources_jsp/top.jsp"></s:include>

	<div class="block-div navigatorDiv">
		<div class="locationDiv">设计调查</div>
		<div class="backToIndexDiv">
			<s:a namespace="/Guest" action="SurveyAction_myUncompleted">返回我的调查列表</s:a>
		</div>
	</div>

	<div class="block-div navigatorDiv">
		<div class="locationDiv">

			<s:if test="isLogoExists(logoPath)">
				<img src="<s:url value="%{logoPath}"/>" />
			</s:if>
			<s:else>
				<img src="<s:url value="resources_static/logo.gif"/>" />
			</s:else>
			调查标题: 【 <s:property value="title" /> 】
		</div>
		<div class="backToIndexDiv">
			<s:a namespace="/Guest"
				action="ToPageAction_survey_bag_add?surveyId=%{surveyId}">添加包裹</s:a>
			<s:a namespace="/Guest"
				action="SurveyAction_adjustBagOrder?surveyId=%{surveyId}">调整包裹顺序</s:a>
		</div>
	</div>
	<div class="block-div">
		<table class="dashedTable">
			<s:if test="bagSet == null || bagSet.size() == 0">
				<tr>
					<td>您目前没有创建包裹!!!</td>
				</tr>
			</s:if>
			<s:else>
				<s:iterator value="bagSet">
					<tr>
						<td class="leftTd">包裹名: <s:property value="bagName" /></td>
						<td class="rightTd"><s:a namespace="/Guest"
								action="QuestionAction_toTypeChosen?bagId=%{bagId}&surveyId=%{surveyId}">添加问题</s:a>
							<s:a namespace="/Guest" action="BagAction_editBag?bagId=%{bagId}">编辑包裹</s:a>
							<s:a cssClass="removeBag" namespace="/Guest"
								action="BagAction_removeBag?bagId=%{bagId}&surveyId=%{surveyId}">删除包裹</s:a>
							<s:a namespace="/Guest"
								action="SurveyAction_toMoveOrCopyPage?bagId=%{bagId}">
								<span style="color: red">移动</span>/<span style="color: red">复制</span>
							</s:a></td>
					</tr>
					<s:include value="/guestPages/question_modelDisplay.jsp"></s:include>
				</s:iterator>
			</s:else>
			<tr>
				<td colspan="2" align="center"><s:a namespace="/Guest"
						action="SurveyAction_completeSurvey?surveyId=%{surveyId}">完成调查</s:a></td>
			</tr>
		</table>
	</div>
	<s:include value="/resources_jsp/bottom.jsp"></s:include>
</body>
</html>
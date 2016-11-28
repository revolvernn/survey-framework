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
		<div class="locationDiv">调查统计结果</div>
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
					<td></td>
				</tr>
			<s:if test="questionSet == null || questionSet.size()==0">
				<tr>
					<td>您的包裹还没有添加问题!!!</td>
				</tr>
			</s:if>
			<s:else>
			<tr>
				<td>&nbsp;</td>
				<td>
				<table class="dashedTable">
					<s:iterator value="questionSet">
						<tr>
							<td>题干: <s:property value="questionName" /></td>
							<td>
							<s:if test="questionType == 0 || questionType == 1 || questionType == 2">
								<s:a namespace="/Guest" action="StatisticsAction_showNormalChart?questionId=%{questionId}&surveyId=%{surveyId}">选择题显示统计结果</s:a>
								<s:if test="hasOther && otherType == 1">
									<s:a namespace="/Guest" action="StatisticsAction_showOtherTextList?questionId=%{questionId}&surveyId=%{surveyId}"> | 选择题其它文本统计结果</s:a>
								</s:if>
							</s:if>
							<s:if test="questionType == 3">
								<s:a namespace="/Guest" action="StatisticsAction_showTextList?questionId=%{questionId}&surveyId=%{surveyId}">简答题统计结果</s:a>
							</s:if>
							<s:if test="questionType == 4 || questionType == 5 || questionType == 6">
								<s:a namespace="/Guest" action="StatisticsAction_showNormalMatrix?questionId=%{questionId}&surveyId=%{surveyId}">矩阵表格统计结果</s:a>
							</s:if>
							<s:if test="questionType == 6">
								<s:a namespace="/Guest" action="StatisticsAction_showOptionMatrix?questionId=%{questionId}&surveyId=%{surveyId}">下拉列表矩阵表格统计结果</s:a>
							</s:if>
							</td>
					</tr>
				</s:iterator>
				</table>
				</td>
			</s:else>
			</s:iterator>
			</s:else>
		</table>
	</div>

	<s:include value="/resources_jsp/bottom.jsp"></s:include>
</body>
</html>
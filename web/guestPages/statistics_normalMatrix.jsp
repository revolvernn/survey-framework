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
	<s:push value="#request.question">
		<div class="block-div navigatorDiv">
			<div class="locationDiv">
				<s:property value="questionName" />
			</div>
			<div class="backToIndexDiv">
				<s:a namespace="/Guest" action="SurveyAction_myUncompleted">返回我的调查列表</s:a>
			</div>
		</div>

		<div class="block-div">
			<s:if test="questionType == 4 || questionType == 5">
				<table class="dashedTable">
					<tr>
						<td>行标题</td>
						<td>显示图表</td>
					</tr>
						<s:iterator value="matrixRowTitlesArray" status="myStatus">
							<tr>
								<td><s:property /></td>
								<td>
									<img alt="显示图表" src="<s:url namespace="/Guest" action="StatisticsAction_showNormalMatrixChart?questionId=%{questionId}&rowNo=%{#myStatus.index}"/>">
								</td>
							</tr>
						</s:iterator>
				</table>
			</s:if>
			<s:if test="questionType == 6">
				<table class="dashedTable">
					<!-- 列标签组 -->
					<tr>
						<td>&nbsp;</td>
						<s:iterator value="matrixColTitlesArray">
							<td align="center"><s:property /></td>
						</s:iterator>
					</tr>
					<!-- 行标签组 -->
					<s:iterator value="matrixRowTitlesArray" status="rowStatus">
						<tr>
							<td><s:property /></td>
							<s:iterator begin="1" end="matrixColTitlesArray.length" status="colStatus">
								<td>
									<img alt="显示图表" src="<s:url namespace="/Guest" action="StatisticsAction_showOptionMatrixChart?questionId=%{questionId}&rowNo=%{#rowStatus.index}&colNo=%{#colStatus.index}"/>">
								</td>
							</s:iterator>
						</tr>
					</s:iterator>
				</table>
			</s:if>
		</div>
	</s:push>
	<s:include value="/resources_jsp/bottom.jsp"></s:include>


</body>
</html>
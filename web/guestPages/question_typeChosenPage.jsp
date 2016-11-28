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
	<div>
		<s:form namespace="/Guest" action="QuestionAction_toQuestionDesign">
			<s:hidden name="bagId"/>
			<s:hidden name="surveyId"/>
			<s:hidden name="questionId"/>
			<table class="formTable">
				<tr>
					<td colspan="2" align="center">选择题型</td>
				</tr>
				<tr>
					<td>题型</td>
					<td style="text-align: left;">
						<input id="typeId00" type="radio" name="questionType" value="0" <s:if test="questionType==0">checked="checked"</s:if> />
							<label for="typeId00">单选题</label>
							<br>
						<input id="typeId01" type="radio" name="questionType" value="1" <s:if test="questionType==1">checked="checked"</s:if>>
							<label for="typeId01">多选题</label>
							<br>
						<input id="typeId02" type="radio" name="questionType" value="2" <s:if test="questionType==2">checked="checked"</s:if>>
							<label for="typeId02">下拉列表选择题 </label>
							<br>
						<input id="typeId03" type="radio" name="questionType" value="3" <s:if test="questionType==3">checked="checked"</s:if>>
							<label for="typeId03">简答题 </label>
							<br>
						<input id="typeId04" type="radio" name="questionType" value="4" <s:if test="questionType==4">checked="checked"</s:if>>
							<label for="typeId04"> 矩阵单选题</label>
							<br>
						<input id="typeId05" type="radio" name="questionType" value="5" <s:if test="questionType==5">checked="checked"</s:if>>
							<label for="typeId05">矩阵多选题</label>
							<br>
						<input id="typeId06" type="radio" name="questionType" value="6" <s:if test="questionType==6">checked="checked"</s:if>>
							<label for="typeId06">矩阵下拉列表选择题</label>
							<br>
					</td>
				</tr>
				<tr>
					<td colspan="2" align="center">
					<s:submit value="OK"/>
				</tr>
			</table>
		</s:form>
	</div>

	<s:include value="/resources_jsp/bottom.jsp"></s:include>


</body>
</html>
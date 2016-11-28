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
		
		if($("#hasOtherId").is(":checkbox")){
			
			$("#other_Type").hide();
		}
		$("#hasOtherId").click(function() {
			$("#other_Type").toggle();
		});
	});

</script>
</head>
<body>

	<s:include value="/resources_jsp/top.jsp"></s:include>
	<div>
		<s:form namespace="/Guest" action="QuestionAction_saveOrUpdate">
			<s:hidden name="bag.bagId" value="%{bagId}"/>
			<s:hidden name="questionType"/>
			<s:hidden name="bagId"/>
			<s:hidden name="surveyId"/>
			<s:hidden name="questionId"/>
			<table class="formTable">
				<tr>
				</tr>
				<tr>
					<td colspan="2" align="center">编辑问题细节</td>
				</tr>
				<tr>
					<td>题干</td>
					<td>
					
					<s:textfield name="questionName" cssClass="longInput"/>
				</tr>
				<tr>
					<td>题型</td>
					<td>
						<s:property value="@com.revolver.survey.utils.GlobalValues@QUESTION_TYPES.get(questionType)"/>
					</td>
				</tr>
				<s:if test="questionType <= 2 ">
				<tr>
					<td>选项</td>
					<td>
						<s:textarea name="options" cssClass="multiLineTextInput" value="%{optionsForShow}"/>
					</td>
				</tr>
				</s:if>
				<s:if test="questionType <= 1">
					<tr>
						<td>是否在每个选项后加换行</td>
						<td>
							<s:checkbox name="br" id="brId"/><label for="brId">是/否</label>
						</td>
					</tr>
					<tr>
						<td>是否设置其他项</td>
						<td>
							<s:checkbox name="hasOther" id="hasOtherId"/><label for="hasOtherId">是/否</label>
						</td>
					</tr>
					<tr id="other_Type">
						<td>其他项</td>
						<td>
							<s:radio list="#{'0':'和主题型一致','1':'文本框' }" name="otherType"/>
						</td>
					</tr>
				</s:if>
				<s:if test="questionType >= 4">
					<tr>
						<td>行标题标签组</td>
						<td>
							<s:textarea name="matrixRowTitles" cssClass="multiLineTextInput" value="%{matrixRowTitlesForShow}"/>
						</td>
					</tr>
					<tr>
						<td>列标题标签组</td>
						<td>
							<s:textarea name="matrixColTitles" cssClass="multiLineTextInput" value="%{matrixColTitlesForShow}"/>
						</td>
					</tr>
					<s:if test="questionType == 6">
						<tr>
						<td>下拉列表选项集合</td>
						<td>
							<s:textarea name="matrixOptions" cssClass="multiLineTextInput" value="%{matrixOptionsForShow}"/>
						</td>
					</tr>
					</s:if>
				</s:if>
				<tr>
					<td colspan="2" align="center"><s:submit value="OK"/></td>
				</tr>
			</table>
		</s:form>
	</div>
	<s:include value="/resources_jsp/bottom.jsp"></s:include>

</body>
</html>
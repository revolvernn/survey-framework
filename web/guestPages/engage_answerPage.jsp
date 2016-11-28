<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="survey" uri="http://revolver.com/survey"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<s:include value="/resources_jsp/head.jsp"></s:include>
</head>
<body>
	<s:include value="/resources_jsp/top.jsp"></s:include>
	
	<s:push value="#session.currentSurvey">
	<div class="block-div navigatorDiv">
		<div class="locationDiv">
			<s:if test="isLogoExists(logoPath)"><img src="<s:url value="%{logoPath}"/>" /></s:if> 
			<s:else><img src="<s:url value="resources_static/logo.gif"/>" /></s:else>
			当前调查: [ <span style="color:red"><s:property value="title"/></span> ]
		</div>
		<div class="backToIndexDiv">
			<s:a namespace="/Guest" action="SurveyAction_myUncompleted">返回我的调查列表</s:a>
		</div>
	</div>
	<div class="block-div">
		<s:form namespace="/Guest" action="EngageAction_doEngage">
		<table class="dashedTable">
			<s:push value="currentBag">
			<s:hidden name="surveyId"/>
			<s:hidden name="bagId"/>
			<tr>
				<td><s:property value="bagName"/></td>
			</tr>
			<tr>
				<td>
					<s:iterator value="questionSet">
						<table class="dashedTable">
							<tr>
								<td>题干: <s:property value="questionName"/>
								</td>
							</tr>
							<tr>
								<td><survey:generateQuestion currentBagIdOGNL="bagId"/></td>
							</tr>
							
						</table>
					</s:iterator>
				</td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<s:if test="bagOrder > minOrder">
						<s:submit name="submit_prev" value="上一个包裹"/>
					</s:if>
					<s:if test="bagOrder < maxOrder">
						<s:submit name="submit_next" value="下一个包裹"/>
					</s:if>
					<s:if test="bagOrder == maxOrder">
						<s:submit name="submit_done" value="完成"/>
					</s:if>
					<s:submit name="submit_quit" value="放弃"/>
				</td>
			</tr>
			</s:push>
		</table>
		</s:form>
		</div>
	</s:push>
	<s:include value="/resources_jsp/bottom.jsp"></s:include>
</body>
</html>
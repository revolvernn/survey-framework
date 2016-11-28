<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<s:include value="/resources_jsp/head.jsp"></s:include>
</head>
<body>
	<s:include value="/resources_jsp/top.jsp"></s:include>

	<table class="invisibleTable">
		<tr>
			<td>
				<div class="top10Block">
					<span class="leftSpan">最多人参与的调查Top10</span> <span class="rightSpan">
					<s:a namespace="/Guest" action="EngageAction_findAllAvailableSurvey">查看全部可以参与的调查</s:a></span>
					<br />
					<s:if test="#request.hotTenSurveys == null || #request.hotTenSurveys.size() == 0">
						<div class="textAlignCenter">抱歉，现在还没有人参与我们的调查！</div>
					</s:if>
					<s:else>
						<ul>
							<s:iterator value="#request.hotTenSurveys">
								<li>
									<s:a namespace="/Guest" action="EngageAction_entry?surveyId=%{surveyId}">
										<s:property value="title"/>
									</s:a>
								</li>
							</s:iterator>
						</ul>
					</s:else>
				</div>
			</td>
			<td>
				<div class="top10Block">
					<span class="leftSpan">最新调查Top10</span> <span class="rightSpan">
					<s:a namespace="/Guest" action="EngageAction_findAllAvailableSurvey">查看全部可以参与的调查</s:a></span>
					<br />
					<s:if test="#request.newTenSurveys == null || #request.newTenSurveys.size() == 0">
						<div class="textAlignCenter">抱歉，现在还没有人创建调查！赶快创建第一个调查吧，就等你了！</div>
					</s:if>
					<s:else>
						<ul>
							<s:iterator value="#request.newTenSurveys">
								<li>
									<s:a namespace="/Guest" action="EngageAction_entry?surveyId=%{surveyId}">
										<s:property value="title"/>
									</s:a>
								</li>
							</s:iterator>
						</ul>
					</s:else>
				</div>
			</td>
		</tr>
	</table>
	
	<s:include value="/resources_jsp/bottom.jsp"></s:include>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<s:if test="questionSet == null || questionSet.size()==0">
<tr>
	<td>&nbsp;</td>
	<td>您的包裹还没有添加问题!!!</td>
</tr>
</s:if>
<s:else>
<tr>
<td>&nbsp;</td>
<td>
	<s:iterator value="questionSet">
		<table class="dashedTable">
			<tr>
				<td>题干: <s:property value="questionName"/>
					<s:a name="/Guest" action="QuestionAction_editQuestion?questionId=%{questionId}&bagId=%{bagId}&surveyId=%{surveyId}">编辑问题</s:a> |
					<s:a name="/Guest" action="QuestionAction_removeQuestion?questionId=%{questionId}&bagId=%{bagId}&surveyId=%{surveyId}">删除问题</s:a>
				</td>
			</tr>
			<tr>
				<td>
					<!-- 单选题 -->
					<s:if test="questionType == 0 ">
						<s:iterator value="optionsArray">
							<input type="radio" value="<s:property />"/>
							<s:property /><s:if test="br"><br></s:if>
						</s:iterator>
						<s:if test="hasOther">
							<s:if test="otherType==0"><input type="radio">其它</s:if>
							<s:if test="otherType==1"><input type="text">其它</s:if>
						</s:if>
					</s:if>
					<!-- 多选题 -->
					<s:if test="questionType==1">
						<s:iterator value="optionsArray">
							<input type="checkbox" value="<s:property />"/>
							<s:property /><s:if test="br"><br></s:if>
						</s:iterator>
						<s:if test="hasOther">
							<s:if test="otherType==0"><input type="checkbox">其它</s:if>
							<s:if test="otherType==1"><input type="text">其它</s:if>
						</s:if>
					</s:if>
					<!-- 下拉列表选择题 -->
					<s:if test="questionType==2">
						<select >
							<s:iterator value="optionsArray">
									<option>
										<s:property/>
									</option>
							</s:iterator>
						</select>
					</s:if>
					<!-- 简答题 -->
					<s:if test="questionType==3">
						<input type="text">
					</s:if>
					<s:if test="questionType >=4">
						<table class="dashedTable">
							<!-- 列标签组 -->
							<tr>
								<td>&nbsp;</td>
								<s:iterator value="matrixColTitlesArray">
								<td>
									<s:property/>
								</td>
								</s:iterator>
							</tr>
							<!-- 行标签组 -->
							<s:iterator value="matrixRowTitlesArray">
								<tr>
									<td><s:property/></td>
									<s:iterator begin="1" end="matrixColTitlesArray.length">
										<td>
										<!-- 矩形单选 -->
										<s:if test="questionType==4"><input type="radio"/></s:if>
										<!-- 矩形多选 -->
										<s:if test="questionType==5"><input type="checkbox"/></s:if>
										<!-- 下拉选择 -->
										<s:if test="questionType==6">
											<select>
												<s:iterator value="matrixOptionsArray">
													<option><s:property/></option>
												</s:iterator>
											</select>
											</s:if>
										</td>
									</s:iterator>
								</tr>
							</s:iterator>
						</table>
					</s:if>
				</td>
			</tr>
		</table>
	</s:iterator>
</td>
</tr>
</s:else>
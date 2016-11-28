<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript">
		$(function() {
			$("#goPage").change(function() {
				var pageNoStr = this.value;
				//alert("1231");
				if(isNaN(pageNoStr)){
					this.value = this.defaultValue;
					return;
				}
				
				var targetUrl = "<s:url namespace='%{#pageNamespace}' action='%{#pageActionName}'/>?pageNoStr="+pageNoStr+"<s:property value='%{#extraParams}'/><s:property value='%{#extraParam}'/>";
				
				window.location.href=targetUrl;
			});
		});
</script>

<s:if test="#request.page == null || #request.page.dataList == null || #request.page.dataList.empty">
	没有数据
</s:if>
<s:else>
	<s:if test="#request.page.hasPrev">
		<s:a action="%{#pageActionName}?pageNoStr=1%{#extraParams}%{#extraParam}" namespace="%{#pageNamespace}">首页</s:a>
		<s:a action="%{#pageActionName}?pageNoStr=%{#request.page.prev}%{#extraParams}%{#extraParam}" namespace="%{#pageNamespace}">上一页</s:a>
	</s:if>
	
	共<s:property value="#request.page.totalRecordNo"/>条记录
	
	前往第<input type="text" id="goPage" value="<s:property value="#request.page.pageNo"/>" class="shortInput"/>页
	
	共<s:property value="#request.page.totalPageNo"/>页
	
	<s:if test="#request.page.hasNext">
		<s:a action="%{#pageActionName}?pageNoStr=%{#request.page.next}%{#extraParams}%{#extraParam}" namespace="%{#pageNamespace}">下一页</s:a>
		<s:a action="%{#pageActionName}?pageNoStr=%{#request.page.totalPageNo}%{#extraParams}%{#extraParam}" namespace="%{#pageNamespace}">末页</s:a>
	</s:if>
</s:else>
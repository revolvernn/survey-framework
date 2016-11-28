<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib uri="http://revolver.com/survey" prefix="admin" %>
<div id="guestInfoBlock">
	<div id="guestInfoContent">
		<s:if test="#session.loginAdmin == null">
			管理员未登录 <s:a namespace="/Admin" action="AdminAction_toAdminMain">管理员登录</s:a>
		</s:if>
		<s:else>
			管理员账号：[<s:property value="#session.loginAdmin.adminName"/>]
			<s:a namespace="/Admin" action="AdminAction_logout">退出登录</s:a>
		</s:else>
	</div>
</div>
<div id="adminNavigation" class="block-div" style="text-align: center;">
	<admin:auth targetRes="ExcelAction_showAllSurvey">
		[<s:a namespace="/Admin" action="ExcelAction_showAllSurvey">Excel</s:a>]
	</admin:auth>
	<admin:auth targetRes="ResourceAction_showAllResources">
		[<s:a namespace="/Admin" action="ResourceAction_showAllResources">资源列表</s:a>]
	</admin:auth>
	<admin:auth targetRes="AuthorityAction_toCreatePage">
		[<s:a namespace="/Admin" action="AuthorityAction_toCreatePage">创建权限</s:a>]
	</admin:auth>
	<admin:auth targetRes="AuthorityAction_showAuthorities">
		[<s:a namespace="/Admin" action="AuthorityAction_showAuthorities">权限列表</s:a>]
	</admin:auth>
	<admin:auth targetRes="RoleAction_toCreatePage">
		[<s:a namespace="/Admin" action="RoleAction_toCreatePage">创建角色</s:a>]
	</admin:auth>
	<admin:auth targetRes="RoleAction_showRoles">
		[<s:a namespace="/Admin" action="RoleAction_showRoles">角色列表</s:a>]
	</admin:auth>
	<admin:auth targetRes="AdminAction_showAdmins">
		[<s:a namespace="/Admin" action="AdminAction_showAdmins">管理员列表</s:a>]
	</admin:auth>
	<admin:auth targetRes="AdminAction_generateAdmin">
		[<s:a namespace="/Admin" action="AdminAction_generateAdmin">批量生成管理员账号</s:a>]
	</admin:auth>
	<admin:auth targetRes="AdminAction_batchCalculateAuth">
		[<s:a namespace="/Admin" action="AdminAction_batchCalculateAuth">批量计算权限码</s:a>]
	</admin:auth>
	<admin:auth targetRes="LogAction_showLogs">
		[<s:a namespace="/Admin" action="LogAction_showLogs">查看日志</s:a>]
	</admin:auth>
</div>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/common/taglibs2.jsp" %>

<!doctype html>
<html>
<head>
${style_css }
${jquery_js }
${finedo_core_js }
${finedo_commonui_js }
</head>

<body>
<div>
	<div class="add-common-head">
    	<div class="add-common-name">网元信息详细信息<br/>
            <ul>
            	<li class="add-link-li">全部</li> 
            </ul>
        </div>
    </div>
    
	<div class="finedo-nav-title">基本信息</div> 
	<ul class="finedo-ul">
			
		<li>
			<fsdp:label label="网元名称">${znywnetelement.elename}</fsdp:label>
		</li>
		<li>
			<fsdp:label label="IP地址">${znywnetelement.eleip}</fsdp:label>
		</li>
		<li>
			<fsdp:label label="所属厂家">${znywnetelement.elefactory}</fsdp:label>
		</li>
		<li>
			<fsdp:label label="局域网">${znywnetelement.elenet}</fsdp:label>
		</li>
		<li>
			<fsdp:label label="网元类型">${znywnetelement.eletype}</fsdp:label>
		</li>
		<li>
			<fsdp:label label="创建者">${znywnetelement.creator}</fsdp:label>
		</li>
		<li>
			<fsdp:label label="创建时间">${znywnetelement.createtime}</fsdp:label>
		</li>
		<li>
			<fsdp:label label="所属地市">${znywnetelement.city}</fsdp:label>
		</li>
		<li>
			<fsdp:label label="登录类型">${znywnetelement.logintype}</fsdp:label>
		</li>
		<li>
			<fsdp:label label="端口">${znywnetelement.port}</fsdp:label>
		</li>
		<li>
			<fsdp:label label="用户名">${znywnetelement.username}</fsdp:label>
		</li>
		<li>
			<fsdp:label label="密码">${znywnetelement.password}</fsdp:label>
		</li>
		<li>
			<fsdp:label label="备注">${znywnetelement.remark}</fsdp:label>
		</li>
	</ul>
</div>
</body>
</html>

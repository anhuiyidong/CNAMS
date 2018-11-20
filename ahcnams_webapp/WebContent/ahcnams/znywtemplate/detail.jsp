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
    	<div class="add-common-name">模板详细信息<br/>
            <ul>
            	<li class="add-link-li">全部</li> 
            </ul>
        </div>
    </div>
    
	<div class="finedo-nav-title">基本信息</div> 
	<ul class="finedo-ul">
			
		<li>
			<fsdp:label label="模板名称">${znywtemplate.templatename}</fsdp:label>
		</li>
		<li>
			<fsdp:label label="创建者">${znywtemplate.creator}</fsdp:label>
		</li>
		<li>
			<fsdp:label label="创建时间">${znywtemplate.createtime}</fsdp:label>
		</li>
		<li>
			<fsdp:label label="模板状态">${znywtemplate.templatestatus}</fsdp:label>
		</li>
		<li>
			<fsdp:label label="备注">${znywtemplate.remark}</fsdp:label>
		</li>
	</ul>
</div>
</body>
</html>

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
    	<div class="add-common-name">任务详细信息<br/>
            <ul>
            	<li class="add-link-li">全部</li> 
            </ul>
        </div>
    </div>
    
	<div class="finedo-nav-title">基本信息</div> 
	<ul class="finedo-ul">
			
		<li>
			<fsdp:label label="模板id">${znywtask.templateid}</fsdp:label>
		</li>
		<li>
			<fsdp:label label="执行人">${znywtask.executor}</fsdp:label>
		</li>
		<li>
			<fsdp:label label="执行时间">${znywtask.executetime}</fsdp:label>
		</li>
		<li>
			<fsdp:label label="状态">${znywtask.status}</fsdp:label>
		</li>
		<li>
			<fsdp:label label="开始时间">${znywtask.starttime}</fsdp:label>
		</li>
		<li>
			<fsdp:label label="结束时间">${znywtask.expiretime}</fsdp:label>
		</li>
		<li>
			<fsdp:label label="部门">${znywtask.dept}</fsdp:label>
		</li>
		<li>
			<fsdp:label label="备注">${znywtask.remark}</fsdp:label>
		</li>
	</ul>
</div>
</body>
</html>

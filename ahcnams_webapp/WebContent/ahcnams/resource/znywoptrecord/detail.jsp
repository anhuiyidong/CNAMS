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
    	<div class="add-common-name">操作记录详细信息<br/>
            <ul>
            	<li class="add-link-li">全部</li> 
            </ul>
        </div>
    </div>
    
	<div class="finedo-nav-title">基本信息</div> 
	<ul class="finedo-ul">
			
		<li>
			<fsdp:label label="任务id">${znywoptrecord.taskid}</fsdp:label>
		</li>
		<li>
			<fsdp:label label="操作人">${znywoptrecord.optperson}</fsdp:label>
		</li>
		<li>
			<fsdp:label label="操作时间">${znywoptrecord.opttime}</fsdp:label>
		</li>
	</ul>
</div>
</body>
</html>

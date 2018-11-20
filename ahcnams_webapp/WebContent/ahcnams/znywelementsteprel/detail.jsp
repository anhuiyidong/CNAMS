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
    	<div class="add-common-name">网元和模板步骤对应关系详细信息<br/>
            <ul>
            	<li class="add-link-li">全部</li> 
            </ul>
        </div>
    </div>
    
	<div class="finedo-nav-title">基本信息</div> 
	<ul class="finedo-ul">
			
		<li>
			<fsdp:label label="网元id">${znywelementsteprel.eletid}</fsdp:label>
		</li>
		<li>
			<fsdp:label label="步骤id">${znywelementsteprel.stepid}</fsdp:label>
		</li>
	</ul>
</div>
</body>
</html>

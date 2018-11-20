<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/common/taglibs2.jsp" %>

<!doctype html>
<html>
<head>
${style_css }
${jquery_js }
${finedo_core_js }
${finedo_commonui_js }
${finedo_dialog_js }
<script>
$(document).ready(function() {
	
	$.getJSON('${ctx}/finedo/sysparam/querysysparam', {"paramid":"1012"}, function(jsondata){
		if(jsondata && jsondata.rows && jsondata.rows.length > 0){
			initLogLevel(jsondata.rows[0].paramvalue);
		}else{
			initLogLevel('DEBUG');
		}
	});
});
function dosubmit() {
	var paramvalue = finedo.getselect("paramvalue").getvalue();
	finedo.message.showWaiting();
	$.getJSON('${ctx}/finedo/sysparam/setloglevel', {"paramvalue":paramvalue}, function(jsondata){
		finedo.message.hidewaiting();
		if(jsondata.fail){
			finedo.message.error(jsondata.resultdesc);
			return;
		}
		finedo.message.tip('日志级别设置成功');
	});
}
function initLogLevel(defaultvalue){
	var options={
		type:"single",		/*single:单选, multi:多选*/
		data:[
			{"code":"OFF", "value":"关闭日志"},
			{"code":"TRACE", "value":"追踪"},
			{"code":"DEBUG", "value":"调试"},
			{"code":"INFO", "value":"信息"},
			{"code":"WARN", "value":"告警"},
			{"code":"ERROR", "value":"错误"},
			{"code":"FATAL", "value":"重大错误"}
		],
		value:defaultvalue
	};
	finedo.getselect("paramvalue", options);
}
</script>
</head>

<body>
<div>
	<div class="add-common-head">
    	<div class="add-common-name-add" id="addtitle">设置log级别</div>
        <input type="button" class="finedo-button-blue" style="float:right" value="设    置" onclick="dosubmit();">
    </div>
        
    <form method="post" action="${ctx }/finedo/sysparam/setloglevel" id="ajaxForm" name="ajaxForm">
    <div id="common_add_div" >
	   	<ul class="finedo-ul">
			<li>
				<span class="finedo-label-title" style="width:150px"><font color=red>*</font>请选择日志级别：</span>
				<input type="text" id="paramvalue" name="paramvalue"></input>
			</li>
		</ul>
		
		<ul>
    		<li class="add-center"><input type="button" class="finedo-button" value="设    置" onClick="dosubmit()" ></li>
   		</ul>
	</div>
    </form>
</div>
</body>
</html>

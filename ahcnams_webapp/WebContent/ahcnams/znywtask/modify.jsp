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
${finedo_date_js }
<script>
// 数据校验
function checkdata() {
   /**
 	* 	通用数据验证
 	* 	label  		名称
	*	datatype 	数据类型  string email phone url date datetime time number digits 
	*	minlength 	最小长度
	*	maxlength 	最大长度
	*	required 	是否必填 true/false
	*/
	var result=finedo.validate({
		"templateid":{label:"模板id", datatype:"string", required:true},
		"executor":{label:"执行人", datatype:"string", required:true},
		"executetime":{label:"执行时间", datatype:"string", required:true},
		"status":{label:"状态", datatype:"string", required:true},
		"starttime":{label:"开始时间", datatype:"string", required:true},
		"expiretime":{label:"结束时间", datatype:"string", required:true},
		"dept":{label:"部门", datatype:"string", required:true},
		"remark":{label:"备注", datatype:"string", required:true},
	});
	
   	//TODO: 自定义数据验证
	

	return result;
}

// 提交
function donext() {
	dosubmit();
}

function dosubmit() {
	if(!checkdata()) 
		return;

	var form = $("#ajaxForm");
	var options = {     
        url:	   form.attr("action"),
        success:   submitcallback,
        type:      'POST',
        dataType:  "json",
	    clearForm: true
    };
	finedo.message.showWaiting();
	form.ajaxSubmit(options);
}

function submitcallback(jsondata){
	finedo.message.hideWaiting();
	finedo.message.info(jsondata.resultdesc, "修改信息");
}
</script>
</head>

<body>
<div>
	<div class="add-common-head">
    	<div class="add-common-name-add">修改任务<br/>
            <ul>
            	<li id="common_add_card" class="add-link-li">全部</li>
            </ul>
        </div>
        <input type="button" class="finedo-button-blue" style="float:right;" value="提交" onclick="donext();">
    </div>
        
    <form method="post" action="${ctx }/finedo/znywtask/modify" id="ajaxForm" name="ajaxForm">
	<input value="${znywtask.taskid}" id="taskid" name="taskid" type="hidden"/>
    <div class="finedo-nav-title">基本信息</div>
		<ul class="finedo-ul">
			<li>
				<span class="finedo-label-title"><font color=red>*</font>模板id</span>
				<fsdp:text id="templateid" hint="模板id" value="${znywtask.templateid}" />
			</li>
			<li>
				<span class="finedo-label-title"><font color=red>*</font>执行人</span>
				<fsdp:text id="executor" hint="执行人" value="${znywtask.executor}" />
			</li>
			<li>
				<span class="finedo-label-title"><font color=red>*</font>执行时间</span>
				<fsdp:text id="executetime" hint="执行时间" value="${znywtask.executetime}" />
			</li>
			<li>
				<span class="finedo-label-title"><font color=red>*</font>状态</span>
				<fsdp:text id="status" hint="状态" value="${znywtask.status}" />
			</li>
			<li>
				<span class="finedo-label-title"><font color=red>*</font>开始时间</span>
				<fsdp:text id="starttime" hint="开始时间" value="${znywtask.starttime}" />
			</li>
			<li>
				<span class="finedo-label-title"><font color=red>*</font>结束时间</span>
				<fsdp:text id="expiretime" hint="结束时间" value="${znywtask.expiretime}" />
			</li>
			<li>
				<span class="finedo-label-title"><font color=red>*</font>部门</span>
				<fsdp:text id="dept" hint="部门" value="${znywtask.dept}" />
			</li>
			<li>
				<span class="finedo-label-title"><font color=red>*</font>备注</span>
				<fsdp:text id="remark" hint="备注" value="${znywtask.remark}" />
			</li>
		</ul>
	
		<ul>
	    	<li class="add-center"><input type="button" class="finedo-button" value="提    交" onClick="dosubmit()" ></li>
	    </ul>
	</div>
	</form>
</body>
</html>
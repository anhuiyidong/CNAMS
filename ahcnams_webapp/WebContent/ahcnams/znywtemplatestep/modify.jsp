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
		"stepname":{label:"步骤名称", datatype:"string", required:true},
		"stepscript":{label:"脚本", datatype:"string", required:true},
		"relatedele":{label:"关联网元id", datatype:"string", required:true},
		"stepremark":{label:"备注", datatype:"string", required:true},
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
    	<div class="add-common-name-add">修改模板步骤<br/>
            <ul>
            	<li id="common_add_card" class="add-link-li">全部</li>
            </ul>
        </div>
        <input type="button" class="finedo-button-blue" style="float:right;" value="提交" onclick="donext();">
    </div>
        
    <form method="post" action="${ctx }/finedo/znywtemplatestep/modify" id="ajaxForm" name="ajaxForm">
	<input value="${znywtemplatestep.stepid}" id="stepid" name="stepid" type="hidden"/>
    <div class="finedo-nav-title">基本信息</div>
		<ul class="finedo-ul">
			<li>
				<span class="finedo-label-title"><font color=red>*</font>步骤名称</span>
				<fsdp:text id="stepname" hint="步骤名称" value="${znywtemplatestep.stepname}" />
			</li>
			<li>
				<span class="finedo-label-title"><font color=red>*</font>脚本</span>
				<fsdp:text id="stepscript" hint="脚本" value="${znywtemplatestep.stepscript}" />
			</li>
			<li>
				<span class="finedo-label-title"><font color=red>*</font>关联网元id</span>
				<fsdp:text id="relatedele" hint="关联网元id" value="${znywtemplatestep.relatedele}" />
			</li>
			<li>
				<span class="finedo-label-title"><font color=red>*</font>备注</span>
				<fsdp:text id="stepremark" hint="备注" value="${znywtemplatestep.stepremark}" />
			</li>
		</ul>
	
		<ul>
	    	<li class="add-center"><input type="button" class="finedo-button" value="提    交" onClick="dosubmit()" ></li>
	    </ul>
	</div>
	</form>
</body>
</html>
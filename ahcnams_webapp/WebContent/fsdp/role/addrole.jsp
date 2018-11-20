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
${finedo_choose_js }
${finedo_tree_js }

<script>
// 卡片显示与隐藏 
$(document).ready(function() {
    $('#common_add_card').click(function(e) {
    	$('#common_add_div').css('display', 'block');
    	$('#common_add_card').attr('class', 'add-link-li');
    	
    	$('#permission_add_div').css('display', 'none');
		$('#permission_add_card').removeClass();
    });
    
    $('#permission_add_card').click(function(e) {
    	$('#common_add_div').css('display', 'none');
    	$('#common_add_card').removeClass();
    	
    	$('#permission_add_div').css('display', 'block');
		$('#permission_add_card').attr('class', 'add-link-li');
    });
	$('label[for="roletype"]').click(function(e) {
		var roletypeval = $(this).attr('value');
		if(roletypeval == '组织岗位角色'){
			$("#orgname").attr("disabled",false);
		}else{
			$("#orgid").val('');
			$("#orgname").val('');
			$("#orgname").attr("disabled",true);
		}
	});
	//初始化角色权限树
	$.getJSON('${ctx}/finedo/permission/getpermissiontree', function(jsondata){
		if(jsondata.fail){
			finedo.message.error(jsondata.resultdesc);
			return;
		}
		finedo.getTree('treediv',{
			simple:true,
			selecttype:'multi',
			nodes:jsondata.object
		});
	});
	
});
 
// 数据验证
function checkdata() {
   /**
 	* 	通用数据验证
 	* 	label  		名称
	*	datatype 	数据类型  string email phone url date datetime time number digits 
	*	minlength 	最小长度
	*	maxlength 	最大长度
	*	required 	是否必填 true/false
	*/
	if($("#roletype").val() == '组织岗位角色'){
		if($("#orgid").val() == '请选择组织机构!'){
			finedo.message.warning();
			return false;
		}
	}
	var result=finedo.validate({
		"rolename":{label:"角色名称", datatype:"string", maxlength:100, required:true},
		"usercount":{label:"限定人数", datatype:"number", required:true}
	}, true);
	if(!result){
		return result;
	}
	var selecteditems = finedo.getTree('treediv').getselecteditems();
	var s = '';
	for(var i=0; i<selecteditems.length; i++){
		if(!selecteditems[i].orgnode){
			continue;
		}
		if (s != '') s += ',';
		s += selecteditems[i].orgnode;
	}
	$("#permissionid").val(s);
	return result;
}

// 普通新建
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
	finedo.message.info(jsondata.resultdesc);
}
function checkInteger(input){
	var obj = $(input);
	obj.val(obj.val().replace(/[^\d.]/g,""));//清除“数字”和“.”以外的字符   
	obj.val(obj.val().replace(/^\./g,""));//验证第一个字符是数字而不是.   
	obj.val(obj.val().replace(/\.{2,}/g,"."));//只保留第一个. 清除多余的.  
	obj.val(obj.val().replace(".","$#$").replace(/\./g,"").replace("$#$","."));
}
function chooseOrg(){
	finedo.choose.singleOrg(function(data){
		$("#orgid").val(data.id);
		$("#orgname").val(data.name);
	});
}
</script>
</head>

<body>
<div>
	<div class="add-common-head">
    	<div class="add-common-name-add">新建角色信息<br/>
            <ul>
            	<li id="common_add_card" class="add-link-li">基本信息</li> 
            	<li>|</li>
                <li id="permission_add_card">权限信息</li>
            </ul>
        </div>
        <input type="button" class="finedo-button-blue" style="float:right" value="提交" onclick="dosubmit();">
    </div>
    
    <form method="post" action="${ctx }/finedo/role/addRole" id="ajaxForm" name="ajaxForm">
    <input type="hidden" id="permissionid" name="permissionid">
    <div id="common_add_div" >
    	<div class="finedo-nav-title">基本信息</div>
	   	<table class="finedo-table">
		    <tr>
		        <td class="finedo-label-title">角色类型：</td>
		        <td>
		        	<fsdp:radio datasource="岗位角色类型" id="roletype" selectedvalue="组织岗位角色"></fsdp:radio>
		        </td>
		        <td class="finedo-label-title">组织节点：</td>
		        <td>
		        	<input type="hidden" value="" name="orgid" id="orgid">
		    		<input class="finedo-text" type="text" id="orgname" name="orgname" onclick="chooseOrg()">
		        </td>
		    </tr>
		    <tr>
		        <td class="finedo-label-title"><font color=red>*</font>角色名称：</td>
		        <td>
		        	<input class="finedo-text" type="text" id="rolename" name="rolename" maxlength="100">
		        </td>
		        <td class="finedo-label-title"><font color=red>*</font>限定人数：</td>
		        <td>
		        	<input class="finedo-text" type="text" id="usercount" name="usercount" maxlength="6" onkeypress="checkInteger(this)" onblur="checkInteger(this)">
		        </td>
		    </tr>
		    <tr>
		        <td class="finedo-label-title"><font color=red>*</font>角色级别：</td>
		        <td>
		        	<fsdp:radio datasource="岗位角色级别" id="rolelvl" selectedvalue="十岗"></fsdp:radio>
		        </td>
		        <td class="finedo-label-title"><font color=red>*</font>组织节点：</td>
		        <td>
		        	<fsdp:radio datasource="岗位角色状态" id="state" selectedvalue="有效"></fsdp:radio>
		        </td>
		    </tr>
		    <tr>
		        <td class="finedo-label-title">职责描述：</td>
		        <td colspan="3">
		        	<input class="finedo-text" type="text" id="dutydesc" name="dutydesc" style="width:90%;">
		        </td>
		    </tr>
		    <tr>
		        <td class="finedo-label-title">备注：</td>
		        <td colspan="3">
		        	<input class="finedo-text" type="text" id="remark" name="remark" style="width:90%;">
		        </td>
		    </tr>
		</table>
	</div>    
    <div id="permission_add_div" style="display:none;width:95%;position: fixed; top:60px;left:10px; bottom:10px; overflow-y:auto; overflow-x:auto;">
   		<ul id="treediv" class="fdtree" style="margin-left:5px;" ></ul>
    </div>
    </form>
</div>
</body>
</html>

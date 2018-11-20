<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/common/taglibs2.jsp" %>
<!DOCTYPE html>
<html>
<head>
<title>修改组织机构</title>
${style_css }
${jquery_js }
${finedo_core_js }
${finedo_commonui_js }
${finedo_dialog_js }
${finedo_date_js }
${finedo_choose_js }

</head>
<body>
<div>
	<div class="add-common-head">
    	<div class="add-common-name-add">修改组织机构信息<br/>
            <ul>
            	<li id="common_add_card" class="add-link-li">全部</li>
            </ul>
        </div>
        <input type="button" class="finedo-button-blue" style="float:right" value="提交" onclick="dosubmit();">
    </div>
    
    <form method="post" action="${ctx }/finedo/organization/modifyOrg" id="ajaxForm" name="ajaxForm">
    <input type="hidden" id="orgid" name="orgid" value="${param.orgid }">
    <div id="common_add_div" >
    	<div class="finedo-nav-title">基本信息</div>
	   	<ul class="finedo-ul">
			<li>
				<input type="hidden" value="" name="parentorgid" id="parentorgid">
				<span class="finedo-label-title"><font color=red>*</font>上级组织</span>
				<input class="finedo-text" type="text" id="parentorgname" name="parentorgname" value="" onclick="chooseParentOrg()">
			</li>
			<li>
				<span class="finedo-label-title"><font color=red>*</font>机构类型</span>
				<fsdp:select datasource="组织机构类型" id="orgtype"></fsdp:select>
			</li>
			<li>
				<span class="finedo-label-title"><font color=red>*</font>机构名称</span>
				<input class="finedo-text" type="text" id="orgname" name="orgname" value="" maxlength="100">
			</li>
			<li>
				<span class="finedo-label-title">联系人</span>
				<input class="finedo-text" type="text" id="linkman" name="linkman" value="" maxlength="50">
			</li>
			<li>
				<span class="finedo-label-title">联系电话</span>
				<input class="finedo-text" type="text" id="phoneno" name="phoneno" value="" maxlength="20">
			</li>
			<li>
				<span class="finedo-label-title">联系地址</span>
				<input class="finedo-text" type="text" id="address" name="address" value="" maxlength="500">
			</li>
			<li>
				<span class="finedo-label-title">机构介绍</span>
				<input class="finedo-text" type="text" id="orgdesc" name="orgdesc" value="" maxlength="500">
			</li>
			<li>
				<span class="finedo-label-title">备注</span>
				<input class="finedo-text" type="text" id="remark" name="remark" value="" maxlength="500">
			</li>
		</ul>
	    <ul>
	    	<li class="add-center"><input type="button" class="finedo-button" value="提    交" onClick="dosubmit()" ></li>
	    </ul>
    </div>
    </form>
</div>
<script language="javascript">
var orgid = "${param.orgid}";
$(document).ready(function() {
    finedo.action.ajax({
    	url:'${ctx}/finedo/organization/queryOrgById?orgid='+orgid,
    	callback:function(jsondata){
    		if(jsondata.fail){
    			finedo.message.error(jsondata.resultdesc);
    			return;
    		}
    		var orgobj = jsondata.object;
    		$("#parentorgid").val(orgobj.parentorgid);
    		$("#parentorgname").val(orgobj.parentorgname);
    		$("#orgname").val(orgobj.orgname);
    		$("#linkman").val(orgobj.linkman);
    		$("#phoneno").val(orgobj.phoneno);
    		$("#address").val(orgobj.address);
    		$("#orgdesc").val(orgobj.orgdesc);
    		$("#remark").val(orgobj.remark);
    		$("#orgtype").val(orgobj.orgtype);
    		$("#orgtype_name").html(orgobj.orgtype);
    	}
    });
});
function chooseParentOrg(){
	finedo.choose.singleOrg(function(data){
		$("#parentorgid").val(data.id);
		$("#parentorgname").val(data.name);
	});
}
//数据验证
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
		"parentorgname":{label:"上级组织", datatype:"string", maxlength:100, required:true},
		"orgtype":{label:"机构类型", datatype:"string", maxlength:20, required:true},
		"orgname":{label:"机构名称", datatype:"string", maxlength:100, required:true},
		"linkman":{label:"联系人", datatype:"string", maxlength:50, required:false},
		"phoneno":{label:"联系电话", datatype:"phone", maxlength:20, required:false},
		"address":{label:"联系地址", datatype:"string", maxlength:500, required:false},
		"orgdesc":{label:"机构介绍", datatype:"string", maxlength:500, required:false},
		"remark":{label:"备注", datatype:"string", maxlength:500, required:false}
	}, true);
	return result;
}
//普通新建
function dosubmit() {
	if(!checkdata()) 
		return;
	finedo.action.ajaxForm({
		form:"ajaxForm",
		callback:function(jsondata){
			if(jsondata.fail){
				finedo.message.error(jsondata.resultdesc);
				return;
			}
			finedo.message.tip(jsondata.resultdesc, function(){
				finedo.dialog.closeDialog()
			});
		},
		clearForm:true
	});
}
</script>
</body>
</html>

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
		"elename":{label:"网元名称", datatype:"string", required:true},
		"eleip":{label:"IP地址", datatype:"string", required:true},
		"elefactory":{label:"所属厂家", datatype:"string", required:true},
		"elenet":{label:"局域网", datatype:"string", required:true},
		"eletype":{label:"网元类型", datatype:"string", required:true},
		"creator":{label:"创建者", datatype:"string", required:true},
		"createtime":{label:"创建时间", datatype:"string", required:true},
		"city":{label:"所属地市", datatype:"string", required:true},
		"logintype":{label:"登录类型", datatype:"string", required:true},
		"port":{label:"端口", datatype:"string", required:true},
		"username":{label:"用户名", datatype:"string", required:true},
		"password":{label:"密码", datatype:"string", required:true},
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
    	<div class="add-common-name-add">修改网元信息<br/>
            <ul>
            	<li id="common_add_card" class="add-link-li">全部</li>
            </ul>
        </div>
        <input type="button" class="finedo-button-blue" style="float:right;" value="提交" onclick="donext();">
    </div>
        
    <form method="post" action="${ctx }/finedo/znywnetelement/modify" id="ajaxForm" name="ajaxForm">
	<input value="${znywnetelement.eletid}" id="eletid" name="eletid" type="hidden"/>
    <div class="finedo-nav-title">基本信息</div>
		<ul class="finedo-ul">
			<li>
				<span class="finedo-label-title"><font color=red>*</font>网元名称</span>
				<fsdp:text id="elename" hint="网元名称" value="${znywnetelement.elename}" />
			</li>
			<li>
				<span class="finedo-label-title"><font color=red>*</font>IP地址</span>
				<fsdp:text id="eleip" hint="IP地址" value="${znywnetelement.eleip}" />
			</li>
			<li>
				<span class="finedo-label-title"><font color=red>*</font>所属厂家</span>
				<fsdp:text id="elefactory" hint="所属厂家" value="${znywnetelement.elefactory}" />
			</li>
			<li>
				<span class="finedo-label-title"><font color=red>*</font>局域网</span>
				<fsdp:text id="elenet" hint="局域网" value="${znywnetelement.elenet}" />
			</li>
			<li>
				<span class="finedo-label-title"><font color=red>*</font>网元类型</span>
				<fsdp:text id="eletype" hint="网元类型" value="${znywnetelement.eletype}" />
			</li>
			<li>
				<span class="finedo-label-title"><font color=red>*</font>创建者</span>
				<fsdp:text id="creator" hint="创建者" value="${znywnetelement.creator}" />
			</li>
			<li>
				<span class="finedo-label-title"><font color=red>*</font>创建时间</span>
				<fsdp:text id="createtime" hint="创建时间" value="${znywnetelement.createtime}" />
			</li>
			<li>
				<span class="finedo-label-title"><font color=red>*</font>所属地市</span>
				<fsdp:text id="city" hint="所属地市" value="${znywnetelement.city}" />
			</li>
			<li>
				<span class="finedo-label-title"><font color=red>*</font>登录类型</span>
				<fsdp:text id="logintype" hint="登录类型" value="${znywnetelement.logintype}" />
			</li>
			<li>
				<span class="finedo-label-title"><font color=red>*</font>端口</span>
				<fsdp:text id="port" hint="端口" value="${znywnetelement.port}" />
			</li>
			<li>
				<span class="finedo-label-title"><font color=red>*</font>用户名</span>
				<fsdp:text id="username" hint="用户名" value="${znywnetelement.username}" />
			</li>
			<li>
				<span class="finedo-label-title"><font color=red>*</font>密码</span>
				<fsdp:text id="password" hint="密码" value="${znywnetelement.password}" />
			</li>
			<li>
				<span class="finedo-label-title"><font color=red>*</font>备注</span>
				<fsdp:text id="remark" hint="备注" value="${znywnetelement.remark}" />
			</li>
		</ul>
	
		<ul>
	    	<li class="add-center"><input type="button" class="finedo-button" value="提    交" onClick="dosubmit()" ></li>
	    </ul>
	</div>
	</form>
</body>
</html>
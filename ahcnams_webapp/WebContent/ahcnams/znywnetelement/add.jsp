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
${finedo_upload_js }
<script>
/************** 卡片显示与隐藏  ****************/
$(document).ready(function() {
    $('#common_add_card').click(function(e) {
    	$('#common_add_div').css('display', 'block');
    	$('#common_add_card').attr('class', 'add-link-li');
    	
    	$('#excel_add_div').css('display', 'none');
		$('#excel_add_card').removeClass();
		finedo.getFileControl('uploaddiv').reset(false);
    });
    
    $('#excel_add_card').click(function(e) {
    	$('#common_add_div').css('display', 'none');
    	$('#common_add_card').removeClass();
    	
    	$('#excel_add_div').css('display', 'block');
		$('#excel_add_card').attr('class', 'add-link-li');
    });
});
/************** 卡片显示与隐藏  ****************/

// Web组件初始化 
$(document).ready(function() {
    finedo.getFileControl('uploaddiv');
});

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
	if($('#common_add_div').css('display') == 'block') {
		dosubmit();
	}else {
		doimport();
	}
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
	finedo.message.info(jsondata.resultdesc, "新建信息");
}

//批量导入
function doimport() {
	var fileControl=finedo.getFileControl('uploaddiv');
	var filearr=fileControl.getFileList();
	if(filearr.length != 1) {
		finedo.message.error("未上传Excel文件");
		return;
	}
	$('#fileid').val(filearr[0].fileid);
	
	var form = $("#importForm");
	var options = {     
        url:	   form.attr("action"),
        success:   importcallback,
        type:      'POST',
        dataType:  "json",
	    clearForm: false
    };
	finedo.message.showWaiting();
	form.ajaxSubmit(options);
}

function importcallback(jsondata){
	finedo.message.hideWaiting();

	$('#importresultdiv').css('display', 'block');
	var resulthtml="<li><span class='finedo-label-title'>导入情况</span><span class='finedo-label-info'><font color=red>" + jsondata.resultdesc + "   总记录数: " + jsondata.object.rowcount + " &nbsp;&nbsp; 成功记录数: " + jsondata.object.successcount + "&nbsp;&nbsp; 失败记录数: " + jsondata.object.failcount + "</font></span></li>";
	var faillist=eval(jsondata.object.faillist);
	for(var i=0; i<faillist.length; i++)  {
		resulthtml += "<li><span class='finedo-label-title'>失败明细</span><span class='finedo-label-info'>" + faillist[i].resultdesc + "</span></li>";
	}
	
	$('#importresultul').html(resulthtml);
}
</script>
</head>

<body>
<div>
	<div class="add-common-head">
    	<div class="add-common-name-add">新建网元信息<br/>
            <ul>
            	<li id="common_add_card" class="add-link-li">普通新建</li> 
            	<li>|</li>
                <li id="excel_add_card">批量导入</li>
            </ul>
        </div>
        <input type="button" class="finedo-button-blue" style="float:right" value="提交" onclick="donext();">
    </div>
        
    <form method="post" action="${ctx }/finedo/znywnetelement/add" id="ajaxForm" name="ajaxForm">
    <div id="common_add_div" >
    	<div class="finedo-nav-title">基本信息</div>
	   	<ul class="finedo-ul">
				<li>
					<span class="finedo-label-title"><font color=red>*</font>网元名称</span>
					<fsdp:text id="elename" hint="网元名称" />
				</li>
				<li>
					<span class="finedo-label-title"><font color=red>*</font>IP地址</span>
					<fsdp:text id="eleip" hint="IP地址" />
				</li>
				<li>
					<span class="finedo-label-title"><font color=red>*</font>所属厂家</span>
					<fsdp:text id="elefactory" hint="所属厂家" />
				</li>
				<li>
					<span class="finedo-label-title"><font color=red>*</font>局域网</span>
					<fsdp:text id="elenet" hint="局域网" />
				</li>
				<li>
					<span class="finedo-label-title"><font color=red>*</font>网元类型</span>
					<fsdp:text id="eletype" hint="网元类型" />
				</li>
				<li>
					<span class="finedo-label-title"><font color=red>*</font>创建者</span>
					<fsdp:text id="creator" hint="创建者" />
				</li>
				<li>
					<span class="finedo-label-title"><font color=red>*</font>创建时间</span>
					<fsdp:text id="createtime" hint="创建时间" />
				</li>
				<li>
					<span class="finedo-label-title"><font color=red>*</font>所属地市</span>
					<fsdp:text id="city" hint="所属地市" />
				</li>
				<li>
					<span class="finedo-label-title"><font color=red>*</font>登录类型</span>
					<fsdp:text id="logintype" hint="登录类型" />
				</li>
				<li>
					<span class="finedo-label-title"><font color=red>*</font>端口</span>
					<fsdp:text id="port" hint="端口" />
				</li>
				<li>
					<span class="finedo-label-title"><font color=red>*</font>用户名</span>
					<fsdp:text id="username" hint="用户名" />
				</li>
				<li>
					<span class="finedo-label-title"><font color=red>*</font>密码</span>
					<fsdp:text id="password" hint="密码" />
				</li>
				<li>
					<span class="finedo-label-title"><font color=red>*</font>备注</span>
					<fsdp:text id="remark" hint="备注" />
				</li>
			</ul>
			
			<ul>
	    		<li class="add-center"><input type="button" class="finedo-button" value="提    交" onClick="dosubmit()" ></li>
	   		</ul>
		</div>
    </form>
    
    <div id="excel_add_div" style="display:none"> 
   		<form method="post" action="${ctx }/finedo/znywnetelement/importexcel" id="importForm" name="importForm">
   			<input type="hidden" id="fileid" name="fileid">
   		</form>
   		
   		<div class="finedo-nav-title">导入Excel</div>
		<ul class="finedo-ul">
			<li>
				<input type="text" id="uploaddiv" name="uploaddiv" filter=".xls,.xlsx" multiupload="false">
			</li>
        </ul>
        
        <ul>
    		<li class="add-center"><input type="button" class="finedo-button" value="提    交" onclick="doimport();"></li>
		</ul>
   		
   		
   		<div id="importresultdiv" style="display:none">
	    	<div class="finedo-nav-title"><font color=red>导入Excel结果</font></div>
		    <div class="query-common-con">
		    	<ul id="importresultul" class="finedo-ul"></ul>
		    </div>
	    </div>
	    
	   
	    <div class="finedo-nav-title">导入Excel格式说明</div>
		<ul class="finedo-ul">
			<li>
				<span class="finedo-label-title">模板下载</span><span class="finedo-label-info"><a href="import.xlsx" >网元信息信息批量导入Excel模板 </a></span>
			</li>
			
			<li>
				<span class="finedo-label-title">第1列：</span><span class="finedo-label-info">网元名称</span>
			</li>
			<li>
				<span class="finedo-label-title">第2列：</span><span class="finedo-label-info">IP地址</span>
			</li>
			<li>
				<span class="finedo-label-title">第3列：</span><span class="finedo-label-info">所属厂家</span>
			</li>
			<li>
				<span class="finedo-label-title">第4列：</span><span class="finedo-label-info">局域网</span>
			</li>
			<li>
				<span class="finedo-label-title">第5列：</span><span class="finedo-label-info">网元类型</span>
			</li>
			<li>
				<span class="finedo-label-title">第6列：</span><span class="finedo-label-info">创建者</span>
			</li>
			<li>
				<span class="finedo-label-title">第7列：</span><span class="finedo-label-info">创建时间</span>
			</li>
			<li>
				<span class="finedo-label-title">第8列：</span><span class="finedo-label-info">所属地市</span>
			</li>
			<li>
				<span class="finedo-label-title">第9列：</span><span class="finedo-label-info">登录类型</span>
			</li>
			<li>
				<span class="finedo-label-title">第10列：</span><span class="finedo-label-info">端口</span>
			</li>
			<li>
				<span class="finedo-label-title">第11列：</span><span class="finedo-label-info">用户名</span>
			</li>
			<li>
				<span class="finedo-label-title">第12列：</span><span class="finedo-label-info">密码</span>
			</li>
			<li>
				<span class="finedo-label-title">第13列：</span><span class="finedo-label-info">备注</span>
			</li>
		</ul>
	</div>
</div>
</body>
</html>

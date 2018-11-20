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
    	<div class="add-common-name-add">新建任务<br/>
            <ul>
            	<li id="common_add_card" class="add-link-li">普通新建</li> 
            	<li>|</li>
                <li id="excel_add_card">批量导入</li>
            </ul>
        </div>
        <input type="button" class="finedo-button-blue" style="float:right" value="提交" onclick="donext();">
    </div>
        
    <form method="post" action="${ctx }/finedo/znywtask/add" id="ajaxForm" name="ajaxForm">
    <div id="common_add_div" >
    	<div class="finedo-nav-title">基本信息</div>
	   	<ul class="finedo-ul">
				<li>
					<span class="finedo-label-title"><font color=red>*</font>模板id</span>
					<fsdp:text id="templateid" hint="模板id" />
				</li>
				<li>
					<span class="finedo-label-title"><font color=red>*</font>执行人</span>
					<fsdp:text id="executor" hint="执行人" />
				</li>
				<li>
					<span class="finedo-label-title"><font color=red>*</font>执行时间</span>
					<fsdp:text id="executetime" hint="执行时间" />
				</li>
				<li>
					<span class="finedo-label-title"><font color=red>*</font>状态</span>
					<fsdp:text id="status" hint="状态" />
				</li>
				<li>
					<span class="finedo-label-title"><font color=red>*</font>开始时间</span>
					<fsdp:text id="starttime" hint="开始时间" />
				</li>
				<li>
					<span class="finedo-label-title"><font color=red>*</font>结束时间</span>
					<fsdp:text id="expiretime" hint="结束时间" />
				</li>
				<li>
					<span class="finedo-label-title"><font color=red>*</font>部门</span>
					<fsdp:text id="dept" hint="部门" />
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
   		<form method="post" action="${ctx }/finedo/znywtask/importexcel" id="importForm" name="importForm">
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
				<span class="finedo-label-title">模板下载</span><span class="finedo-label-info"><a href="import.xlsx" >任务信息批量导入Excel模板 </a></span>
			</li>
			
			<li>
				<span class="finedo-label-title">第1列：</span><span class="finedo-label-info">模板id</span>
			</li>
			<li>
				<span class="finedo-label-title">第2列：</span><span class="finedo-label-info">执行人</span>
			</li>
			<li>
				<span class="finedo-label-title">第3列：</span><span class="finedo-label-info">执行时间</span>
			</li>
			<li>
				<span class="finedo-label-title">第4列：</span><span class="finedo-label-info">状态</span>
			</li>
			<li>
				<span class="finedo-label-title">第5列：</span><span class="finedo-label-info">开始时间</span>
			</li>
			<li>
				<span class="finedo-label-title">第6列：</span><span class="finedo-label-info">结束时间</span>
			</li>
			<li>
				<span class="finedo-label-title">第7列：</span><span class="finedo-label-info">部门</span>
			</li>
			<li>
				<span class="finedo-label-title">第8列：</span><span class="finedo-label-info">备注</span>
			</li>
		</ul>
	</div>
</div>
</body>
</html>

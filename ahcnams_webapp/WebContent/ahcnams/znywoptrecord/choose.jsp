<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/common/taglibs2.jsp" %>

<!doctype html>
<html>
<head>
${style_css }
${jquery_js }
${finedo_core_js }
${finedo_grid_js }
${finedo_dialog_js }

<script type="text/javascript">
/************** 查询函数定义****************/

$(document.body).ready(function(){
	$("#optid").blur(function(){
		if(this.value==""){
			this.value="请输入操作记录id";
		}
	}); 
	
	$("#optid").focus(function(){
		if(this.value=="请输入操作记录id"){
			this.value="";
		}
	}); 
});

function doQuery(){
	var text=$('#optid').val();
	var param = {optid:text};
	finedo.getgrid("datagrid").query(param);
}

function doQueryFast(event) {
	if(event.keyCode != 13)
		return;
	doQuery();
}
/************** 查询函数定义****************/

</script>
</head>

<body class="scrollcss">

<div class="index">
	<!-- 工具栏  -->
	<div class="table-title">
		<!-- 标题 -->
    	<div class="common-name icon-class-query">选择<br/>
			<ul>
            	<li class="link-li">全部</li>
            </ul>
        </div>
        
        <!-- 快捷查询 -->
        <div style="width:220px" class="query-box">
        	<input type="text" style="width:200px" class="query-box-text" id="optid" name="optid" value="请输入操作记录id" onkeypress="doQueryFast(event);">
            <input type="button" class="query-box-magnifier" onclick="doQuery();">
        </div>
    </div>
   
    <!-- 表格栏  -->
    <fsdp:grid className="table" id="datagrid" url="${ctx }/finedo/znywoptrecord/query" selecttype="${param.selecttype }">
		<fsdp:field code="taskid" name="任务id" width="100"></fsdp:field>
		<fsdp:field code="optperson" name="操作人" width="100"></fsdp:field>
		<fsdp:field code="opttime" name="操作时间" width="100"></fsdp:field>
	</fsdp:grid>
</div>

</body>
</html>

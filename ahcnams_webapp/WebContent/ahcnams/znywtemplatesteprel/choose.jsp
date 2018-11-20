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
	$("#relid").blur(function(){
		if(this.value==""){
			this.value="请输入关系id";
		}
	}); 
	
	$("#relid").focus(function(){
		if(this.value=="请输入关系id"){
			this.value="";
		}
	}); 
});

function doQuery(){
	var text=$('#relid').val();
	var param = {relid:text};
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
        	<input type="text" style="width:200px" class="query-box-text" id="relid" name="relid" value="请输入关系id" onkeypress="doQueryFast(event);">
            <input type="button" class="query-box-magnifier" onclick="doQuery();">
        </div>
    </div>
   
    <!-- 表格栏  -->
    <fsdp:grid className="table" id="datagrid" url="${ctx }/finedo/znywtemplatesteprel/query" selecttype="${param.selecttype }">
		<fsdp:field code="templateid" name="模板id" width="100"></fsdp:field>
		<fsdp:field code="stepid" name="步骤id" width="100"></fsdp:field>
	</fsdp:grid>
</div>

</body>
</html>

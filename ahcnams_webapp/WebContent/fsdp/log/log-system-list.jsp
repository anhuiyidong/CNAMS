<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/common/taglibs2.jsp" %>

<!DOCTYPE html>
<html>
<head>
<title>异常日志查询</title>
${style_css }
${jquery_js }
${finedo_core_js }
${finedo_commonui_js }
${finedo_dialog_js }
${finedo_grid_js }
${finedo_date_js }

<script>
function doSearch(){  
	var param = {logindatebegin: $("#logindatebegin").val(),logindateend: $("#logindateend").val()};
	finedo.getgrid("datagrid").query(param);
}  
</script>
</head>
<body class="query-body">
<div style="width:100%;">
	<!-- 工具栏  -->
	<div class="query-title">
		<!-- 标题 -->
    	<div class="query-title-name">系统日志查询</div>
         
        <div class="query-boxbig">
        	<input class="query-fast-date" type="text" style="width:160px;" id="opttimebegin" name="opttimebegin" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})">
			-
			<input class="query-fast-date" type="text" style="width:160px;" id="opttimeend" name="opttimeend" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})">
        	<input type="button" class="query-btn-nextstep" onclick="doSearch();" value="查询">  
        </div>       	
   	</div>
  
    <!-- 表格栏  -->
    <fsdp:grid id="datagrid" url="${ctx }/finedo/log/querySystemLog" selecttype="none">
		<fsdp:field code="optsn" name="业务流水号" width="110"></fsdp:field>
		<fsdp:field code="exceptiontype" name="异常类型" width="80"></fsdp:field>
		<fsdp:field code="exceptioncontent" name="异常内容" width="250"></fsdp:field>
		<fsdp:field code="logtime" name="异常发生时间" width="100"></fsdp:field>
		<fsdp:field code="opttime" name="操作时间" width="100"></fsdp:field>
	</fsdp:grid>
</div>
</body>
</html>
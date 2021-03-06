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
${finedo_grid_js }
${finedo_date_js }

<script type="text/javascript">
function doQueryFast() {
	var param = {begintime: $('#begintime').val(), endtime: $('#endtime').val()};
	finedo.getgrid("datagrid").query(param);
}
</script>
</head>

<body class="query-body">
  	
<div style="width:100%;">
	<!-- 工具栏  -->
	<div class="query-title">
		<!-- 标题 -->
    	<div class="query-title-name">服务请求监控</div>
         
        <div class="query-boxbig">
        	<input class="query-fast-date" type="text" style="width:160px;" id="begintime" name="begintime" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})">
			-
			<input class="query-fast-date" type="text" style="width:160px;" id="endtime" name="endtime" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})">
        	<input type="button" class="query-btn-nextstep" onclick="doQueryFast();" value="查询">  
        </div>       	
   	</div>
  
    <!-- 表格栏  -->
    <fsdp:grid id="datagrid" url="${ctx }/finedo/monitor/queryService" selecttype="none" servorder="true">
    	<fsdp:field code="servicename" name="服务名称" width="300"></fsdp:field>
		<fsdp:field code="begintime" name="开始请求时间" width="150"></fsdp:field>
		<fsdp:field code="endtime" name="结束请求时间" width="150"></fsdp:field>
		<fsdp:field code="cost" name="请求时长（毫秒）" width="100" order="true"></fsdp:field>
	</fsdp:grid>
</div>
</body>
</html>
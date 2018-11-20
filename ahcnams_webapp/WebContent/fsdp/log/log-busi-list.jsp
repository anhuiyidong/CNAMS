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
	var param = {opttimebegin: $("#opttimebegin").val(),opttimeend: $("#opttimeend").val()};
	finedo.getgrid("datagrid").query(param);
}
</script>
</head>
<body class="query-body">
  	
<div style="width:100%;">
	<!-- 工具栏  -->
	<div class="query-title">
		<!-- 标题 -->
    	<div class="query-title-name">业务日志查询</div>
         
        <div class="query-boxbig">
        	<input class="query-fast-date" type="text" style="width:160px;" id="opttimebegin" name="opttimebegin" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})">
			-
			<input class="query-fast-date" type="text" style="width:160px;" id="opttimeend" name="opttimeend" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})">
        	<input type="button" class="query-btn-nextstep" onclick="doQueryFast();" value="查询">  
        </div>       	
   	</div>
	<fsdp:grid id="datagrid" url="${ctx }/finedo/log/queryBusiLog" selecttype="none">
		<fsdp:field code="optsn" name="业务流水号" width="110"></fsdp:field>
		<fsdp:field code="optrname" name="操作人" width="80"></fsdp:field>
		<fsdp:field code="opname" name="操作名称" width="220"></fsdp:field>
		<fsdp:field code="opdesc" name="操作描述" width="350"></fsdp:field>
		<fsdp:field code="opttime" name="操作时间" width="150"></fsdp:field>
	</fsdp:grid>
</div>
</body>
</html>
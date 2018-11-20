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

<script type="text/javascript">
/************** 表格操作函数定义****************/
// 表格"操作"单元格重载
function formatOperation(row){
	var operation = '<a href="#" onclick="showModifyDialog(\'' + row.taskid + '\');">[编辑]</a>&nbsp;';
	operation += '<a href="#" onclick="finedo.action.doDelete(\'datagrid\',\'${ctx}/finedo/znywtask/delete\',\'' + row.taskid + '\')">[删除]</a>&nbsp;';
	return operation;
}

// 单元格重载
function formatPkey(row){
	return '<a href="#" onclick="showDetailDialog(\'' + row.taskid + '\');">' + row.taskid + '</a>&nbsp;';
}

// 行信息展开
function doExpandRow(data){
	var datahtml="<div class='data'><ul>";
	
	datahtml=datahtml + "<li><span class='data-name'>任务id</span><span class='data-con'>" + finedo.fn.replaceNull(data.taskid) + "</span></li>";
	datahtml=datahtml + "<li><span class='data-name'>模板id</span><span class='data-con'>" + finedo.fn.replaceNull(data.templateid) + "</span></li>";
	datahtml=datahtml + "<li><span class='data-name'>执行人</span><span class='data-con'>" + finedo.fn.replaceNull(data.executor) + "</span></li>";
	datahtml=datahtml + "<li><span class='data-name'>执行时间</span><span class='data-con'>" + finedo.fn.replaceNull(data.executetime) + "</span></li>";
	datahtml=datahtml + "<li><span class='data-name'>状态</span><span class='data-con'>" + finedo.fn.replaceNull(data.status) + "</span></li>";
	datahtml=datahtml + "<li><span class='data-name'>开始时间</span><span class='data-con'>" + finedo.fn.replaceNull(data.starttime) + "</span></li>";
	datahtml=datahtml + "<li><span class='data-name'>结束时间</span><span class='data-con'>" + finedo.fn.replaceNull(data.expiretime) + "</span></li>";
	datahtml=datahtml + "<li><span class='data-name'>部门</span><span class='data-con'>" + finedo.fn.replaceNull(data.dept) + "</span></li>";
	datahtml=datahtml + "<li><span class='data-name'>备注</span><span class='data-con'>" + finedo.fn.replaceNull(data.remark) + "</span></li>";
	
	datahtml=datahtml + "</ul></div>";
	return datahtml;
}
/************** 表格操作函数定义****************/


/************** 查询函数定义****************/
function doQuery() {
	opquerycond();
	
	
	var param = {taskid: $('#taskid').val(), templateid: $('#templateid').val(), executor: $('#executor').val(), executetime: $('#executetime').val(), status: $('#status').val(), starttime: $('#starttime').val(), expiretime: $('#expiretime').val(), dept: $('#dept').val()};
	finedo.getgrid("datagrid").query(param);
}

function doQueryCancel() {
	opquerycond();
}

function doQueryFast() {
	var text=$('#query-box-text').val();
	
	var param = {taskid:text};
	finedo.getgrid("datagrid").query(param);
}
/************** 查询函数定义****************/

/************** 增、删、改函数定义****************/
function showAddDialog() {
	finedo.dialog.show({
		width:850,
		height:550,
		'title':'新增信息',
		'url':'add.jsp'
	});
}

function showModifyDialog(pkeyid) {
	finedo.dialog.show({
		width:850,
		height:550,
		'title':'修改信息',
		'url':'${ctx}/finedo/znywtask/modifypage?taskid=' + pkeyid
	});
}

function showDetailDialog(pkeyid) {
	finedo.dialog.show({
		width:850,
		height:550,
		'title':'详情信息',
		'url':'${ctx}/finedo/znywtask/detail?taskid=' + pkeyid
	});
}

function doExport() {
	var param=finedo.getgrid("datagrid").getqueryparams();
	
	$("#downiframe").attr('src', '${ctx }/finedo/znywtask/exportexcel' + (param ? '?' + $.param(param) : ''));
}
/************** 增、删、改函数定义****************/

</script>
</head>

<body class="scrollcss">

<div style="width:100%;">
	<!-- 工具栏  -->
	<div class="query-title">
		<!-- 标题 -->
    	<div class="query-title-name">任务管理 </div>
    	
    	<div class="query-boxbig">
	    	<input type="button" class="query-btn-nextstep" onclick="showAddDialog();" value="新建任务">
	    	<input type="button" class="query-btn-nextstep" onclick="doExport();" value="批量导出">
       	 	
    		<div class="query-fast">
	        	<input type="text" class="query-fast-text" id="query-box-text" value="">
	            <input type="button" class="query-fast-magnifier" onclick="doQueryFast();">
	        </div>
	        <input type="button" class="query-btn-nextstep" onclick="opquerycond();" value="高级搜索">
    	</div>
	</div>
	
	<!-- 高级搜索栏  -->
    <div class="query-advanced-search-con" id="advanced-search-con" style="display:none; ">
    	<div class="query-common-query" id="common-con">
        	<ul class="finedo-ul">
				<li>
					<span class="finedo-label-title">任务id</span>
					<fsdp:text id="taskid"/>	
				</li>
				<li>
					<span class="finedo-label-title">模板id</span>
					<fsdp:text id="templateid"/>	
				</li>
				<li>
					<span class="finedo-label-title">执行人</span>
					<fsdp:text id="executor"/>	
				</li>
				<li>
					<span class="finedo-label-title">执行时间</span>
					<fsdp:text id="executetime"/>	
				</li>
				<li>
					<span class="finedo-label-title">状态</span>
					<fsdp:text id="status"/>	
				</li>
				<li>
					<span class="finedo-label-title">开始时间</span>
					<fsdp:text id="starttime"/>	
				</li>
				<li>
					<span class="finedo-label-title">结束时间</span>
					<fsdp:text id="expiretime"/>	
				</li>
				<li>
					<span class="finedo-label-title">部门</span>
					<fsdp:text id="dept"/>	
				</li>
				<li>
					<span class="finedo-label-title">备注</span>
					<fsdp:text id="remark"/>	
				</li>
			</ul>
        </div>
        <div class="query-operate">
            <input class="finedo-button-blue" type="button" value="查    询" onclick="doQuery();">&nbsp;&nbsp;&nbsp;&nbsp;
           	<input class="finedo-button-blue" type="button" value="取    消" onclick="doQueryCancel();">
        </div>
    </div>
    
    <!-- 表格栏  -->
    <fsdp:grid id="datagrid" url="${ctx }/finedo/znywtask/query" selecttype="multi" expand="doExpandRow">
		<fsdp:field code="taskid" name="任务id" width="100" formatter="formatPkey" order="true"></fsdp:field>
		<fsdp:field code="templateid" name="模板id" width="100"></fsdp:field>
		<fsdp:field code="executor" name="执行人" width="100"></fsdp:field>
		<fsdp:field code="executetime" name="执行时间" width="100"></fsdp:field>
		<fsdp:field code="status" name="状态" width="100"></fsdp:field>
		<fsdp:field code="starttime" name="开始时间" width="100"></fsdp:field>
		<fsdp:field code="expiretime" name="结束时间" width="100"></fsdp:field>
		<fsdp:field code="dept" name="部门" width="100"></fsdp:field>
		<fsdp:field code="operation" name="操作" formatter="formatOperation"></fsdp:field>
	</fsdp:grid>
</div>

<iframe id="downiframe" name="downiframe" style="display:none" ></iframe>
</body>
</html>

<script>
/**************  展开与隐藏 搜索条件框 ****************/
function opquerycond(){
	var divdisplay=$('#advanced-search-con').css('display');
	
	if(divdisplay == 'block'){
		$('#advanced-search-con').css('display', 'none');
		$('#advanced-search').attr('class', 'query-as-link');
	
	}else{
		$('#advanced-search-con').css('display', 'block');
		$('#advanced-search').attr('class', 'query-as-hover');
	}
}
</script>


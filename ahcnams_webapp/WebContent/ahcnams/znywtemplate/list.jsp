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
	var operation = '<a href="#" onclick="showModifyDialog(\'' + row.templateid + '\');">[编辑]</a>&nbsp;';
	operation += '<a href="#" onclick="finedo.action.doDelete(\'datagrid\',\'${ctx}/finedo/znywtemplate/delete\',\'' + row.templateid + '\')">[删除]</a>&nbsp;';
	return operation;
}

// 单元格重载
function formatPkey(row){
	return '<a href="#" onclick="showDetailDialog(\'' + row.templateid + '\');">' + row.templateid + '</a>&nbsp;';
}

// 行信息展开
function doExpandRow(data){
	var datahtml="<div class='data'><ul>";
	
	datahtml=datahtml + "<li><span class='data-name'>模板id</span><span class='data-con'>" + finedo.fn.replaceNull(data.templateid) + "</span></li>";
	datahtml=datahtml + "<li><span class='data-name'>模板名称</span><span class='data-con'>" + finedo.fn.replaceNull(data.templatename) + "</span></li>";
	datahtml=datahtml + "<li><span class='data-name'>创建者</span><span class='data-con'>" + finedo.fn.replaceNull(data.creator) + "</span></li>";
	datahtml=datahtml + "<li><span class='data-name'>创建时间</span><span class='data-con'>" + finedo.fn.replaceNull(data.createtime) + "</span></li>";
	datahtml=datahtml + "<li><span class='data-name'>模板状态</span><span class='data-con'>" + finedo.fn.replaceNull(data.templatestatus) + "</span></li>";
	datahtml=datahtml + "<li><span class='data-name'>备注</span><span class='data-con'>" + finedo.fn.replaceNull(data.remark) + "</span></li>";
	
	datahtml=datahtml + "</ul></div>";
	return datahtml;
}
/************** 表格操作函数定义****************/


/************** 查询函数定义****************/
function doQuery() {
	opquerycond();
	
	
	var param = {templateid: $('#templateid').val(), templatename: $('#templatename').val(), creator: $('#creator').val(), createtime: $('#createtime').val(), templatestatus: $('#templatestatus').val()};
	finedo.getgrid("datagrid").query(param);
}

function doQueryCancel() {
	opquerycond();
}

function doQueryFast() {
	var text=$('#query-box-text').val();
	
	var param = {templateid:text};
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
		'url':'${ctx}/finedo/znywtemplate/modifypage?templateid=' + pkeyid
	});
}

function showDetailDialog(pkeyid) {
	finedo.dialog.show({
		width:850,
		height:550,
		'title':'详情信息',
		'url':'${ctx}/finedo/znywtemplate/detail?templateid=' + pkeyid
	});
}

function doExport() {
	var param=finedo.getgrid("datagrid").getqueryparams();
	
	$("#downiframe").attr('src', '${ctx }/finedo/znywtemplate/exportexcel' + (param ? '?' + $.param(param) : ''));
}
/************** 增、删、改函数定义****************/

</script>
</head>

<body class="scrollcss">

<div style="width:100%;">
	<!-- 工具栏  -->
	<div class="query-title">
		<!-- 标题 -->
    	<div class="query-title-name">模板管理 </div>
    	
    	<div class="query-boxbig">
	    	<input type="button" class="query-btn-nextstep" onclick="showAddDialog();" value="新建模板">
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
					<span class="finedo-label-title">模板id</span>
					<fsdp:text id="templateid"/>	
				</li>
				<li>
					<span class="finedo-label-title">模板名称</span>
					<fsdp:text id="templatename"/>	
				</li>
				<li>
					<span class="finedo-label-title">创建者</span>
					<fsdp:text id="creator"/>	
				</li>
				<li>
					<span class="finedo-label-title">创建时间</span>
					<fsdp:text id="createtime"/>	
				</li>
				<li>
					<span class="finedo-label-title">模板状态</span>
					<fsdp:text id="templatestatus"/>	
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
    <fsdp:grid id="datagrid" url="${ctx }/finedo/znywtemplate/query" selecttype="multi" expand="doExpandRow">
		<fsdp:field code="templateid" name="模板id" width="100" formatter="formatPkey" order="true"></fsdp:field>
		<fsdp:field code="templatename" name="模板名称" width="100"></fsdp:field>
		<fsdp:field code="creator" name="创建者" width="100"></fsdp:field>
		<fsdp:field code="createtime" name="创建时间" width="100"></fsdp:field>
		<fsdp:field code="templatestatus" name="模板状态" width="100"></fsdp:field>
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


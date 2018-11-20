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
	var operation = '<a href="#" onclick="showModifyDialog(\'' + row.stepid + '\');">[编辑]</a>&nbsp;';
	operation += '<a href="#" onclick="finedo.action.doDelete(\'datagrid\',\'${ctx}/finedo/znywtemplatestep/delete\',\'' + row.stepid + '\')">[删除]</a>&nbsp;';
	return operation;
}

// 单元格重载
function formatPkey(row){
	return '<a href="#" onclick="showDetailDialog(\'' + row.stepid + '\');">' + row.stepid + '</a>&nbsp;';
}

// 行信息展开
function doExpandRow(data){
	var datahtml="<div class='data'><ul>";
	
	datahtml=datahtml + "<li><span class='data-name'>步骤id</span><span class='data-con'>" + finedo.fn.replaceNull(data.stepid) + "</span></li>";
	datahtml=datahtml + "<li><span class='data-name'>步骤名称</span><span class='data-con'>" + finedo.fn.replaceNull(data.stepname) + "</span></li>";
	datahtml=datahtml + "<li><span class='data-name'>脚本</span><span class='data-con'>" + finedo.fn.replaceNull(data.stepscript) + "</span></li>";
	datahtml=datahtml + "<li><span class='data-name'>关联网元id</span><span class='data-con'>" + finedo.fn.replaceNull(data.relatedele) + "</span></li>";
	datahtml=datahtml + "<li><span class='data-name'>备注</span><span class='data-con'>" + finedo.fn.replaceNull(data.stepremark) + "</span></li>";
	
	datahtml=datahtml + "</ul></div>";
	return datahtml;
}
/************** 表格操作函数定义****************/


/************** 查询函数定义****************/
function doQuery() {
	opquerycond();
	
	
	var param = {stepid: $('#stepid').val(), stepname: $('#stepname').val(), stepscript: $('#stepscript').val(), relatedele: $('#relatedele').val(), stepremark: $('#stepremark').val()};
	finedo.getgrid("datagrid").query(param);
}

function doQueryCancel() {
	opquerycond();
}

function doQueryFast() {
	var text=$('#query-box-text').val();
	
	var param = {stepid:text};
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
		'url':'${ctx}/finedo/znywtemplatestep/modifypage?stepid=' + pkeyid
	});
}

function showDetailDialog(pkeyid) {
	finedo.dialog.show({
		width:850,
		height:550,
		'title':'详情信息',
		'url':'${ctx}/finedo/znywtemplatestep/detail?stepid=' + pkeyid
	});
}

function doExport() {
	var param=finedo.getgrid("datagrid").getqueryparams();
	
	$("#downiframe").attr('src', '${ctx }/finedo/znywtemplatestep/exportexcel' + (param ? '?' + $.param(param) : ''));
}
/************** 增、删、改函数定义****************/

</script>
</head>

<body class="scrollcss">

<div style="width:100%;">
	<!-- 工具栏  -->
	<div class="query-title">
		<!-- 标题 -->
    	<div class="query-title-name">模板步骤管理 </div>
    	
    	<div class="query-boxbig">
	    	<input type="button" class="query-btn-nextstep" onclick="showAddDialog();" value="新建模板步骤">
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
					<span class="finedo-label-title">步骤id</span>
					<fsdp:text id="stepid"/>	
				</li>
				<li>
					<span class="finedo-label-title">步骤名称</span>
					<fsdp:text id="stepname"/>	
				</li>
				<li>
					<span class="finedo-label-title">脚本</span>
					<fsdp:text id="stepscript"/>	
				</li>
				<li>
					<span class="finedo-label-title">关联网元id</span>
					<fsdp:text id="relatedele"/>	
				</li>
				<li>
					<span class="finedo-label-title">备注</span>
					<fsdp:text id="stepremark"/>	
				</li>
			</ul>
        </div>
        <div class="query-operate">
            <input class="finedo-button-blue" type="button" value="查    询" onclick="doQuery();">&nbsp;&nbsp;&nbsp;&nbsp;
           	<input class="finedo-button-blue" type="button" value="取    消" onclick="doQueryCancel();">
        </div>
    </div>
    
    <!-- 表格栏  -->
    <fsdp:grid id="datagrid" url="${ctx }/finedo/znywtemplatestep/query" selecttype="multi" expand="doExpandRow">
		<fsdp:field code="stepid" name="步骤id" width="100" formatter="formatPkey" order="true"></fsdp:field>
		<fsdp:field code="stepname" name="步骤名称" width="100"></fsdp:field>
		<fsdp:field code="stepscript" name="脚本" width="100"></fsdp:field>
		<fsdp:field code="relatedele" name="关联网元id" width="100"></fsdp:field>
		<fsdp:field code="stepremark" name="备注" width="100"></fsdp:field>
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


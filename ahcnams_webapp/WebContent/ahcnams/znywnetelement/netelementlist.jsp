<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/common/taglibs2.jsp" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>网元管理</title>
    <link rel="stylesheet" type="text/css" href="../resource/css/style.css">
    
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
			var operation = '<a href="#" onclick="showModifyDialog(\'' + row.eletid + '\');">[编辑]</a>&nbsp;';
			operation += '<a href="#" onclick="finedo.action.doDelete(\'datagrid\',\'${ctx}/finedo/znywnetelement/delete\',\'' + row.eletid + '\')">[删除]</a>&nbsp;';
			return operation;
		}
		
		function showModifyDialog(pkeyid) {
			finedo.dialog.show({
				width:850,
				height:550,
				'title':'修改信息',
				'url':'netmodify.jsp?eletid=' + pkeyid
			});
		}
		
		/************** 增、删、改函数定义****************/
		function showAddDialog() {
			finedo.dialog.show({
				width:850,
				height:550,
				'title':'新增信息',
				'url':'addnetelement.jsp'
			});
		}
	</script>
</head>
<body>
<div class="html-body">
    <div class="cont-body">
        <div class="tree">
            <div class="tree-one">地市</div>
            <ul>
                <li>全省</li>
                <li>合肥</li>
                <li>芜湖</li>
                <li>蚌埠</li>
                <li>安庆</li>
                <li>黄山</li>
                <li>淮南</li>
                <li>阜阳</li>
                <li>马鞍山</li>
                <li>淮北</li>
                <li>铜陵</li>
            </ul>
        </div>
        <div class="query" style=" width: 80%; display: inline-block;">
            <div class="query-title">
                <span>网元管理</span>
                <div class="query-btn2">
                    <label>网元类型：<select><option>MME</option><option>SAEGW</option><option>DNS</option></select></label>
                    <label>网元名称：<input type="text" placeholder="请输入网元名称"></label>
                    <label>IP地址：<input type="text" placeholder="请输入IP地址"></label>
                    <input type="button" value="查询">
                    <input type="button" value="高级搜索">
                    <input type="button" onclick="showAddDialog();" value="添加网元">
                </div>
            </div>
            
            <!-- 表格栏  -->
		    <fsdp:grid id="datagrid" url="${ctx }/finedo/znywnetelement/query" selecttype="multi" expand="doExpandRow">
				<fsdp:field code="elename" name="网元名称" width="100"></fsdp:field>
				<fsdp:field code="eleip" name="IP地址" width="100"></fsdp:field>
				<fsdp:field code="elefactory" name="所属厂家" width="100"></fsdp:field>
				<fsdp:field code="elenet" name="局域网" width="100"></fsdp:field>
				<fsdp:field code="eletype" name="网元类型" width="100"></fsdp:field>
				<fsdp:field code="creator" name="创建者" width="100"></fsdp:field>
				<fsdp:field code="createtime" name="创建时间" width="100"></fsdp:field>
				<fsdp:field code="city" name="所属地市" width="100"></fsdp:field>
				<fsdp:field code="operation" name="操作" formatter="formatOperation"></fsdp:field>
			</fsdp:grid>
        </div>
    </div>
</div>
</body>
</html>



<%-- <!doctype html>
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
	var operation = '<a href="#" onclick="showModifyDialog(\'' + row.eletid + '\');">[编辑]</a>&nbsp;';
	operation += '<a href="#" onclick="finedo.action.doDelete(\'datagrid\',\'${ctx}/finedo/znywnetelement/delete\',\'' + row.eletid + '\')">[删除]</a>&nbsp;';
	return operation;
}

// 单元格重载
function formatPkey(row){
	return '<a href="#" onclick="showDetailDialog(\'' + row.eletid + '\');">' + row.eletid + '</a>&nbsp;';
}

// 行信息展开
function doExpandRow(data){
	var datahtml="<div class='data'><ul>";
	
	datahtml=datahtml + "<li><span class='data-name'>网元id</span><span class='data-con'>" + finedo.fn.replaceNull(data.eletid) + "</span></li>";
	datahtml=datahtml + "<li><span class='data-name'>网元名称</span><span class='data-con'>" + finedo.fn.replaceNull(data.elename) + "</span></li>";
	datahtml=datahtml + "<li><span class='data-name'>IP地址</span><span class='data-con'>" + finedo.fn.replaceNull(data.eleip) + "</span></li>";
	datahtml=datahtml + "<li><span class='data-name'>所属厂家</span><span class='data-con'>" + finedo.fn.replaceNull(data.elefactory) + "</span></li>";
	datahtml=datahtml + "<li><span class='data-name'>局域网</span><span class='data-con'>" + finedo.fn.replaceNull(data.elenet) + "</span></li>";
	datahtml=datahtml + "<li><span class='data-name'>网元类型</span><span class='data-con'>" + finedo.fn.replaceNull(data.eletype) + "</span></li>";
	datahtml=datahtml + "<li><span class='data-name'>创建者</span><span class='data-con'>" + finedo.fn.replaceNull(data.creator) + "</span></li>";
	datahtml=datahtml + "<li><span class='data-name'>创建时间</span><span class='data-con'>" + finedo.fn.replaceNull(data.createtime) + "</span></li>";
	datahtml=datahtml + "<li><span class='data-name'>所属地市</span><span class='data-con'>" + finedo.fn.replaceNull(data.city) + "</span></li>";
	datahtml=datahtml + "<li><span class='data-name'>登录类型</span><span class='data-con'>" + finedo.fn.replaceNull(data.logintype) + "</span></li>";
	datahtml=datahtml + "<li><span class='data-name'>端口</span><span class='data-con'>" + finedo.fn.replaceNull(data.port) + "</span></li>";
	datahtml=datahtml + "<li><span class='data-name'>用户名</span><span class='data-con'>" + finedo.fn.replaceNull(data.username) + "</span></li>";
	datahtml=datahtml + "<li><span class='data-name'>密码</span><span class='data-con'>" + finedo.fn.replaceNull(data.password) + "</span></li>";
	datahtml=datahtml + "<li><span class='data-name'>备注</span><span class='data-con'>" + finedo.fn.replaceNull(data.remark) + "</span></li>";
	
	datahtml=datahtml + "</ul></div>";
	return datahtml;
}
/************** 表格操作函数定义****************/


/************** 查询函数定义****************/
function doQuery() {
	opquerycond();
	
	
	var param = {eletid: $('#eletid').val(), elename: $('#elename').val(), eleip: $('#eleip').val(), elefactory: $('#elefactory').val(), elenet: $('#elenet').val(), eletype: $('#eletype').val(), creator: $('#creator').val(), createtime: $('#createtime').val(), city: $('#city').val(), logintype: $('#logintype').val(), port: $('#port').val(), username: $('#username').val(), password: $('#password').val()};
	finedo.getgrid("datagrid").query(param);
}

function doQueryCancel() {
	opquerycond();
}

function doQueryFast() {
	var text=$('#query-box-text').val();
	
	var param = {eletid:text};
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
		'url':'${ctx}/finedo/znywnetelement/modifypage?eletid=' + pkeyid
	});
}

function showDetailDialog(pkeyid) {
	finedo.dialog.show({
		width:850,
		height:550,
		'title':'详情信息',
		'url':'${ctx}/finedo/znywnetelement/detail?eletid=' + pkeyid
	});
}

function doExport() {
	var param=finedo.getgrid("datagrid").getqueryparams();
	
	$("#downiframe").attr('src', '${ctx }/finedo/znywnetelement/exportexcel' + (param ? '?' + $.param(param) : ''));
}
/************** 增、删、改函数定义****************/

</script>
</head>

<body class="scrollcss">

<div style="width:100%;">
	<!-- 工具栏  -->
	<div class="query-title">
		<!-- 标题 -->
    	<div class="query-title-name">网元信息管理 </div>
    	
    	<div class="query-boxbig">
	    	<input type="button" class="query-btn-nextstep" onclick="showAddDialog();" value="新建网元信息">
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
					<span class="finedo-label-title">网元id</span>
					<fsdp:text id="eletid"/>	
				</li>
				<li>
					<span class="finedo-label-title">网元名称</span>
					<fsdp:text id="elename"/>	
				</li>
				<li>
					<span class="finedo-label-title">IP地址</span>
					<fsdp:text id="eleip"/>	
				</li>
				<li>
					<span class="finedo-label-title">所属厂家</span>
					<fsdp:text id="elefactory"/>	
				</li>
				<li>
					<span class="finedo-label-title">局域网</span>
					<fsdp:text id="elenet"/>	
				</li>
				<li>
					<span class="finedo-label-title">网元类型</span>
					<fsdp:text id="eletype"/>	
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
					<span class="finedo-label-title">所属地市</span>
					<fsdp:text id="city"/>	
				</li>
				<li>
					<span class="finedo-label-title">登录类型</span>
					<fsdp:text id="logintype"/>	
				</li>
				<li>
					<span class="finedo-label-title">端口</span>
					<fsdp:text id="port"/>	
				</li>
				<li>
					<span class="finedo-label-title">用户名</span>
					<fsdp:text id="username"/>	
				</li>
				<li>
					<span class="finedo-label-title">密码</span>
					<fsdp:text id="password"/>	
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
    <fsdp:grid id="datagrid" url="${ctx }/finedo/znywnetelement/query" selecttype="multi" expand="doExpandRow">
		<fsdp:field code="eletid" name="网元id" width="100" formatter="formatPkey" order="true"></fsdp:field>
		<fsdp:field code="elename" name="网元名称" width="100"></fsdp:field>
		<fsdp:field code="eleip" name="IP地址" width="100"></fsdp:field>
		<fsdp:field code="elefactory" name="所属厂家" width="100"></fsdp:field>
		<fsdp:field code="elenet" name="局域网" width="100"></fsdp:field>
		<fsdp:field code="eletype" name="网元类型" width="100"></fsdp:field>
		<fsdp:field code="creator" name="创建者" width="100"></fsdp:field>
		<fsdp:field code="createtime" name="创建时间" width="100"></fsdp:field>
		<fsdp:field code="city" name="所属地市" width="100"></fsdp:field>
		<fsdp:field code="logintype" name="登录类型" width="100"></fsdp:field>
		<fsdp:field code="port" name="端口" width="100"></fsdp:field>
		<fsdp:field code="username" name="用户名" width="100"></fsdp:field>
		<fsdp:field code="password" name="密码" width="100"></fsdp:field>
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
</script> --%>


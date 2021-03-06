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
	$("#eletid").blur(function(){
		if(this.value==""){
			this.value="请输入网元id";
		}
	}); 
	
	$("#eletid").focus(function(){
		if(this.value=="请输入网元id"){
			this.value="";
		}
	}); 
});

function doQuery(){
	var text=$('#eletid').val();
	var param = {eletid:text};
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
        	<input type="text" style="width:200px" class="query-box-text" id="eletid" name="eletid" value="请输入网元id" onkeypress="doQueryFast(event);">
            <input type="button" class="query-box-magnifier" onclick="doQuery();">
        </div>
    </div>
   
    <!-- 表格栏  -->
    <fsdp:grid className="table" id="datagrid" url="${ctx }/finedo/znywnetelement/query" selecttype="${param.selecttype }">
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
		<fsdp:field code="remark" name="备注" width="100"></fsdp:field>
	</fsdp:grid>
</div>

</body>
</html>

<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="/common/taglibs2.jsp"%>

<!doctype html>
<html>
<head>
${style_css } 
${jquery_js } 
${finedo_core_js } 
${finedo_commonui_js }
${finedo_dialog_js } 
${finedo_date_js } 
${finedo_upload_js }

<link rel="stylesheet" type="text/css" href="../resource/css/style.css">

<script>
	/************** 卡片显示与隐藏  ****************/
	$(document).ready(function() {
		$('#common_add_card').click(function(e) {
			$('#common_add_div').css('display', 'block');
			$('#common_add_card').attr('class', 'add-link-li');

			$('#excel_add_div').css('display', 'none');
			$('#excel_add_card').removeClass();
			finedo.getFileControl('uploaddiv').reset(false);
		});

		$('#excel_add_card').click(function(e) {
			$('#common_add_div').css('display', 'none');
			$('#common_add_card').removeClass();

			$('#excel_add_div').css('display', 'block');
			$('#excel_add_card').attr('class', 'add-link-li');
		});

		var options = {
			type : "single", /*single:单选, multi:多选*/
			data : [ {
				"code" : "合肥",
				"value" : "合肥"
			}, {
				"code" : "芜湖",
				"value" : "芜湖"
			}, {
				"code" : "蚌埠",
				"value" : "蚌埠"
			} , {
				"code" : "安庆",
				"value" : "安庆"
			} , {
				"code" : "黄山",
				"value" : "黄山"
			} , {
				"code" : "淮南",
				"value" : "淮南"
			} , {
				"code" : "淮北",
				"value" : "淮北"
			} , {
				"code" : "阜阳",
				"value" : "阜阳"
			} , {
				"code" : "铜陵",
				"value" : "铜陵"
			} , {
				"code" : "六安",
				"value" : "六安"
			} , {
				"code" : "池州",
				"value" : "池州"
			} , {
				"code" : "亳州",
				"value" : "亳州"
			} , {
				"code" : "宿州",
				"value" : "宿州"
			} , {
				"code" : "滁州",
				"value" : "滁州"
			} , {
				"code" : "宣城",
				"value" : "宣城"
			} , {
				"code" : "马鞍山",
				"value" : "马鞍山"
			} ]
		
		};
		var selectcontrol1 = finedo.getselect("covercity", options);
		
		var para = {};
		finedo.message.showWaiting();
		$.ajax({
	        "url": "/ahcnams_webapp/finedo/znywfactoryinfo/query",
	        "type": "post",
	        "data": JSON.stringify(para),
	        "contentType": "application/json",
	        "dataType": "json",
	        "success":getFactoryCallback ,
	        "error": function(){
	        	finedo.message.error = '查询失败！';
	        }
		});
		
		$("#port").val("22");
	});

	//获取厂家类型回调函数
	function getFactoryCallback(jsondata){
		finedo.message.hideWaiting();
		console.log(jsondata);
		//alert(jsondata);
		var list = jsondata.rows;
		
		var jsonstr="[]";
		var jsonarray = eval('('+jsonstr+')');
		
		for(var i=0; i<list.length; i++){	
			var arr  =
			{
			    "name" : list[i].facname,
			    "value" : list[i].facname
			}
			jsonarray.push(arr);
		}
		
		var options = {
				type : "single", /*single:单选, multi:多选*/
				data : jsonarray
		};
		
		var selectcontrol = finedo.getselect("factorysel", options);
	};

	// 数据校验
	function checkdata() {
		/**
		 * 	通用数据验证
		 * 	label  		名称
		 *	datatype 	数据类型  string email phone url date datetime time number digits 
		 *	minlength 	最小长度
		 *	maxlength 	最大长度
		 *	required 	是否必填 true/false
		 */
		var result = finedo.validate({
			"elename" : {
				label : "网元名称",
				datatype : "string",
				required : true
			},
			"eleip" : {
				label : "IP地址",
				datatype : "string",
				required : true
			},
			"elefactory" : {
				label : "所属厂家",
				datatype : "string",
				required : true
			},
			"elenet" : {
				label : "局域网",
				datatype : "string",
				required : true
			},
			"eletype" : {
				label : "网元类型",
				datatype : "string",
				required : true
			},
/* 			"creator" : {
				label : "创建者",
				datatype : "string",
				required : true
			},
			"createtime" : {
				label : "创建时间",
				datatype : "string",
				required : true
			}, */
			"city" : {
				label : "所属地市",
				datatype : "string",
				required : true
			},
			"logintype" : {
				label : "登录类型",
				datatype : "string",
				required : true
			},
			"port" : {
				label : "端口",
				datatype : "string",
				required : true
			},
			"username" : {
				label : "用户名",
				datatype : "string",
				required : true
			},
			"password" : {
				label : "密码",
				datatype : "string",
				required : true
			},
/* 			"remark" : {
				label : "备注",
				datatype : "string",
				required : true
			}, */
		});

		//TODO: 自定义数据验证

		return result;
	}

	// 提交
	function donext() {
		if ($('#common_add_div').css('display') == 'block') {
			dosubmit();
		} else {
			doimport();
		}
	}

	//普通新建
	function doclicklogintype(type) {
		
		if (type == "SSH") {
			$("#logintype").val("SSH");
			$("#port").val("22");
			$("#SSH").addClass("a-btn active");
			$("#TELNET").removeClass("a-btn active");
			$("#TELNET").addClass("a-btn");
		} else {
			$("#logintype").val("TELNET");
			$("#port").val("23");
			$("#SSH").removeClass("a-btn active");
			$("#SSH").addClass("a-btn");
			$("#TELNET").addClass("a-btn active");
		}
	}
	
	//网元类型切换点击函数
 	function doclickelementtype(type){
 		if (type == "MME") {
			$("#eletype").val("MME");
			$("#MME").addClass("a-btn active");
			$("#SAEGW").removeClass("a-btn active");
			$("#SAEGW").addClass("a-btn");
			$("#DNS").removeClass("a-btn active");
			$("#DNS").addClass("a-btn");
		} else if(type == "SAEGW") {
			$("#eletype").val("SAEGW");
			$("#MME").removeClass("a-btn active");
			$("#MME").addClass("a-btn");
			$("#SAEGW").addClass("a-btn active");
			$("#DNS").removeClass("a-btn active");
			$("#DNS").addClass("a-btn");
		}else{
			$("#eletype").val("DNS");
			$("#MME").removeClass("a-btn active");
			$("#MME").addClass("a-btn");
			$("#SAEGW").removeClass("a-btn active");
			$("#SAEGW").addClass("a-btn");
			$("#DNS").addClass("a-btn active");
		}
	} 
	
	// 普通新建
	function dosubmit() {
		if(!checkdata()) {
		 	alert("null")
			return;
		} 
		var MMEType = $("#MME").attr("class");
		if (MMEType == "a-btn active") {
			$("#eletype").val("MME");
		}

		var DNSType = $("#DNS").attr("class");
		if (DNSType == "a-btn active") {
			$("#eletype").val("DNS");
		}

		var SAEGWType = $("#SAEGW").attr("class");
		if (SAEGWType == "a-btn active") {
			$("#eletype").val("SAEGW");
		}

		var SSHType = $("#SSH").attr("class");
		if (SSHType == "a-btn active") {
			$("#logintype").val("SSH");
		}

		var TELNETType = $("#TELNET").attr("class");
		if (TELNETType == "a-btn active") {
			$("#logintype").val("TELNET");
		}
		
		$("#city").val(finedo.getselect("covercity").getvalue());
		alert(finedo.getselect("factorysel").getvalue());
		$("#elefactory").val(finedo.getselect("factorysel").getvalue());
		

		var form = $("#ajaxForm");
		var options = {
			url : form.attr("action"),
			success : submitcallback,
			type : 'POST',
			dataType : "json",
			clearForm : true
		};
		finedo.message.showWaiting();
		form.ajaxSubmit(options);

		/* 	var action = '${ctx }/finedo/znywnetelement/add';
		 finedo.message.showWaiting();
		 finedo.action.doJsonRequest(action, netelement, function(jsondata){
		 finedo.message.hideWaiting();
		 if(jsondata.resultcode != finedo.resultcode.success){
		 finedo.message.error(jsondata.resultdesc);
		 return;
		 }
		 finedo.message.tip(jsondata.resultdesc, function(){
		 finedo.dialog.closeAll();
		 });
		 }); */
	}

	function submitcallback(jsondata) {
		finedo.message.hideWaiting();
		finedo.message.info(jsondata.resultdesc, "新建信息");
	}

	//批量导入
	function doimport() {
		var fileControl = finedo.getFileControl('uploaddiv');
		var filearr = fileControl.getFileList();
		if (filearr.length != 1) {
			finedo.message.error("未上传Excel文件");
			return;
		}
		$('#fileid').val(filearr[0].fileid);

		var form = $("#importForm");
		var options = {
			url : form.attr("action"),
			success : importcallback,
			type : 'POST',
			dataType : "json",
			clearForm : false
		};
		finedo.message.showWaiting();
		form.ajaxSubmit(options);
	}

	function importcallback(jsondata) {
		finedo.message.hideWaiting();

		$('#importresultdiv').css('display', 'block');
		var resulthtml = "<li><span class='finedo-label-title'>导入情况</span><span class='finedo-label-info'><font color=red>"
				+ jsondata.resultdesc
				+ "   总记录数: "
				+ jsondata.object.rowcount
				+ " &nbsp;&nbsp; 成功记录数: "
				+ jsondata.object.successcount
				+ "&nbsp;&nbsp; 失败记录数: "
				+ jsondata.object.failcount
				+ "</font></span></li>";
		var faillist = eval(jsondata.object.faillist);
		for ( var i = 0; i < faillist.length; i++) {
			resulthtml += "<li><span class='finedo-label-title'>失败明细</span><span class='finedo-label-info'>"
					+ faillist[i].resultdesc + "</span></li>";
		}

		$('#importresultul').html(resulthtml);
	}
</script>
</head>

<body>
	<div class="html-body">
		<div class="container">
			<form method="post" action="${ctx }/finedo/znywnetelement/add"
				id="ajaxForm" name="ajaxForm">
				<input id="elefactory" name="elefactory" type="hidden" value="非度" />
				<input id="eletype" name="eletype" type="hidden" value="" /> 
				<input id="city" name="city" type="hidden" value="合肥" /> 
				<input id="logintype" name="logintype" type="hidden" value="SSH" />
				<div class="register-software-body">
					<div class="register-software-title">
						<img src="../resource/images/cd.png"> <span>添加网元</span>
					</div>
					<div class="register-body" style="padding-bottom: 5px;">
						<table cellpadding="0" cellspacing="0" class="addtable">
							<tbody>
								<tr>
									<td class="td-title">网元类型：</td>
									<td class="td-body">
										<ul class="select-tab">
											<li><a class="a-btn active" id="MME" onclick="doclickelementtype('MME')">MME</a></li>
											<li><a class="a-btn" id="SAEGW" onclick="doclickelementtype('SAEGW')">SAEGW</a></li>
											<li><a class="a-btn" id="DNS" onclick="doclickelementtype('DNS')">DNS</a></li>
										</ul>
									</td>
								</tr>
								<tr>
									<td class="td-title"><span>*</span>网元名称：</td>
									<td class="td-body"><fsdp:text id="elename" hint="请输入网元名称" /></td>
								</tr>
								<tr>
									<td class="td-title">厂家：</td>
									<td class="td-body"><input type="text" id="factorysel"></input></td>
								</tr>
								<tr>
									<td class="td-title"><span>*</span>所属地市：</td>
									<td class="td-body"><input type="text" id="covercity"></input></td>
								</tr>
								<tr>
									<td class="td-title"><span>*</span>IP：</td>
									<td class="td-body"><fsdp:text id="eleip" hint="请输入IP地址" /></td>
								</tr>
								<tr>
									<td class="td-title"><span>*</span>局域网：</td>
									<td class="td-body"><fsdp:text id="elenet" hint="请输入局域网地址" /></td>
								</tr>
							</tbody>
						</table>
						<table cellpadding="0" cellspacing="0" class="addtable">
							<tbody>
								<tr>
									<td class="td-title">登录方式：</td>
									<td class="td-body">
										<ul class="select-tab">
											<li><a class="a-btn active" id="SSH"
												onclick="doclicklogintype('SSH')">SSH</a></li>
											<li><a class="a-btn" id="TELNET"
												onclick="doclicklogintype('TELNET')">TELNET</a></li>
										</ul>
									</td>
								</tr>
								<tr>
									<td class="td-title"><span>*</span>端口：</td>
									<td class="td-body"><fsdp:text id="port" hint="请输入端口号" /></td>
								</tr>

								<tr>
									<td class="td-title"><span>*</span>用户名：</td>
									<td class="td-body"><fsdp:text id="username" hint="请输入用户名" /></td>
								</tr>
								<tr>
									<td class="td-title"><span>*</span>密码：</td>
									<td class="td-body"><fsdp:text id="password" hint="请输入密码" /><!-- <input id="password" class="finedo-text" type="password" hint="请输入密码" /> --><input type="button" class="btn1" value="连通性测试"></td>
								</tr>
								<tr>
									<td class="td-title" style="vertical-align: top">备注：</td>
									<td class="td-body"><textarea id="remark"></textarea></td>
								</tr>
							</tbody>
						</table>
					</div>

				</div>
				<div class="submit-btn-body">
					<input type="button" class="btn1" value="保存" onclick="dosubmit();">
				</div>
			</form>
		</div>
	</div>
</body>
</html>

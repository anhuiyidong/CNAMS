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
${finedo_date_js }

<link rel="stylesheet" type="text/css" href="../resource/css/style.css">

<script>
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
	var result=finedo.validate({
		"elename":{label:"网元名称", datatype:"string", required:true},
		"eleip":{label:"IP地址", datatype:"string", required:true},
		"elefactory":{label:"所属厂家", datatype:"string", required:true},
		"elenet":{label:"局域网", datatype:"string", required:true},
		"eletype":{label:"网元类型", datatype:"string", required:true},
		"creator":{label:"创建者", datatype:"string", required:true},
		"createtime":{label:"创建时间", datatype:"string", required:true},
		"city":{label:"所属地市", datatype:"string", required:true},
		"logintype":{label:"登录类型", datatype:"string", required:true},
		"port":{label:"端口", datatype:"string", required:true},
		"username":{label:"用户名", datatype:"string", required:true},
		"password":{label:"密码", datatype:"string", required:true},
		"remark":{label:"备注", datatype:"string", required:true},
	});
	
   	//TODO: 自定义数据验证
	

	return result;
}

// 提交
function donext() {
	dosubmit();
}

function dosubmit() {
	if(!checkdata()) 
		return;

	var form = $("#ajaxForm");
	var options = {     
        url:	   form.attr("action"),
        success:   submitcallback,
        type:      'POST',
        dataType:  "json",
	    clearForm: true
    };
	finedo.message.showWaiting();
	form.ajaxSubmit(options);
}

function submitcallback(jsondata){
	finedo.message.hideWaiting();
	finedo.message.info(jsondata.resultdesc, "修改信息");
}
</script>
</head>

<body>
	<div class="html-body">
	    <div class="container">
	        <div class="register-software-body">
	            <div class="register-software-title">
	                <img src="images/cd.png">
	                <span>添加网元</span>
	            </div>
	            <div class="register-body" style=" padding-bottom: 5px;">
	                <table cellpadding="0" cellspacing="0" class="addtable">
	                    <tbody>
	                    <tr>
	                        <td class="td-title">网元类型：</td>
	                        <td class="td-body">
	                            <ul class="select-tab">
	                                <li><a class="a-btn active">MME</a></li>
	                                <li><a class="a-btn">SAEGW</a></li>
	                                <li><a class="a-btn">DNS</a></li>
	                            </ul>
	                        </td>
	                    </tr>
	                    <tr>
	                        <td class="td-title"><span>*</span>网元名称：</td>
	                        <td class="td-body"><input type="text" placeholder="请输入网元名称" /></td>
	                    </tr>
	                    <tr>
	                        <td class="td-title">厂家：</td>
	                        <td class="td-body">
	                            <ul class="select-tab">
	                                <li><a class="a-btn active">非度</a></li>
	                                <li><a class="a-btn">炎黄</a></li>
	                                <li><a class="a-btn">国创</a></li>
	                                <li><a class="a-btn">思特奇</a></li>
	                            </ul>
	                        </td>
	                    </tr>
	                    <tr>
	                        <td class="td-title"><span>*</span>所属地市：</td>
	                        <td class="td-body"><select><option>合肥</option></select></td>
	                    </tr>
	                    <tr>
	                        <td class="td-title"><span>*</span>IP：</td>
	                        <td class="td-body"><input type="text" placeholder="请输入IP地址" /></td>
	                    </tr>
	                    </tbody>
	                </table>
	                <table cellpadding="0" cellspacing="0" class="addtable">
	                    <tbody>
	                    <tr>
	                        <td class="td-title">登录方式：</td>
	                        <td class="td-body">
	                            <ul class="select-tab">
	                                <li><a class="a-btn active">SSH</a></li>
	                                <li><a class="a-btn">TELENT</a></li>
	                            </ul>
	                        </td>
	                    </tr>
	                    <tr>
	                        <td class="td-title"><span>*</span>端口：</td>
	                        <td class="td-body"><input type="text" placeholder="请输入端口号" /></td>
	                    </tr>
	                    
	                    <tr>
	                        <td class="td-title"><span>*</span>用户名：</td>
	                        <td class="td-body"><input type="text" placeholder="请输入用户名" /></td>
	                    </tr>
	                    <tr>
	                        <td class="td-title"><span>*</span>密码：</td>
	                        <td class="td-body"><input type="text" placeholder="请输入密码" /><input type="button" class="btn1" value="连通性测试"></td>
	                    </tr>
	                    <tr>
	                        <td class="td-title" style="vertical-align: top">备注：</td>
	                        <td class="td-body"><textarea></textarea></td>
	                    </tr>
	                    </tbody>
	                </table>
	            </div>
	        </div>
	        <div class="submit-btn-body">
	            <input type="button" class="btn1" value="保存">
	        </div>
	    </div>
	</div>
</body>
</html>
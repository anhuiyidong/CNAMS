finedo.choose = {
	/**
	 * 组织机构单选
	 */
	singleOrg:function(ccallback){
		finedo.dialog.show({'selecttype':'single',
			width:300,
			'title':'双击选择',
			'loadtype':'iframe',
			'url':ctx+'/fsdp/org/choose.jsp?selecttype=single',
			callback:function(data){
				if($.isFunction(ccallback))
					ccallback(data);
			}
		});
	}
	
	/**
	 * 组织机构多选
	 */
	,multiOrg:function(ccallback){
		finedo.dialog.show({'selecttype':'multi',
			width:300,
			'title':'组织机构选择',
			'loadtype':'iframe',
			'url':ctx+'/fsdp/org/choose.jsp?selecttype=multi',
			callback:function(data){
				if($.isFunction(ccallback))
					ccallback(data);
			}
		});
	}
	
	/**
	 * 单选角色
	 */
	,singleRole:function(ccallback){
		finedo.dialog.show({'selecttype':'single',
			width:1000,height:600,
			'title':'选择岗位角色（双击表格行）',
			'loadtype':'iframe',
			'url':ctx+'/fsdp/role/choose.jsp?selecttype=single',
			callback:function(data){
				if($.isFunction(ccallback))
					ccallback(data);
			}
		});
	}
	
	/**
	 * 多选角色
	 */
	,multiRole:function(ccallback){
		finedo.dialog.show({'selecttype':'multi',
			width:1000,height:600,
			'title':'选择角色',
			'loadtype':'iframe',
			'url':ctx+'/fsdp/role/choose.jsp?selecttype=multi',
			callback:function(data){
				if($.isFunction(ccallback))
					ccallback(data);
			}
		});
	}

	/**
	 * 单选用户
	 */
	,singleUser:function(ccallback){
		finedo.dialog.show({'selecttype':'single',
			width:900,height:600,
			'title':'选择用户（双击表格行）',
			'loadtype':'iframe',
			'url':ctx+'/fsdp/user/choose.jsp?selecttype=single',
			callback:function(data){
				if($.isFunction(ccallback))
					ccallback(data);
			}
		});
	}

	/**
	 * 多选用户
	 */
	,multiUser:function(ccallback){
		finedo.dialog.show({'selecttype':'multi',
			width:1000,height:600,
			'title':'选择用户',
			'loadtype':'iframe',
			'url':ctx+'/fsdp/user/choose.jsp?selecttype=multi',
			callback:function(data){
				if($.isFunction(ccallback))
					ccallback(data);
			}
		});
	}

		
	/**
	 * 单选网元信息
	 */
	,singleZnywnetelement:function(ccallback){
		finedo.dialog.show({'selecttype':'single',
			width:900,height:600,
			'title':'选择网元信息（双击表格行）',
			'loadtype':'iframe',
			'url':ctx+'/ahcnams/znywnetelement/choose.jsp?selecttype=single',
			callback:function(data){
				if($.isFunction(ccallback))
					ccallback(data);
			}
		});
	}
	
	/**
	 * 多选网元信息
	 */
	,multiZnywnetelement:function(ccallback){
		finedo.dialog.show({'selecttype':'multi',
			width:1000,height:600,
			'title':'选择网元信息',
			'loadtype':'iframe',
			'url':ctx+'/ahcnams/znywnetelement/choose.jsp?selecttype=multi',
			callback:function(data){
				if($.isFunction(ccallback))
					ccallback(data);
			}
		});
	}
	
	
	/**
	 * 单选模板
	 */
	,singleZnywtemplate:function(ccallback){
		finedo.dialog.show({'selecttype':'single',
			width:900,height:600,
			'title':'选择模板（双击表格行）',
			'loadtype':'iframe',
			'url':ctx+'/ahcnams/znywtemplate/choose.jsp?selecttype=single',
			callback:function(data){
				if($.isFunction(ccallback))
					ccallback(data);
			}
		});
	}
	
	/**
	 * 多选模板
	 */
	,multiZnywtemplate:function(ccallback){
		finedo.dialog.show({'selecttype':'multi',
			width:1000,height:600,
			'title':'选择模板',
			'loadtype':'iframe',
			'url':ctx+'/ahcnams/znywtemplate/choose.jsp?selecttype=multi',
			callback:function(data){
				if($.isFunction(ccallback))
					ccallback(data);
			}
		});
	}
	
	
	/**
	 * 单选模板步骤
	 */
	,singleZnywtemplatestep:function(ccallback){
		finedo.dialog.show({'selecttype':'single',
			width:900,height:600,
			'title':'选择模板步骤（双击表格行）',
			'loadtype':'iframe',
			'url':ctx+'/ahcnams/znywtemplatestep/choose.jsp?selecttype=single',
			callback:function(data){
				if($.isFunction(ccallback))
					ccallback(data);
			}
		});
	}
	
	/**
	 * 多选模板步骤
	 */
	,multiZnywtemplatestep:function(ccallback){
		finedo.dialog.show({'selecttype':'multi',
			width:1000,height:600,
			'title':'选择模板步骤',
			'loadtype':'iframe',
			'url':ctx+'/ahcnams/znywtemplatestep/choose.jsp?selecttype=multi',
			callback:function(data){
				if($.isFunction(ccallback))
					ccallback(data);
			}
		});
	}
	
	
	/**
	 * 单选网元和模板步骤对应关系
	 */
	,singleZnywelementsteprel:function(ccallback){
		finedo.dialog.show({'selecttype':'single',
			width:900,height:600,
			'title':'选择网元和模板步骤对应关系（双击表格行）',
			'loadtype':'iframe',
			'url':ctx+'/ahcnams/znywelementsteprel/choose.jsp?selecttype=single',
			callback:function(data){
				if($.isFunction(ccallback))
					ccallback(data);
			}
		});
	}
	
	/**
	 * 多选网元和模板步骤对应关系
	 */
	,multiZnywelementsteprel:function(ccallback){
		finedo.dialog.show({'selecttype':'multi',
			width:1000,height:600,
			'title':'选择网元和模板步骤对应关系',
			'loadtype':'iframe',
			'url':ctx+'/ahcnams/znywelementsteprel/choose.jsp?selecttype=multi',
			callback:function(data){
				if($.isFunction(ccallback))
					ccallback(data);
			}
		});
	}
	
	
	/**
	 * 单选模板和步骤关系
	 */
	,singleZnywtemplatesteprel:function(ccallback){
		finedo.dialog.show({'selecttype':'single',
			width:900,height:600,
			'title':'选择模板和步骤关系（双击表格行）',
			'loadtype':'iframe',
			'url':ctx+'/ahcnams/znywtemplatesteprel/choose.jsp?selecttype=single',
			callback:function(data){
				if($.isFunction(ccallback))
					ccallback(data);
			}
		});
	}
	
	/**
	 * 多选模板和步骤关系
	 */
	,multiZnywtemplatesteprel:function(ccallback){
		finedo.dialog.show({'selecttype':'multi',
			width:1000,height:600,
			'title':'选择模板和步骤关系',
			'loadtype':'iframe',
			'url':ctx+'/ahcnams/znywtemplatesteprel/choose.jsp?selecttype=multi',
			callback:function(data){
				if($.isFunction(ccallback))
					ccallback(data);
			}
		});
	}
	
	
	/**
	 * 单选任务
	 */
	,singleZnywtask:function(ccallback){
		finedo.dialog.show({'selecttype':'single',
			width:900,height:600,
			'title':'选择任务（双击表格行）',
			'loadtype':'iframe',
			'url':ctx+'/ahcnams/znywtask/choose.jsp?selecttype=single',
			callback:function(data){
				if($.isFunction(ccallback))
					ccallback(data);
			}
		});
	}
	
	/**
	 * 多选任务
	 */
	,multiZnywtask:function(ccallback){
		finedo.dialog.show({'selecttype':'multi',
			width:1000,height:600,
			'title':'选择任务',
			'loadtype':'iframe',
			'url':ctx+'/ahcnams/znywtask/choose.jsp?selecttype=multi',
			callback:function(data){
				if($.isFunction(ccallback))
					ccallback(data);
			}
		});
	}
	
	
	/**
	 * 单选操作记录
	 */
	,singleZnywoptrecord:function(ccallback){
		finedo.dialog.show({'selecttype':'single',
			width:900,height:600,
			'title':'选择操作记录（双击表格行）',
			'loadtype':'iframe',
			'url':ctx+'/ahcnams/znywoptrecord/choose.jsp?selecttype=single',
			callback:function(data){
				if($.isFunction(ccallback))
					ccallback(data);
			}
		});
	}
	
	/**
	 * 多选操作记录
	 */
	,multiZnywoptrecord:function(ccallback){
		finedo.dialog.show({'selecttype':'multi',
			width:1000,height:600,
			'title':'选择操作记录',
			'loadtype':'iframe',
			'url':ctx+'/ahcnams/znywoptrecord/choose.jsp?selecttype=multi',
			callback:function(data){
				if($.isFunction(ccallback))
					ccallback(data);
			}
		});
	}
	
	
};

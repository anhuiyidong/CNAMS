/**
 * 网元信息管理服务接口
 *
 * @version 1.0
 * @since 2018-11-20
 */
package cn.finedo.ahcnams.service.znywnetelement;

import java.io.File;
import java.util.List;
import java.util.ArrayList;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import cn.finedo.common.annotation.Proxy;
import cn.finedo.common.date.DateUtil;
import cn.finedo.common.domain.FileImportResultDomain;
import cn.finedo.common.domain.PageDomain;
import cn.finedo.common.domain.PageParamDomain;
import cn.finedo.common.domain.ResultDomain;
import cn.finedo.common.domain.ReturnValueDomain;
import cn.finedo.common.pojo.SysEntityfile;
import cn.finedo.common.protocol.JsonUtil;
import cn.finedo.common.valid.DataType;
import cn.finedo.common.valid.ValidateItem;
import cn.finedo.common.valid.ValidateUtil;
import cn.finedo.fsdp.server.framework.ServerFeature;
import cn.finedo.fsdp.service.common.excel.ExcelUtil;
import cn.finedo.fsdp.service.common.excel.HeaderDomain;
import cn.finedo.fsdp.service.common.configure.ConfigureUtil;
import cn.finedo.fsdp.service.file.FileServiceAPProxy;
import cn.finedo.ahcnams.pojo.Znywnetelement;
import cn.finedo.ahcnams.service.znywnetelement.domain.ZnywnetelementListDomain;
import cn.finedo.ahcnams.service.znywnetelement.domain.ZnywnetelementQueryDomain;

@Controller
@Scope("singleton")
@RequestMapping("service/finedo/znywnetelement")
public class ZnywnetelementServiceAP {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private ZnywnetelementService znywnetelementservice;
	
	/**
	 * 网元信息查询
	 * @param ZnywnetelementQueryDomain
	 * @return ReturnValueDomain<Znywnetelement>对象
	 */
	@Proxy(method="query", inarg="ZnywnetelementQueryDomain", outarg="ReturnValueDomain<PageDomain<Znywnetelement>>")
	@ResponseBody
	@RequestMapping("/query")
	public ReturnValueDomain<PageDomain<Znywnetelement>> query(HttpServletRequest request) {
		ReturnValueDomain<PageDomain<Znywnetelement>> ret = new ReturnValueDomain<PageDomain<Znywnetelement>>();
		ZnywnetelementQueryDomain znywnetelementquerydomain = null;
		 
		try {
			znywnetelementquerydomain = JsonUtil.request2Domain(request, ZnywnetelementQueryDomain.class);
		}catch(Exception e) {
			logger.error("请求参数解析异常", e);
			return ret.setFail("请求参数解析异常");
		}
		
		return znywnetelementservice.query(znywnetelementquerydomain);
	}
	 
	/**
	 * 网元信息新增
	 * 
	 * @param ZnywnetelementListDomain
	 * @return ReturnValueDomain<String>
	 */
	@Proxy(method="add", inarg="ZnywnetelementListDomain", outarg="ReturnValueDomain<String>")
	@ResponseBody
	@RequestMapping("/add")
	public ReturnValueDomain<String> add(HttpServletRequest request) {
		ReturnValueDomain<String> ret = new ReturnValueDomain<String>();
		ZnywnetelementListDomain znywnetelementlistdomain = null;
		 
		try {
			znywnetelementlistdomain = JsonUtil.request2Domain(request, ZnywnetelementListDomain.class);
		}catch(Exception e) {
			logger.error("请求参数解析异常", e);
			return ret.setFail("请求参数解析异常");
		}
	
		List<Znywnetelement> znywnetelementlist= znywnetelementlistdomain.getZnywnetelementlist();
		
		List<ValidateItem> items = new ArrayList<ValidateItem>();
		items.add(new ValidateItem("elename", "网元名称", false, DataType.STRING));
		items.add(new ValidateItem("eleip", "IP地址", false, DataType.STRING));
		items.add(new ValidateItem("elefactory", "所属厂家", false, DataType.STRING));
		items.add(new ValidateItem("elenet", "局域网", false, DataType.STRING));
		items.add(new ValidateItem("eletype", "网元类型", false, DataType.STRING));
		items.add(new ValidateItem("creator", "创建者", false, DataType.STRING));
		items.add(new ValidateItem("createtime", "创建时间", false, DataType.STRING));
		items.add(new ValidateItem("city", "所属地市", false, DataType.STRING));
		items.add(new ValidateItem("logintype", "登录类型", false, DataType.STRING));
		items.add(new ValidateItem("port", "端口", false, DataType.STRING));
		items.add(new ValidateItem("username", "用户名", false, DataType.STRING));
		items.add(new ValidateItem("password", "密码", false, DataType.STRING));
		items.add(new ValidateItem("remark", "备注", false, DataType.STRING));
		ReturnValueDomain<String> validret = ValidateUtil.checkForList(znywnetelementlist, items);
		if (validret.hasFail()) {
			return validret;
		}

		return znywnetelementservice.add(znywnetelementlistdomain);
	 }

	/**
	 * 网元信息修改
	 * @param ZnywnetelementListDomain
	 * @return ReturnValueDomain<String>对象
	 */
	@Proxy(method="update", inarg="ZnywnetelementListDomain", outarg="ReturnValueDomain<String>")
	@ResponseBody
	@RequestMapping("/update")
	public ReturnValueDomain<String> update(HttpServletRequest request) {
		ReturnValueDomain<String> ret = new ReturnValueDomain<String>();
		ZnywnetelementListDomain znywnetelementlistdomain = null;
		  
		try {
			znywnetelementlistdomain = JsonUtil.request2Domain(request, ZnywnetelementListDomain.class);
		}catch(Exception e) {
			logger.error("请求参数解析异常", e);
			return ret.setFail("请求参数解析异常");
		}

		List<Znywnetelement> znywnetelementlist= znywnetelementlistdomain.getZnywnetelementlist();
		
		List<ValidateItem> items = new ArrayList<ValidateItem>();
		items.add(new ValidateItem("eletid", "网元id", true, DataType.STRING));
		items.add(new ValidateItem("elename", "网元名称", false, DataType.STRING));
		items.add(new ValidateItem("eleip", "IP地址", false, DataType.STRING));
		items.add(new ValidateItem("elefactory", "所属厂家", false, DataType.STRING));
		items.add(new ValidateItem("elenet", "局域网", false, DataType.STRING));
		items.add(new ValidateItem("eletype", "网元类型", false, DataType.STRING));
		items.add(new ValidateItem("creator", "创建者", false, DataType.STRING));
		items.add(new ValidateItem("createtime", "创建时间", false, DataType.STRING));
		items.add(new ValidateItem("city", "所属地市", false, DataType.STRING));
		items.add(new ValidateItem("logintype", "登录类型", false, DataType.STRING));
		items.add(new ValidateItem("port", "端口", false, DataType.STRING));
		items.add(new ValidateItem("username", "用户名", false, DataType.STRING));
		items.add(new ValidateItem("password", "密码", false, DataType.STRING));
		items.add(new ValidateItem("remark", "备注", false, DataType.STRING));
		ReturnValueDomain<String> validret = ValidateUtil.checkForList(znywnetelementlist, items);
		if (validret.hasFail()) {
			return validret;
		}

		return znywnetelementservice.update(znywnetelementlistdomain);
	}
	
	/**
	 * 网元信息删除
	 * 
	 * @param ZnywnetelementListDomain
	 * @return ReturnValueDomain<String>
	 */
	@Proxy(method="delete", inarg="ZnywnetelementListDomain", outarg="ReturnValueDomain<String>")
	@ResponseBody
	@RequestMapping("/delete")
	public ReturnValueDomain<String> delete(HttpServletRequest request) {
		ReturnValueDomain<String> ret = new ReturnValueDomain<String>();
		ZnywnetelementListDomain znywnetelementlistdomain = null;
		
		try {
			znywnetelementlistdomain = JsonUtil.request2Domain(request, ZnywnetelementListDomain.class);
		}catch(Exception e) {
			logger.error("请求参数解析异常", e);
			return ret.setFail("请求参数解析异常");
		}
		
		List<Znywnetelement> znywnetelementlist= znywnetelementlistdomain.getZnywnetelementlist();
		
		List<ValidateItem> items = new ArrayList<ValidateItem>();
		items.add(new ValidateItem("eletid", "网元id", false, DataType.STRING));
		ReturnValueDomain<String> validret = ValidateUtil.checkForList(znywnetelementlist, items);
		if (validret.hasFail()) {
			return validret;
		}
		
		return znywnetelementservice.delete(znywnetelementlistdomain);
	}
	
	/**
	 * 批量导入网元信息
	 * 
	 * @param SysEntityfile
	 * @return ReturnValueDomain<FileImportResultDomain>对象
	 */
	@Proxy(method="importexcel", inarg="SysEntityfile", outarg="ReturnValueDomain<FileImportResultDomain>")
	@ResponseBody
	@RequestMapping(value="/importexcel")
	public ReturnValueDomain<FileImportResultDomain> importexcel(HttpServletRequest request) {
		ReturnValueDomain<FileImportResultDomain> ret=new ReturnValueDomain<FileImportResultDomain>();
		
		SysEntityfile entityfile = null;
		try {
			entityfile = JsonUtil.request2Domain(request, SysEntityfile.class);
		}catch(Exception e) {
			logger.error("请求参数解析异常", e);
			return ret.setFail("请求参数解析异常");
		}

		ReturnValueDomain<SysEntityfile> queryfileret=FileServiceAPProxy.queryById(entityfile);
		entityfile=queryfileret.getObject();
		
		String uploadpath = ConfigureUtil.getParamByName("系统基本参数", "上传路径");
		String filename=uploadpath + File.separator + entityfile.getFilepath() + File.separator + entityfile.getFileid() + entityfile.getFiletype();
				
		// 总记录数
		int rowcount=0;
		// 成功记录数 
		int successcount=0;
		// 失败明细
		List<ResultDomain> faillist=new ArrayList<ResultDomain>();
		List<Znywnetelement> datalist;
		try {
			List<HeaderDomain> headerlist=new ArrayList<HeaderDomain>();
			
			headerlist.add(new HeaderDomain("0", "elename", "网元名称"));
			headerlist.add(new HeaderDomain("1", "eleip", "IP地址"));
			headerlist.add(new HeaderDomain("2", "elefactory", "所属厂家"));
			headerlist.add(new HeaderDomain("3", "elenet", "局域网"));
			headerlist.add(new HeaderDomain("4", "eletype", "网元类型"));
			headerlist.add(new HeaderDomain("5", "creator", "创建者"));
			headerlist.add(new HeaderDomain("6", "createtime", "创建时间"));
			headerlist.add(new HeaderDomain("7", "city", "所属地市"));
			headerlist.add(new HeaderDomain("8", "logintype", "登录类型"));
			headerlist.add(new HeaderDomain("9", "port", "端口"));
			headerlist.add(new HeaderDomain("10", "username", "用户名"));
			headerlist.add(new HeaderDomain("11", "password", "密码"));
			headerlist.add(new HeaderDomain("12", "remark", "备注"));
			
			datalist=ExcelUtil.readExcel(filename, headerlist, Znywnetelement.class);
			rowcount=datalist.size();
			
			// 合法性校验
			List<ValidateItem> items = new ArrayList<ValidateItem>();
			items.add(new ValidateItem("elename", "网元名称", false, DataType.STRING));
			items.add(new ValidateItem("eleip", "IP地址", false, DataType.STRING));
			items.add(new ValidateItem("elefactory", "所属厂家", false, DataType.STRING));
			items.add(new ValidateItem("elenet", "局域网", false, DataType.STRING));
			items.add(new ValidateItem("eletype", "网元类型", false, DataType.STRING));
			items.add(new ValidateItem("creator", "创建者", false, DataType.STRING));
			items.add(new ValidateItem("createtime", "创建时间", false, DataType.STRING));
			items.add(new ValidateItem("city", "所属地市", false, DataType.STRING));
			items.add(new ValidateItem("logintype", "登录类型", false, DataType.STRING));
			items.add(new ValidateItem("port", "端口", false, DataType.STRING));
			items.add(new ValidateItem("username", "用户名", false, DataType.STRING));
			items.add(new ValidateItem("password", "密码", false, DataType.STRING));
			items.add(new ValidateItem("remark", "备注", false, DataType.STRING));
			
			ReturnValueDomain<String> validret = ValidateUtil.checkForList(datalist, items);
			int failindex=0;
			for(ResultDomain rd : validret.getResultlist()) {
				rd.setResultdesc("[行号:" + failindex + 2 + "]" + rd.getResultdesc());
				faillist.add(rd);
				
				failindex++;
			}
			successcount=rowcount - failindex;
		}catch(Exception e) {
			logger.error("导入失败", e);
			return ret.setFail("导入失败");
		}
		
		if(successcount != rowcount) {
			FileImportResultDomain importresult=new FileImportResultDomain();
			importresult.setRowcount(rowcount);
			importresult.setSuccesscount(successcount);
			importresult.setFailcount(rowcount-successcount);
			importresult.setFaillist(faillist);
						
			return ret.setFail("导入数据合法性校验不通过", importresult);
		}
		
		ZnywnetelementListDomain znywnetelementlistdomain=new ZnywnetelementListDomain();
		znywnetelementlistdomain.setZnywnetelementlist(datalist);
		ReturnValueDomain<String> oneret= znywnetelementservice.add(znywnetelementlistdomain);
		if(oneret.hasFail()) {
			return ret.setFail("导入失败:" + oneret.getResultdesc());
		}
	
		FileImportResultDomain importresult=new FileImportResultDomain();
		importresult.setRowcount(rowcount);
		importresult.setSuccesscount(successcount);
		importresult.setFailcount(rowcount-successcount);
		importresult.setFaillist(faillist);
		
		return ret.setSuccess("导入成功", importresult);
	}
	
	 /**
	  * 批量导出用户信息
	  * 
	  * @param ZnywnetelementQueryDomain
	  * @return ReturnValueDomain<SysEntityfile>对象
	  */
	@Proxy(method="exportexcel", inarg="ZnywnetelementQueryDomain", outarg="ReturnValueDomain<SysEntityfile>")
	@ResponseBody
	@RequestMapping("/exportexcel")
	public ReturnValueDomain<SysEntityfile> exportexcel(HttpServletRequest request) {
		ReturnValueDomain<SysEntityfile> ret=new ReturnValueDomain<SysEntityfile>();
		
		ZnywnetelementQueryDomain znywnetelementquerydomain = null;
		try {
			znywnetelementquerydomain = JsonUtil.request2Domain(request, ZnywnetelementQueryDomain.class);
		}catch(Exception e) {
			logger.error("请求参数解析异常", e);
			return ret.setFail("请求参数解析异常");
		}
		
		PageParamDomain pageparam=new PageParamDomain();
		pageparam.setRownumperpage(ServerFeature.EXPORT_MAXSIZE);
		pageparam.setPageindex(0);
		znywnetelementquerydomain.setPageparam(pageparam);
		
		ReturnValueDomain<PageDomain<Znywnetelement>> queryret = znywnetelementservice.query(znywnetelementquerydomain);
		
		List<Znywnetelement> Znywnetelementlist = queryret.getObject().getDatalist();
		
		List<HeaderDomain> headerlist = new ArrayList<HeaderDomain>();
		headerlist.add(new HeaderDomain("0", "elename", "网元名称"));
		headerlist.add(new HeaderDomain("1", "eleip", "IP地址"));
		headerlist.add(new HeaderDomain("2", "elefactory", "所属厂家"));
		headerlist.add(new HeaderDomain("3", "elenet", "局域网"));
		headerlist.add(new HeaderDomain("4", "eletype", "网元类型"));
		headerlist.add(new HeaderDomain("5", "creator", "创建者"));
		headerlist.add(new HeaderDomain("6", "createtime", "创建时间"));
		headerlist.add(new HeaderDomain("7", "city", "所属地市"));
		headerlist.add(new HeaderDomain("8", "logintype", "登录类型"));
		headerlist.add(new HeaderDomain("9", "port", "端口"));
		headerlist.add(new HeaderDomain("10", "username", "用户名"));
		headerlist.add(new HeaderDomain("11", "password", "密码"));
		headerlist.add(new HeaderDomain("12", "remark", "备注"));
		
		String filepath=ServerFeature.WEBAPP_HOME + File.separator + "download" + File.separator + DateUtil.getNowTime("yyyyMMdd");
		String filename=UUID.randomUUID().toString() + ".xlsx";
				
		try {
			ExcelUtil.writeExcel(filepath + File.separator + filename, headerlist, Znywnetelementlist);
		} catch (Exception e) {
			logger.error("生成excel文件失败", e);
			return ret.setFail("生成excel文件失败:" + e.getMessage());
		}
		
		SysEntityfile entityfile=new SysEntityfile();
		entityfile.setFilename(filename);
		entityfile.setFilepath(filepath);
		return ret.setSuccess("生成excel文件成功", entityfile);
	}
}

/**
 * 任务管理服务接口
 *
 * @version 1.0
 * @since 2018-11-20
 */
package cn.finedo.ahcnams.service.znywtask;

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
import cn.finedo.ahcnams.pojo.Znywtask;
import cn.finedo.ahcnams.service.znywtask.domain.ZnywtaskListDomain;
import cn.finedo.ahcnams.service.znywtask.domain.ZnywtaskQueryDomain;

@Controller
@Scope("singleton")
@RequestMapping("service/finedo/znywtask")
public class ZnywtaskServiceAP {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private ZnywtaskService znywtaskservice;
	
	/**
	 * 任务查询
	 * @param ZnywtaskQueryDomain
	 * @return ReturnValueDomain<Znywtask>对象
	 */
	@Proxy(method="query", inarg="ZnywtaskQueryDomain", outarg="ReturnValueDomain<PageDomain<Znywtask>>")
	@ResponseBody
	@RequestMapping("/query")
	public ReturnValueDomain<PageDomain<Znywtask>> query(HttpServletRequest request) {
		ReturnValueDomain<PageDomain<Znywtask>> ret = new ReturnValueDomain<PageDomain<Znywtask>>();
		ZnywtaskQueryDomain znywtaskquerydomain = null;
		 
		try {
			znywtaskquerydomain = JsonUtil.request2Domain(request, ZnywtaskQueryDomain.class);
		}catch(Exception e) {
			logger.error("请求参数解析异常", e);
			return ret.setFail("请求参数解析异常");
		}
		
		return znywtaskservice.query(znywtaskquerydomain);
	}
	 
	/**
	 * 任务新增
	 * 
	 * @param ZnywtaskListDomain
	 * @return ReturnValueDomain<String>
	 */
	@Proxy(method="add", inarg="ZnywtaskListDomain", outarg="ReturnValueDomain<String>")
	@ResponseBody
	@RequestMapping("/add")
	public ReturnValueDomain<String> add(HttpServletRequest request) {
		ReturnValueDomain<String> ret = new ReturnValueDomain<String>();
		ZnywtaskListDomain znywtasklistdomain = null;
		 
		try {
			znywtasklistdomain = JsonUtil.request2Domain(request, ZnywtaskListDomain.class);
		}catch(Exception e) {
			logger.error("请求参数解析异常", e);
			return ret.setFail("请求参数解析异常");
		}
	
		List<Znywtask> znywtasklist= znywtasklistdomain.getZnywtasklist();
		
		List<ValidateItem> items = new ArrayList<ValidateItem>();
		items.add(new ValidateItem("templateid", "模板id", false, DataType.STRING));
		items.add(new ValidateItem("executor", "执行人", false, DataType.STRING));
		items.add(new ValidateItem("executetime", "执行时间", false, DataType.STRING));
		items.add(new ValidateItem("status", "状态", false, DataType.STRING));
		items.add(new ValidateItem("starttime", "开始时间", false, DataType.STRING));
		items.add(new ValidateItem("expiretime", "结束时间", false, DataType.STRING));
		items.add(new ValidateItem("dept", "部门", false, DataType.STRING));
		items.add(new ValidateItem("remark", "备注", false, DataType.STRING));
		ReturnValueDomain<String> validret = ValidateUtil.checkForList(znywtasklist, items);
		if (validret.hasFail()) {
			return validret;
		}

		return znywtaskservice.add(znywtasklistdomain);
	 }

	/**
	 * 任务修改
	 * @param ZnywtaskListDomain
	 * @return ReturnValueDomain<String>对象
	 */
	@Proxy(method="update", inarg="ZnywtaskListDomain", outarg="ReturnValueDomain<String>")
	@ResponseBody
	@RequestMapping("/update")
	public ReturnValueDomain<String> update(HttpServletRequest request) {
		ReturnValueDomain<String> ret = new ReturnValueDomain<String>();
		ZnywtaskListDomain znywtasklistdomain = null;
		  
		try {
			znywtasklistdomain = JsonUtil.request2Domain(request, ZnywtaskListDomain.class);
		}catch(Exception e) {
			logger.error("请求参数解析异常", e);
			return ret.setFail("请求参数解析异常");
		}

		List<Znywtask> znywtasklist= znywtasklistdomain.getZnywtasklist();
		
		List<ValidateItem> items = new ArrayList<ValidateItem>();
		items.add(new ValidateItem("taskid", "任务id", true, DataType.STRING));
		items.add(new ValidateItem("templateid", "模板id", false, DataType.STRING));
		items.add(new ValidateItem("executor", "执行人", false, DataType.STRING));
		items.add(new ValidateItem("executetime", "执行时间", false, DataType.STRING));
		items.add(new ValidateItem("status", "状态", false, DataType.STRING));
		items.add(new ValidateItem("starttime", "开始时间", false, DataType.STRING));
		items.add(new ValidateItem("expiretime", "结束时间", false, DataType.STRING));
		items.add(new ValidateItem("dept", "部门", false, DataType.STRING));
		items.add(new ValidateItem("remark", "备注", false, DataType.STRING));
		ReturnValueDomain<String> validret = ValidateUtil.checkForList(znywtasklist, items);
		if (validret.hasFail()) {
			return validret;
		}

		return znywtaskservice.update(znywtasklistdomain);
	}
	
	/**
	 * 任务删除
	 * 
	 * @param ZnywtaskListDomain
	 * @return ReturnValueDomain<String>
	 */
	@Proxy(method="delete", inarg="ZnywtaskListDomain", outarg="ReturnValueDomain<String>")
	@ResponseBody
	@RequestMapping("/delete")
	public ReturnValueDomain<String> delete(HttpServletRequest request) {
		ReturnValueDomain<String> ret = new ReturnValueDomain<String>();
		ZnywtaskListDomain znywtasklistdomain = null;
		
		try {
			znywtasklistdomain = JsonUtil.request2Domain(request, ZnywtaskListDomain.class);
		}catch(Exception e) {
			logger.error("请求参数解析异常", e);
			return ret.setFail("请求参数解析异常");
		}
		
		List<Znywtask> znywtasklist= znywtasklistdomain.getZnywtasklist();
		
		List<ValidateItem> items = new ArrayList<ValidateItem>();
		items.add(new ValidateItem("taskid", "任务id", false, DataType.STRING));
		ReturnValueDomain<String> validret = ValidateUtil.checkForList(znywtasklist, items);
		if (validret.hasFail()) {
			return validret;
		}
		
		return znywtaskservice.delete(znywtasklistdomain);
	}
	
	/**
	 * 批量导入任务
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
		List<Znywtask> datalist;
		try {
			List<HeaderDomain> headerlist=new ArrayList<HeaderDomain>();
			
			headerlist.add(new HeaderDomain("0", "templateid", "模板id"));
			headerlist.add(new HeaderDomain("1", "executor", "执行人"));
			headerlist.add(new HeaderDomain("2", "executetime", "执行时间"));
			headerlist.add(new HeaderDomain("3", "status", "状态"));
			headerlist.add(new HeaderDomain("4", "starttime", "开始时间"));
			headerlist.add(new HeaderDomain("5", "expiretime", "结束时间"));
			headerlist.add(new HeaderDomain("6", "dept", "部门"));
			headerlist.add(new HeaderDomain("7", "remark", "备注"));
			
			datalist=ExcelUtil.readExcel(filename, headerlist, Znywtask.class);
			rowcount=datalist.size();
			
			// 合法性校验
			List<ValidateItem> items = new ArrayList<ValidateItem>();
			items.add(new ValidateItem("templateid", "模板id", false, DataType.STRING));
			items.add(new ValidateItem("executor", "执行人", false, DataType.STRING));
			items.add(new ValidateItem("executetime", "执行时间", false, DataType.STRING));
			items.add(new ValidateItem("status", "状态", false, DataType.STRING));
			items.add(new ValidateItem("starttime", "开始时间", false, DataType.STRING));
			items.add(new ValidateItem("expiretime", "结束时间", false, DataType.STRING));
			items.add(new ValidateItem("dept", "部门", false, DataType.STRING));
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
		
		ZnywtaskListDomain znywtasklistdomain=new ZnywtaskListDomain();
		znywtasklistdomain.setZnywtasklist(datalist);
		ReturnValueDomain<String> oneret= znywtaskservice.add(znywtasklistdomain);
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
	  * @param ZnywtaskQueryDomain
	  * @return ReturnValueDomain<SysEntityfile>对象
	  */
	@Proxy(method="exportexcel", inarg="ZnywtaskQueryDomain", outarg="ReturnValueDomain<SysEntityfile>")
	@ResponseBody
	@RequestMapping("/exportexcel")
	public ReturnValueDomain<SysEntityfile> exportexcel(HttpServletRequest request) {
		ReturnValueDomain<SysEntityfile> ret=new ReturnValueDomain<SysEntityfile>();
		
		ZnywtaskQueryDomain znywtaskquerydomain = null;
		try {
			znywtaskquerydomain = JsonUtil.request2Domain(request, ZnywtaskQueryDomain.class);
		}catch(Exception e) {
			logger.error("请求参数解析异常", e);
			return ret.setFail("请求参数解析异常");
		}
		
		PageParamDomain pageparam=new PageParamDomain();
		pageparam.setRownumperpage(ServerFeature.EXPORT_MAXSIZE);
		pageparam.setPageindex(0);
		znywtaskquerydomain.setPageparam(pageparam);
		
		ReturnValueDomain<PageDomain<Znywtask>> queryret = znywtaskservice.query(znywtaskquerydomain);
		
		List<Znywtask> Znywtasklist = queryret.getObject().getDatalist();
		
		List<HeaderDomain> headerlist = new ArrayList<HeaderDomain>();
		headerlist.add(new HeaderDomain("0", "templateid", "模板id"));
		headerlist.add(new HeaderDomain("1", "executor", "执行人"));
		headerlist.add(new HeaderDomain("2", "executetime", "执行时间"));
		headerlist.add(new HeaderDomain("3", "status", "状态"));
		headerlist.add(new HeaderDomain("4", "starttime", "开始时间"));
		headerlist.add(new HeaderDomain("5", "expiretime", "结束时间"));
		headerlist.add(new HeaderDomain("6", "dept", "部门"));
		headerlist.add(new HeaderDomain("7", "remark", "备注"));
		
		String filepath=ServerFeature.WEBAPP_HOME + File.separator + "download" + File.separator + DateUtil.getNowTime("yyyyMMdd");
		String filename=UUID.randomUUID().toString() + ".xlsx";
				
		try {
			ExcelUtil.writeExcel(filepath + File.separator + filename, headerlist, Znywtasklist);
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

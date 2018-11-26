/**
 * 厂家信息表管理服务接口
 *
 * @version 1.0
 * @since 2018-11-24
 */
package cn.finedo.ahcnams.service.znywfactoryinfo;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.finedo.ahcnams.pojo.Znywfactoryinfo;
import cn.finedo.ahcnams.service.znywfactoryinfo.domain.ZnywfactoryinfoListDomain;
import cn.finedo.ahcnams.service.znywfactoryinfo.domain.ZnywfactoryinfoQueryDomain;
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
import cn.finedo.fsdp.service.common.configure.ConfigureUtil;
import cn.finedo.fsdp.service.common.excel.ExcelUtil;
import cn.finedo.fsdp.service.common.excel.HeaderDomain;
import cn.finedo.fsdp.service.file.FileServiceAPProxy;

@Controller
@Scope("singleton")
@RequestMapping("service/finedo/znywfactoryinfo")
public class ZnywfactoryinfoServiceAP {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private ZnywfactoryinfoService znywfactoryinfoservice;
	
	/**
	 * 厂家信息表查询
	 * @param ZnywfactoryinfoQueryDomain
	 * @return ReturnValueDomain<Znywfactoryinfo>对象
	 */
	@Proxy(method="query", inarg="ZnywfactoryinfoQueryDomain", outarg="ReturnValueDomain<PageDomain<Znywfactoryinfo>>")
	@ResponseBody
	@RequestMapping("/query")
	public ReturnValueDomain<PageDomain<Znywfactoryinfo>> query(HttpServletRequest request) {
		ReturnValueDomain<PageDomain<Znywfactoryinfo>> ret = new ReturnValueDomain<PageDomain<Znywfactoryinfo>>();
		ZnywfactoryinfoQueryDomain znywfactoryinfoquerydomain = null;
		 
		try {
			znywfactoryinfoquerydomain = JsonUtil.request2Domain(request, ZnywfactoryinfoQueryDomain.class);
		}catch(Exception e) {
			logger.error("请求参数解析异常", e);
			return ret.setFail("请求参数解析异常");
		}
		
		return znywfactoryinfoservice.query(znywfactoryinfoquerydomain);
	}
	 
	/**
	 * 厂家信息表新增
	 * 
	 * @param ZnywfactoryinfoListDomain
	 * @return ReturnValueDomain<String>
	 */
	@Proxy(method="add", inarg="ZnywfactoryinfoListDomain", outarg="ReturnValueDomain<String>")
	@ResponseBody
	@RequestMapping("/add")
	public ReturnValueDomain<String> add(HttpServletRequest request) {
		ReturnValueDomain<String> ret = new ReturnValueDomain<String>();
		ZnywfactoryinfoListDomain znywfactoryinfolistdomain = null;
		 
		try {
			znywfactoryinfolistdomain = JsonUtil.request2Domain(request, ZnywfactoryinfoListDomain.class);
		}catch(Exception e) {
			logger.error("请求参数解析异常", e);
			return ret.setFail("请求参数解析异常");
		}
	
		List<Znywfactoryinfo> znywfactoryinfolist= znywfactoryinfolistdomain.getZnywfactoryinfolist();
		
		List<ValidateItem> items = new ArrayList<ValidateItem>();
		items.add(new ValidateItem("facname", "厂家名称", true, DataType.STRING));
		items.add(new ValidateItem("contactor", "联系人", true, DataType.STRING));
		items.add(new ValidateItem("remark", "备注", true, DataType.STRING));
		ReturnValueDomain<String> validret = ValidateUtil.checkForList(znywfactoryinfolist, items);
		if (validret.hasFail()) {
			return validret;
		}

		return znywfactoryinfoservice.add(znywfactoryinfolistdomain);
	 }

	/**
	 * 厂家信息表修改
	 * @param ZnywfactoryinfoListDomain
	 * @return ReturnValueDomain<String>对象
	 */
	@Proxy(method="update", inarg="ZnywfactoryinfoListDomain", outarg="ReturnValueDomain<String>")
	@ResponseBody
	@RequestMapping("/update")
	public ReturnValueDomain<String> update(HttpServletRequest request) {
		ReturnValueDomain<String> ret = new ReturnValueDomain<String>();
		ZnywfactoryinfoListDomain znywfactoryinfolistdomain = null;
		  
		try {
			znywfactoryinfolistdomain = JsonUtil.request2Domain(request, ZnywfactoryinfoListDomain.class);
		}catch(Exception e) {
			logger.error("请求参数解析异常", e);
			return ret.setFail("请求参数解析异常");
		}

		List<Znywfactoryinfo> znywfactoryinfolist= znywfactoryinfolistdomain.getZnywfactoryinfolist();
		
		List<ValidateItem> items = new ArrayList<ValidateItem>();
		items.add(new ValidateItem("facid", "厂家id", false, DataType.STRING));
		items.add(new ValidateItem("facname", "厂家名称", true, DataType.STRING));
		items.add(new ValidateItem("contactor", "联系人", true, DataType.STRING));
		items.add(new ValidateItem("remark", "备注", true, DataType.STRING));
		ReturnValueDomain<String> validret = ValidateUtil.checkForList(znywfactoryinfolist, items);
		if (validret.hasFail()) {
			return validret;
		}

		return znywfactoryinfoservice.update(znywfactoryinfolistdomain);
	}
	
	/**
	 * 厂家信息表删除
	 * 
	 * @param ZnywfactoryinfoListDomain
	 * @return ReturnValueDomain<String>
	 */
	@Proxy(method="delete", inarg="ZnywfactoryinfoListDomain", outarg="ReturnValueDomain<String>")
	@ResponseBody
	@RequestMapping("/delete")
	public ReturnValueDomain<String> delete(HttpServletRequest request) {
		ReturnValueDomain<String> ret = new ReturnValueDomain<String>();
		ZnywfactoryinfoListDomain znywfactoryinfolistdomain = null;
		
		try {
			znywfactoryinfolistdomain = JsonUtil.request2Domain(request, ZnywfactoryinfoListDomain.class);
		}catch(Exception e) {
			logger.error("请求参数解析异常", e);
			return ret.setFail("请求参数解析异常");
		}
		
		List<Znywfactoryinfo> znywfactoryinfolist= znywfactoryinfolistdomain.getZnywfactoryinfolist();
		
		List<ValidateItem> items = new ArrayList<ValidateItem>();
		items.add(new ValidateItem("facid", "厂家id", false, DataType.STRING));
		ReturnValueDomain<String> validret = ValidateUtil.checkForList(znywfactoryinfolist, items);
		if (validret.hasFail()) {
			return validret;
		}
		
		return znywfactoryinfoservice.delete(znywfactoryinfolistdomain);
	}
	
	/**
	 * 批量导入厂家信息表
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
		List<Znywfactoryinfo> datalist;
		try {
			List<HeaderDomain> headerlist=new ArrayList<HeaderDomain>();
			
			headerlist.add(new HeaderDomain("0", "facname", "厂家名称"));
			headerlist.add(new HeaderDomain("1", "contactor", "联系人"));
			headerlist.add(new HeaderDomain("2", "remark", "备注"));
			
			datalist=ExcelUtil.readExcel(filename, headerlist, Znywfactoryinfo.class);
			rowcount=datalist.size();
			
			// 合法性校验
			List<ValidateItem> items = new ArrayList<ValidateItem>();
			items.add(new ValidateItem("facname", "厂家名称", true, DataType.STRING));
			items.add(new ValidateItem("contactor", "联系人", true, DataType.STRING));
			items.add(new ValidateItem("remark", "备注", true, DataType.STRING));
			
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
		
		ZnywfactoryinfoListDomain znywfactoryinfolistdomain=new ZnywfactoryinfoListDomain();
		znywfactoryinfolistdomain.setZnywfactoryinfolist(datalist);
		ReturnValueDomain<String> oneret= znywfactoryinfoservice.add(znywfactoryinfolistdomain);
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
	  * @param ZnywfactoryinfoQueryDomain
	  * @return ReturnValueDomain<SysEntityfile>对象
	  */
	@Proxy(method="exportexcel", inarg="ZnywfactoryinfoQueryDomain", outarg="ReturnValueDomain<SysEntityfile>")
	@ResponseBody
	@RequestMapping("/exportexcel")
	public ReturnValueDomain<SysEntityfile> exportexcel(HttpServletRequest request) {
		ReturnValueDomain<SysEntityfile> ret=new ReturnValueDomain<SysEntityfile>();
		
		ZnywfactoryinfoQueryDomain znywfactoryinfoquerydomain = null;
		try {
			znywfactoryinfoquerydomain = JsonUtil.request2Domain(request, ZnywfactoryinfoQueryDomain.class);
		}catch(Exception e) {
			logger.error("请求参数解析异常", e);
			return ret.setFail("请求参数解析异常");
		}
		
		PageParamDomain pageparam=new PageParamDomain();
		pageparam.setRownumperpage(ServerFeature.EXPORT_MAXSIZE);
		pageparam.setPageindex(0);
		znywfactoryinfoquerydomain.setPageparam(pageparam);
		
		ReturnValueDomain<PageDomain<Znywfactoryinfo>> queryret = znywfactoryinfoservice.query(znywfactoryinfoquerydomain);
		
		List<Znywfactoryinfo> Znywfactoryinfolist = queryret.getObject().getDatalist();
		
		List<HeaderDomain> headerlist = new ArrayList<HeaderDomain>();
		headerlist.add(new HeaderDomain("0", "facname", "厂家名称"));
		headerlist.add(new HeaderDomain("1", "contactor", "联系人"));
		headerlist.add(new HeaderDomain("2", "remark", "备注"));
		
		String filepath=ServerFeature.WEBAPP_HOME + File.separator + "download" + File.separator + DateUtil.getNowTime("yyyyMMdd");
		String filename=UUID.randomUUID().toString() + ".xlsx";
				
		try {
			ExcelUtil.writeExcel(filepath + File.separator + filename, headerlist, Znywfactoryinfolist);
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

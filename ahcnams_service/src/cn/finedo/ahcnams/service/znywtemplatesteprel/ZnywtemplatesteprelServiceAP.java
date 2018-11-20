/**
 * 模板和步骤关系管理服务接口
 *
 * @version 1.0
 * @since 2018-11-20
 */
package cn.finedo.ahcnams.service.znywtemplatesteprel;

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
import cn.finedo.ahcnams.pojo.Znywtemplatesteprel;
import cn.finedo.ahcnams.service.znywtemplatesteprel.domain.ZnywtemplatesteprelListDomain;
import cn.finedo.ahcnams.service.znywtemplatesteprel.domain.ZnywtemplatesteprelQueryDomain;

@Controller
@Scope("singleton")
@RequestMapping("service/finedo/znywtemplatesteprel")
public class ZnywtemplatesteprelServiceAP {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private ZnywtemplatesteprelService znywtemplatesteprelservice;
	
	/**
	 * 模板和步骤关系查询
	 * @param ZnywtemplatesteprelQueryDomain
	 * @return ReturnValueDomain<Znywtemplatesteprel>对象
	 */
	@Proxy(method="query", inarg="ZnywtemplatesteprelQueryDomain", outarg="ReturnValueDomain<PageDomain<Znywtemplatesteprel>>")
	@ResponseBody
	@RequestMapping("/query")
	public ReturnValueDomain<PageDomain<Znywtemplatesteprel>> query(HttpServletRequest request) {
		ReturnValueDomain<PageDomain<Znywtemplatesteprel>> ret = new ReturnValueDomain<PageDomain<Znywtemplatesteprel>>();
		ZnywtemplatesteprelQueryDomain znywtemplatesteprelquerydomain = null;
		 
		try {
			znywtemplatesteprelquerydomain = JsonUtil.request2Domain(request, ZnywtemplatesteprelQueryDomain.class);
		}catch(Exception e) {
			logger.error("请求参数解析异常", e);
			return ret.setFail("请求参数解析异常");
		}
		
		return znywtemplatesteprelservice.query(znywtemplatesteprelquerydomain);
	}
	 
	/**
	 * 模板和步骤关系新增
	 * 
	 * @param ZnywtemplatesteprelListDomain
	 * @return ReturnValueDomain<String>
	 */
	@Proxy(method="add", inarg="ZnywtemplatesteprelListDomain", outarg="ReturnValueDomain<String>")
	@ResponseBody
	@RequestMapping("/add")
	public ReturnValueDomain<String> add(HttpServletRequest request) {
		ReturnValueDomain<String> ret = new ReturnValueDomain<String>();
		ZnywtemplatesteprelListDomain znywtemplatesteprellistdomain = null;
		 
		try {
			znywtemplatesteprellistdomain = JsonUtil.request2Domain(request, ZnywtemplatesteprelListDomain.class);
		}catch(Exception e) {
			logger.error("请求参数解析异常", e);
			return ret.setFail("请求参数解析异常");
		}
	
		List<Znywtemplatesteprel> znywtemplatesteprellist= znywtemplatesteprellistdomain.getZnywtemplatesteprellist();
		
		List<ValidateItem> items = new ArrayList<ValidateItem>();
		items.add(new ValidateItem("templateid", "模板id", false, DataType.STRING));
		items.add(new ValidateItem("stepid", "步骤id", false, DataType.STRING));
		ReturnValueDomain<String> validret = ValidateUtil.checkForList(znywtemplatesteprellist, items);
		if (validret.hasFail()) {
			return validret;
		}

		return znywtemplatesteprelservice.add(znywtemplatesteprellistdomain);
	 }

	/**
	 * 模板和步骤关系修改
	 * @param ZnywtemplatesteprelListDomain
	 * @return ReturnValueDomain<String>对象
	 */
	@Proxy(method="update", inarg="ZnywtemplatesteprelListDomain", outarg="ReturnValueDomain<String>")
	@ResponseBody
	@RequestMapping("/update")
	public ReturnValueDomain<String> update(HttpServletRequest request) {
		ReturnValueDomain<String> ret = new ReturnValueDomain<String>();
		ZnywtemplatesteprelListDomain znywtemplatesteprellistdomain = null;
		  
		try {
			znywtemplatesteprellistdomain = JsonUtil.request2Domain(request, ZnywtemplatesteprelListDomain.class);
		}catch(Exception e) {
			logger.error("请求参数解析异常", e);
			return ret.setFail("请求参数解析异常");
		}

		List<Znywtemplatesteprel> znywtemplatesteprellist= znywtemplatesteprellistdomain.getZnywtemplatesteprellist();
		
		List<ValidateItem> items = new ArrayList<ValidateItem>();
		items.add(new ValidateItem("relid", "关系id", true, DataType.STRING));
		items.add(new ValidateItem("templateid", "模板id", false, DataType.STRING));
		items.add(new ValidateItem("stepid", "步骤id", false, DataType.STRING));
		ReturnValueDomain<String> validret = ValidateUtil.checkForList(znywtemplatesteprellist, items);
		if (validret.hasFail()) {
			return validret;
		}

		return znywtemplatesteprelservice.update(znywtemplatesteprellistdomain);
	}
	
	/**
	 * 模板和步骤关系删除
	 * 
	 * @param ZnywtemplatesteprelListDomain
	 * @return ReturnValueDomain<String>
	 */
	@Proxy(method="delete", inarg="ZnywtemplatesteprelListDomain", outarg="ReturnValueDomain<String>")
	@ResponseBody
	@RequestMapping("/delete")
	public ReturnValueDomain<String> delete(HttpServletRequest request) {
		ReturnValueDomain<String> ret = new ReturnValueDomain<String>();
		ZnywtemplatesteprelListDomain znywtemplatesteprellistdomain = null;
		
		try {
			znywtemplatesteprellistdomain = JsonUtil.request2Domain(request, ZnywtemplatesteprelListDomain.class);
		}catch(Exception e) {
			logger.error("请求参数解析异常", e);
			return ret.setFail("请求参数解析异常");
		}
		
		List<Znywtemplatesteprel> znywtemplatesteprellist= znywtemplatesteprellistdomain.getZnywtemplatesteprellist();
		
		List<ValidateItem> items = new ArrayList<ValidateItem>();
		items.add(new ValidateItem("relid", "关系id", false, DataType.STRING));
		ReturnValueDomain<String> validret = ValidateUtil.checkForList(znywtemplatesteprellist, items);
		if (validret.hasFail()) {
			return validret;
		}
		
		return znywtemplatesteprelservice.delete(znywtemplatesteprellistdomain);
	}
	
	/**
	 * 批量导入模板和步骤关系
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
		List<Znywtemplatesteprel> datalist;
		try {
			List<HeaderDomain> headerlist=new ArrayList<HeaderDomain>();
			
			headerlist.add(new HeaderDomain("0", "templateid", "模板id"));
			headerlist.add(new HeaderDomain("1", "stepid", "步骤id"));
			
			datalist=ExcelUtil.readExcel(filename, headerlist, Znywtemplatesteprel.class);
			rowcount=datalist.size();
			
			// 合法性校验
			List<ValidateItem> items = new ArrayList<ValidateItem>();
			items.add(new ValidateItem("templateid", "模板id", false, DataType.STRING));
			items.add(new ValidateItem("stepid", "步骤id", false, DataType.STRING));
			
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
		
		ZnywtemplatesteprelListDomain znywtemplatesteprellistdomain=new ZnywtemplatesteprelListDomain();
		znywtemplatesteprellistdomain.setZnywtemplatesteprellist(datalist);
		ReturnValueDomain<String> oneret= znywtemplatesteprelservice.add(znywtemplatesteprellistdomain);
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
	  * @param ZnywtemplatesteprelQueryDomain
	  * @return ReturnValueDomain<SysEntityfile>对象
	  */
	@Proxy(method="exportexcel", inarg="ZnywtemplatesteprelQueryDomain", outarg="ReturnValueDomain<SysEntityfile>")
	@ResponseBody
	@RequestMapping("/exportexcel")
	public ReturnValueDomain<SysEntityfile> exportexcel(HttpServletRequest request) {
		ReturnValueDomain<SysEntityfile> ret=new ReturnValueDomain<SysEntityfile>();
		
		ZnywtemplatesteprelQueryDomain znywtemplatesteprelquerydomain = null;
		try {
			znywtemplatesteprelquerydomain = JsonUtil.request2Domain(request, ZnywtemplatesteprelQueryDomain.class);
		}catch(Exception e) {
			logger.error("请求参数解析异常", e);
			return ret.setFail("请求参数解析异常");
		}
		
		PageParamDomain pageparam=new PageParamDomain();
		pageparam.setRownumperpage(ServerFeature.EXPORT_MAXSIZE);
		pageparam.setPageindex(0);
		znywtemplatesteprelquerydomain.setPageparam(pageparam);
		
		ReturnValueDomain<PageDomain<Znywtemplatesteprel>> queryret = znywtemplatesteprelservice.query(znywtemplatesteprelquerydomain);
		
		List<Znywtemplatesteprel> Znywtemplatesteprellist = queryret.getObject().getDatalist();
		
		List<HeaderDomain> headerlist = new ArrayList<HeaderDomain>();
		headerlist.add(new HeaderDomain("0", "templateid", "模板id"));
		headerlist.add(new HeaderDomain("1", "stepid", "步骤id"));
		
		String filepath=ServerFeature.WEBAPP_HOME + File.separator + "download" + File.separator + DateUtil.getNowTime("yyyyMMdd");
		String filename=UUID.randomUUID().toString() + ".xlsx";
				
		try {
			ExcelUtil.writeExcel(filepath + File.separator + filename, headerlist, Znywtemplatesteprellist);
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

/**
 * 网元和模板步骤对应关系管理服务接口
 *
 * @version 1.0
 * @since 2018-11-20
 */
package cn.finedo.ahcnams.service.znywelementsteprel;

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
import cn.finedo.ahcnams.pojo.Znywelementsteprel;
import cn.finedo.ahcnams.service.znywelementsteprel.domain.ZnywelementsteprelListDomain;
import cn.finedo.ahcnams.service.znywelementsteprel.domain.ZnywelementsteprelQueryDomain;

@Controller
@Scope("singleton")
@RequestMapping("service/finedo/znywelementsteprel")
public class ZnywelementsteprelServiceAP {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private ZnywelementsteprelService znywelementsteprelservice;
	
	/**
	 * 网元和模板步骤对应关系查询
	 * @param ZnywelementsteprelQueryDomain
	 * @return ReturnValueDomain<Znywelementsteprel>对象
	 */
	@Proxy(method="query", inarg="ZnywelementsteprelQueryDomain", outarg="ReturnValueDomain<PageDomain<Znywelementsteprel>>")
	@ResponseBody
	@RequestMapping("/query")
	public ReturnValueDomain<PageDomain<Znywelementsteprel>> query(HttpServletRequest request) {
		ReturnValueDomain<PageDomain<Znywelementsteprel>> ret = new ReturnValueDomain<PageDomain<Znywelementsteprel>>();
		ZnywelementsteprelQueryDomain znywelementsteprelquerydomain = null;
		 
		try {
			znywelementsteprelquerydomain = JsonUtil.request2Domain(request, ZnywelementsteprelQueryDomain.class);
		}catch(Exception e) {
			logger.error("请求参数解析异常", e);
			return ret.setFail("请求参数解析异常");
		}
		
		return znywelementsteprelservice.query(znywelementsteprelquerydomain);
	}
	 
	/**
	 * 网元和模板步骤对应关系新增
	 * 
	 * @param ZnywelementsteprelListDomain
	 * @return ReturnValueDomain<String>
	 */
	@Proxy(method="add", inarg="ZnywelementsteprelListDomain", outarg="ReturnValueDomain<String>")
	@ResponseBody
	@RequestMapping("/add")
	public ReturnValueDomain<String> add(HttpServletRequest request) {
		ReturnValueDomain<String> ret = new ReturnValueDomain<String>();
		ZnywelementsteprelListDomain znywelementsteprellistdomain = null;
		 
		try {
			znywelementsteprellistdomain = JsonUtil.request2Domain(request, ZnywelementsteprelListDomain.class);
		}catch(Exception e) {
			logger.error("请求参数解析异常", e);
			return ret.setFail("请求参数解析异常");
		}
	
		List<Znywelementsteprel> znywelementsteprellist= znywelementsteprellistdomain.getZnywelementsteprellist();
		
		List<ValidateItem> items = new ArrayList<ValidateItem>();
		items.add(new ValidateItem("eletid", "网元id", false, DataType.STRING));
		items.add(new ValidateItem("stepid", "步骤id", false, DataType.STRING));
		ReturnValueDomain<String> validret = ValidateUtil.checkForList(znywelementsteprellist, items);
		if (validret.hasFail()) {
			return validret;
		}

		return znywelementsteprelservice.add(znywelementsteprellistdomain);
	 }

	/**
	 * 网元和模板步骤对应关系修改
	 * @param ZnywelementsteprelListDomain
	 * @return ReturnValueDomain<String>对象
	 */
	@Proxy(method="update", inarg="ZnywelementsteprelListDomain", outarg="ReturnValueDomain<String>")
	@ResponseBody
	@RequestMapping("/update")
	public ReturnValueDomain<String> update(HttpServletRequest request) {
		ReturnValueDomain<String> ret = new ReturnValueDomain<String>();
		ZnywelementsteprelListDomain znywelementsteprellistdomain = null;
		  
		try {
			znywelementsteprellistdomain = JsonUtil.request2Domain(request, ZnywelementsteprelListDomain.class);
		}catch(Exception e) {
			logger.error("请求参数解析异常", e);
			return ret.setFail("请求参数解析异常");
		}

		List<Znywelementsteprel> znywelementsteprellist= znywelementsteprellistdomain.getZnywelementsteprellist();
		
		List<ValidateItem> items = new ArrayList<ValidateItem>();
		items.add(new ValidateItem("relid", "关系id", true, DataType.STRING));
		items.add(new ValidateItem("eletid", "网元id", false, DataType.STRING));
		items.add(new ValidateItem("stepid", "步骤id", false, DataType.STRING));
		ReturnValueDomain<String> validret = ValidateUtil.checkForList(znywelementsteprellist, items);
		if (validret.hasFail()) {
			return validret;
		}

		return znywelementsteprelservice.update(znywelementsteprellistdomain);
	}
	
	/**
	 * 网元和模板步骤对应关系删除
	 * 
	 * @param ZnywelementsteprelListDomain
	 * @return ReturnValueDomain<String>
	 */
	@Proxy(method="delete", inarg="ZnywelementsteprelListDomain", outarg="ReturnValueDomain<String>")
	@ResponseBody
	@RequestMapping("/delete")
	public ReturnValueDomain<String> delete(HttpServletRequest request) {
		ReturnValueDomain<String> ret = new ReturnValueDomain<String>();
		ZnywelementsteprelListDomain znywelementsteprellistdomain = null;
		
		try {
			znywelementsteprellistdomain = JsonUtil.request2Domain(request, ZnywelementsteprelListDomain.class);
		}catch(Exception e) {
			logger.error("请求参数解析异常", e);
			return ret.setFail("请求参数解析异常");
		}
		
		List<Znywelementsteprel> znywelementsteprellist= znywelementsteprellistdomain.getZnywelementsteprellist();
		
		List<ValidateItem> items = new ArrayList<ValidateItem>();
		items.add(new ValidateItem("relid", "关系id", false, DataType.STRING));
		ReturnValueDomain<String> validret = ValidateUtil.checkForList(znywelementsteprellist, items);
		if (validret.hasFail()) {
			return validret;
		}
		
		return znywelementsteprelservice.delete(znywelementsteprellistdomain);
	}
	
	/**
	 * 批量导入网元和模板步骤对应关系
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
		List<Znywelementsteprel> datalist;
		try {
			List<HeaderDomain> headerlist=new ArrayList<HeaderDomain>();
			
			headerlist.add(new HeaderDomain("0", "eletid", "网元id"));
			headerlist.add(new HeaderDomain("1", "stepid", "步骤id"));
			
			datalist=ExcelUtil.readExcel(filename, headerlist, Znywelementsteprel.class);
			rowcount=datalist.size();
			
			// 合法性校验
			List<ValidateItem> items = new ArrayList<ValidateItem>();
			items.add(new ValidateItem("eletid", "网元id", false, DataType.STRING));
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
		
		ZnywelementsteprelListDomain znywelementsteprellistdomain=new ZnywelementsteprelListDomain();
		znywelementsteprellistdomain.setZnywelementsteprellist(datalist);
		ReturnValueDomain<String> oneret= znywelementsteprelservice.add(znywelementsteprellistdomain);
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
	  * @param ZnywelementsteprelQueryDomain
	  * @return ReturnValueDomain<SysEntityfile>对象
	  */
	@Proxy(method="exportexcel", inarg="ZnywelementsteprelQueryDomain", outarg="ReturnValueDomain<SysEntityfile>")
	@ResponseBody
	@RequestMapping("/exportexcel")
	public ReturnValueDomain<SysEntityfile> exportexcel(HttpServletRequest request) {
		ReturnValueDomain<SysEntityfile> ret=new ReturnValueDomain<SysEntityfile>();
		
		ZnywelementsteprelQueryDomain znywelementsteprelquerydomain = null;
		try {
			znywelementsteprelquerydomain = JsonUtil.request2Domain(request, ZnywelementsteprelQueryDomain.class);
		}catch(Exception e) {
			logger.error("请求参数解析异常", e);
			return ret.setFail("请求参数解析异常");
		}
		
		PageParamDomain pageparam=new PageParamDomain();
		pageparam.setRownumperpage(ServerFeature.EXPORT_MAXSIZE);
		pageparam.setPageindex(0);
		znywelementsteprelquerydomain.setPageparam(pageparam);
		
		ReturnValueDomain<PageDomain<Znywelementsteprel>> queryret = znywelementsteprelservice.query(znywelementsteprelquerydomain);
		
		List<Znywelementsteprel> Znywelementsteprellist = queryret.getObject().getDatalist();
		
		List<HeaderDomain> headerlist = new ArrayList<HeaderDomain>();
		headerlist.add(new HeaderDomain("0", "eletid", "网元id"));
		headerlist.add(new HeaderDomain("1", "stepid", "步骤id"));
		
		String filepath=ServerFeature.WEBAPP_HOME + File.separator + "download" + File.separator + DateUtil.getNowTime("yyyyMMdd");
		String filename=UUID.randomUUID().toString() + ".xlsx";
				
		try {
			ExcelUtil.writeExcel(filepath + File.separator + filename, headerlist, Znywelementsteprellist);
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

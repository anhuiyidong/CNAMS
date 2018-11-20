/**
 * 模板管理Action
 * 
 * @version 1.0
 * @since 2018-11-20
 */
package cn.finedo.ahcnams.webapp.znywtemplate;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import cn.finedo.common.domain.PageDomain;
import cn.finedo.common.domain.PageParamDomain;
import cn.finedo.common.domain.ReturnValueDomain;
import cn.finedo.common.file.FileDownloadUtil;
import cn.finedo.common.non.NonUtil;
import cn.finedo.common.pojo.SysEntityfile;
import cn.finedo.common.protocol.FormUtil;
import cn.finedo.fsdp.webapp.common.utils.PageUtil;
import cn.finedo.ahcnams.pojo.Znywtemplate;
import cn.finedo.ahcnams.service.znywtemplate.ZnywtemplateServiceAPProxy;
import cn.finedo.ahcnams.service.znywtemplate.domain.ZnywtemplateListDomain;
import cn.finedo.ahcnams.service.znywtemplate.domain.ZnywtemplateQueryDomain;

@Controller
@Scope("singleton")
@RequestMapping("/finedo/znywtemplate")
public class ZnywtemplateAction {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	/**
	 * 模板查询
	 * @param Znywtemplate
	 * @param PageParamDomain
	 */
	@RequestMapping("/query")
	@ResponseBody
	public Object query(HttpServletRequest request) throws Exception {
		Znywtemplate znywtemplate = FormUtil.request2Domain(request, Znywtemplate.class);
		PageParamDomain pageparam=PageUtil.getPageParam(request);
		
		ZnywtemplateQueryDomain znywtemplatequerydomain = new ZnywtemplateQueryDomain();
		znywtemplatequerydomain.setZnywtemplate(znywtemplate);
		znywtemplatequerydomain.setPageparam(pageparam);
		
		ReturnValueDomain<PageDomain<Znywtemplate>> ret=ZnywtemplateServiceAPProxy.query(znywtemplatequerydomain);
		PageDomain<Znywtemplate> page = ret.getObject();
		
		return PageUtil.build(page.getDatalist(), page.getRowcount());
	}
	
	/**
	 * 模板新增
	 * @param ZnywtemplateListDomain
	 */
	@RequestMapping("/add")
	@ResponseBody
	public Object add(HttpServletRequest request) throws Exception {
		Znywtemplate znywtemplate = FormUtil.request2Domain(request, Znywtemplate.class);
		
		ZnywtemplateListDomain znywtemplatelistdomain = new ZnywtemplateListDomain();
		List<Znywtemplate> znywtemplatelist=new ArrayList<Znywtemplate>();
		znywtemplatelist.add(znywtemplate);
		znywtemplatelistdomain.setZnywtemplatelist(znywtemplatelist);
	
		ReturnValueDomain<String> ret=ZnywtemplateServiceAPProxy.add(znywtemplatelistdomain);
		
		return ret;
	}
		
	/**
	 * 根据主键查询模板
	 * @param Znywtemplate
	 */
	@RequestMapping("/modifypage")
	public Object modifypage(HttpServletRequest request) throws Exception{
		Znywtemplate znywtemplate=FormUtil.request2Domain(request, Znywtemplate.class);
		
		ZnywtemplateQueryDomain znywtemplatequerydomain= new ZnywtemplateQueryDomain();
		znywtemplatequerydomain.setZnywtemplate(znywtemplate);
		znywtemplatequerydomain.setPageparam(null);
		
		ReturnValueDomain<PageDomain<Znywtemplate>> ret=ZnywtemplateServiceAPProxy.query(znywtemplatequerydomain);
		PageDomain<Znywtemplate> page = ret.getObject();
		List<Znywtemplate> znywtemplatelist = page.getDatalist();
		
		if (NonUtil.isNotNon(znywtemplatelist)) {
			request.setAttribute("znywtemplate", znywtemplatelist.get(0));
		}
		
		return "/ahcnams_service/znywtemplate/modify.jsp";
	}
	
	/**
	 * 模板修改
	 * @param ZnywtemplateListDomain
	 */
	@RequestMapping("/modify")
	@ResponseBody
	public Object modify(HttpServletRequest request) throws Exception {
		// 传入的领域对象
		Znywtemplate znywtemplate = FormUtil.request2Domain(request, Znywtemplate.class);
		
		ZnywtemplateListDomain znywtemplatelistdomain = new ZnywtemplateListDomain();
		List<Znywtemplate> znywtemplatelist=new ArrayList<Znywtemplate>();
		znywtemplatelist.add(znywtemplate);
		znywtemplatelistdomain.setZnywtemplatelist(znywtemplatelist);
		
		ReturnValueDomain<String> ret=ZnywtemplateServiceAPProxy.update(znywtemplatelistdomain);
		
		return ret;
	}

	/**
	 * 模板删除
	 * @param ZnywtemplateListDomain
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public Object delete(HttpServletRequest request) throws Exception{
		// 传入的领域对象
		List<Znywtemplate> znywtemplatelist=new ArrayList<Znywtemplate>();
		
		String idsstr = request.getParameter("id");
		String[] idarray = idsstr.split(",");
		for(String id : idarray){
			Znywtemplate znywtemplate = new Znywtemplate();
			
			znywtemplate.setTemplateid(id);
			znywtemplatelist.add(znywtemplate);
		}
		ZnywtemplateListDomain znywtemplatelistdomain = new ZnywtemplateListDomain();
		znywtemplatelistdomain.setZnywtemplatelist(znywtemplatelist);
		
		ReturnValueDomain<String> ret=ZnywtemplateServiceAPProxy.delete(znywtemplatelistdomain);
		
		return ret;
	}
	
	/**
	 * 模板详情
	 * @param Znywtemplate
	 */
	@RequestMapping("/detail")
	public Object detail(HttpServletRequest request) throws Exception{
		// 传入的领域对象
		Znywtemplate znywtemplate=FormUtil.request2Domain(request, Znywtemplate.class);
		
		ZnywtemplateQueryDomain znywtemplatequerydomain= new ZnywtemplateQueryDomain();
		znywtemplatequerydomain.setZnywtemplate(znywtemplate);
		znywtemplatequerydomain.setPageparam(null);
		
		ReturnValueDomain<PageDomain<Znywtemplate>> ret=ZnywtemplateServiceAPProxy.query(znywtemplatequerydomain);
		PageDomain<Znywtemplate> page = ret.getObject();
		List<Znywtemplate> znywtemplatelist = page.getDatalist();
		
		if (NonUtil.isNotNon(znywtemplatelist)) {
			request.setAttribute("znywtemplate", znywtemplatelist.get(0));
		}
				
		return "/ahcnams_service/znywtemplate/detail.jsp";
	}
	
	/**
	 * 导入
	 */
	@RequestMapping(value="/importexcel")
	@ResponseBody
	public Object importexcel(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String fileid=request.getParameter("fileid");
		
		SysEntityfile entityfile=new SysEntityfile();
		entityfile.setFileid(fileid);
		
		return ZnywtemplateServiceAPProxy.importexcel(entityfile);
	}
	
	/**
	 * 导出
	 */
	@RequestMapping(value="/exportexcel")
	public void exportexcel(HttpServletRequest request, HttpServletResponse response) throws Exception{
		Znywtemplate znywtemplate=FormUtil.request2Domain(request, Znywtemplate.class);
		ZnywtemplateQueryDomain znywtemplatequerydomain = new ZnywtemplateQueryDomain();
		znywtemplatequerydomain.setZnywtemplate(znywtemplate);

		ReturnValueDomain<SysEntityfile> ret=ZnywtemplateServiceAPProxy.exportexcel(znywtemplatequerydomain);
		SysEntityfile entityfile=ret.getObject();
		String filepath=entityfile.getFilepath();
		String filename=entityfile.getFilename();
		
		FileDownloadUtil.filedownload(filename, filepath + File.separator + filename, request, response);
	}
}

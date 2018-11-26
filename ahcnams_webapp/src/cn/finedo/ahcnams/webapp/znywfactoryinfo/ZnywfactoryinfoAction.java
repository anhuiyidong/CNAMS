/**
 * 厂家信息表管理Action
 * 
 * @version 1.0
 * @since 2018-11-24
 */
package cn.finedo.ahcnams.webapp.znywfactoryinfo;

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

import cn.finedo.ahcnams.pojo.Znywfactoryinfo;
import cn.finedo.ahcnams.service.znywfactoryinfo.ZnywfactoryinfoServiceAPProxy;
import cn.finedo.ahcnams.service.znywfactoryinfo.domain.ZnywfactoryinfoListDomain;
import cn.finedo.ahcnams.service.znywfactoryinfo.domain.ZnywfactoryinfoQueryDomain;
import cn.finedo.common.domain.PageDomain;
import cn.finedo.common.domain.PageParamDomain;
import cn.finedo.common.domain.ReturnValueDomain;
import cn.finedo.common.file.FileDownloadUtil;
import cn.finedo.common.non.NonUtil;
import cn.finedo.common.pojo.SysEntityfile;
import cn.finedo.common.protocol.FormUtil;
import cn.finedo.fsdp.webapp.common.utils.PageUtil;

@Controller
@Scope("singleton")
@RequestMapping("/finedo/znywfactoryinfo")
public class ZnywfactoryinfoAction {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	/**
	 * 厂家信息表查询
	 * @param Znywfactoryinfo
	 * @param PageParamDomain
	 */
	@RequestMapping("/query")
	@ResponseBody
	public Object query(HttpServletRequest request) throws Exception {
		Znywfactoryinfo znywfactoryinfo = FormUtil.request2Domain(request, Znywfactoryinfo.class);
		PageParamDomain pageparam=PageUtil.getPageParam(request);
		
		ZnywfactoryinfoQueryDomain znywfactoryinfoquerydomain = new ZnywfactoryinfoQueryDomain();
		znywfactoryinfoquerydomain.setZnywfactoryinfo(znywfactoryinfo);
		znywfactoryinfoquerydomain.setPageparam(pageparam);
		
		ReturnValueDomain<PageDomain<Znywfactoryinfo>> ret=ZnywfactoryinfoServiceAPProxy.query(znywfactoryinfoquerydomain);
		PageDomain<Znywfactoryinfo> page = ret.getObject();
		
		return PageUtil.build(page.getDatalist(), page.getRowcount());
	}
	
	/**
	 * 厂家信息表新增
	 * @param ZnywfactoryinfoListDomain
	 */
	@RequestMapping("/add")
	@ResponseBody
	public Object add(HttpServletRequest request) throws Exception {
		Znywfactoryinfo znywfactoryinfo = FormUtil.request2Domain(request, Znywfactoryinfo.class);
		
		ZnywfactoryinfoListDomain znywfactoryinfolistdomain = new ZnywfactoryinfoListDomain();
		List<Znywfactoryinfo> znywfactoryinfolist=new ArrayList<Znywfactoryinfo>();
		znywfactoryinfolist.add(znywfactoryinfo);
		znywfactoryinfolistdomain.setZnywfactoryinfolist(znywfactoryinfolist);
	
		ReturnValueDomain<String> ret=ZnywfactoryinfoServiceAPProxy.add(znywfactoryinfolistdomain);
		
		return ret;
	}
		
	/**
	 * 根据主键查询厂家信息表
	 * @param Znywfactoryinfo
	 */
	@RequestMapping("/modifypage")
	public Object modifypage(HttpServletRequest request) throws Exception{
		Znywfactoryinfo znywfactoryinfo=FormUtil.request2Domain(request, Znywfactoryinfo.class);
		
		ZnywfactoryinfoQueryDomain znywfactoryinfoquerydomain= new ZnywfactoryinfoQueryDomain();
		znywfactoryinfoquerydomain.setZnywfactoryinfo(znywfactoryinfo);
		znywfactoryinfoquerydomain.setPageparam(null);
		
		ReturnValueDomain<PageDomain<Znywfactoryinfo>> ret=ZnywfactoryinfoServiceAPProxy.query(znywfactoryinfoquerydomain);
		PageDomain<Znywfactoryinfo> page = ret.getObject();
		List<Znywfactoryinfo> znywfactoryinfolist = page.getDatalist();
		
		if (NonUtil.isNotNon(znywfactoryinfolist)) {
			request.setAttribute("znywfactoryinfo", znywfactoryinfolist.get(0));
		}
		
		return "/ahcnams_service/znywfactoryinfo/modify.jsp";
	}
	
	/**
	 * 厂家信息表修改
	 * @param ZnywfactoryinfoListDomain
	 */
	@RequestMapping("/modify")
	@ResponseBody
	public Object modify(HttpServletRequest request) throws Exception {
		// 传入的领域对象
		Znywfactoryinfo znywfactoryinfo = FormUtil.request2Domain(request, Znywfactoryinfo.class);
		
		ZnywfactoryinfoListDomain znywfactoryinfolistdomain = new ZnywfactoryinfoListDomain();
		List<Znywfactoryinfo> znywfactoryinfolist=new ArrayList<Znywfactoryinfo>();
		znywfactoryinfolist.add(znywfactoryinfo);
		znywfactoryinfolistdomain.setZnywfactoryinfolist(znywfactoryinfolist);
		
		ReturnValueDomain<String> ret=ZnywfactoryinfoServiceAPProxy.update(znywfactoryinfolistdomain);
		
		return ret;
	}

	/**
	 * 厂家信息表删除
	 * @param ZnywfactoryinfoListDomain
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public Object delete(HttpServletRequest request) throws Exception{
		// 传入的领域对象
		List<Znywfactoryinfo> znywfactoryinfolist=new ArrayList<Znywfactoryinfo>();
		
		String idsstr = request.getParameter("id");
		String[] idarray = idsstr.split(",");
		for(String id : idarray){
			Znywfactoryinfo znywfactoryinfo = new Znywfactoryinfo();
			
			znywfactoryinfo.setFacid(id);
			znywfactoryinfolist.add(znywfactoryinfo);
		}
		ZnywfactoryinfoListDomain znywfactoryinfolistdomain = new ZnywfactoryinfoListDomain();
		znywfactoryinfolistdomain.setZnywfactoryinfolist(znywfactoryinfolist);
		
		ReturnValueDomain<String> ret=ZnywfactoryinfoServiceAPProxy.delete(znywfactoryinfolistdomain);
		
		return ret;
	}
	
	/**
	 * 厂家信息表详情
	 * @param Znywfactoryinfo
	 */
	@RequestMapping("/detail")
	public Object detail(HttpServletRequest request) throws Exception{
		// 传入的领域对象
		Znywfactoryinfo znywfactoryinfo=FormUtil.request2Domain(request, Znywfactoryinfo.class);
		
		ZnywfactoryinfoQueryDomain znywfactoryinfoquerydomain= new ZnywfactoryinfoQueryDomain();
		znywfactoryinfoquerydomain.setZnywfactoryinfo(znywfactoryinfo);
		znywfactoryinfoquerydomain.setPageparam(null);
		
		ReturnValueDomain<PageDomain<Znywfactoryinfo>> ret=ZnywfactoryinfoServiceAPProxy.query(znywfactoryinfoquerydomain);
		PageDomain<Znywfactoryinfo> page = ret.getObject();
		List<Znywfactoryinfo> znywfactoryinfolist = page.getDatalist();
		
		if (NonUtil.isNotNon(znywfactoryinfolist)) {
			request.setAttribute("znywfactoryinfo", znywfactoryinfolist.get(0));
		}
				
		return "/ahcnams_service/znywfactoryinfo/detail.jsp";
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
		
		return ZnywfactoryinfoServiceAPProxy.importexcel(entityfile);
	}
	
	/**
	 * 导出
	 */
	@RequestMapping(value="/exportexcel")
	public void exportexcel(HttpServletRequest request, HttpServletResponse response) throws Exception{
		Znywfactoryinfo znywfactoryinfo=FormUtil.request2Domain(request, Znywfactoryinfo.class);
		ZnywfactoryinfoQueryDomain znywfactoryinfoquerydomain = new ZnywfactoryinfoQueryDomain();
		znywfactoryinfoquerydomain.setZnywfactoryinfo(znywfactoryinfo);

		ReturnValueDomain<SysEntityfile> ret=ZnywfactoryinfoServiceAPProxy.exportexcel(znywfactoryinfoquerydomain);
		SysEntityfile entityfile=ret.getObject();
		String filepath=entityfile.getFilepath();
		String filename=entityfile.getFilename();
		
		FileDownloadUtil.filedownload(filename, filepath + File.separator + filename, request, response);
	}
}

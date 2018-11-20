/**
 * 网元和模板步骤对应关系管理Action
 * 
 * @version 1.0
 * @since 2018-11-20
 */
package cn.finedo.ahcnams.webapp.znywelementsteprel;

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
import cn.finedo.ahcnams.pojo.Znywelementsteprel;
import cn.finedo.ahcnams.service.znywelementsteprel.ZnywelementsteprelServiceAPProxy;
import cn.finedo.ahcnams.service.znywelementsteprel.domain.ZnywelementsteprelListDomain;
import cn.finedo.ahcnams.service.znywelementsteprel.domain.ZnywelementsteprelQueryDomain;

@Controller
@Scope("singleton")
@RequestMapping("/finedo/znywelementsteprel")
public class ZnywelementsteprelAction {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	/**
	 * 网元和模板步骤对应关系查询
	 * @param Znywelementsteprel
	 * @param PageParamDomain
	 */
	@RequestMapping("/query")
	@ResponseBody
	public Object query(HttpServletRequest request) throws Exception {
		Znywelementsteprel znywelementsteprel = FormUtil.request2Domain(request, Znywelementsteprel.class);
		PageParamDomain pageparam=PageUtil.getPageParam(request);
		
		ZnywelementsteprelQueryDomain znywelementsteprelquerydomain = new ZnywelementsteprelQueryDomain();
		znywelementsteprelquerydomain.setZnywelementsteprel(znywelementsteprel);
		znywelementsteprelquerydomain.setPageparam(pageparam);
		
		ReturnValueDomain<PageDomain<Znywelementsteprel>> ret=ZnywelementsteprelServiceAPProxy.query(znywelementsteprelquerydomain);
		PageDomain<Znywelementsteprel> page = ret.getObject();
		
		return PageUtil.build(page.getDatalist(), page.getRowcount());
	}
	
	/**
	 * 网元和模板步骤对应关系新增
	 * @param ZnywelementsteprelListDomain
	 */
	@RequestMapping("/add")
	@ResponseBody
	public Object add(HttpServletRequest request) throws Exception {
		Znywelementsteprel znywelementsteprel = FormUtil.request2Domain(request, Znywelementsteprel.class);
		
		ZnywelementsteprelListDomain znywelementsteprellistdomain = new ZnywelementsteprelListDomain();
		List<Znywelementsteprel> znywelementsteprellist=new ArrayList<Znywelementsteprel>();
		znywelementsteprellist.add(znywelementsteprel);
		znywelementsteprellistdomain.setZnywelementsteprellist(znywelementsteprellist);
	
		ReturnValueDomain<String> ret=ZnywelementsteprelServiceAPProxy.add(znywelementsteprellistdomain);
		
		return ret;
	}
		
	/**
	 * 根据主键查询网元和模板步骤对应关系
	 * @param Znywelementsteprel
	 */
	@RequestMapping("/modifypage")
	public Object modifypage(HttpServletRequest request) throws Exception{
		Znywelementsteprel znywelementsteprel=FormUtil.request2Domain(request, Znywelementsteprel.class);
		
		ZnywelementsteprelQueryDomain znywelementsteprelquerydomain= new ZnywelementsteprelQueryDomain();
		znywelementsteprelquerydomain.setZnywelementsteprel(znywelementsteprel);
		znywelementsteprelquerydomain.setPageparam(null);
		
		ReturnValueDomain<PageDomain<Znywelementsteprel>> ret=ZnywelementsteprelServiceAPProxy.query(znywelementsteprelquerydomain);
		PageDomain<Znywelementsteprel> page = ret.getObject();
		List<Znywelementsteprel> znywelementsteprellist = page.getDatalist();
		
		if (NonUtil.isNotNon(znywelementsteprellist)) {
			request.setAttribute("znywelementsteprel", znywelementsteprellist.get(0));
		}
		
		return "/ahcnams_service/znywelementsteprel/modify.jsp";
	}
	
	/**
	 * 网元和模板步骤对应关系修改
	 * @param ZnywelementsteprelListDomain
	 */
	@RequestMapping("/modify")
	@ResponseBody
	public Object modify(HttpServletRequest request) throws Exception {
		// 传入的领域对象
		Znywelementsteprel znywelementsteprel = FormUtil.request2Domain(request, Znywelementsteprel.class);
		
		ZnywelementsteprelListDomain znywelementsteprellistdomain = new ZnywelementsteprelListDomain();
		List<Znywelementsteprel> znywelementsteprellist=new ArrayList<Znywelementsteprel>();
		znywelementsteprellist.add(znywelementsteprel);
		znywelementsteprellistdomain.setZnywelementsteprellist(znywelementsteprellist);
		
		ReturnValueDomain<String> ret=ZnywelementsteprelServiceAPProxy.update(znywelementsteprellistdomain);
		
		return ret;
	}

	/**
	 * 网元和模板步骤对应关系删除
	 * @param ZnywelementsteprelListDomain
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public Object delete(HttpServletRequest request) throws Exception{
		// 传入的领域对象
		List<Znywelementsteprel> znywelementsteprellist=new ArrayList<Znywelementsteprel>();
		
		String idsstr = request.getParameter("id");
		String[] idarray = idsstr.split(",");
		for(String id : idarray){
			Znywelementsteprel znywelementsteprel = new Znywelementsteprel();
			
			znywelementsteprel.setRelid(id);
			znywelementsteprellist.add(znywelementsteprel);
		}
		ZnywelementsteprelListDomain znywelementsteprellistdomain = new ZnywelementsteprelListDomain();
		znywelementsteprellistdomain.setZnywelementsteprellist(znywelementsteprellist);
		
		ReturnValueDomain<String> ret=ZnywelementsteprelServiceAPProxy.delete(znywelementsteprellistdomain);
		
		return ret;
	}
	
	/**
	 * 网元和模板步骤对应关系详情
	 * @param Znywelementsteprel
	 */
	@RequestMapping("/detail")
	public Object detail(HttpServletRequest request) throws Exception{
		// 传入的领域对象
		Znywelementsteprel znywelementsteprel=FormUtil.request2Domain(request, Znywelementsteprel.class);
		
		ZnywelementsteprelQueryDomain znywelementsteprelquerydomain= new ZnywelementsteprelQueryDomain();
		znywelementsteprelquerydomain.setZnywelementsteprel(znywelementsteprel);
		znywelementsteprelquerydomain.setPageparam(null);
		
		ReturnValueDomain<PageDomain<Znywelementsteprel>> ret=ZnywelementsteprelServiceAPProxy.query(znywelementsteprelquerydomain);
		PageDomain<Znywelementsteprel> page = ret.getObject();
		List<Znywelementsteprel> znywelementsteprellist = page.getDatalist();
		
		if (NonUtil.isNotNon(znywelementsteprellist)) {
			request.setAttribute("znywelementsteprel", znywelementsteprellist.get(0));
		}
				
		return "/ahcnams_service/znywelementsteprel/detail.jsp";
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
		
		return ZnywelementsteprelServiceAPProxy.importexcel(entityfile);
	}
	
	/**
	 * 导出
	 */
	@RequestMapping(value="/exportexcel")
	public void exportexcel(HttpServletRequest request, HttpServletResponse response) throws Exception{
		Znywelementsteprel znywelementsteprel=FormUtil.request2Domain(request, Znywelementsteprel.class);
		ZnywelementsteprelQueryDomain znywelementsteprelquerydomain = new ZnywelementsteprelQueryDomain();
		znywelementsteprelquerydomain.setZnywelementsteprel(znywelementsteprel);

		ReturnValueDomain<SysEntityfile> ret=ZnywelementsteprelServiceAPProxy.exportexcel(znywelementsteprelquerydomain);
		SysEntityfile entityfile=ret.getObject();
		String filepath=entityfile.getFilepath();
		String filename=entityfile.getFilename();
		
		FileDownloadUtil.filedownload(filename, filepath + File.separator + filename, request, response);
	}
}

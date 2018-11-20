/**
 * 模板和步骤关系管理Action
 * 
 * @version 1.0
 * @since 2018-11-20
 */
package cn.finedo.ahcnams.webapp.znywtemplatesteprel;

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
import cn.finedo.ahcnams.pojo.Znywtemplatesteprel;
import cn.finedo.ahcnams.service.znywtemplatesteprel.ZnywtemplatesteprelServiceAPProxy;
import cn.finedo.ahcnams.service.znywtemplatesteprel.domain.ZnywtemplatesteprelListDomain;
import cn.finedo.ahcnams.service.znywtemplatesteprel.domain.ZnywtemplatesteprelQueryDomain;

@Controller
@Scope("singleton")
@RequestMapping("/finedo/znywtemplatesteprel")
public class ZnywtemplatesteprelAction {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	/**
	 * 模板和步骤关系查询
	 * @param Znywtemplatesteprel
	 * @param PageParamDomain
	 */
	@RequestMapping("/query")
	@ResponseBody
	public Object query(HttpServletRequest request) throws Exception {
		Znywtemplatesteprel znywtemplatesteprel = FormUtil.request2Domain(request, Znywtemplatesteprel.class);
		PageParamDomain pageparam=PageUtil.getPageParam(request);
		
		ZnywtemplatesteprelQueryDomain znywtemplatesteprelquerydomain = new ZnywtemplatesteprelQueryDomain();
		znywtemplatesteprelquerydomain.setZnywtemplatesteprel(znywtemplatesteprel);
		znywtemplatesteprelquerydomain.setPageparam(pageparam);
		
		ReturnValueDomain<PageDomain<Znywtemplatesteprel>> ret=ZnywtemplatesteprelServiceAPProxy.query(znywtemplatesteprelquerydomain);
		PageDomain<Znywtemplatesteprel> page = ret.getObject();
		
		return PageUtil.build(page.getDatalist(), page.getRowcount());
	}
	
	/**
	 * 模板和步骤关系新增
	 * @param ZnywtemplatesteprelListDomain
	 */
	@RequestMapping("/add")
	@ResponseBody
	public Object add(HttpServletRequest request) throws Exception {
		Znywtemplatesteprel znywtemplatesteprel = FormUtil.request2Domain(request, Znywtemplatesteprel.class);
		
		ZnywtemplatesteprelListDomain znywtemplatesteprellistdomain = new ZnywtemplatesteprelListDomain();
		List<Znywtemplatesteprel> znywtemplatesteprellist=new ArrayList<Znywtemplatesteprel>();
		znywtemplatesteprellist.add(znywtemplatesteprel);
		znywtemplatesteprellistdomain.setZnywtemplatesteprellist(znywtemplatesteprellist);
	
		ReturnValueDomain<String> ret=ZnywtemplatesteprelServiceAPProxy.add(znywtemplatesteprellistdomain);
		
		return ret;
	}
		
	/**
	 * 根据主键查询模板和步骤关系
	 * @param Znywtemplatesteprel
	 */
	@RequestMapping("/modifypage")
	public Object modifypage(HttpServletRequest request) throws Exception{
		Znywtemplatesteprel znywtemplatesteprel=FormUtil.request2Domain(request, Znywtemplatesteprel.class);
		
		ZnywtemplatesteprelQueryDomain znywtemplatesteprelquerydomain= new ZnywtemplatesteprelQueryDomain();
		znywtemplatesteprelquerydomain.setZnywtemplatesteprel(znywtemplatesteprel);
		znywtemplatesteprelquerydomain.setPageparam(null);
		
		ReturnValueDomain<PageDomain<Znywtemplatesteprel>> ret=ZnywtemplatesteprelServiceAPProxy.query(znywtemplatesteprelquerydomain);
		PageDomain<Znywtemplatesteprel> page = ret.getObject();
		List<Znywtemplatesteprel> znywtemplatesteprellist = page.getDatalist();
		
		if (NonUtil.isNotNon(znywtemplatesteprellist)) {
			request.setAttribute("znywtemplatesteprel", znywtemplatesteprellist.get(0));
		}
		
		return "/ahcnams_service/znywtemplatesteprel/modify.jsp";
	}
	
	/**
	 * 模板和步骤关系修改
	 * @param ZnywtemplatesteprelListDomain
	 */
	@RequestMapping("/modify")
	@ResponseBody
	public Object modify(HttpServletRequest request) throws Exception {
		// 传入的领域对象
		Znywtemplatesteprel znywtemplatesteprel = FormUtil.request2Domain(request, Znywtemplatesteprel.class);
		
		ZnywtemplatesteprelListDomain znywtemplatesteprellistdomain = new ZnywtemplatesteprelListDomain();
		List<Znywtemplatesteprel> znywtemplatesteprellist=new ArrayList<Znywtemplatesteprel>();
		znywtemplatesteprellist.add(znywtemplatesteprel);
		znywtemplatesteprellistdomain.setZnywtemplatesteprellist(znywtemplatesteprellist);
		
		ReturnValueDomain<String> ret=ZnywtemplatesteprelServiceAPProxy.update(znywtemplatesteprellistdomain);
		
		return ret;
	}

	/**
	 * 模板和步骤关系删除
	 * @param ZnywtemplatesteprelListDomain
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public Object delete(HttpServletRequest request) throws Exception{
		// 传入的领域对象
		List<Znywtemplatesteprel> znywtemplatesteprellist=new ArrayList<Znywtemplatesteprel>();
		
		String idsstr = request.getParameter("id");
		String[] idarray = idsstr.split(",");
		for(String id : idarray){
			Znywtemplatesteprel znywtemplatesteprel = new Znywtemplatesteprel();
			
			znywtemplatesteprel.setRelid(id);
			znywtemplatesteprellist.add(znywtemplatesteprel);
		}
		ZnywtemplatesteprelListDomain znywtemplatesteprellistdomain = new ZnywtemplatesteprelListDomain();
		znywtemplatesteprellistdomain.setZnywtemplatesteprellist(znywtemplatesteprellist);
		
		ReturnValueDomain<String> ret=ZnywtemplatesteprelServiceAPProxy.delete(znywtemplatesteprellistdomain);
		
		return ret;
	}
	
	/**
	 * 模板和步骤关系详情
	 * @param Znywtemplatesteprel
	 */
	@RequestMapping("/detail")
	public Object detail(HttpServletRequest request) throws Exception{
		// 传入的领域对象
		Znywtemplatesteprel znywtemplatesteprel=FormUtil.request2Domain(request, Znywtemplatesteprel.class);
		
		ZnywtemplatesteprelQueryDomain znywtemplatesteprelquerydomain= new ZnywtemplatesteprelQueryDomain();
		znywtemplatesteprelquerydomain.setZnywtemplatesteprel(znywtemplatesteprel);
		znywtemplatesteprelquerydomain.setPageparam(null);
		
		ReturnValueDomain<PageDomain<Znywtemplatesteprel>> ret=ZnywtemplatesteprelServiceAPProxy.query(znywtemplatesteprelquerydomain);
		PageDomain<Znywtemplatesteprel> page = ret.getObject();
		List<Znywtemplatesteprel> znywtemplatesteprellist = page.getDatalist();
		
		if (NonUtil.isNotNon(znywtemplatesteprellist)) {
			request.setAttribute("znywtemplatesteprel", znywtemplatesteprellist.get(0));
		}
				
		return "/ahcnams_service/znywtemplatesteprel/detail.jsp";
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
		
		return ZnywtemplatesteprelServiceAPProxy.importexcel(entityfile);
	}
	
	/**
	 * 导出
	 */
	@RequestMapping(value="/exportexcel")
	public void exportexcel(HttpServletRequest request, HttpServletResponse response) throws Exception{
		Znywtemplatesteprel znywtemplatesteprel=FormUtil.request2Domain(request, Znywtemplatesteprel.class);
		ZnywtemplatesteprelQueryDomain znywtemplatesteprelquerydomain = new ZnywtemplatesteprelQueryDomain();
		znywtemplatesteprelquerydomain.setZnywtemplatesteprel(znywtemplatesteprel);

		ReturnValueDomain<SysEntityfile> ret=ZnywtemplatesteprelServiceAPProxy.exportexcel(znywtemplatesteprelquerydomain);
		SysEntityfile entityfile=ret.getObject();
		String filepath=entityfile.getFilepath();
		String filename=entityfile.getFilename();
		
		FileDownloadUtil.filedownload(filename, filepath + File.separator + filename, request, response);
	}
}

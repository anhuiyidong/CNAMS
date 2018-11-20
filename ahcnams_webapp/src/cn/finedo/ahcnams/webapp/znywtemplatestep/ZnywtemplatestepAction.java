/**
 * 模板步骤管理Action
 * 
 * @version 1.0
 * @since 2018-11-20
 */
package cn.finedo.ahcnams.webapp.znywtemplatestep;

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
import cn.finedo.ahcnams.pojo.Znywtemplatestep;
import cn.finedo.ahcnams.service.znywtemplatestep.ZnywtemplatestepServiceAPProxy;
import cn.finedo.ahcnams.service.znywtemplatestep.domain.ZnywtemplatestepListDomain;
import cn.finedo.ahcnams.service.znywtemplatestep.domain.ZnywtemplatestepQueryDomain;

@Controller
@Scope("singleton")
@RequestMapping("/finedo/znywtemplatestep")
public class ZnywtemplatestepAction {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	/**
	 * 模板步骤查询
	 * @param Znywtemplatestep
	 * @param PageParamDomain
	 */
	@RequestMapping("/query")
	@ResponseBody
	public Object query(HttpServletRequest request) throws Exception {
		Znywtemplatestep znywtemplatestep = FormUtil.request2Domain(request, Znywtemplatestep.class);
		PageParamDomain pageparam=PageUtil.getPageParam(request);
		
		ZnywtemplatestepQueryDomain znywtemplatestepquerydomain = new ZnywtemplatestepQueryDomain();
		znywtemplatestepquerydomain.setZnywtemplatestep(znywtemplatestep);
		znywtemplatestepquerydomain.setPageparam(pageparam);
		
		ReturnValueDomain<PageDomain<Znywtemplatestep>> ret=ZnywtemplatestepServiceAPProxy.query(znywtemplatestepquerydomain);
		PageDomain<Znywtemplatestep> page = ret.getObject();
		
		return PageUtil.build(page.getDatalist(), page.getRowcount());
	}
	
	/**
	 * 模板步骤新增
	 * @param ZnywtemplatestepListDomain
	 */
	@RequestMapping("/add")
	@ResponseBody
	public Object add(HttpServletRequest request) throws Exception {
		Znywtemplatestep znywtemplatestep = FormUtil.request2Domain(request, Znywtemplatestep.class);
		
		ZnywtemplatestepListDomain znywtemplatesteplistdomain = new ZnywtemplatestepListDomain();
		List<Znywtemplatestep> znywtemplatesteplist=new ArrayList<Znywtemplatestep>();
		znywtemplatesteplist.add(znywtemplatestep);
		znywtemplatesteplistdomain.setZnywtemplatesteplist(znywtemplatesteplist);
	
		ReturnValueDomain<String> ret=ZnywtemplatestepServiceAPProxy.add(znywtemplatesteplistdomain);
		
		return ret;
	}
		
	/**
	 * 根据主键查询模板步骤
	 * @param Znywtemplatestep
	 */
	@RequestMapping("/modifypage")
	public Object modifypage(HttpServletRequest request) throws Exception{
		Znywtemplatestep znywtemplatestep=FormUtil.request2Domain(request, Znywtemplatestep.class);
		
		ZnywtemplatestepQueryDomain znywtemplatestepquerydomain= new ZnywtemplatestepQueryDomain();
		znywtemplatestepquerydomain.setZnywtemplatestep(znywtemplatestep);
		znywtemplatestepquerydomain.setPageparam(null);
		
		ReturnValueDomain<PageDomain<Znywtemplatestep>> ret=ZnywtemplatestepServiceAPProxy.query(znywtemplatestepquerydomain);
		PageDomain<Znywtemplatestep> page = ret.getObject();
		List<Znywtemplatestep> znywtemplatesteplist = page.getDatalist();
		
		if (NonUtil.isNotNon(znywtemplatesteplist)) {
			request.setAttribute("znywtemplatestep", znywtemplatesteplist.get(0));
		}
		
		return "/ahcnams_service/znywtemplatestep/modify.jsp";
	}
	
	/**
	 * 模板步骤修改
	 * @param ZnywtemplatestepListDomain
	 */
	@RequestMapping("/modify")
	@ResponseBody
	public Object modify(HttpServletRequest request) throws Exception {
		// 传入的领域对象
		Znywtemplatestep znywtemplatestep = FormUtil.request2Domain(request, Znywtemplatestep.class);
		
		ZnywtemplatestepListDomain znywtemplatesteplistdomain = new ZnywtemplatestepListDomain();
		List<Znywtemplatestep> znywtemplatesteplist=new ArrayList<Znywtemplatestep>();
		znywtemplatesteplist.add(znywtemplatestep);
		znywtemplatesteplistdomain.setZnywtemplatesteplist(znywtemplatesteplist);
		
		ReturnValueDomain<String> ret=ZnywtemplatestepServiceAPProxy.update(znywtemplatesteplistdomain);
		
		return ret;
	}

	/**
	 * 模板步骤删除
	 * @param ZnywtemplatestepListDomain
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public Object delete(HttpServletRequest request) throws Exception{
		// 传入的领域对象
		List<Znywtemplatestep> znywtemplatesteplist=new ArrayList<Znywtemplatestep>();
		
		String idsstr = request.getParameter("id");
		String[] idarray = idsstr.split(",");
		for(String id : idarray){
			Znywtemplatestep znywtemplatestep = new Znywtemplatestep();
			
			znywtemplatestep.setStepid(id);
			znywtemplatesteplist.add(znywtemplatestep);
		}
		ZnywtemplatestepListDomain znywtemplatesteplistdomain = new ZnywtemplatestepListDomain();
		znywtemplatesteplistdomain.setZnywtemplatesteplist(znywtemplatesteplist);
		
		ReturnValueDomain<String> ret=ZnywtemplatestepServiceAPProxy.delete(znywtemplatesteplistdomain);
		
		return ret;
	}
	
	/**
	 * 模板步骤详情
	 * @param Znywtemplatestep
	 */
	@RequestMapping("/detail")
	public Object detail(HttpServletRequest request) throws Exception{
		// 传入的领域对象
		Znywtemplatestep znywtemplatestep=FormUtil.request2Domain(request, Znywtemplatestep.class);
		
		ZnywtemplatestepQueryDomain znywtemplatestepquerydomain= new ZnywtemplatestepQueryDomain();
		znywtemplatestepquerydomain.setZnywtemplatestep(znywtemplatestep);
		znywtemplatestepquerydomain.setPageparam(null);
		
		ReturnValueDomain<PageDomain<Znywtemplatestep>> ret=ZnywtemplatestepServiceAPProxy.query(znywtemplatestepquerydomain);
		PageDomain<Znywtemplatestep> page = ret.getObject();
		List<Znywtemplatestep> znywtemplatesteplist = page.getDatalist();
		
		if (NonUtil.isNotNon(znywtemplatesteplist)) {
			request.setAttribute("znywtemplatestep", znywtemplatesteplist.get(0));
		}
				
		return "/ahcnams_service/znywtemplatestep/detail.jsp";
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
		
		return ZnywtemplatestepServiceAPProxy.importexcel(entityfile);
	}
	
	/**
	 * 导出
	 */
	@RequestMapping(value="/exportexcel")
	public void exportexcel(HttpServletRequest request, HttpServletResponse response) throws Exception{
		Znywtemplatestep znywtemplatestep=FormUtil.request2Domain(request, Znywtemplatestep.class);
		ZnywtemplatestepQueryDomain znywtemplatestepquerydomain = new ZnywtemplatestepQueryDomain();
		znywtemplatestepquerydomain.setZnywtemplatestep(znywtemplatestep);

		ReturnValueDomain<SysEntityfile> ret=ZnywtemplatestepServiceAPProxy.exportexcel(znywtemplatestepquerydomain);
		SysEntityfile entityfile=ret.getObject();
		String filepath=entityfile.getFilepath();
		String filename=entityfile.getFilename();
		
		FileDownloadUtil.filedownload(filename, filepath + File.separator + filename, request, response);
	}
}

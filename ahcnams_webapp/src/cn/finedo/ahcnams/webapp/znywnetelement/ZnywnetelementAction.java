/**
 * 网元信息管理Action
 * 
 * @version 1.0
 * @since 2018-11-20
 */
package cn.finedo.ahcnams.webapp.znywnetelement;

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
import cn.finedo.ahcnams.pojo.Znywnetelement;
import cn.finedo.ahcnams.service.znywnetelement.ZnywnetelementServiceAPProxy;
import cn.finedo.ahcnams.service.znywnetelement.domain.ZnywnetelementListDomain;
import cn.finedo.ahcnams.service.znywnetelement.domain.ZnywnetelementQueryDomain;

@Controller
@Scope("singleton")
@RequestMapping("/finedo/znywnetelement")
public class ZnywnetelementAction {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	/**
	 * 网元信息查询
	 * @param Znywnetelement
	 * @param PageParamDomain
	 */
	@RequestMapping("/query")
	@ResponseBody
	public Object query(HttpServletRequest request) throws Exception {
		Znywnetelement znywnetelement = FormUtil.request2Domain(request, Znywnetelement.class);
		PageParamDomain pageparam=PageUtil.getPageParam(request);
		
		ZnywnetelementQueryDomain znywnetelementquerydomain = new ZnywnetelementQueryDomain();
		znywnetelementquerydomain.setZnywnetelement(znywnetelement);
		znywnetelementquerydomain.setPageparam(pageparam);
		
		ReturnValueDomain<PageDomain<Znywnetelement>> ret=ZnywnetelementServiceAPProxy.query(znywnetelementquerydomain);
		PageDomain<Znywnetelement> page = ret.getObject();
		
		return PageUtil.build(page.getDatalist(), page.getRowcount());
	}
	
	/**
	 * 网元信息新增
	 * @param ZnywnetelementListDomain
	 */
	@RequestMapping("/add")
	@ResponseBody
	public Object add(HttpServletRequest request) throws Exception {
		Znywnetelement znywnetelement = FormUtil.request2Domain(request, Znywnetelement.class);
		
		ZnywnetelementListDomain znywnetelementlistdomain = new ZnywnetelementListDomain();
		List<Znywnetelement> znywnetelementlist=new ArrayList<Znywnetelement>();
		znywnetelementlist.add(znywnetelement);
		znywnetelementlistdomain.setZnywnetelementlist(znywnetelementlist);
	
		ReturnValueDomain<String> ret=ZnywnetelementServiceAPProxy.add(znywnetelementlistdomain);
		
		return ret;
	}
		
	/**
	 * 根据主键查询网元信息
	 * @param Znywnetelement
	 */
	@RequestMapping("/modifypage")
	public Object modifypage(HttpServletRequest request) throws Exception{
		Znywnetelement znywnetelement=FormUtil.request2Domain(request, Znywnetelement.class);
		
		ZnywnetelementQueryDomain znywnetelementquerydomain= new ZnywnetelementQueryDomain();
		znywnetelementquerydomain.setZnywnetelement(znywnetelement);
		znywnetelementquerydomain.setPageparam(null);
		
		ReturnValueDomain<PageDomain<Znywnetelement>> ret=ZnywnetelementServiceAPProxy.query(znywnetelementquerydomain);
		PageDomain<Znywnetelement> page = ret.getObject();
		List<Znywnetelement> znywnetelementlist = page.getDatalist();
		
		if (NonUtil.isNotNon(znywnetelementlist)) {
			request.setAttribute("znywnetelement", znywnetelementlist.get(0));
		}
		
		return "/ahcnams_service/znywnetelement/modify.jsp";
	}
	
	/**
	 * 网元信息修改
	 * @param ZnywnetelementListDomain
	 */
	@RequestMapping("/modify")
	@ResponseBody
	public Object modify(HttpServletRequest request) throws Exception {
		// 传入的领域对象
		Znywnetelement znywnetelement = FormUtil.request2Domain(request, Znywnetelement.class);
		
		ZnywnetelementListDomain znywnetelementlistdomain = new ZnywnetelementListDomain();
		List<Znywnetelement> znywnetelementlist=new ArrayList<Znywnetelement>();
		znywnetelementlist.add(znywnetelement);
		znywnetelementlistdomain.setZnywnetelementlist(znywnetelementlist);
		
		ReturnValueDomain<String> ret=ZnywnetelementServiceAPProxy.update(znywnetelementlistdomain);
		
		return ret;
	}

	/**
	 * 网元信息删除
	 * @param ZnywnetelementListDomain
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public Object delete(HttpServletRequest request) throws Exception{
		// 传入的领域对象
		List<Znywnetelement> znywnetelementlist=new ArrayList<Znywnetelement>();
		
		String idsstr = request.getParameter("id");
		String[] idarray = idsstr.split(",");
		for(String id : idarray){
			Znywnetelement znywnetelement = new Znywnetelement();
			
			znywnetelement.setEletid(id);
			znywnetelementlist.add(znywnetelement);
		}
		ZnywnetelementListDomain znywnetelementlistdomain = new ZnywnetelementListDomain();
		znywnetelementlistdomain.setZnywnetelementlist(znywnetelementlist);
		
		ReturnValueDomain<String> ret=ZnywnetelementServiceAPProxy.delete(znywnetelementlistdomain);
		
		return ret;
	}
	
	/**
	 * 网元信息详情
	 * @param Znywnetelement
	 */
	@RequestMapping("/detail")
	public Object detail(HttpServletRequest request) throws Exception{
		// 传入的领域对象
		Znywnetelement znywnetelement=FormUtil.request2Domain(request, Znywnetelement.class);
		
		ZnywnetelementQueryDomain znywnetelementquerydomain= new ZnywnetelementQueryDomain();
		znywnetelementquerydomain.setZnywnetelement(znywnetelement);
		znywnetelementquerydomain.setPageparam(null);
		
		ReturnValueDomain<PageDomain<Znywnetelement>> ret=ZnywnetelementServiceAPProxy.query(znywnetelementquerydomain);
		PageDomain<Znywnetelement> page = ret.getObject();
		List<Znywnetelement> znywnetelementlist = page.getDatalist();
		
		if (NonUtil.isNotNon(znywnetelementlist)) {
			request.setAttribute("znywnetelement", znywnetelementlist.get(0));
		}
				
		return "/ahcnams_service/znywnetelement/detail.jsp";
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
		
		return ZnywnetelementServiceAPProxy.importexcel(entityfile);
	}
	
	/**
	 * 导出
	 */
	@RequestMapping(value="/exportexcel")
	public void exportexcel(HttpServletRequest request, HttpServletResponse response) throws Exception{
		Znywnetelement znywnetelement=FormUtil.request2Domain(request, Znywnetelement.class);
		ZnywnetelementQueryDomain znywnetelementquerydomain = new ZnywnetelementQueryDomain();
		znywnetelementquerydomain.setZnywnetelement(znywnetelement);

		ReturnValueDomain<SysEntityfile> ret=ZnywnetelementServiceAPProxy.exportexcel(znywnetelementquerydomain);
		SysEntityfile entityfile=ret.getObject();
		String filepath=entityfile.getFilepath();
		String filename=entityfile.getFilename();
		
		FileDownloadUtil.filedownload(filename, filepath + File.separator + filename, request, response);
	}
}

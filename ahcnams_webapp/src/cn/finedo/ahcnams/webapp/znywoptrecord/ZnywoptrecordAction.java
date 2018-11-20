/**
 * 操作记录管理Action
 * 
 * @version 1.0
 * @since 2018-11-20
 */
package cn.finedo.ahcnams.webapp.znywoptrecord;

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
import cn.finedo.ahcnams.pojo.Znywoptrecord;
import cn.finedo.ahcnams.service.znywoptrecord.ZnywoptrecordServiceAPProxy;
import cn.finedo.ahcnams.service.znywoptrecord.domain.ZnywoptrecordListDomain;
import cn.finedo.ahcnams.service.znywoptrecord.domain.ZnywoptrecordQueryDomain;

@Controller
@Scope("singleton")
@RequestMapping("/finedo/znywoptrecord")
public class ZnywoptrecordAction {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	/**
	 * 操作记录查询
	 * @param Znywoptrecord
	 * @param PageParamDomain
	 */
	@RequestMapping("/query")
	@ResponseBody
	public Object query(HttpServletRequest request) throws Exception {
		Znywoptrecord znywoptrecord = FormUtil.request2Domain(request, Znywoptrecord.class);
		PageParamDomain pageparam=PageUtil.getPageParam(request);
		
		ZnywoptrecordQueryDomain znywoptrecordquerydomain = new ZnywoptrecordQueryDomain();
		znywoptrecordquerydomain.setZnywoptrecord(znywoptrecord);
		znywoptrecordquerydomain.setPageparam(pageparam);
		
		ReturnValueDomain<PageDomain<Znywoptrecord>> ret=ZnywoptrecordServiceAPProxy.query(znywoptrecordquerydomain);
		PageDomain<Znywoptrecord> page = ret.getObject();
		
		return PageUtil.build(page.getDatalist(), page.getRowcount());
	}
	
	/**
	 * 操作记录新增
	 * @param ZnywoptrecordListDomain
	 */
	@RequestMapping("/add")
	@ResponseBody
	public Object add(HttpServletRequest request) throws Exception {
		Znywoptrecord znywoptrecord = FormUtil.request2Domain(request, Znywoptrecord.class);
		
		ZnywoptrecordListDomain znywoptrecordlistdomain = new ZnywoptrecordListDomain();
		List<Znywoptrecord> znywoptrecordlist=new ArrayList<Znywoptrecord>();
		znywoptrecordlist.add(znywoptrecord);
		znywoptrecordlistdomain.setZnywoptrecordlist(znywoptrecordlist);
	
		ReturnValueDomain<String> ret=ZnywoptrecordServiceAPProxy.add(znywoptrecordlistdomain);
		
		return ret;
	}
		
	/**
	 * 根据主键查询操作记录
	 * @param Znywoptrecord
	 */
	@RequestMapping("/modifypage")
	public Object modifypage(HttpServletRequest request) throws Exception{
		Znywoptrecord znywoptrecord=FormUtil.request2Domain(request, Znywoptrecord.class);
		
		ZnywoptrecordQueryDomain znywoptrecordquerydomain= new ZnywoptrecordQueryDomain();
		znywoptrecordquerydomain.setZnywoptrecord(znywoptrecord);
		znywoptrecordquerydomain.setPageparam(null);
		
		ReturnValueDomain<PageDomain<Znywoptrecord>> ret=ZnywoptrecordServiceAPProxy.query(znywoptrecordquerydomain);
		PageDomain<Znywoptrecord> page = ret.getObject();
		List<Znywoptrecord> znywoptrecordlist = page.getDatalist();
		
		if (NonUtil.isNotNon(znywoptrecordlist)) {
			request.setAttribute("znywoptrecord", znywoptrecordlist.get(0));
		}
		
		return "/ahcnams_service/znywoptrecord/modify.jsp";
	}
	
	/**
	 * 操作记录修改
	 * @param ZnywoptrecordListDomain
	 */
	@RequestMapping("/modify")
	@ResponseBody
	public Object modify(HttpServletRequest request) throws Exception {
		// 传入的领域对象
		Znywoptrecord znywoptrecord = FormUtil.request2Domain(request, Znywoptrecord.class);
		
		ZnywoptrecordListDomain znywoptrecordlistdomain = new ZnywoptrecordListDomain();
		List<Znywoptrecord> znywoptrecordlist=new ArrayList<Znywoptrecord>();
		znywoptrecordlist.add(znywoptrecord);
		znywoptrecordlistdomain.setZnywoptrecordlist(znywoptrecordlist);
		
		ReturnValueDomain<String> ret=ZnywoptrecordServiceAPProxy.update(znywoptrecordlistdomain);
		
		return ret;
	}

	/**
	 * 操作记录删除
	 * @param ZnywoptrecordListDomain
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public Object delete(HttpServletRequest request) throws Exception{
		// 传入的领域对象
		List<Znywoptrecord> znywoptrecordlist=new ArrayList<Znywoptrecord>();
		
		String idsstr = request.getParameter("id");
		String[] idarray = idsstr.split(",");
		for(String id : idarray){
			Znywoptrecord znywoptrecord = new Znywoptrecord();
			
			znywoptrecord.setOptid(id);
			znywoptrecordlist.add(znywoptrecord);
		}
		ZnywoptrecordListDomain znywoptrecordlistdomain = new ZnywoptrecordListDomain();
		znywoptrecordlistdomain.setZnywoptrecordlist(znywoptrecordlist);
		
		ReturnValueDomain<String> ret=ZnywoptrecordServiceAPProxy.delete(znywoptrecordlistdomain);
		
		return ret;
	}
	
	/**
	 * 操作记录详情
	 * @param Znywoptrecord
	 */
	@RequestMapping("/detail")
	public Object detail(HttpServletRequest request) throws Exception{
		// 传入的领域对象
		Znywoptrecord znywoptrecord=FormUtil.request2Domain(request, Znywoptrecord.class);
		
		ZnywoptrecordQueryDomain znywoptrecordquerydomain= new ZnywoptrecordQueryDomain();
		znywoptrecordquerydomain.setZnywoptrecord(znywoptrecord);
		znywoptrecordquerydomain.setPageparam(null);
		
		ReturnValueDomain<PageDomain<Znywoptrecord>> ret=ZnywoptrecordServiceAPProxy.query(znywoptrecordquerydomain);
		PageDomain<Znywoptrecord> page = ret.getObject();
		List<Znywoptrecord> znywoptrecordlist = page.getDatalist();
		
		if (NonUtil.isNotNon(znywoptrecordlist)) {
			request.setAttribute("znywoptrecord", znywoptrecordlist.get(0));
		}
				
		return "/ahcnams_service/znywoptrecord/detail.jsp";
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
		
		return ZnywoptrecordServiceAPProxy.importexcel(entityfile);
	}
	
	/**
	 * 导出
	 */
	@RequestMapping(value="/exportexcel")
	public void exportexcel(HttpServletRequest request, HttpServletResponse response) throws Exception{
		Znywoptrecord znywoptrecord=FormUtil.request2Domain(request, Znywoptrecord.class);
		ZnywoptrecordQueryDomain znywoptrecordquerydomain = new ZnywoptrecordQueryDomain();
		znywoptrecordquerydomain.setZnywoptrecord(znywoptrecord);

		ReturnValueDomain<SysEntityfile> ret=ZnywoptrecordServiceAPProxy.exportexcel(znywoptrecordquerydomain);
		SysEntityfile entityfile=ret.getObject();
		String filepath=entityfile.getFilepath();
		String filename=entityfile.getFilename();
		
		FileDownloadUtil.filedownload(filename, filepath + File.separator + filename, request, response);
	}
}

/**
 * 任务管理Action
 * 
 * @version 1.0
 * @since 2018-11-20
 */
package cn.finedo.ahcnams.webapp.znywtask;

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
import cn.finedo.ahcnams.pojo.Znywtask;
import cn.finedo.ahcnams.service.znywtask.ZnywtaskServiceAPProxy;
import cn.finedo.ahcnams.service.znywtask.domain.ZnywtaskListDomain;
import cn.finedo.ahcnams.service.znywtask.domain.ZnywtaskQueryDomain;

@Controller
@Scope("singleton")
@RequestMapping("/finedo/znywtask")
public class ZnywtaskAction {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	/**
	 * 任务查询
	 * @param Znywtask
	 * @param PageParamDomain
	 */
	@RequestMapping("/query")
	@ResponseBody
	public Object query(HttpServletRequest request) throws Exception {
		Znywtask znywtask = FormUtil.request2Domain(request, Znywtask.class);
		PageParamDomain pageparam=PageUtil.getPageParam(request);
		
		ZnywtaskQueryDomain znywtaskquerydomain = new ZnywtaskQueryDomain();
		znywtaskquerydomain.setZnywtask(znywtask);
		znywtaskquerydomain.setPageparam(pageparam);
		
		ReturnValueDomain<PageDomain<Znywtask>> ret=ZnywtaskServiceAPProxy.query(znywtaskquerydomain);
		PageDomain<Znywtask> page = ret.getObject();
		
		return PageUtil.build(page.getDatalist(), page.getRowcount());
	}
	
	/**
	 * 任务新增
	 * @param ZnywtaskListDomain
	 */
	@RequestMapping("/add")
	@ResponseBody
	public Object add(HttpServletRequest request) throws Exception {
		Znywtask znywtask = FormUtil.request2Domain(request, Znywtask.class);
		
		ZnywtaskListDomain znywtasklistdomain = new ZnywtaskListDomain();
		List<Znywtask> znywtasklist=new ArrayList<Znywtask>();
		znywtasklist.add(znywtask);
		znywtasklistdomain.setZnywtasklist(znywtasklist);
	
		ReturnValueDomain<String> ret=ZnywtaskServiceAPProxy.add(znywtasklistdomain);
		
		return ret;
	}
		
	/**
	 * 根据主键查询任务
	 * @param Znywtask
	 */
	@RequestMapping("/modifypage")
	public Object modifypage(HttpServletRequest request) throws Exception{
		Znywtask znywtask=FormUtil.request2Domain(request, Znywtask.class);
		
		ZnywtaskQueryDomain znywtaskquerydomain= new ZnywtaskQueryDomain();
		znywtaskquerydomain.setZnywtask(znywtask);
		znywtaskquerydomain.setPageparam(null);
		
		ReturnValueDomain<PageDomain<Znywtask>> ret=ZnywtaskServiceAPProxy.query(znywtaskquerydomain);
		PageDomain<Znywtask> page = ret.getObject();
		List<Znywtask> znywtasklist = page.getDatalist();
		
		if (NonUtil.isNotNon(znywtasklist)) {
			request.setAttribute("znywtask", znywtasklist.get(0));
		}
		
		return "/ahcnams_service/znywtask/modify.jsp";
	}
	
	/**
	 * 任务修改
	 * @param ZnywtaskListDomain
	 */
	@RequestMapping("/modify")
	@ResponseBody
	public Object modify(HttpServletRequest request) throws Exception {
		// 传入的领域对象
		Znywtask znywtask = FormUtil.request2Domain(request, Znywtask.class);
		
		ZnywtaskListDomain znywtasklistdomain = new ZnywtaskListDomain();
		List<Znywtask> znywtasklist=new ArrayList<Znywtask>();
		znywtasklist.add(znywtask);
		znywtasklistdomain.setZnywtasklist(znywtasklist);
		
		ReturnValueDomain<String> ret=ZnywtaskServiceAPProxy.update(znywtasklistdomain);
		
		return ret;
	}

	/**
	 * 任务删除
	 * @param ZnywtaskListDomain
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public Object delete(HttpServletRequest request) throws Exception{
		// 传入的领域对象
		List<Znywtask> znywtasklist=new ArrayList<Znywtask>();
		
		String idsstr = request.getParameter("id");
		String[] idarray = idsstr.split(",");
		for(String id : idarray){
			Znywtask znywtask = new Znywtask();
			
			znywtask.setTaskid(id);
			znywtasklist.add(znywtask);
		}
		ZnywtaskListDomain znywtasklistdomain = new ZnywtaskListDomain();
		znywtasklistdomain.setZnywtasklist(znywtasklist);
		
		ReturnValueDomain<String> ret=ZnywtaskServiceAPProxy.delete(znywtasklistdomain);
		
		return ret;
	}
	
	/**
	 * 任务详情
	 * @param Znywtask
	 */
	@RequestMapping("/detail")
	public Object detail(HttpServletRequest request) throws Exception{
		// 传入的领域对象
		Znywtask znywtask=FormUtil.request2Domain(request, Znywtask.class);
		
		ZnywtaskQueryDomain znywtaskquerydomain= new ZnywtaskQueryDomain();
		znywtaskquerydomain.setZnywtask(znywtask);
		znywtaskquerydomain.setPageparam(null);
		
		ReturnValueDomain<PageDomain<Znywtask>> ret=ZnywtaskServiceAPProxy.query(znywtaskquerydomain);
		PageDomain<Znywtask> page = ret.getObject();
		List<Znywtask> znywtasklist = page.getDatalist();
		
		if (NonUtil.isNotNon(znywtasklist)) {
			request.setAttribute("znywtask", znywtasklist.get(0));
		}
				
		return "/ahcnams_service/znywtask/detail.jsp";
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
		
		return ZnywtaskServiceAPProxy.importexcel(entityfile);
	}
	
	/**
	 * 导出
	 */
	@RequestMapping(value="/exportexcel")
	public void exportexcel(HttpServletRequest request, HttpServletResponse response) throws Exception{
		Znywtask znywtask=FormUtil.request2Domain(request, Znywtask.class);
		ZnywtaskQueryDomain znywtaskquerydomain = new ZnywtaskQueryDomain();
		znywtaskquerydomain.setZnywtask(znywtask);

		ReturnValueDomain<SysEntityfile> ret=ZnywtaskServiceAPProxy.exportexcel(znywtaskquerydomain);
		SysEntityfile entityfile=ret.getObject();
		String filepath=entityfile.getFilepath();
		String filename=entityfile.getFilename();
		
		FileDownloadUtil.filedownload(filename, filepath + File.separator + filename, request, response);
	}
}

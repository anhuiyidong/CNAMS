/**
 * 模板和步骤关系管理服务
 * 
 * @version 1.0
 * @since 2018-11-20
 */
package cn.finedo.ahcnams.service.znywtemplatesteprel;

import java.util.List;
import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import cn.finedo.common.domain.PageDomain;
import cn.finedo.common.domain.PageParamDomain;
import cn.finedo.common.domain.ReturnValueDomain;
import cn.finedo.fsdp.service.common.exception.TransactionException;
import cn.finedo.fsdp.service.common.id.IDUtil;
import cn.finedo.fsdp.service.common.jdbc.JdbcTemplate;
import cn.finedo.common.non.NonUtil;
import cn.finedo.ahcnams.pojo.Znywtemplatesteprel;
import cn.finedo.ahcnams.service.znywtemplatesteprel.domain.ZnywtemplatesteprelListDomain;
import cn.finedo.ahcnams.service.znywtemplatesteprel.domain.ZnywtemplatesteprelQueryDomain;

@Service
@Transactional
@Scope("singleton")
public class ZnywtemplatesteprelService {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Resource(name="jdbcTemplate")
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private IDUtil idutil;
	
		
	/**
	 * 模板和步骤关系查询
	 * @param ZnywtemplatesteprelQueryDomain
	 * @return ReturnValueDomain<PageDomain<Znywtemplatesteprel>>
	 */
	public ReturnValueDomain<PageDomain<Znywtemplatesteprel>> query(ZnywtemplatesteprelQueryDomain znywtemplatesteprelquerydomain) {
		ReturnValueDomain<PageDomain<Znywtemplatesteprel>> ret = new ReturnValueDomain<PageDomain<Znywtemplatesteprel>>();
		
		StringBuilder sql=new StringBuilder("SELECT * FROM tb_znyw_template_step_rel WHERE 1=1");
			
		Znywtemplatesteprel znywtemplatesteprel=null;
		PageParamDomain pageparam=null;
		if(NonUtil.isNotNon(znywtemplatesteprelquerydomain)) {
			pageparam=znywtemplatesteprelquerydomain.getPageparam();
			znywtemplatesteprel=znywtemplatesteprelquerydomain.getZnywtemplatesteprel();
			
			if(NonUtil.isNotNon(znywtemplatesteprel)) {
				
				if(NonUtil.isNotNon(znywtemplatesteprel.getRelid())) {
					sql.append(" AND relid=:relid");
				}
				if(NonUtil.isNotNon(znywtemplatesteprel.getTemplateid())) {
					sql.append(" AND templateid=:templateid");
				}
				if(NonUtil.isNotNon(znywtemplatesteprel.getStepid())) {
					sql.append(" AND stepid=:stepid");
				}
			}
		}
				
		PageDomain<Znywtemplatesteprel> retpage=null;
		try {
			retpage =  jdbcTemplate.queryForPage(sql.toString(), znywtemplatesteprel, Znywtemplatesteprel.class, pageparam);
		}catch (Exception e) {
			logger.error("模板和步骤关系查询异常", e);
			return ret.setFail("模板和步骤关系查询异常");
		}
		
		return ret.setSuccess("查询模板和步骤关系成功", retpage);
	}
	
	/**
	 * 模板和步骤关系新增
	 * @param ZnywtemplatesteprelListDomain
	 * @return ReturnValueDomain<Znywtemplatesteprel>
	 */
	public ReturnValueDomain<String> add(ZnywtemplatesteprelListDomain znywtemplatesteprellistdomain) {
		ReturnValueDomain<String> ret = new ReturnValueDomain<String>();
		
		if (NonUtil.isNon(znywtemplatesteprellistdomain)) {
			return ret.setFail("无模板和步骤关系");
		}
		
		List<Znywtemplatesteprel> znywtemplatesteprellist=znywtemplatesteprellistdomain.getZnywtemplatesteprellist();
		
		if (NonUtil.isNon(znywtemplatesteprellist)) {
			return ret.setFail("无模板和步骤关系");
		}
		
		try{
			for(Znywtemplatesteprel znywtemplatesteprel : znywtemplatesteprellist) {
				
				String relid=idutil.getID("znywtemplatesteprel");
				znywtemplatesteprel.setRelid(relid);
			}
		}catch(Exception e) {
		    logger.error("模板和步骤关系处理异常", e);
			return ret.setFail("模板和步骤关系处理异常");
		}
		
  		String sql="INSERT INTO tb_znyw_template_step_rel (relid, templateid, stepid) " +
  		           "VALUES (:relid, :templateid, :stepid)";
		
		try {
			jdbcTemplate.batchUpdate(sql, znywtemplatesteprellist);
		} catch (Exception e) {
			logger.error("模板和步骤关系入库异常", e);
			throw new TransactionException(e);
		}
		
		return ret.setSuccess("模板和步骤关系新增成功");
	}
	
	/**
	 * 模板和步骤关系修改
	 * @param ZnywtemplatesteprelListDomain
	 * @return ReturnValueDomain<String>
	 */
	public ReturnValueDomain<String> update(ZnywtemplatesteprelListDomain znywtemplatesteprellistdomain) {
		ReturnValueDomain<String> ret = new ReturnValueDomain<String>();
		
		if (NonUtil.isNon(znywtemplatesteprellistdomain)) {
			return ret.setFail("无模板和步骤关系");
		}
		
		List<Znywtemplatesteprel> znywtemplatesteprellist=znywtemplatesteprellistdomain.getZnywtemplatesteprellist();
		
		if (NonUtil.isNon(znywtemplatesteprellist)) {
			return ret.setFail("无模板和步骤关系");
		}
		
				
		String sql="UPDATE tb_znyw_template_step_rel SET relid=:relid, templateid=:templateid, stepid=:stepid " +
  		           "WHERE relid=:relid";
		
		try {
			jdbcTemplate.batchUpdate(sql, znywtemplatesteprellist);
		} catch (Exception e) {
			logger.error("模板和步骤关系修改异常", e);
			throw new TransactionException(e);
		}
		
		return ret.setSuccess("模板和步骤关系修改成功");
	}
	
	/**
	 * 模板和步骤关系删除
	 * @param ZnywtemplatesteprelListDomain
	 * @return ReturnValueDomain<SysUser>
	 */
	public ReturnValueDomain<String> delete(ZnywtemplatesteprelListDomain znywtemplatesteprellistdomain) {
		ReturnValueDomain<String> ret = new ReturnValueDomain<String>();
		
		if (NonUtil.isNon(znywtemplatesteprellistdomain)) {
			return ret.setFail("无模板和步骤关系");
		}
		
		List<Znywtemplatesteprel> znywtemplatesteprellist = znywtemplatesteprellistdomain.getZnywtemplatesteprellist();
		
		if (NonUtil.isNon(znywtemplatesteprellist)) {
			return ret.setFail("无模板和步骤关系");
		}
		
		String sql="DELETE FROM tb_znyw_template_step_rel WHERE relid=:relid";
		
		try {
			jdbcTemplate.batchUpdate(sql, znywtemplatesteprellist);
		} catch (Exception e) {
			logger.error("模板和步骤关系删除异常", e);
			throw new TransactionException(e);
		}
				
		return ret.setSuccess("模板和步骤关系删除成功");
	}
}

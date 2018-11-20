/**
 * 模板步骤管理服务
 * 
 * @version 1.0
 * @since 2018-11-20
 */
package cn.finedo.ahcnams.service.znywtemplatestep;

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
import cn.finedo.ahcnams.pojo.Znywtemplatestep;
import cn.finedo.ahcnams.service.znywtemplatestep.domain.ZnywtemplatestepListDomain;
import cn.finedo.ahcnams.service.znywtemplatestep.domain.ZnywtemplatestepQueryDomain;

@Service
@Transactional
@Scope("singleton")
public class ZnywtemplatestepService {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Resource(name="jdbcTemplate")
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private IDUtil idutil;
	
		
	/**
	 * 模板步骤查询
	 * @param ZnywtemplatestepQueryDomain
	 * @return ReturnValueDomain<PageDomain<Znywtemplatestep>>
	 */
	public ReturnValueDomain<PageDomain<Znywtemplatestep>> query(ZnywtemplatestepQueryDomain znywtemplatestepquerydomain) {
		ReturnValueDomain<PageDomain<Znywtemplatestep>> ret = new ReturnValueDomain<PageDomain<Znywtemplatestep>>();
		
		StringBuilder sql=new StringBuilder("SELECT * FROM tb_znyw_templatestep WHERE 1=1");
			
		Znywtemplatestep znywtemplatestep=null;
		PageParamDomain pageparam=null;
		if(NonUtil.isNotNon(znywtemplatestepquerydomain)) {
			pageparam=znywtemplatestepquerydomain.getPageparam();
			znywtemplatestep=znywtemplatestepquerydomain.getZnywtemplatestep();
			
			if(NonUtil.isNotNon(znywtemplatestep)) {
				
				if(NonUtil.isNotNon(znywtemplatestep.getStepid())) {
					sql.append(" AND stepid=:stepid");
				}
				if(NonUtil.isNotNon(znywtemplatestep.getStepname())) {
					sql.append(" AND stepname=:stepname");
				}
				if(NonUtil.isNotNon(znywtemplatestep.getStepscript())) {
					sql.append(" AND stepscript=:stepscript");
				}
				if(NonUtil.isNotNon(znywtemplatestep.getRelatedele())) {
					sql.append(" AND relatedele=:relatedele");
				}
				if(NonUtil.isNotNon(znywtemplatestep.getStepremark())) {
					sql.append(" AND stepremark=:stepremark");
				}
			}
		}
				
		PageDomain<Znywtemplatestep> retpage=null;
		try {
			retpage =  jdbcTemplate.queryForPage(sql.toString(), znywtemplatestep, Znywtemplatestep.class, pageparam);
		}catch (Exception e) {
			logger.error("模板步骤查询异常", e);
			return ret.setFail("模板步骤查询异常");
		}
		
		return ret.setSuccess("查询模板步骤成功", retpage);
	}
	
	/**
	 * 模板步骤新增
	 * @param ZnywtemplatestepListDomain
	 * @return ReturnValueDomain<Znywtemplatestep>
	 */
	public ReturnValueDomain<String> add(ZnywtemplatestepListDomain znywtemplatesteplistdomain) {
		ReturnValueDomain<String> ret = new ReturnValueDomain<String>();
		
		if (NonUtil.isNon(znywtemplatesteplistdomain)) {
			return ret.setFail("无模板步骤");
		}
		
		List<Znywtemplatestep> znywtemplatesteplist=znywtemplatesteplistdomain.getZnywtemplatesteplist();
		
		if (NonUtil.isNon(znywtemplatesteplist)) {
			return ret.setFail("无模板步骤");
		}
		
		try{
			for(Znywtemplatestep znywtemplatestep : znywtemplatesteplist) {
				
				String stepid=idutil.getID("znywtemplatestep");
				znywtemplatestep.setStepid(stepid);
			}
		}catch(Exception e) {
		    logger.error("模板步骤处理异常", e);
			return ret.setFail("模板步骤处理异常");
		}
		
  		String sql="INSERT INTO tb_znyw_templatestep (stepid, stepname, stepscript, relatedele, stepremark) " +
  		           "VALUES (:stepid, :stepname, :stepscript, :relatedele, :stepremark)";
		
		try {
			jdbcTemplate.batchUpdate(sql, znywtemplatesteplist);
		} catch (Exception e) {
			logger.error("模板步骤入库异常", e);
			throw new TransactionException(e);
		}
		
		return ret.setSuccess("模板步骤新增成功");
	}
	
	/**
	 * 模板步骤修改
	 * @param ZnywtemplatestepListDomain
	 * @return ReturnValueDomain<String>
	 */
	public ReturnValueDomain<String> update(ZnywtemplatestepListDomain znywtemplatesteplistdomain) {
		ReturnValueDomain<String> ret = new ReturnValueDomain<String>();
		
		if (NonUtil.isNon(znywtemplatesteplistdomain)) {
			return ret.setFail("无模板步骤");
		}
		
		List<Znywtemplatestep> znywtemplatesteplist=znywtemplatesteplistdomain.getZnywtemplatesteplist();
		
		if (NonUtil.isNon(znywtemplatesteplist)) {
			return ret.setFail("无模板步骤");
		}
		
				
		String sql="UPDATE tb_znyw_templatestep SET stepid=:stepid, stepname=:stepname, stepscript=:stepscript, relatedele=:relatedele, stepremark=:stepremark " +
  		           "WHERE stepid=:stepid";
		
		try {
			jdbcTemplate.batchUpdate(sql, znywtemplatesteplist);
		} catch (Exception e) {
			logger.error("模板步骤修改异常", e);
			throw new TransactionException(e);
		}
		
		return ret.setSuccess("模板步骤修改成功");
	}
	
	/**
	 * 模板步骤删除
	 * @param ZnywtemplatestepListDomain
	 * @return ReturnValueDomain<SysUser>
	 */
	public ReturnValueDomain<String> delete(ZnywtemplatestepListDomain znywtemplatesteplistdomain) {
		ReturnValueDomain<String> ret = new ReturnValueDomain<String>();
		
		if (NonUtil.isNon(znywtemplatesteplistdomain)) {
			return ret.setFail("无模板步骤");
		}
		
		List<Znywtemplatestep> znywtemplatesteplist = znywtemplatesteplistdomain.getZnywtemplatesteplist();
		
		if (NonUtil.isNon(znywtemplatesteplist)) {
			return ret.setFail("无模板步骤");
		}
		
		String sql="DELETE FROM tb_znyw_templatestep WHERE stepid=:stepid";
		
		try {
			jdbcTemplate.batchUpdate(sql, znywtemplatesteplist);
		} catch (Exception e) {
			logger.error("模板步骤删除异常", e);
			throw new TransactionException(e);
		}
				
		return ret.setSuccess("模板步骤删除成功");
	}
}

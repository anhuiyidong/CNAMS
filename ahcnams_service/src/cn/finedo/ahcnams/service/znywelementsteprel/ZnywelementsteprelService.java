/**
 * 网元和模板步骤对应关系管理服务
 * 
 * @version 1.0
 * @since 2018-11-20
 */
package cn.finedo.ahcnams.service.znywelementsteprel;

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
import cn.finedo.ahcnams.pojo.Znywelementsteprel;
import cn.finedo.ahcnams.service.znywelementsteprel.domain.ZnywelementsteprelListDomain;
import cn.finedo.ahcnams.service.znywelementsteprel.domain.ZnywelementsteprelQueryDomain;

@Service
@Transactional
@Scope("singleton")
public class ZnywelementsteprelService {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Resource(name="jdbcTemplate")
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private IDUtil idutil;
	
		
	/**
	 * 网元和模板步骤对应关系查询
	 * @param ZnywelementsteprelQueryDomain
	 * @return ReturnValueDomain<PageDomain<Znywelementsteprel>>
	 */
	public ReturnValueDomain<PageDomain<Znywelementsteprel>> query(ZnywelementsteprelQueryDomain znywelementsteprelquerydomain) {
		ReturnValueDomain<PageDomain<Znywelementsteprel>> ret = new ReturnValueDomain<PageDomain<Znywelementsteprel>>();
		
		StringBuilder sql=new StringBuilder("SELECT * FROM tb_znyw_element_step_rel WHERE 1=1");
			
		Znywelementsteprel znywelementsteprel=null;
		PageParamDomain pageparam=null;
		if(NonUtil.isNotNon(znywelementsteprelquerydomain)) {
			pageparam=znywelementsteprelquerydomain.getPageparam();
			znywelementsteprel=znywelementsteprelquerydomain.getZnywelementsteprel();
			
			if(NonUtil.isNotNon(znywelementsteprel)) {
				
				if(NonUtil.isNotNon(znywelementsteprel.getRelid())) {
					sql.append(" AND relid=:relid");
				}
				if(NonUtil.isNotNon(znywelementsteprel.getEletid())) {
					sql.append(" AND eletid=:eletid");
				}
				if(NonUtil.isNotNon(znywelementsteprel.getStepid())) {
					sql.append(" AND stepid=:stepid");
				}
			}
		}
				
		PageDomain<Znywelementsteprel> retpage=null;
		try {
			retpage =  jdbcTemplate.queryForPage(sql.toString(), znywelementsteprel, Znywelementsteprel.class, pageparam);
		}catch (Exception e) {
			logger.error("网元和模板步骤对应关系查询异常", e);
			return ret.setFail("网元和模板步骤对应关系查询异常");
		}
		
		return ret.setSuccess("查询网元和模板步骤对应关系成功", retpage);
	}
	
	/**
	 * 网元和模板步骤对应关系新增
	 * @param ZnywelementsteprelListDomain
	 * @return ReturnValueDomain<Znywelementsteprel>
	 */
	public ReturnValueDomain<String> add(ZnywelementsteprelListDomain znywelementsteprellistdomain) {
		ReturnValueDomain<String> ret = new ReturnValueDomain<String>();
		
		if (NonUtil.isNon(znywelementsteprellistdomain)) {
			return ret.setFail("无网元和模板步骤对应关系");
		}
		
		List<Znywelementsteprel> znywelementsteprellist=znywelementsteprellistdomain.getZnywelementsteprellist();
		
		if (NonUtil.isNon(znywelementsteprellist)) {
			return ret.setFail("无网元和模板步骤对应关系");
		}
		
		try{
			for(Znywelementsteprel znywelementsteprel : znywelementsteprellist) {
				
				String relid=idutil.getID("znywelementsteprel");
				znywelementsteprel.setRelid(relid);
			}
		}catch(Exception e) {
		    logger.error("网元和模板步骤对应关系处理异常", e);
			return ret.setFail("网元和模板步骤对应关系处理异常");
		}
		
  		String sql="INSERT INTO tb_znyw_element_step_rel (relid, eletid, stepid) " +
  		           "VALUES (:relid, :eletid, :stepid)";
		
		try {
			jdbcTemplate.batchUpdate(sql, znywelementsteprellist);
		} catch (Exception e) {
			logger.error("网元和模板步骤对应关系入库异常", e);
			throw new TransactionException(e);
		}
		
		return ret.setSuccess("网元和模板步骤对应关系新增成功");
	}
	
	/**
	 * 网元和模板步骤对应关系修改
	 * @param ZnywelementsteprelListDomain
	 * @return ReturnValueDomain<String>
	 */
	public ReturnValueDomain<String> update(ZnywelementsteprelListDomain znywelementsteprellistdomain) {
		ReturnValueDomain<String> ret = new ReturnValueDomain<String>();
		
		if (NonUtil.isNon(znywelementsteprellistdomain)) {
			return ret.setFail("无网元和模板步骤对应关系");
		}
		
		List<Znywelementsteprel> znywelementsteprellist=znywelementsteprellistdomain.getZnywelementsteprellist();
		
		if (NonUtil.isNon(znywelementsteprellist)) {
			return ret.setFail("无网元和模板步骤对应关系");
		}
		
				
		String sql="UPDATE tb_znyw_element_step_rel SET relid=:relid, eletid=:eletid, stepid=:stepid " +
  		           "WHERE relid=:relid";
		
		try {
			jdbcTemplate.batchUpdate(sql, znywelementsteprellist);
		} catch (Exception e) {
			logger.error("网元和模板步骤对应关系修改异常", e);
			throw new TransactionException(e);
		}
		
		return ret.setSuccess("网元和模板步骤对应关系修改成功");
	}
	
	/**
	 * 网元和模板步骤对应关系删除
	 * @param ZnywelementsteprelListDomain
	 * @return ReturnValueDomain<SysUser>
	 */
	public ReturnValueDomain<String> delete(ZnywelementsteprelListDomain znywelementsteprellistdomain) {
		ReturnValueDomain<String> ret = new ReturnValueDomain<String>();
		
		if (NonUtil.isNon(znywelementsteprellistdomain)) {
			return ret.setFail("无网元和模板步骤对应关系");
		}
		
		List<Znywelementsteprel> znywelementsteprellist = znywelementsteprellistdomain.getZnywelementsteprellist();
		
		if (NonUtil.isNon(znywelementsteprellist)) {
			return ret.setFail("无网元和模板步骤对应关系");
		}
		
		String sql="DELETE FROM tb_znyw_element_step_rel WHERE relid=:relid";
		
		try {
			jdbcTemplate.batchUpdate(sql, znywelementsteprellist);
		} catch (Exception e) {
			logger.error("网元和模板步骤对应关系删除异常", e);
			throw new TransactionException(e);
		}
				
		return ret.setSuccess("网元和模板步骤对应关系删除成功");
	}
}

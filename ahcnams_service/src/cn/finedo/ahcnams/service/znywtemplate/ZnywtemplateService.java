/**
 * 模板管理服务
 * 
 * @version 1.0
 * @since 2018-11-20
 */
package cn.finedo.ahcnams.service.znywtemplate;

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
import cn.finedo.ahcnams.pojo.Znywtemplate;
import cn.finedo.ahcnams.service.znywtemplate.domain.ZnywtemplateListDomain;
import cn.finedo.ahcnams.service.znywtemplate.domain.ZnywtemplateQueryDomain;

@Service
@Transactional
@Scope("singleton")
public class ZnywtemplateService {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Resource(name="jdbcTemplate")
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private IDUtil idutil;
	
		
	/**
	 * 模板查询
	 * @param ZnywtemplateQueryDomain
	 * @return ReturnValueDomain<PageDomain<Znywtemplate>>
	 */
	public ReturnValueDomain<PageDomain<Znywtemplate>> query(ZnywtemplateQueryDomain znywtemplatequerydomain) {
		ReturnValueDomain<PageDomain<Znywtemplate>> ret = new ReturnValueDomain<PageDomain<Znywtemplate>>();
		
		StringBuilder sql=new StringBuilder("SELECT * FROM tb_znyw_template WHERE 1=1");
			
		Znywtemplate znywtemplate=null;
		PageParamDomain pageparam=null;
		if(NonUtil.isNotNon(znywtemplatequerydomain)) {
			pageparam=znywtemplatequerydomain.getPageparam();
			znywtemplate=znywtemplatequerydomain.getZnywtemplate();
			
			if(NonUtil.isNotNon(znywtemplate)) {
				
				if(NonUtil.isNotNon(znywtemplate.getTemplateid())) {
					sql.append(" AND templateid=:templateid");
				}
				if(NonUtil.isNotNon(znywtemplate.getTemplatename())) {
					sql.append(" AND templatename=:templatename");
				}
				if(NonUtil.isNotNon(znywtemplate.getCreator())) {
					sql.append(" AND creator=:creator");
				}
				if(NonUtil.isNotNon(znywtemplate.getCreatetime())) {
					sql.append(" AND createtime=:createtime");
				}
				if(NonUtil.isNotNon(znywtemplate.getTemplatestatus())) {
					sql.append(" AND templatestatus=:templatestatus");
				}
				if(NonUtil.isNotNon(znywtemplate.getRemark())) {
					sql.append(" AND remark=:remark");
				}
			}
		}
				
		PageDomain<Znywtemplate> retpage=null;
		try {
			retpage =  jdbcTemplate.queryForPage(sql.toString(), znywtemplate, Znywtemplate.class, pageparam);
		}catch (Exception e) {
			logger.error("模板查询异常", e);
			return ret.setFail("模板查询异常");
		}
		
		return ret.setSuccess("查询模板成功", retpage);
	}
	
	/**
	 * 模板新增
	 * @param ZnywtemplateListDomain
	 * @return ReturnValueDomain<Znywtemplate>
	 */
	public ReturnValueDomain<String> add(ZnywtemplateListDomain znywtemplatelistdomain) {
		ReturnValueDomain<String> ret = new ReturnValueDomain<String>();
		
		if (NonUtil.isNon(znywtemplatelistdomain)) {
			return ret.setFail("无模板");
		}
		
		List<Znywtemplate> znywtemplatelist=znywtemplatelistdomain.getZnywtemplatelist();
		
		if (NonUtil.isNon(znywtemplatelist)) {
			return ret.setFail("无模板");
		}
		
		try{
			for(Znywtemplate znywtemplate : znywtemplatelist) {
				
				String templateid=idutil.getID("znywtemplate");
				znywtemplate.setTemplateid(templateid);
			}
		}catch(Exception e) {
		    logger.error("模板处理异常", e);
			return ret.setFail("模板处理异常");
		}
		
  		String sql="INSERT INTO tb_znyw_template (templateid, templatename, creator, createtime, templatestatus, remark) " +
  		           "VALUES (:templateid, :templatename, :creator, :createtime, :templatestatus, :remark)";
		
		try {
			jdbcTemplate.batchUpdate(sql, znywtemplatelist);
		} catch (Exception e) {
			logger.error("模板入库异常", e);
			throw new TransactionException(e);
		}
		
		return ret.setSuccess("模板新增成功");
	}
	
	/**
	 * 模板修改
	 * @param ZnywtemplateListDomain
	 * @return ReturnValueDomain<String>
	 */
	public ReturnValueDomain<String> update(ZnywtemplateListDomain znywtemplatelistdomain) {
		ReturnValueDomain<String> ret = new ReturnValueDomain<String>();
		
		if (NonUtil.isNon(znywtemplatelistdomain)) {
			return ret.setFail("无模板");
		}
		
		List<Znywtemplate> znywtemplatelist=znywtemplatelistdomain.getZnywtemplatelist();
		
		if (NonUtil.isNon(znywtemplatelist)) {
			return ret.setFail("无模板");
		}
		
				
		String sql="UPDATE tb_znyw_template SET templateid=:templateid, templatename=:templatename, creator=:creator, createtime=:createtime, templatestatus=:templatestatus, remark=:remark " +
  		           "WHERE templateid=:templateid";
		
		try {
			jdbcTemplate.batchUpdate(sql, znywtemplatelist);
		} catch (Exception e) {
			logger.error("模板修改异常", e);
			throw new TransactionException(e);
		}
		
		return ret.setSuccess("模板修改成功");
	}
	
	/**
	 * 模板删除
	 * @param ZnywtemplateListDomain
	 * @return ReturnValueDomain<SysUser>
	 */
	public ReturnValueDomain<String> delete(ZnywtemplateListDomain znywtemplatelistdomain) {
		ReturnValueDomain<String> ret = new ReturnValueDomain<String>();
		
		if (NonUtil.isNon(znywtemplatelistdomain)) {
			return ret.setFail("无模板");
		}
		
		List<Znywtemplate> znywtemplatelist = znywtemplatelistdomain.getZnywtemplatelist();
		
		if (NonUtil.isNon(znywtemplatelist)) {
			return ret.setFail("无模板");
		}
		
		String sql="DELETE FROM tb_znyw_template WHERE templateid=:templateid";
		
		try {
			jdbcTemplate.batchUpdate(sql, znywtemplatelist);
		} catch (Exception e) {
			logger.error("模板删除异常", e);
			throw new TransactionException(e);
		}
				
		return ret.setSuccess("模板删除成功");
	}
}

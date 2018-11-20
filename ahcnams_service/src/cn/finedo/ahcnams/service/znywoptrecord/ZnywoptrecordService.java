/**
 * 操作记录管理服务
 * 
 * @version 1.0
 * @since 2018-11-20
 */
package cn.finedo.ahcnams.service.znywoptrecord;

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
import cn.finedo.ahcnams.pojo.Znywoptrecord;
import cn.finedo.ahcnams.service.znywoptrecord.domain.ZnywoptrecordListDomain;
import cn.finedo.ahcnams.service.znywoptrecord.domain.ZnywoptrecordQueryDomain;

@Service
@Transactional
@Scope("singleton")
public class ZnywoptrecordService {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Resource(name="jdbcTemplate")
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private IDUtil idutil;
	
		
	/**
	 * 操作记录查询
	 * @param ZnywoptrecordQueryDomain
	 * @return ReturnValueDomain<PageDomain<Znywoptrecord>>
	 */
	public ReturnValueDomain<PageDomain<Znywoptrecord>> query(ZnywoptrecordQueryDomain znywoptrecordquerydomain) {
		ReturnValueDomain<PageDomain<Znywoptrecord>> ret = new ReturnValueDomain<PageDomain<Znywoptrecord>>();
		
		StringBuilder sql=new StringBuilder("SELECT * FROM tb_znyw_optrecord WHERE 1=1");
			
		Znywoptrecord znywoptrecord=null;
		PageParamDomain pageparam=null;
		if(NonUtil.isNotNon(znywoptrecordquerydomain)) {
			pageparam=znywoptrecordquerydomain.getPageparam();
			znywoptrecord=znywoptrecordquerydomain.getZnywoptrecord();
			
			if(NonUtil.isNotNon(znywoptrecord)) {
				
				if(NonUtil.isNotNon(znywoptrecord.getOptid())) {
					sql.append(" AND optid=:optid");
				}
				if(NonUtil.isNotNon(znywoptrecord.getTaskid())) {
					sql.append(" AND taskid=:taskid");
				}
				if(NonUtil.isNotNon(znywoptrecord.getOptperson())) {
					sql.append(" AND optperson=:optperson");
				}
				if(NonUtil.isNotNon(znywoptrecord.getOpttime())) {
					sql.append(" AND opttime=:opttime");
				}
			}
		}
				
		PageDomain<Znywoptrecord> retpage=null;
		try {
			retpage =  jdbcTemplate.queryForPage(sql.toString(), znywoptrecord, Znywoptrecord.class, pageparam);
		}catch (Exception e) {
			logger.error("操作记录查询异常", e);
			return ret.setFail("操作记录查询异常");
		}
		
		return ret.setSuccess("查询操作记录成功", retpage);
	}
	
	/**
	 * 操作记录新增
	 * @param ZnywoptrecordListDomain
	 * @return ReturnValueDomain<Znywoptrecord>
	 */
	public ReturnValueDomain<String> add(ZnywoptrecordListDomain znywoptrecordlistdomain) {
		ReturnValueDomain<String> ret = new ReturnValueDomain<String>();
		
		if (NonUtil.isNon(znywoptrecordlistdomain)) {
			return ret.setFail("无操作记录");
		}
		
		List<Znywoptrecord> znywoptrecordlist=znywoptrecordlistdomain.getZnywoptrecordlist();
		
		if (NonUtil.isNon(znywoptrecordlist)) {
			return ret.setFail("无操作记录");
		}
		
		try{
			for(Znywoptrecord znywoptrecord : znywoptrecordlist) {
				
				String optid=idutil.getID("znywoptrecord");
				znywoptrecord.setOptid(optid);
			}
		}catch(Exception e) {
		    logger.error("操作记录处理异常", e);
			return ret.setFail("操作记录处理异常");
		}
		
  		String sql="INSERT INTO tb_znyw_optrecord (optid, taskid, optperson, opttime) " +
  		           "VALUES (:optid, :taskid, :optperson, :opttime)";
		
		try {
			jdbcTemplate.batchUpdate(sql, znywoptrecordlist);
		} catch (Exception e) {
			logger.error("操作记录入库异常", e);
			throw new TransactionException(e);
		}
		
		return ret.setSuccess("操作记录新增成功");
	}
	
	/**
	 * 操作记录修改
	 * @param ZnywoptrecordListDomain
	 * @return ReturnValueDomain<String>
	 */
	public ReturnValueDomain<String> update(ZnywoptrecordListDomain znywoptrecordlistdomain) {
		ReturnValueDomain<String> ret = new ReturnValueDomain<String>();
		
		if (NonUtil.isNon(znywoptrecordlistdomain)) {
			return ret.setFail("无操作记录");
		}
		
		List<Znywoptrecord> znywoptrecordlist=znywoptrecordlistdomain.getZnywoptrecordlist();
		
		if (NonUtil.isNon(znywoptrecordlist)) {
			return ret.setFail("无操作记录");
		}
		
				
		String sql="UPDATE tb_znyw_optrecord SET optid=:optid, taskid=:taskid, optperson=:optperson, opttime=:opttime " +
  		           "WHERE optid=:optid";
		
		try {
			jdbcTemplate.batchUpdate(sql, znywoptrecordlist);
		} catch (Exception e) {
			logger.error("操作记录修改异常", e);
			throw new TransactionException(e);
		}
		
		return ret.setSuccess("操作记录修改成功");
	}
	
	/**
	 * 操作记录删除
	 * @param ZnywoptrecordListDomain
	 * @return ReturnValueDomain<SysUser>
	 */
	public ReturnValueDomain<String> delete(ZnywoptrecordListDomain znywoptrecordlistdomain) {
		ReturnValueDomain<String> ret = new ReturnValueDomain<String>();
		
		if (NonUtil.isNon(znywoptrecordlistdomain)) {
			return ret.setFail("无操作记录");
		}
		
		List<Znywoptrecord> znywoptrecordlist = znywoptrecordlistdomain.getZnywoptrecordlist();
		
		if (NonUtil.isNon(znywoptrecordlist)) {
			return ret.setFail("无操作记录");
		}
		
		String sql="DELETE FROM tb_znyw_optrecord WHERE optid=:optid";
		
		try {
			jdbcTemplate.batchUpdate(sql, znywoptrecordlist);
		} catch (Exception e) {
			logger.error("操作记录删除异常", e);
			throw new TransactionException(e);
		}
				
		return ret.setSuccess("操作记录删除成功");
	}
}

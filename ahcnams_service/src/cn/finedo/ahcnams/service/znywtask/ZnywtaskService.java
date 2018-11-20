/**
 * 任务管理服务
 * 
 * @version 1.0
 * @since 2018-11-20
 */
package cn.finedo.ahcnams.service.znywtask;

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
import cn.finedo.ahcnams.pojo.Znywtask;
import cn.finedo.ahcnams.service.znywtask.domain.ZnywtaskListDomain;
import cn.finedo.ahcnams.service.znywtask.domain.ZnywtaskQueryDomain;

@Service
@Transactional
@Scope("singleton")
public class ZnywtaskService {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Resource(name="jdbcTemplate")
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private IDUtil idutil;
	
		
	/**
	 * 任务查询
	 * @param ZnywtaskQueryDomain
	 * @return ReturnValueDomain<PageDomain<Znywtask>>
	 */
	public ReturnValueDomain<PageDomain<Znywtask>> query(ZnywtaskQueryDomain znywtaskquerydomain) {
		ReturnValueDomain<PageDomain<Znywtask>> ret = new ReturnValueDomain<PageDomain<Znywtask>>();
		
		StringBuilder sql=new StringBuilder("SELECT * FROM tb_znyw_task WHERE 1=1");
			
		Znywtask znywtask=null;
		PageParamDomain pageparam=null;
		if(NonUtil.isNotNon(znywtaskquerydomain)) {
			pageparam=znywtaskquerydomain.getPageparam();
			znywtask=znywtaskquerydomain.getZnywtask();
			
			if(NonUtil.isNotNon(znywtask)) {
				
				if(NonUtil.isNotNon(znywtask.getTaskid())) {
					sql.append(" AND taskid=:taskid");
				}
				if(NonUtil.isNotNon(znywtask.getTemplateid())) {
					sql.append(" AND templateid=:templateid");
				}
				if(NonUtil.isNotNon(znywtask.getExecutor())) {
					sql.append(" AND executor=:executor");
				}
				if(NonUtil.isNotNon(znywtask.getExecutetime())) {
					sql.append(" AND executetime=:executetime");
				}
				if(NonUtil.isNotNon(znywtask.getStatus())) {
					sql.append(" AND status=:status");
				}
				if(NonUtil.isNotNon(znywtask.getStarttime())) {
					sql.append(" AND starttime=:starttime");
				}
				if(NonUtil.isNotNon(znywtask.getExpiretime())) {
					sql.append(" AND expiretime=:expiretime");
				}
				if(NonUtil.isNotNon(znywtask.getDept())) {
					sql.append(" AND dept=:dept");
				}
				if(NonUtil.isNotNon(znywtask.getRemark())) {
					sql.append(" AND remark=:remark");
				}
			}
		}
				
		PageDomain<Znywtask> retpage=null;
		try {
			retpage =  jdbcTemplate.queryForPage(sql.toString(), znywtask, Znywtask.class, pageparam);
		}catch (Exception e) {
			logger.error("任务查询异常", e);
			return ret.setFail("任务查询异常");
		}
		
		return ret.setSuccess("查询任务成功", retpage);
	}
	
	/**
	 * 任务新增
	 * @param ZnywtaskListDomain
	 * @return ReturnValueDomain<Znywtask>
	 */
	public ReturnValueDomain<String> add(ZnywtaskListDomain znywtasklistdomain) {
		ReturnValueDomain<String> ret = new ReturnValueDomain<String>();
		
		if (NonUtil.isNon(znywtasklistdomain)) {
			return ret.setFail("无任务");
		}
		
		List<Znywtask> znywtasklist=znywtasklistdomain.getZnywtasklist();
		
		if (NonUtil.isNon(znywtasklist)) {
			return ret.setFail("无任务");
		}
		
		try{
			for(Znywtask znywtask : znywtasklist) {
				
				String taskid=idutil.getID("znywtask");
				znywtask.setTaskid(taskid);
			}
		}catch(Exception e) {
		    logger.error("任务处理异常", e);
			return ret.setFail("任务处理异常");
		}
		
  		String sql="INSERT INTO tb_znyw_task (taskid, templateid, executor, executetime, status, starttime, expiretime, dept, remark) " +
  		           "VALUES (:taskid, :templateid, :executor, :executetime, :status, :starttime, :expiretime, :dept, :remark)";
		
		try {
			jdbcTemplate.batchUpdate(sql, znywtasklist);
		} catch (Exception e) {
			logger.error("任务入库异常", e);
			throw new TransactionException(e);
		}
		
		return ret.setSuccess("任务新增成功");
	}
	
	/**
	 * 任务修改
	 * @param ZnywtaskListDomain
	 * @return ReturnValueDomain<String>
	 */
	public ReturnValueDomain<String> update(ZnywtaskListDomain znywtasklistdomain) {
		ReturnValueDomain<String> ret = new ReturnValueDomain<String>();
		
		if (NonUtil.isNon(znywtasklistdomain)) {
			return ret.setFail("无任务");
		}
		
		List<Znywtask> znywtasklist=znywtasklistdomain.getZnywtasklist();
		
		if (NonUtil.isNon(znywtasklist)) {
			return ret.setFail("无任务");
		}
		
				
		String sql="UPDATE tb_znyw_task SET taskid=:taskid, templateid=:templateid, executor=:executor, executetime=:executetime, status=:status, starttime=:starttime, expiretime=:expiretime, dept=:dept, remark=:remark " +
  		           "WHERE taskid=:taskid";
		
		try {
			jdbcTemplate.batchUpdate(sql, znywtasklist);
		} catch (Exception e) {
			logger.error("任务修改异常", e);
			throw new TransactionException(e);
		}
		
		return ret.setSuccess("任务修改成功");
	}
	
	/**
	 * 任务删除
	 * @param ZnywtaskListDomain
	 * @return ReturnValueDomain<SysUser>
	 */
	public ReturnValueDomain<String> delete(ZnywtaskListDomain znywtasklistdomain) {
		ReturnValueDomain<String> ret = new ReturnValueDomain<String>();
		
		if (NonUtil.isNon(znywtasklistdomain)) {
			return ret.setFail("无任务");
		}
		
		List<Znywtask> znywtasklist = znywtasklistdomain.getZnywtasklist();
		
		if (NonUtil.isNon(znywtasklist)) {
			return ret.setFail("无任务");
		}
		
		String sql="DELETE FROM tb_znyw_task WHERE taskid=:taskid";
		
		try {
			jdbcTemplate.batchUpdate(sql, znywtasklist);
		} catch (Exception e) {
			logger.error("任务删除异常", e);
			throw new TransactionException(e);
		}
				
		return ret.setSuccess("任务删除成功");
	}
}

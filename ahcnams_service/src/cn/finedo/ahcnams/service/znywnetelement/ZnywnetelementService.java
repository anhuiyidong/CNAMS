/**
 * 网元信息管理服务
 * 
 * @version 1.0
 * @since 2018-11-20
 */
package cn.finedo.ahcnams.service.znywnetelement;

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
import cn.finedo.ahcnams.pojo.Znywnetelement;
import cn.finedo.ahcnams.service.znywnetelement.domain.ZnywnetelementListDomain;
import cn.finedo.ahcnams.service.znywnetelement.domain.ZnywnetelementQueryDomain;

@Service
@Transactional
@Scope("singleton")
public class ZnywnetelementService {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Resource(name="jdbcTemplate")
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private IDUtil idutil;
	
		
	/**
	 * 网元信息查询
	 * @param ZnywnetelementQueryDomain
	 * @return ReturnValueDomain<PageDomain<Znywnetelement>>
	 */
	public ReturnValueDomain<PageDomain<Znywnetelement>> query(ZnywnetelementQueryDomain znywnetelementquerydomain) {
		ReturnValueDomain<PageDomain<Znywnetelement>> ret = new ReturnValueDomain<PageDomain<Znywnetelement>>();
		
		StringBuilder sql=new StringBuilder("SELECT * FROM tb_znyw_netelement WHERE 1=1");
			
		Znywnetelement znywnetelement=null;
		PageParamDomain pageparam=null;
		if(NonUtil.isNotNon(znywnetelementquerydomain)) {
			pageparam=znywnetelementquerydomain.getPageparam();
			znywnetelement=znywnetelementquerydomain.getZnywnetelement();
			
			if(NonUtil.isNotNon(znywnetelement)) {
				
				if(NonUtil.isNotNon(znywnetelement.getEletid())) {
					sql.append(" AND eletid=:eletid");
				}
				if(NonUtil.isNotNon(znywnetelement.getElename())) {
					sql.append(" AND elename=:elename");
				}
				if(NonUtil.isNotNon(znywnetelement.getEleip())) {
					sql.append(" AND eleip=:eleip");
				}
				if(NonUtil.isNotNon(znywnetelement.getElefactory())) {
					sql.append(" AND elefactory=:elefactory");
				}
				if(NonUtil.isNotNon(znywnetelement.getElenet())) {
					sql.append(" AND elenet=:elenet");
				}
				if(NonUtil.isNotNon(znywnetelement.getEletype())) {
					sql.append(" AND eletype=:eletype");
				}
				if(NonUtil.isNotNon(znywnetelement.getCreator())) {
					sql.append(" AND creator=:creator");
				}
				if(NonUtil.isNotNon(znywnetelement.getCreatetime())) {
					sql.append(" AND createtime=:createtime");
				}
				if(NonUtil.isNotNon(znywnetelement.getCity())) {
					sql.append(" AND city=:city");
				}
				if(NonUtil.isNotNon(znywnetelement.getLogintype())) {
					sql.append(" AND logintype=:logintype");
				}
				if(NonUtil.isNotNon(znywnetelement.getPort())) {
					sql.append(" AND port=:port");
				}
				if(NonUtil.isNotNon(znywnetelement.getUsername())) {
					sql.append(" AND username=:username");
				}
				if(NonUtil.isNotNon(znywnetelement.getPassword())) {
					sql.append(" AND password=:password");
				}
				if(NonUtil.isNotNon(znywnetelement.getRemark())) {
					sql.append(" AND remark=:remark");
				}
			}
		}
				
		PageDomain<Znywnetelement> retpage=null;
		try {
			retpage =  jdbcTemplate.queryForPage(sql.toString(), znywnetelement, Znywnetelement.class, pageparam);
		}catch (Exception e) {
			logger.error("网元信息查询异常", e);
			return ret.setFail("网元信息查询异常");
		}
		
		return ret.setSuccess("查询网元信息成功", retpage);
	}
	
	/**
	 * 网元信息新增
	 * @param ZnywnetelementListDomain
	 * @return ReturnValueDomain<Znywnetelement>
	 */
	public ReturnValueDomain<String> add(ZnywnetelementListDomain znywnetelementlistdomain) {
		ReturnValueDomain<String> ret = new ReturnValueDomain<String>();
		
		if (NonUtil.isNon(znywnetelementlistdomain)) {
			return ret.setFail("无网元信息");
		}
		
		List<Znywnetelement> znywnetelementlist=znywnetelementlistdomain.getZnywnetelementlist();
		
		if (NonUtil.isNon(znywnetelementlist)) {
			return ret.setFail("无网元信息");
		}
		
		try{
			for(Znywnetelement znywnetelement : znywnetelementlist) {
				
				String eletid=idutil.getID("znywnetelement");
				znywnetelement.setEletid(eletid);
			}
		}catch(Exception e) {
		    logger.error("网元信息处理异常", e);
			return ret.setFail("网元信息处理异常");
		}
		
  		String sql="INSERT INTO tb_znyw_netelement (eletid, elename, eleip, elefactory, elenet, eletype, creator, createtime, city, logintype, port, username, password, remark) " +
  		           "VALUES (:eletid, :elename, :eleip, :elefactory, :elenet, :eletype, :creator, :createtime, :city, :logintype, :port, :username, :password, :remark)";
		
		try {
			jdbcTemplate.batchUpdate(sql, znywnetelementlist);
		} catch (Exception e) {
			logger.error("网元信息入库异常", e);
			throw new TransactionException(e);
		}
		
		return ret.setSuccess("网元信息新增成功");
	}
	
	/**
	 * 网元信息修改
	 * @param ZnywnetelementListDomain
	 * @return ReturnValueDomain<String>
	 */
	public ReturnValueDomain<String> update(ZnywnetelementListDomain znywnetelementlistdomain) {
		ReturnValueDomain<String> ret = new ReturnValueDomain<String>();
		
		if (NonUtil.isNon(znywnetelementlistdomain)) {
			return ret.setFail("无网元信息");
		}
		
		List<Znywnetelement> znywnetelementlist=znywnetelementlistdomain.getZnywnetelementlist();
		
		if (NonUtil.isNon(znywnetelementlist)) {
			return ret.setFail("无网元信息");
		}
		
				
		String sql="UPDATE tb_znyw_netelement SET eletid=:eletid, elename=:elename, eleip=:eleip, elefactory=:elefactory, elenet=:elenet, eletype=:eletype, creator=:creator, createtime=:createtime, city=:city, logintype=:logintype, port=:port, username=:username, password=:password, remark=:remark " +
  		           "WHERE eletid=:eletid";
		
		try {
			jdbcTemplate.batchUpdate(sql, znywnetelementlist);
		} catch (Exception e) {
			logger.error("网元信息修改异常", e);
			throw new TransactionException(e);
		}
		
		return ret.setSuccess("网元信息修改成功");
	}
	
	/**
	 * 网元信息删除
	 * @param ZnywnetelementListDomain
	 * @return ReturnValueDomain<SysUser>
	 */
	public ReturnValueDomain<String> delete(ZnywnetelementListDomain znywnetelementlistdomain) {
		ReturnValueDomain<String> ret = new ReturnValueDomain<String>();
		
		if (NonUtil.isNon(znywnetelementlistdomain)) {
			return ret.setFail("无网元信息");
		}
		
		List<Znywnetelement> znywnetelementlist = znywnetelementlistdomain.getZnywnetelementlist();
		
		if (NonUtil.isNon(znywnetelementlist)) {
			return ret.setFail("无网元信息");
		}
		
		String sql="DELETE FROM tb_znyw_netelement WHERE eletid=:eletid";
		
		try {
			jdbcTemplate.batchUpdate(sql, znywnetelementlist);
		} catch (Exception e) {
			logger.error("网元信息删除异常", e);
			throw new TransactionException(e);
		}
				
		return ret.setSuccess("网元信息删除成功");
	}
}

/**
 * 厂家信息表管理服务
 * 
 * @version 1.0
 * @since 2018-11-24
 */
package cn.finedo.ahcnams.service.znywfactoryinfo;

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
import cn.finedo.ahcnams.pojo.Znywfactoryinfo;
import cn.finedo.ahcnams.service.znywfactoryinfo.domain.ZnywfactoryinfoListDomain;
import cn.finedo.ahcnams.service.znywfactoryinfo.domain.ZnywfactoryinfoQueryDomain;

@Service
@Transactional
@Scope("singleton")
public class ZnywfactoryinfoService {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Resource(name="jdbcTemplate")
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private IDUtil idutil;
	
		
	/**
	 * 厂家信息表查询
	 * @param ZnywfactoryinfoQueryDomain
	 * @return ReturnValueDomain<PageDomain<Znywfactoryinfo>>
	 */
	public ReturnValueDomain<PageDomain<Znywfactoryinfo>> query(ZnywfactoryinfoQueryDomain znywfactoryinfoquerydomain) {
		ReturnValueDomain<PageDomain<Znywfactoryinfo>> ret = new ReturnValueDomain<PageDomain<Znywfactoryinfo>>();
		
		StringBuilder sql=new StringBuilder("SELECT * FROM tb_znyw_factoryinfo WHERE 1=1");
			
		Znywfactoryinfo znywfactoryinfo=null;
		PageParamDomain pageparam=null;
		if(NonUtil.isNotNon(znywfactoryinfoquerydomain)) {
			pageparam=znywfactoryinfoquerydomain.getPageparam();
			znywfactoryinfo=znywfactoryinfoquerydomain.getZnywfactoryinfo();
			
			if(NonUtil.isNotNon(znywfactoryinfo)) {
				
				if(NonUtil.isNotNon(znywfactoryinfo.getFacid())) {
					sql.append(" AND facid=:facid");
				}
				if(NonUtil.isNotNon(znywfactoryinfo.getFacname())) {
					sql.append(" AND facname=:facname");
				}
				if(NonUtil.isNotNon(znywfactoryinfo.getContactor())) {
					sql.append(" AND contactor=:contactor");
				}
				if(NonUtil.isNotNon(znywfactoryinfo.getRemark())) {
					sql.append(" AND remark=:remark");
				}
			}
		}
				
		PageDomain<Znywfactoryinfo> retpage=null;
		try {
			retpage =  jdbcTemplate.queryForPage(sql.toString(), znywfactoryinfo, Znywfactoryinfo.class, pageparam);
		}catch (Exception e) {
			logger.error("厂家信息表查询异常", e);
			return ret.setFail("厂家信息表查询异常");
		}
		
		return ret.setSuccess("查询厂家信息表成功", retpage);
	}
	
	/**
	 * 厂家信息表新增
	 * @param ZnywfactoryinfoListDomain
	 * @return ReturnValueDomain<Znywfactoryinfo>
	 */
	public ReturnValueDomain<String> add(ZnywfactoryinfoListDomain znywfactoryinfolistdomain) {
		ReturnValueDomain<String> ret = new ReturnValueDomain<String>();
		
		if (NonUtil.isNon(znywfactoryinfolistdomain)) {
			return ret.setFail("无厂家信息表");
		}
		
		List<Znywfactoryinfo> znywfactoryinfolist=znywfactoryinfolistdomain.getZnywfactoryinfolist();
		
		if (NonUtil.isNon(znywfactoryinfolist)) {
			return ret.setFail("无厂家信息表");
		}
		
		try{
			for(Znywfactoryinfo znywfactoryinfo : znywfactoryinfolist) {
				
				String facid=idutil.getID("znywfactoryinfo");
				znywfactoryinfo.setFacid(facid);
			}
		}catch(Exception e) {
		    logger.error("厂家信息表处理异常", e);
			return ret.setFail("厂家信息表处理异常");
		}
		
  		String sql="INSERT INTO tb_znyw_factoryinfo (facid, facname, contactor, remark) " +
  		           "VALUES (:facid, :facname, :contactor, :remark)";
		
		try {
			jdbcTemplate.batchUpdate(sql, znywfactoryinfolist);
		} catch (Exception e) {
			logger.error("厂家信息表入库异常", e);
			throw new TransactionException(e);
		}
		
		return ret.setSuccess("厂家信息表新增成功");
	}
	
	/**
	 * 厂家信息表修改
	 * @param ZnywfactoryinfoListDomain
	 * @return ReturnValueDomain<String>
	 */
	public ReturnValueDomain<String> update(ZnywfactoryinfoListDomain znywfactoryinfolistdomain) {
		ReturnValueDomain<String> ret = new ReturnValueDomain<String>();
		
		if (NonUtil.isNon(znywfactoryinfolistdomain)) {
			return ret.setFail("无厂家信息表");
		}
		
		List<Znywfactoryinfo> znywfactoryinfolist=znywfactoryinfolistdomain.getZnywfactoryinfolist();
		
		if (NonUtil.isNon(znywfactoryinfolist)) {
			return ret.setFail("无厂家信息表");
		}
		
				
		String sql="UPDATE tb_znyw_factoryinfo SET facid=:facid, facname=:facname, contactor=:contactor, remark=:remark " +
  		           "WHERE facid=:facid";
		
		try {
			jdbcTemplate.batchUpdate(sql, znywfactoryinfolist);
		} catch (Exception e) {
			logger.error("厂家信息表修改异常", e);
			throw new TransactionException(e);
		}
		
		return ret.setSuccess("厂家信息表修改成功");
	}
	
	/**
	 * 厂家信息表删除
	 * @param ZnywfactoryinfoListDomain
	 * @return ReturnValueDomain<SysUser>
	 */
	public ReturnValueDomain<String> delete(ZnywfactoryinfoListDomain znywfactoryinfolistdomain) {
		ReturnValueDomain<String> ret = new ReturnValueDomain<String>();
		
		if (NonUtil.isNon(znywfactoryinfolistdomain)) {
			return ret.setFail("无厂家信息表");
		}
		
		List<Znywfactoryinfo> znywfactoryinfolist = znywfactoryinfolistdomain.getZnywfactoryinfolist();
		
		if (NonUtil.isNon(znywfactoryinfolist)) {
			return ret.setFail("无厂家信息表");
		}
		
		String sql="DELETE FROM tb_znyw_factoryinfo WHERE facid=:facid";
		
		try {
			jdbcTemplate.batchUpdate(sql, znywfactoryinfolist);
		} catch (Exception e) {
			logger.error("厂家信息表删除异常", e);
			throw new TransactionException(e);
		}
				
		return ret.setSuccess("厂家信息表删除成功");
	}
}

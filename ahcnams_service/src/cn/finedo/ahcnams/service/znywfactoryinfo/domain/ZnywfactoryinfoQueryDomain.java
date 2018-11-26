/**
 * 厂家信息表查询领域对象
 *
 * @version 1.0
 * @since 2018-11-24
 */
package cn.finedo.ahcnams.service.znywfactoryinfo.domain;

import cn.finedo.ahcnams.pojo.Znywfactoryinfo;
import cn.finedo.common.domain.BaseDomain;
import cn.finedo.common.domain.PageParamDomain;

public class ZnywfactoryinfoQueryDomain extends BaseDomain {
	private static final long serialVersionUID = 1L;
	
	// 分页信息
	private PageParamDomain pageparam = null;
	
	private Znywfactoryinfo znywfactoryinfo = null;

	public PageParamDomain getPageparam() {
		return pageparam;
	}

	public void setPageparam(PageParamDomain pageparam) {
		this.pageparam = pageparam;
	}

	public Znywfactoryinfo getZnywfactoryinfo() {
		return znywfactoryinfo;
	}

	public void setZnywfactoryinfo(Znywfactoryinfo znywfactoryinfo) {
		this.znywfactoryinfo = znywfactoryinfo;
	}
}

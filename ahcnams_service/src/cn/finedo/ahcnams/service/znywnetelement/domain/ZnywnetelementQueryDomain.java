/**
 * 网元信息查询领域对象
 *
 * @version 1.0
 * @since 2018-11-20
 */
package cn.finedo.ahcnams.service.znywnetelement.domain;

import cn.finedo.common.domain.BaseDomain;
import cn.finedo.common.domain.PageParamDomain;
import cn.finedo.ahcnams.pojo.Znywnetelement;

public class ZnywnetelementQueryDomain extends BaseDomain {
	private static final long serialVersionUID = 1L;
	
	// 分页信息
	private PageParamDomain pageparam = null;
	
	private Znywnetelement znywnetelement = null;

	public PageParamDomain getPageparam() {
		return pageparam;
	}

	public void setPageparam(PageParamDomain pageparam) {
		this.pageparam = pageparam;
	}

	public Znywnetelement getZnywnetelement() {
		return znywnetelement;
	}

	public void setZnywnetelement(Znywnetelement znywnetelement) {
		this.znywnetelement = znywnetelement;
	}
}

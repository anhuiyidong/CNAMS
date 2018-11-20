/**
 * 模板步骤查询领域对象
 *
 * @version 1.0
 * @since 2018-11-20
 */
package cn.finedo.ahcnams.service.znywtemplatestep.domain;

import cn.finedo.common.domain.BaseDomain;
import cn.finedo.common.domain.PageParamDomain;
import cn.finedo.ahcnams.pojo.Znywtemplatestep;

public class ZnywtemplatestepQueryDomain extends BaseDomain {
	private static final long serialVersionUID = 1L;
	
	// 分页信息
	private PageParamDomain pageparam = null;
	
	private Znywtemplatestep znywtemplatestep = null;

	public PageParamDomain getPageparam() {
		return pageparam;
	}

	public void setPageparam(PageParamDomain pageparam) {
		this.pageparam = pageparam;
	}

	public Znywtemplatestep getZnywtemplatestep() {
		return znywtemplatestep;
	}

	public void setZnywtemplatestep(Znywtemplatestep znywtemplatestep) {
		this.znywtemplatestep = znywtemplatestep;
	}
}

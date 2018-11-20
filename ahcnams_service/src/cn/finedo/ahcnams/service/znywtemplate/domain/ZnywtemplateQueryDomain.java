/**
 * 模板查询领域对象
 *
 * @version 1.0
 * @since 2018-11-20
 */
package cn.finedo.ahcnams.service.znywtemplate.domain;

import cn.finedo.common.domain.BaseDomain;
import cn.finedo.common.domain.PageParamDomain;
import cn.finedo.ahcnams.pojo.Znywtemplate;

public class ZnywtemplateQueryDomain extends BaseDomain {
	private static final long serialVersionUID = 1L;
	
	// 分页信息
	private PageParamDomain pageparam = null;
	
	private Znywtemplate znywtemplate = null;

	public PageParamDomain getPageparam() {
		return pageparam;
	}

	public void setPageparam(PageParamDomain pageparam) {
		this.pageparam = pageparam;
	}

	public Znywtemplate getZnywtemplate() {
		return znywtemplate;
	}

	public void setZnywtemplate(Znywtemplate znywtemplate) {
		this.znywtemplate = znywtemplate;
	}
}

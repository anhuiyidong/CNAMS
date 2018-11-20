/**
 * 模板和步骤关系查询领域对象
 *
 * @version 1.0
 * @since 2018-11-20
 */
package cn.finedo.ahcnams.service.znywtemplatesteprel.domain;

import cn.finedo.common.domain.BaseDomain;
import cn.finedo.common.domain.PageParamDomain;
import cn.finedo.ahcnams.pojo.Znywtemplatesteprel;

public class ZnywtemplatesteprelQueryDomain extends BaseDomain {
	private static final long serialVersionUID = 1L;
	
	// 分页信息
	private PageParamDomain pageparam = null;
	
	private Znywtemplatesteprel znywtemplatesteprel = null;

	public PageParamDomain getPageparam() {
		return pageparam;
	}

	public void setPageparam(PageParamDomain pageparam) {
		this.pageparam = pageparam;
	}

	public Znywtemplatesteprel getZnywtemplatesteprel() {
		return znywtemplatesteprel;
	}

	public void setZnywtemplatesteprel(Znywtemplatesteprel znywtemplatesteprel) {
		this.znywtemplatesteprel = znywtemplatesteprel;
	}
}

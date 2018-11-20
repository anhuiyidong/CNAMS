/**
 * 网元和模板步骤对应关系查询领域对象
 *
 * @version 1.0
 * @since 2018-11-20
 */
package cn.finedo.ahcnams.service.znywelementsteprel.domain;

import cn.finedo.common.domain.BaseDomain;
import cn.finedo.common.domain.PageParamDomain;
import cn.finedo.ahcnams.pojo.Znywelementsteprel;

public class ZnywelementsteprelQueryDomain extends BaseDomain {
	private static final long serialVersionUID = 1L;
	
	// 分页信息
	private PageParamDomain pageparam = null;
	
	private Znywelementsteprel znywelementsteprel = null;

	public PageParamDomain getPageparam() {
		return pageparam;
	}

	public void setPageparam(PageParamDomain pageparam) {
		this.pageparam = pageparam;
	}

	public Znywelementsteprel getZnywelementsteprel() {
		return znywelementsteprel;
	}

	public void setZnywelementsteprel(Znywelementsteprel znywelementsteprel) {
		this.znywelementsteprel = znywelementsteprel;
	}
}

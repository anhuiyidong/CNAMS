/**
 * 任务查询领域对象
 *
 * @version 1.0
 * @since 2018-11-20
 */
package cn.finedo.ahcnams.service.znywtask.domain;

import cn.finedo.common.domain.BaseDomain;
import cn.finedo.common.domain.PageParamDomain;
import cn.finedo.ahcnams.pojo.Znywtask;

public class ZnywtaskQueryDomain extends BaseDomain {
	private static final long serialVersionUID = 1L;
	
	// 分页信息
	private PageParamDomain pageparam = null;
	
	private Znywtask znywtask = null;

	public PageParamDomain getPageparam() {
		return pageparam;
	}

	public void setPageparam(PageParamDomain pageparam) {
		this.pageparam = pageparam;
	}

	public Znywtask getZnywtask() {
		return znywtask;
	}

	public void setZnywtask(Znywtask znywtask) {
		this.znywtask = znywtask;
	}
}

/**
 * 操作记录查询领域对象
 *
 * @version 1.0
 * @since 2018-11-20
 */
package cn.finedo.ahcnams.service.znywoptrecord.domain;

import cn.finedo.common.domain.BaseDomain;
import cn.finedo.common.domain.PageParamDomain;
import cn.finedo.ahcnams.pojo.Znywoptrecord;

public class ZnywoptrecordQueryDomain extends BaseDomain {
	private static final long serialVersionUID = 1L;
	
	// 分页信息
	private PageParamDomain pageparam = null;
	
	private Znywoptrecord znywoptrecord = null;

	public PageParamDomain getPageparam() {
		return pageparam;
	}

	public void setPageparam(PageParamDomain pageparam) {
		this.pageparam = pageparam;
	}

	public Znywoptrecord getZnywoptrecord() {
		return znywoptrecord;
	}

	public void setZnywoptrecord(Znywoptrecord znywoptrecord) {
		this.znywoptrecord = znywoptrecord;
	}
}

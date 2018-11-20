/**
 * 模板列表领域对象
 *
 * @version 1.0
 * @since 2018-11-20
 */
package cn.finedo.ahcnams.service.znywtemplate.domain;

import java.util.List;
import java.util.ArrayList;
import cn.finedo.common.domain.BaseDomain;
import cn.finedo.ahcnams.pojo.Znywtemplate;

public class ZnywtemplateListDomain extends BaseDomain {
	private static final long serialVersionUID = 1L;
	
	// 模板list
	private List<Znywtemplate> znywtemplatelist=new ArrayList<Znywtemplate>();

	public List<Znywtemplate> getZnywtemplatelist() {
		return znywtemplatelist;
	}

	public void setZnywtemplatelist(List<Znywtemplate> znywtemplatelist) {
		this.znywtemplatelist = znywtemplatelist;
	}
}

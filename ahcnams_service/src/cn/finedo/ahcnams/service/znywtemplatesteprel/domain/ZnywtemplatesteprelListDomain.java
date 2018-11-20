/**
 * 模板和步骤关系列表领域对象
 *
 * @version 1.0
 * @since 2018-11-20
 */
package cn.finedo.ahcnams.service.znywtemplatesteprel.domain;

import java.util.List;
import java.util.ArrayList;
import cn.finedo.common.domain.BaseDomain;
import cn.finedo.ahcnams.pojo.Znywtemplatesteprel;

public class ZnywtemplatesteprelListDomain extends BaseDomain {
	private static final long serialVersionUID = 1L;
	
	// 模板和步骤关系list
	private List<Znywtemplatesteprel> znywtemplatesteprellist=new ArrayList<Znywtemplatesteprel>();

	public List<Znywtemplatesteprel> getZnywtemplatesteprellist() {
		return znywtemplatesteprellist;
	}

	public void setZnywtemplatesteprellist(List<Znywtemplatesteprel> znywtemplatesteprellist) {
		this.znywtemplatesteprellist = znywtemplatesteprellist;
	}
}

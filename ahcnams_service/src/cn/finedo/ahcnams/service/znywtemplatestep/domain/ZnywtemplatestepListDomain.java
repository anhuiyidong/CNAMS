/**
 * 模板步骤列表领域对象
 *
 * @version 1.0
 * @since 2018-11-20
 */
package cn.finedo.ahcnams.service.znywtemplatestep.domain;

import java.util.List;
import java.util.ArrayList;
import cn.finedo.common.domain.BaseDomain;
import cn.finedo.ahcnams.pojo.Znywtemplatestep;

public class ZnywtemplatestepListDomain extends BaseDomain {
	private static final long serialVersionUID = 1L;
	
	// 模板步骤list
	private List<Znywtemplatestep> znywtemplatesteplist=new ArrayList<Znywtemplatestep>();

	public List<Znywtemplatestep> getZnywtemplatesteplist() {
		return znywtemplatesteplist;
	}

	public void setZnywtemplatesteplist(List<Znywtemplatestep> znywtemplatesteplist) {
		this.znywtemplatesteplist = znywtemplatesteplist;
	}
}

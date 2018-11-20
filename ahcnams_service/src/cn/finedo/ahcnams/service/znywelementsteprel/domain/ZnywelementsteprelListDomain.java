/**
 * 网元和模板步骤对应关系列表领域对象
 *
 * @version 1.0
 * @since 2018-11-20
 */
package cn.finedo.ahcnams.service.znywelementsteprel.domain;

import java.util.List;
import java.util.ArrayList;
import cn.finedo.common.domain.BaseDomain;
import cn.finedo.ahcnams.pojo.Znywelementsteprel;

public class ZnywelementsteprelListDomain extends BaseDomain {
	private static final long serialVersionUID = 1L;
	
	// 网元和模板步骤对应关系list
	private List<Znywelementsteprel> znywelementsteprellist=new ArrayList<Znywelementsteprel>();

	public List<Znywelementsteprel> getZnywelementsteprellist() {
		return znywelementsteprellist;
	}

	public void setZnywelementsteprellist(List<Znywelementsteprel> znywelementsteprellist) {
		this.znywelementsteprellist = znywelementsteprellist;
	}
}

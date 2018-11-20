/**
 * 网元信息列表领域对象
 *
 * @version 1.0
 * @since 2018-11-20
 */
package cn.finedo.ahcnams.service.znywnetelement.domain;

import java.util.List;
import java.util.ArrayList;
import cn.finedo.common.domain.BaseDomain;
import cn.finedo.ahcnams.pojo.Znywnetelement;

public class ZnywnetelementListDomain extends BaseDomain {
	private static final long serialVersionUID = 1L;
	
	// 网元信息list
	private List<Znywnetelement> znywnetelementlist=new ArrayList<Znywnetelement>();

	public List<Znywnetelement> getZnywnetelementlist() {
		return znywnetelementlist;
	}

	public void setZnywnetelementlist(List<Znywnetelement> znywnetelementlist) {
		this.znywnetelementlist = znywnetelementlist;
	}
}

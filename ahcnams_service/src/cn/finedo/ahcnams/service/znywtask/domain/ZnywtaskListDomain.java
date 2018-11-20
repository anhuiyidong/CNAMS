/**
 * 任务列表领域对象
 *
 * @version 1.0
 * @since 2018-11-20
 */
package cn.finedo.ahcnams.service.znywtask.domain;

import java.util.List;
import java.util.ArrayList;
import cn.finedo.common.domain.BaseDomain;
import cn.finedo.ahcnams.pojo.Znywtask;

public class ZnywtaskListDomain extends BaseDomain {
	private static final long serialVersionUID = 1L;
	
	// 任务list
	private List<Znywtask> znywtasklist=new ArrayList<Znywtask>();

	public List<Znywtask> getZnywtasklist() {
		return znywtasklist;
	}

	public void setZnywtasklist(List<Znywtask> znywtasklist) {
		this.znywtasklist = znywtasklist;
	}
}

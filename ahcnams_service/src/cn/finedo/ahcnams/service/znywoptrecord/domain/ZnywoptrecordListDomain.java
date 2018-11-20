/**
 * 操作记录列表领域对象
 *
 * @version 1.0
 * @since 2018-11-20
 */
package cn.finedo.ahcnams.service.znywoptrecord.domain;

import java.util.List;
import java.util.ArrayList;
import cn.finedo.common.domain.BaseDomain;
import cn.finedo.ahcnams.pojo.Znywoptrecord;

public class ZnywoptrecordListDomain extends BaseDomain {
	private static final long serialVersionUID = 1L;
	
	// 操作记录list
	private List<Znywoptrecord> znywoptrecordlist=new ArrayList<Znywoptrecord>();

	public List<Znywoptrecord> getZnywoptrecordlist() {
		return znywoptrecordlist;
	}

	public void setZnywoptrecordlist(List<Znywoptrecord> znywoptrecordlist) {
		this.znywoptrecordlist = znywoptrecordlist;
	}
}

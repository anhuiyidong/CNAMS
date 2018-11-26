/**
 * 厂家信息表列表领域对象
 *
 * @version 1.0
 * @since 2018-11-24
 */
package cn.finedo.ahcnams.service.znywfactoryinfo.domain;

import java.util.ArrayList;
import java.util.List;

import cn.finedo.ahcnams.pojo.Znywfactoryinfo;
import cn.finedo.common.domain.BaseDomain;

public class ZnywfactoryinfoListDomain extends BaseDomain {
	private static final long serialVersionUID = 1L;
	
	// 厂家信息表list
	private List<Znywfactoryinfo> znywfactoryinfolist=new ArrayList<Znywfactoryinfo>();

	public List<Znywfactoryinfo> getZnywfactoryinfolist() {
		return znywfactoryinfolist;
	}

	public void setZnywfactoryinfolist(List<Znywfactoryinfo> znywfactoryinfolist) {
		this.znywfactoryinfolist = znywfactoryinfolist;
	}
}

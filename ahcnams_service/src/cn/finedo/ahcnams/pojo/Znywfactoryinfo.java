/*
 *厂家信息表
 *
 *@version:1.0
 *@company:finedo.cn
 */
package cn.finedo.ahcnams.pojo;

import cn.finedo.common.domain.BaseDomain;

public class Znywfactoryinfo extends BaseDomain {
	private static final long serialVersionUID = 1L;

	//厂家id
	private String facid;

	//厂家名称
	private String facname;

	//联系人
	private String contactor;

	//备注
	private String remark;

	public void setFacid(String facid) {
		this.facid = facid;
	}

	public String getFacid() {
		return this.facid;
	}

	public void setFacname(String facname) {
		this.facname = facname;
	}

	public String getFacname() {
		return this.facname;
	}

	public void setContactor(String contactor) {
		this.contactor = contactor;
	}

	public String getContactor() {
		return this.contactor;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getRemark() {
		return this.remark;
	}

}

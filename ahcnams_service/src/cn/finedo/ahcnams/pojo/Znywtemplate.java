/*
 *模板
 *
 *@version:1.0
 *@company:finedo.cn
 */
package cn.finedo.ahcnams.pojo;

import cn.finedo.common.domain.BaseDomain;

public class Znywtemplate extends BaseDomain {
	private static final long serialVersionUID = 1L;

	//模板id
	private String templateid;

	//模板名称
	private String templatename;

	//创建者
	private String creator;

	//创建时间
	private String createtime;

	//模板状态
	private String templatestatus;

	//备注
	private String remark;

	public void setTemplateid(String templateid) {
		this.templateid = templateid;
	}

	public String getTemplateid() {
		return this.templateid;
	}

	public void setTemplatename(String templatename) {
		this.templatename = templatename;
	}

	public String getTemplatename() {
		return this.templatename;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getCreator() {
		return this.creator;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}

	public String getCreatetime() {
		return this.createtime;
	}

	public void setTemplatestatus(String templatestatus) {
		this.templatestatus = templatestatus;
	}

	public String getTemplatestatus() {
		return this.templatestatus;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getRemark() {
		return this.remark;
	}

}

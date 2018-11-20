/*
 *模板和步骤关系
 *
 *@version:1.0
 *@company:finedo.cn
 */
package cn.finedo.ahcnams.pojo;

import cn.finedo.common.domain.BaseDomain;

public class Znywtemplatesteprel extends BaseDomain {
	private static final long serialVersionUID = 1L;

	//关系id
	private String relid;

	//模板id
	private String templateid;

	//步骤id
	private String stepid;

	//模板id 名称
	private String templatename;

	//步骤id 名称
	private String stepname;

	public void setRelid(String relid) {
		this.relid = relid;
	}

	public String getRelid() {
		return this.relid;
	}

	public void setTemplateid(String templateid) {
		this.templateid = templateid;
	}

	public String getTemplateid() {
		return this.templateid;
	}

	public void setStepid(String stepid) {
		this.stepid = stepid;
	}

	public String getStepid() {
		return this.stepid;
	}

	public void setTemplatename(String templatename) {
		this.templatename = templatename;
	}

	public String getTemplatename() {
		return this.templatename;
	}

	public void setStepname(String stepname) {
		this.stepname = stepname;
	}

	public String getStepname() {
		return this.stepname;
	}

}

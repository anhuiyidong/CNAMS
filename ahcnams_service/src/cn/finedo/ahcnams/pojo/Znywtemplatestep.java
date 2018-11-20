/*
 *模板步骤
 *
 *@version:1.0
 *@company:finedo.cn
 */
package cn.finedo.ahcnams.pojo;

import cn.finedo.common.domain.BaseDomain;

public class Znywtemplatestep extends BaseDomain {
	private static final long serialVersionUID = 1L;

	//步骤id
	private String stepid;

	//步骤名称
	private String stepname;

	//脚本
	private String stepscript;

	//关联网元id
	private String relatedele;

	//备注
	private String stepremark;

	public void setStepid(String stepid) {
		this.stepid = stepid;
	}

	public String getStepid() {
		return this.stepid;
	}

	public void setStepname(String stepname) {
		this.stepname = stepname;
	}

	public String getStepname() {
		return this.stepname;
	}

	public void setStepscript(String stepscript) {
		this.stepscript = stepscript;
	}

	public String getStepscript() {
		return this.stepscript;
	}

	public void setRelatedele(String relatedele) {
		this.relatedele = relatedele;
	}

	public String getRelatedele() {
		return this.relatedele;
	}

	public void setStepremark(String stepremark) {
		this.stepremark = stepremark;
	}

	public String getStepremark() {
		return this.stepremark;
	}

}

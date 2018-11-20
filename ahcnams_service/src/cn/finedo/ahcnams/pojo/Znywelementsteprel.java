/*
 *网元和模板步骤对应关系
 *
 *@version:1.0
 *@company:finedo.cn
 */
package cn.finedo.ahcnams.pojo;

import cn.finedo.common.domain.BaseDomain;

public class Znywelementsteprel extends BaseDomain {
	private static final long serialVersionUID = 1L;

	//关系id
	private String relid;

	//网元id
	private String eletid;

	//步骤id
	private String stepid;

	//网元id 名称
	private String eletname;

	//步骤id 名称
	private String stepname;

	public void setRelid(String relid) {
		this.relid = relid;
	}

	public String getRelid() {
		return this.relid;
	}

	public void setEletid(String eletid) {
		this.eletid = eletid;
	}

	public String getEletid() {
		return this.eletid;
	}

	public void setStepid(String stepid) {
		this.stepid = stepid;
	}

	public String getStepid() {
		return this.stepid;
	}

	public void setEletname(String eletname) {
		this.eletname = eletname;
	}

	public String getEletname() {
		return this.eletname;
	}

	public void setStepname(String stepname) {
		this.stepname = stepname;
	}

	public String getStepname() {
		return this.stepname;
	}

}

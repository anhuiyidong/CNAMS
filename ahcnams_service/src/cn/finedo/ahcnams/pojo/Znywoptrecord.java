/*
 *操作记录
 *
 *@version:1.0
 *@company:finedo.cn
 */
package cn.finedo.ahcnams.pojo;

import cn.finedo.common.domain.BaseDomain;

public class Znywoptrecord extends BaseDomain {
	private static final long serialVersionUID = 1L;

	//操作记录id
	private String optid;

	//任务id
	private String taskid;

	//操作人
	private String optperson;

	//操作时间
	private String opttime;

	//任务id 名称
	private String taskname;

	public void setOptid(String optid) {
		this.optid = optid;
	}

	public String getOptid() {
		return this.optid;
	}

	public void setTaskid(String taskid) {
		this.taskid = taskid;
	}

	public String getTaskid() {
		return this.taskid;
	}

	public void setOptperson(String optperson) {
		this.optperson = optperson;
	}

	public String getOptperson() {
		return this.optperson;
	}

	public void setOpttime(String opttime) {
		this.opttime = opttime;
	}

	public String getOpttime() {
		return this.opttime;
	}

	public void setTaskname(String taskname) {
		this.taskname = taskname;
	}

	public String getTaskname() {
		return this.taskname;
	}

}

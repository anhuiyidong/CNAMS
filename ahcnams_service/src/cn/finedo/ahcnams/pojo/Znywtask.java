/*
 *任务
 *
 *@version:1.0
 *@company:finedo.cn
 */
package cn.finedo.ahcnams.pojo;

import cn.finedo.common.domain.BaseDomain;

public class Znywtask extends BaseDomain {
	private static final long serialVersionUID = 1L;

	//任务id
	private String taskid;

	//模板id
	private String templateid;

	//执行人
	private String executor;

	//执行时间
	private String executetime;

	//状态
	private String status;

	//开始时间
	private String starttime;

	//结束时间
	private String expiretime;

	//部门
	private String dept;

	//备注
	private String remark;

	//模板id 名称
	private String templatename;

	public void setTaskid(String taskid) {
		this.taskid = taskid;
	}

	public String getTaskid() {
		return this.taskid;
	}

	public void setTemplateid(String templateid) {
		this.templateid = templateid;
	}

	public String getTemplateid() {
		return this.templateid;
	}

	public void setExecutor(String executor) {
		this.executor = executor;
	}

	public String getExecutor() {
		return this.executor;
	}

	public void setExecutetime(String executetime) {
		this.executetime = executetime;
	}

	public String getExecutetime() {
		return this.executetime;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}

	public String getStarttime() {
		return this.starttime;
	}

	public void setExpiretime(String expiretime) {
		this.expiretime = expiretime;
	}

	public String getExpiretime() {
		return this.expiretime;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}

	public String getDept() {
		return this.dept;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setTemplatename(String templatename) {
		this.templatename = templatename;
	}

	public String getTemplatename() {
		return this.templatename;
	}

}

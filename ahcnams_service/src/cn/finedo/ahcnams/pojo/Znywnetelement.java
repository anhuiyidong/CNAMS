/*
 *网元信息
 *
 *@version:1.0
 *@company:finedo.cn
 */
package cn.finedo.ahcnams.pojo;

import cn.finedo.common.domain.BaseDomain;

public class Znywnetelement extends BaseDomain {
	private static final long serialVersionUID = 1L;

	//网元id
	private String eletid;

	//网元名称
	private String elename;

	//IP地址
	private String eleip;

	//所属厂家
	private String elefactory;

	//局域网
	private String elenet;

	//网元类型
	private String eletype;

	//创建者
	private String creator;

	//创建时间
	private String createtime;

	//所属地市
	private String city;

	//登录类型
	private String logintype;

	//端口
	private String port;

	//用户名
	private String username;

	//密码
	private String password;

	//备注
	private String remark;

	public void setEletid(String eletid) {
		this.eletid = eletid;
	}

	public String getEletid() {
		return this.eletid;
	}

	public void setElename(String elename) {
		this.elename = elename;
	}

	public String getElename() {
		return this.elename;
	}

	public void setEleip(String eleip) {
		this.eleip = eleip;
	}

	public String getEleip() {
		return this.eleip;
	}

	public void setElefactory(String elefactory) {
		this.elefactory = elefactory;
	}

	public String getElefactory() {
		return this.elefactory;
	}

	public void setElenet(String elenet) {
		this.elenet = elenet;
	}

	public String getElenet() {
		return this.elenet;
	}

	public void setEletype(String eletype) {
		this.eletype = eletype;
	}

	public String getEletype() {
		return this.eletype;
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

	public void setCity(String city) {
		this.city = city;
	}

	public String getCity() {
		return this.city;
	}

	public void setLogintype(String logintype) {
		this.logintype = logintype;
	}

	public String getLogintype() {
		return this.logintype;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public String getPort() {
		return this.port;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUsername() {
		return this.username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword() {
		return this.password;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getRemark() {
		return this.remark;
	}

}

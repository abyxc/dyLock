package net.dy.domain;

import java.io.Serializable;

/**
 * @author 作者 E-mail:elizhiyong@163.com 
 * @version 创建时间：2016年9月5日 上午10:39:30 
 * {@link http://www.chncode.net }
 * 
 * 后台管理员账户表
 */

@SuppressWarnings("serial")
public class Company implements Serializable {
	
	private String id;
	
	private String type;//待定
	
	private String createTime;
	
	private String name;//公司名称
	
	private String address;//地址
	
	private String phone;//联系电话

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Override
	public String toString() {
		return "Company [id=" + id + ", type=" + type + ", createTime=" + createTime + ", name=" + name + ", address=" + address + ", phone=" + phone + "]";
	}

	
}

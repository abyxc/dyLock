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
public class User implements Serializable {
	
	private String id;
	
	private String mobile;
	
	private String companyId;//公司id
	
	private String type;//待定
	
	private String createTime;
	
	private String updateTime;
	
	private String phoneModel;//2表示Ios;1表示Android
	
	private String sdkV;//SDK版本
	
	private String systemV;//手机型号,如三星s7

	private String code;//接受验证码
	
	
	
	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
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

	public String getPhoneModel() {
		return phoneModel;
	}

	public void setPhoneModel(String phoneModel) {
		this.phoneModel = phoneModel;
	}

	public String getSdkV() {
		return sdkV;
	}

	public void setSdkV(String sdkV) {
		this.sdkV = sdkV;
	}

	public String getSystemV() {
		return systemV;
	}

	public void setSystemV(String systemV) {
		this.systemV = systemV;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", mobile=" + mobile + ", companyId=" + companyId + ", type=" + type + ", createTime=" + createTime +  ", phoneModel=" + phoneModel
				+ ", sdkV=" + sdkV + ", systemV=" + systemV + "]";
	}

	
	
}

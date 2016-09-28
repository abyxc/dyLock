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
public class Refresh implements Serializable {
	
	private String id;
	
	private String mobile;
	
	
	private String createTime;
	
	private String phoneModel;//2表示Ios;1表示Android
	
	private String sdkV;//SDK版本
	
	private String systemV;//手机型号,如三星s7
	
	private String lockMac;//锁mac
	
	private String firmwareOld;//当前固件版本
	
	private String firmwareNew;//最更新固件
	
	private String softwareV;//软件版本
	
	private String companyId;
	
	

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
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

	public String getLockMac() {
		return lockMac;
	}

	public void setLockMac(String lockMac) {
		this.lockMac = lockMac;
	}

	public String getFirmwareOld() {
		return firmwareOld;
	}

	public void setFirmwareOld(String firmwareOld) {
		this.firmwareOld = firmwareOld;
	}

	public String getFirmwareNew() {
		return firmwareNew;
	}

	public void setFirmwareNew(String firmwareNew) {
		this.firmwareNew = firmwareNew;
	}

	public String getSoftwareV() {
		return softwareV;
	}

	public void setSoftwareV(String softwareV) {
		this.softwareV = softwareV;
	}

	@Override
	public String toString() {
		return "Refresh [id=" + id + ", mobile=" + mobile + ", createTime=" + createTime + ", phoneModel=" + phoneModel + ", sdkV=" + sdkV + ", systemV=" + systemV + ", lockMac=" + lockMac
				+ ", firmwareOld=" + firmwareOld + ", firmwareNew=" + firmwareNew + ", softwareV=" + softwareV + "]";
	}
	
	

	
}

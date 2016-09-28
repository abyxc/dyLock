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
public class AddLock implements Serializable {
	
	private String id;
	
	private String mobile;
	
	private String companyId;//公司id
	
	private String createTime;
	
	private String phoneModel;//2表示Ios;1表示Android
	
	private String sdkV;//SDK版本
	
	private String systemV;//手机型号,如三星s7
	
	private String lockMac;//锁mac
	
	private String openTime;//开锁时间
	
	private String ipAddress;//手机获取的ip地址
	
	private String judge;//添加是否成功;2表示成功；1表示不成功
	
	private String judgeContent;//添加失败原因;如添加失败，说明原因
	
	private String lockFirmware;//锁固件版本
	
	
	
	public String getLockFirmware() {
		return lockFirmware;
	}

	public void setLockFirmware(String lockFirmware) {
		this.lockFirmware = lockFirmware;
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


	public String getCreateTime() {
		return createTime;
	}

	public String getLockMac() {
		return lockMac;
	}

	public void setLockMac(String lockMac) {
		this.lockMac = lockMac;
	}

	public String getOpenTime() {
		return openTime;
	}

	public void setOpenTime(String openTime) {
		this.openTime = openTime;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public String getJudge() {
		return judge;
	}

	public void setJudge(String judge) {
		this.judge = judge;
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

	public String getJudgeContent() {
		return judgeContent;
	}

	public void setJudgeContent(String judgeContent) {
		this.judgeContent = judgeContent;
	}

	@Override
	public String toString() {
		return "AddLock [id=" + id + ", mobile=" + mobile + ", companyId=" + companyId + ", createTime=" + createTime + ", phoneModel=" + phoneModel + ", sdkV=" + sdkV + ", systemV=" + systemV
				+ ", lockMac=" + lockMac + ", openTime=" + openTime + ", ipAddress=" + ipAddress + ", judge=" + judge + ", judgeContent=" + judgeContent + ", lockFirmware=" + lockFirmware + "]";
	}


	
}

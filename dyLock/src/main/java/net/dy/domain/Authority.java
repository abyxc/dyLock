package net.dy.domain;

import java.io.Serializable;

/**
 * @author 作者 E-mail:elizhiyong@163.com 
 * @version 创建时间：2016年9月5日 上午10:39:30 
 * {@link http://www.chncode.net }
 * 
 * 权限表（具有哪些功能）
 */

@SuppressWarnings("serial")
public class Authority implements Serializable {
	
	private String id;
	
	private String authorityName;//功能名称
	
	private String authorityUrl;//功能路径
	
	private String type;//是否拥有子列表，0：代表有；1代表没有
	
	private String createTime;
	
	
	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAuthorityName() {
		return authorityName;
	}

	public void setAuthorityName(String authorityName) {
		this.authorityName = authorityName;
	}

	public String getAuthorityUrl() {
		return authorityUrl;
	}

	public void setAuthorityUrl(String authorityUrl) {
		this.authorityUrl = authorityUrl;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	
	
}

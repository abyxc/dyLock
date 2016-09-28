package com.mnsn.project.messages;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

/**
 * 知识信息表
 * @author ck
 *  @Time 2015-12-28 下午 3:16:33
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "db_gz_messages")
public class Messages implements Serializable{
	@Id
	@Column(name = "ID", nullable = false, columnDefinition = "varchar(64)")
	@GenericGenerator(name = "idGenerator", strategy = "uuid")
	@GeneratedValue(generator = "idGenerator")
	private String id;// 主键
	@Column(name = "title", columnDefinition = "varchar(255)")
	private String title;//标题
	@Column(name = "user_id", columnDefinition = "varchar(64)")
	private String user_id;//用户id
	@Column(name = "info", columnDefinition = "text")
	private String info;//内容
	@Column(name = "info_type", columnDefinition = "int")
	private String info_type;//信息类型 1:简介，2:知识
	@Column(name = "create_time", columnDefinition = "datetime")
	private Date create_time;//创建时间	
	@Column(name = "update_time", columnDefinition = "datetime")
	private Date update_time;//修改时间		
	@Column(name = "url", columnDefinition = "varchar(255)")
	private String url;//多图时用'//'隔开
	@Column(name = "state", columnDefinition = "int")
	private Integer state;//0:正常，1:删除
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public String getInfo_type() {
		return info_type;
	}
	public void setInfo_type(String info_type) {
		this.info_type = info_type;
	}
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	public Date getUpdate_time() {
		return update_time;
	}
	public void setUpdate_time(Date update_time) {
		this.update_time = update_time;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}	
	
	
}

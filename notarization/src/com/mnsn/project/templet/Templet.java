package com.mnsn.project.templet;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
/**
 * 信息管理模板表
 * @author SZW
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "db_gz_templet")
public class Templet implements Serializable{
	@Id
	@Column(name = "ID", nullable = false, columnDefinition = "varchar(64)")
	@GenericGenerator(name = "idGenerator", strategy = "uuid")
	@GeneratedValue(generator = "idGenerator")
	private String id;// 主键
	
	@Column(name="name",columnDefinition="varchar(64)")
	private String name;//模板名称
	
	@Column(name="content",columnDefinition="varchar(512)")
	private String content;//模板内容
	
	private String type_id;//类型
	
	private String state;//状态 0表示未应用,1表示启用

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getType_id() {
		return type_id;
	}

	public void setType_id(String typeId) {
		type_id = typeId;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
	
	
	
}

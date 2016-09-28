package com.mnsn.project.log;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;


/**
 * 操作日志
 * @author zdb
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "db_gz_log")
public class Log implements Serializable{
	@Id  
	@Column(name = "ID", nullable = false, columnDefinition = "varchar(64)")
	@GeneratedValue(generator = "idGenerator")    
	@GenericGenerator(name = "idGenerator", strategy = "uuid") 
	private String id;// 主键
	
	@Column(name = "create_time",columnDefinition = "datetime")
	private Date create_time;//创建日期
	
	@Column(name = "ip",columnDefinition = "varchar(20)")
	private String ip;//ip
	
	@Column(name = "user_id",columnDefinition = "varchar(64)")
	private String user_id;//操作人
	
	@Column(name = "type",columnDefinition = "int")
	private Integer type;//大类型（什么表）：1用户表，2短信列表，3模板列表，4角色表，5证件类型，6事件类型，7公正知识
						//8申办管理
	
	@Column(name = "type_tiny",columnDefinition = "int")
	private Integer type_tiny;//小类型（具体操作）:1添加，2修改，3删除，4审批，5登陆，6完结，20其他
	
	@Column(name = "memo",columnDefinition = "varchar(512)")
	private String memo;//操作描述
	
	@Column(name = "user_name",columnDefinition = "varchar(512)")
	private String user_name;//登录名


	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date createTime) {
		create_time = createTime;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String userId) {
		user_id = userId;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getType_tiny() {
		return type_tiny;
	}

	public void setType_tiny(Integer typeTiny) {
		type_tiny = typeTiny;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String userName) {
		user_name = userName;
	}



}

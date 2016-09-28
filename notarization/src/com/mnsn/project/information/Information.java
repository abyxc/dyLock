package com.mnsn.project.information;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
/**
 * 信息列表
 * @author SZW
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "db_gz_information")
public class Information implements Serializable{
	@Id  
	@Column(name = "ID", nullable = false, columnDefinition = "varchar(64)")
	@GeneratedValue(generator = "idGenerator")    
	@GenericGenerator(name = "idGenerator", strategy = "uuid") 
	private String id;// 主键
	
	@Column(name="type",columnDefinition="int")
	private Integer type;//类型：0,注册短信，1，登录短信，2，国内业务申办成功，3涉外业务申办成功，
	//4国内业务申办失败，5涉外业务申办失败，6拒绝申办，7.完结后缴费成功提示
	
	@Column(name="state",columnDefinition="int")
	private Integer state;//发送状态，0成功，1失败
	
	@Column(name="state_memo",columnDefinition="varchar(512)")
	private String state_memo;//状态备注，主要是失败时备注，描述失败原因
	
	@Column(name="phone",columnDefinition="varchar(64)")
	private String phone;//短信接收人手机号码
	
	@Column(name="accept_user_id",columnDefinition="varchar(64)")
	private String accept_user_id;//短信接收人id,后台发送的短信才有
	
	@Column(name="upload_main_id",columnDefinition="varchar(64)")
	private String upload_main_id;//申办记录id，验证码短信没有
	
	@Column(name="send_time",columnDefinition="datetime")
	private Date send_time;//发送时间
	
	@Column(name="content",columnDefinition="varchar(512)")
	private String content;//短信内容
	
	@Column(name="send_state",columnDefinition="varchar(16)")
	private String send_state;//发送状态状态(1,成功到达目标号码,0成功提交到短信中心，-1状态返回失败，-2状态返回过期，-3号码错误，-4敏感字符，-5流量超出，-6主叫号码或密码错误，) (在上state为0时有效)
	
//	@Column(name="sms_id",columnDefinition="varchar(64)")
//	private String sms_id;//短信服务接口中的短信的id，理解成这个短信在移动服务器中的id


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public Integer getType() {
		return type;
	}


	public void setType(Integer type) {
		this.type = type;
	}


	public Integer getState() {
		return state;
	}


	public void setState(Integer state) {
		this.state = state;
	}

	public String getState_memo() {
		return state_memo;
	}


	public void setState_memo(String stateMemo) {
		state_memo = stateMemo;
	}

	public String getPhone() {
		return phone;
	}


	public void setPhone(String phone) {
		this.phone = phone;
	}


	public String getAccept_user_id() {
		return accept_user_id;
	}


	public void setAccept_user_id(String acceptUserId) {
		accept_user_id = acceptUserId;
	}


	public String getUpload_main_id() {
		return upload_main_id;
	}


	public void setUpload_main_id(String uploadMainId) {
		upload_main_id = uploadMainId;
	}


	public Date getSend_time() {
		return send_time;
	}


	public void setSend_time(Date sendTime) {
		send_time = sendTime;
	}


	public String getContent() {
		return content;
	}


	public void setContent(String content) {
		this.content = content;
	}


	public String getSend_state() {
		return send_state;
	}


	public void setSend_state(String sendState) {
		send_state = sendState;
	}

}

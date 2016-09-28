package com.mnsn.project.user;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import com.mnsn.project.group.Group;


/**
 *   用户表
 * @author ck
 *  @Time 2015-12-28 下午 3:16:33
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "db_gz_users")
public class User implements Serializable{

	@Id
	@Column(name = "ID", nullable = false, columnDefinition = "varchar(64)")
	@GenericGenerator(name = "idGenerator", strategy = "uuid")
	@GeneratedValue(generator = "idGenerator")
	private String id;//主键	
	@Column(name = "name",columnDefinition = "varchar(100)")
	private String name;//名称	
	@Column(name = "sex",columnDefinition = "int")
	private Integer sex;//性别（0为男，1为女）	
	@Column(name = "mobile_phone",columnDefinition = "varchar(100)")
	private String mobile_phone;//手机号码
	@Column(name = "idcard_type",columnDefinition = "int")
	private Integer idcard_type;//证件类型 (app注册时才需要 1：身份证,2：护照,3：台湾地区身份证,4：港澳通行证)
	@Column(name = "idcard_number",columnDefinition = "varchar(200)")
	private String idcard_number;//证件号码	
	@Column(name = "address",columnDefinition = "varchar(200)")
	private String address;//地址	
	@Column(name = "loginname",columnDefinition = "varchar(100)")
	private String loginname;//登录名	
	@Column(name = "password",columnDefinition = "varchar(100)")
	private String password;//密码
	@Column(name = "imei",columnDefinition = "varchar(100)")
	private String imei;//手机串号	
	@Column(name = "role_type",columnDefinition = "int")
	private Integer role_type;//角色类型:1：app;2表示是后台用户，不能登录app, 
	@Column(name = "role_id",columnDefinition = "varchar(64)")
	private String role_id;//角色id	(只有当用户的是后台用户的时候不为空)			
	@Column(name = "create_time",columnDefinition = "datetime")
	private Date create_time;//创建日期
	@Column(name = "remark",columnDefinition = "varchar(4000)")
	private String remark;//备注
	@Column(name = "pic",columnDefinition = "varchar(100)")
	private String pic;//图

	@Column(name = "openid",columnDefinition = "varchar(100)")
	private String openid;//微信账号，只有微信用户不为空。
	
	@ManyToOne
	@JoinColumn(name = "role_id",insertable = false, updatable = false)
	private Group group;
	
	
	
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
	public Integer getSex() {
		return sex;
	}
	public void setSex(Integer sex) {
		this.sex = sex;
	}
	public String getMobile_phone() {
		return mobile_phone;
	}
	public void setMobile_phone(String mobile_phone) {
		this.mobile_phone = mobile_phone;
	}
	public Integer getIdcard_type() {
		return idcard_type;
	}
	public void setIdcard_type(Integer idcardType) {
		idcard_type = idcardType;
	}
	public String getIdcard_number() {
		return idcard_number;
	}
	public void setIdcard_number(String idcard_number) {
		this.idcard_number = idcard_number;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getLoginname() {
		return loginname;
	}
	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getImei() {
		return imei;
	}
	public void setImei(String imei) {
		this.imei = imei;
	}
	public Integer getRole_type() {
		return role_type;
	}
	public void setRole_type(Integer role_type) {
		this.role_type = role_type;
	}
	public String getRole_id() {
		return role_id;
	}
	public void setRole_id(String role_id) {
		this.role_id = role_id;
	}
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getPic() {
		return pic;
	}
	public void setPic(String pic) {
		this.pic = pic;
	}
	public Group getGroup() {
		return group;
	}
	public void setGroup(Group group) {
		this.group = group;
	}
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}

}

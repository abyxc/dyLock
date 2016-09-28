package com.mnsn.project.uploadMain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 公正事务主表  
 * @author ck
 *  @Time 2015-12-28 下午 3:16:33
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "db_gz_upload_main")
public class UploadMain implements Serializable{
	@Id
	@Column(name = "ID", nullable = false, columnDefinition = "varchar(64)")
	@GenericGenerator(name = "idGenerator", strategy = "uuid")
	@GeneratedValue(generator = "idGenerator")
	private String id;// 主键
	@Column(name = "gz_type_id", columnDefinition = "varchar(64)")
	private String gz_type_id;//关联公正类型表(db_gz_type)ID
	@Column(name = "users_id", columnDefinition = "varchar(64)")
	private String users_id;//用户的id
	@Column(name = "country_id", columnDefinition = "varchar(64)")
	private String country_id;//证件使用国家表（db_gz_country）Id
	@Column(name = "language_id", columnDefinition = "varchar(64)")
	private String language_id;//语言表（db_gz_language）Id
	@Column(name = "gz_use", columnDefinition = "varchar(64)")
	private String gz_use;//公正用途
	@Column(name = "create_time", columnDefinition = "datetime")
	private Date create_time;//创建时间
	@Column(name = "gz_status", columnDefinition = "int")
	private Integer gz_status;//审核状态  (0拒绝受理,1等待审核，2审核成功，3审核失败,4已撤销,5已纳费，6完结)
	@Column(name = "gz_count", columnDefinition = "int")
	private String gz_count;//公证书份数
	@Column(name = "remark", columnDefinition = "varchar(255)")
	private String remark;//备注
	
	@Column(name="gz_class",columnDefinition="varchar(255)")
	private String gz_class;//公正类型  (国内,涉外)公证类型如果是国内的，就在后台不收取任何费用，
							//如果类型为涉外并且必须翻译成英语的话，就必须在后台审核时添加费用，其余翻译费用都需到公证处领取公证书时缴纳。
	
	@Column(name="order_num",columnDefinition="varchar(100)")
	private String order_num;//记录编号
	
	@Column(name="price_language",columnDefinition="decimal(18,3)")
	private BigDecimal price_language;//翻译费用
	
	@Column(name="price_country",columnDefinition="decimal(18,3)")
	private BigDecimal price_country;//使用地费用
	
	@Column(name="price_page",columnDefinition="decimal(18,3)")
	private BigDecimal price_page;//份数费用
	
	@Column(name="gz_receive_type",columnDefinition="varchar(256)")
	private String gz_receive_type;//领取方式（汉字）
	
	public BigDecimal getPrice_language() {
		return price_language;
	}
	public void setPrice_language(BigDecimal priceLanguage) {
		price_language = priceLanguage;
	}
	public BigDecimal getPrice_country() {
		return price_country;
	}
	public void setPrice_country(BigDecimal priceCountry) {
		price_country = priceCountry;
	}
	public BigDecimal getPrice_page() {
		return price_page;
	}
	public void setPrice_page(BigDecimal pricePage) {
		price_page = pricePage;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getGz_type_id() {
		return gz_type_id;
	}
	public void setGz_type_id(String gz_type_id) {
		this.gz_type_id = gz_type_id;
	}
	public String getUsers_id() {
		return users_id;
	}
	public void setUsers_id(String users_id) {
		this.users_id = users_id;
	}
	public String getCountry_id() {
		return country_id;
	}
	public void setCountry_id(String country_id) {
		this.country_id = country_id;
	}
	public String getLanguage_id() {
		return language_id;
	}
	public void setLanguage_id(String language_id) {
		this.language_id = language_id;
	}
	public String getGz_use() {
		return gz_use;
	}
	public void setGz_use(String gz_use) {
		this.gz_use = gz_use;
	}
	
	public Integer getGz_status() {
		return gz_status;
	}
	public void setGz_status(Integer gzStatus) {
		gz_status = gzStatus;
	}
	public String getGz_count() {
		return gz_count;
	}
	public void setGz_count(String gz_count) {
		this.gz_count = gz_count;
	}
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date createTime) {
		create_time = createTime;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getGz_class() {
		return gz_class;
	}
	public void setGz_class(String gzClass) {
		gz_class = gzClass;
	}
	public String getOrder_num() {
		return order_num;
	}
	public void setOrder_num(String orderNum) {
		order_num = orderNum;
	}
	public String getGz_receive_type() {
		return gz_receive_type;
	}
	public void setGz_receive_type(String gzReceiveType) {
		gz_receive_type = gzReceiveType;
	}
	
}

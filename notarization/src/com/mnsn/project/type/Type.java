package com.mnsn.project.type;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

import com.mnsn.project.uploadPicture.UploadPicture;

/**
 * 公正事项类型表
 * @author ck
 *  @Time 2015-12-28 下午 3:16:33
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "db_gz_type")
public class Type implements Serializable{
	@Id
	@Column(name = "ID", nullable = false, columnDefinition = "varchar(64)")
	@GenericGenerator(name = "idGenerator", strategy = "uuid")
	@GeneratedValue(generator = "idGenerator")
	private String id;// 主键
	
	@Column(name = "gz_name", columnDefinition = "varchar(255)")
	private String gz_name;//名称
	
	@Column(name = "remark", columnDefinition = "varchar(255)")
	private String remark;//备注
	
	@Column(name = "type", columnDefinition = "int")
	private Integer type;//0国内，1国外
	
	
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	public String getGz_name() {
		return gz_name;
	}
	public void setGz_name(String gz_name) {
		this.gz_name = gz_name;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}


}

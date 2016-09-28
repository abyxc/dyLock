package com.mnsn.project.pictureName;

import java.io.Serializable;
import java.util.Date;
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
 * 公正证件类型表主表
 * @author ck
 *  @Time 2015-12-28 下午 3:16:33
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "db_gz_picture_name")
public class PictureName implements Serializable{
	@Id
	@Column(name = "ID", nullable = false, columnDefinition = "varchar(64)")
	@GenericGenerator(name = "idGenerator", strategy = "uuid")
	@GeneratedValue(generator = "idGenerator")
	private String id;// 主键
	@Column(name = "pic_name", columnDefinition = "varchar(255)")
	private String pic_name;//名称

	@Column(name = "remark", columnDefinition = "varchar(2048)")
	private String remark;//备注
	
	@Column(name = "create_time", columnDefinition = "datetime")
	private Date create_time;//创建时间
	
	@Column(name = "mast", columnDefinition = "int")
	private Integer mast;//是否必须拍照 0否   1是
	
	//为了给微信页面进行查看的照片的集合，不存储于数据库
	@Transient
	private List<UploadPicture> uploadPictures;
	
	
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPic_name() {
		return pic_name;
	}
	public void setPic_name(String pic_name) {
		this.pic_name = pic_name;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date createTime) {
		create_time = createTime;
	}
	public Integer getMast() {
		return mast;
	}
	public void setMast(Integer mast) {
		this.mast = mast;
	}
	public List<UploadPicture> getUploadPictures() {
		return uploadPictures;
	}
	public void setUploadPictures(List<UploadPicture> uploadPictures) {
		this.uploadPictures = uploadPictures;
	}




}

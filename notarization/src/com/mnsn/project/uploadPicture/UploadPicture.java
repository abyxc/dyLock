package com.mnsn.project.uploadPicture;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

/**
 * 公正照片表
 * @author ck
 *  @Time 2015-12-28 下午 3:16:33
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "db_gz_upload_picture")
public class UploadPicture implements Serializable{
	@Id
	@Column(name = "ID", nullable = false, columnDefinition = "varchar(64)")
	@GenericGenerator(name = "idGenerator", strategy = "uuid")
	@GeneratedValue(generator = "idGenerator")
	private String id;// 主键
	@Column(name = "picture_name_id", columnDefinition = "varchar(64)")
	private String picture_name_id;//证件类型（db_gz_picture_name）ID
	@Column(name = "picture_url", columnDefinition = "varchar(255)")
	private String picture_url;//图片相对路径
	@Column(name = "picture_url_thumbnail", columnDefinition = "varchar(255)")
	private String picture_url_thumbnail;//图片缩略图相对路径
	@Column(name = "upload_main_id", columnDefinition = "varchar(64)")
	private String upload_main_id;//对应公正事务主表id
	
	@Column(name = "picture_url_thummedu", columnDefinition = "varchar(255)")
	private String picture_url_thummedu;//另一个缩略图(中等大小的)
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPicture_name_id() {
		return picture_name_id;
	}
	public void setPicture_name_id(String picture_name_id) {
		this.picture_name_id = picture_name_id;
	}
	public String getPicture_url() {
		return picture_url;
	}
	public void setPicture_url(String picture_url) {
		this.picture_url = picture_url;
	}
	public String getPicture_url_thumbnail() {
		return picture_url_thumbnail;
	}
	public void setPicture_url_thumbnail(String picture_url_thumbnail) {
		this.picture_url_thumbnail = picture_url_thumbnail;
	}
	public String getUpload_main_id() {
		return upload_main_id;
	}
	public void setUpload_main_id(String uploadMainId) {
		upload_main_id = uploadMainId;
	}
	public String getPicture_url_thummedu() {
		return picture_url_thummedu;
	}
	public void setPicture_url_thummedu(String pictureUrlThummedu) {
		picture_url_thummedu = pictureUrlThummedu;
	}
	
	

}

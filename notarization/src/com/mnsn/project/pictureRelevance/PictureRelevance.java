package com.mnsn.project.pictureRelevance;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

/**
 * 公正类型和证件类型并联表
 * @author ck
 *  @Time 2015-12-28 下午 3:16:33
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "db_gz_picture_relevance")
public class PictureRelevance implements Serializable{
	@Id
	@Column(name = "ID", nullable = false, columnDefinition = "varchar(64)")
	@GenericGenerator(name = "idGenerator", strategy = "uuid")
	@GeneratedValue(generator = "idGenerator")
	private String id;// 主键
	@Column(name = "type_id", columnDefinition = "varchar(64)")
	private String type_id;//关联公正类型表(db_gz_type)ID
	@Column(name = "picture_name_id", columnDefinition = "varchar(64)")
	private String picture_name_id;//管理图片名称表(db_gz_picture_name）ID
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getType_id() {
		return type_id;
	}
	public void setType_id(String type_id) {
		this.type_id = type_id;
	}
	public String getPicture_name_id() {
		return picture_name_id;
	}
	public void setPicture_name_id(String picture_name_id) {
		this.picture_name_id = picture_name_id;
	}

	


}

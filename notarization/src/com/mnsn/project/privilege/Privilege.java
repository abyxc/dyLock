package com.mnsn.project.privilege;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
/**
 *   权限表
 * @author ck
 *  @Time 2015-12-28 下午 3:16:33
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "db_gz_privilege")
public class Privilege implements Serializable{
	@Id
	@Column(name = "ID", nullable = false, columnDefinition = "varchar(64)")
	@GenericGenerator(name = "idGenerator", strategy = "uuid")
	@GeneratedValue(generator = "idGenerator")
	private String id;// 主键
	@Column(name = "code", columnDefinition = "varchar(64)")
	private String code;//唯一标识符	
	@Column(name = "name", columnDefinition = "varchar(64)")
	private String name;
	
	@Column(name = "order_num", columnDefinition = "varchar(64)")
	private String order_num;
	
	@Column(name = "url", columnDefinition = "varchar(255)")
	private String url;
	
	@OneToMany(mappedBy="parent",cascade=CascadeType.REMOVE,fetch=FetchType.EAGER)
	@OrderBy("order_num asc")
	private List<Privilege> children = new ArrayList<Privilege>();
	
	@OneToOne
	@JoinColumn(name="parent")
	private Privilege parent;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getOrder_num() {
		return order_num;
	}
	public void setOrder_num(String order_num) {
		this.order_num = order_num;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public List<Privilege> getChildren() {
		return children;
	}
	public void setChildren(List<Privilege> children) {
		this.children = children;
	}
	public Privilege getParent() {
		return parent;
	}
	public void setParent(Privilege parent) {
		this.parent = parent;
	}

	
}

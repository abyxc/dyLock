package com.mnsn.project.group;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.mnsn.project.privilege.Privilege;

/**
 * 角色
 * 
 * 
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "db_gz_group")
public class Group implements Serializable {

	@Id
	@Column(name = "ID", nullable = false, columnDefinition = "varchar(64)")
	@GenericGenerator(name = "idGenerator", strategy = "uuid")
	@GeneratedValue(generator = "idGenerator")
	private String id;// 主键
	@Column(name = "name", columnDefinition = "varchar(256)")
	private String name;//名称
	@Column(name = "remark", columnDefinition = "varchar(256)")
	private String remark;//备注
	@ManyToMany
	@JoinTable(name="db_gz_group_privilege",joinColumns=@JoinColumn(name="group_id"),inverseJoinColumns=@JoinColumn(name="privilege_id"))
	private List<Privilege> privileges = new ArrayList<Privilege>();

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



	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public List<Privilege> getPrivileges() {
		return privileges;
	}

	public void setPrivileges(List<Privilege> privileges) {
		this.privileges = privileges;
	}


}

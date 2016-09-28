package com.mnsn.project.group;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.mnsn.common.BaseAction;
import com.mnsn.project.privilege.Privilege;

@SuppressWarnings("serial")
@Controller
@Scope("prototype")
public class GroupAction extends BaseAction<Group> {

	private Group group;
	private List<Group> groups;
	private String[] privilegeIds;
	private List<Privilege> children = new ArrayList<Privilege>();
	private List<Privilege> privileges = new ArrayList<Privilege>();
	

	public String list(){
		try {
			String hql = "FROM Group WHERE 1=1";
			if (searchTearm1 != null && !"".equals(searchTearm1)) {
				hql += " AND name like '%" + searchTearm1.trim() + "%'";
			}
			pageBean = groupService.findByHql(hql, pageBean);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "list";
	}

	public String delete() {
		//action跳action对中文参数编码
		tranScoding();
		try {
			if(!group.getId().equals("1")){
				groupService.excuteSql("delete from db_gz_users where role_id = '"+group.getId()+"'");
			}
			group = groupService.findEntity(group.getId());
			String memo ="对角色表删除角色【"+group.getName()+"】角色id【"+group.getId()+"】";
			if (group != null) {
				groupService.delete(group);
			}
			insertLog(4, 3, memo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "toList";
	}

	public String toOper() {
		try {
			if (group != null) {
				group = groupService.findEntity(group.getId());
				action = "update";
			} else {
				action = "insert";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
		return "oper";
	}

	public String update() {
		//action跳action对中文参数编码
		tranScoding();
		try {
			Group temp = groupService.findEntity(group.getId());
			temp.setName(group.getName());
			temp.setRemark(group.getRemark());
			String memo ="对角色表修改角色【"+group.getName()+"】";
			groupService.update(temp);
			insertLog(4, 2, memo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "toList";
	}

	public String insert() {
		//action跳action对中文参数编码
		tranScoding();
		try {
			String memo ="对角色表插入角色【"+group.getName()+"】";
			groupService.save(group);
			insertLog(4, 1, memo);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "toList";
	}
	public String toAllot() {
		try {
			privileges = privilegeService.findByHql("FROM Privilege WHERE parent IS NULL ORDER BY order_num DESC");
			group = groupService.findEntity(group.getId());
			List<Privilege> rolePrivileges = group.getPrivileges();
			if (rolePrivileges != null) {
				privilegeIds = new String[rolePrivileges.size()];
				for (int i = 0; i < rolePrivileges.size(); i++) {
					privilegeIds[i] = rolePrivileges.get(i).getId();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "allot";
	}
//
	public String allot() {
		try {
			//action跳action对中文参数编码
			tranScoding();
			group = groupService.findEntity(group.getId());
			if (privilegeIds != null) {
				for (String id : privilegeIds) {
					Privilege bs = new Privilege();
					bs.setId(id);
					children.add(bs);
				}
			}
			String memo ="对角色表角色【"+group.getName()+"】重新分配权限";
			group.setPrivileges(children);
			groupService.update(group);
			insertLog(4, 20, memo);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "toList";
	}



	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}

	public List<Group> getGroups() {
		return groups;
	}

	public void setGroups(List<Group> groups) {
		this.groups = groups;
	}

	public String[] getPrivilegeIds() {
		return privilegeIds;
	}

	public void setPrivilegeIds(String[] privilegeIds) {
		this.privilegeIds = privilegeIds;
	}

	public List<Privilege> getChildren() {
		return children;
	}

	public void setChildren(List<Privilege> children) {
		this.children = children;
	}

	public List<Privilege> getPrivileges() {
		return privileges;
	}

	public void setPrivileges(List<Privilege> privileges) {
		this.privileges = privileges;
	}


}

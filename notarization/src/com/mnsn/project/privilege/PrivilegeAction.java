package com.mnsn.project.privilege;

import java.util.List;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import com.mnsn.common.BaseAction;

/**
 *   用户表
 * @author ck
 *  @Time 2015-12-28 下午 3:16:33
 */
@SuppressWarnings("serial")
@Controller
@Scope("prototype")
public class PrivilegeAction extends BaseAction<Privilege>{
	private Privilege privilege;
	private List<Privilege> privileges;
	private String parent_id;//给一个权限添加下级权限的时候的父类的权限的id，
							//即如果这个字段为空，表示添加的是一级的权限,不为空表示添加一个下级权限
	private String parent_name;
	
	//加载权限列表，加载的是所有的全辖
	public String list() {
		try {
			String hql = "FROM Privilege WHERE parent IS NULL ";
			if(privilege!=null){
				hql += " AND name like '%"+privilege.getName()+"%'"; 
			}
			hql += " order by order_num ";
			privileges = privilegeService.findByHql(hql);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "list";
	}
	
	//前往添加权限页面
	public String toInsert(){
		try{
			if(parent_id != null && !"".equals(parent_id)){//表示添加的是一级权限
				Privilege p = privilegeService.findEntity(parent_id);
				if(p != null){
					parent_name = p.getName();
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return "toInsert";
	}
	
	//添加权限
	public String insert(){
		try{
			if(parent_id == null || "".equals(parent_id)){
				privilege.setParent(null);
				privilegeService.save(privilege);
			}else{
				Privilege p = new Privilege();
				p.setId(parent_id);
				privilege.setParent(p);
				privilegeService.save(privilege);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return "toList";
	}
	
	//前往编辑页面
	public String toUpdate(){
		try{
			privilege = privilegeService.findEntity(privilege.getId());
			if(privilege.getParent() != null){
				parent_name = privilege.getParent().getName();
				parent_id = ""+privilege.getParent().getId();
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return "toUpdate";
	}
	
	
	//修改数据
	public String update(){
		try{
			if(parent_id == null || "".equals(parent_id)){
				privilege.setParent(null);
				privilegeService.update(privilege);
			}else{
				Privilege p = new Privilege();
				p.setId(parent_id);
				privilege.setParent(p);
				privilegeService.update(privilege);
				//日志
//				Log log = new Log();
//				log.setCreate_time(new Date());
//				String userip = ServletActionContext.getRequest().getRemoteAddr();
//				log.setIp(userip);
//				log.setUser_id((getCurrentUser() == null ? null : getCurrentUser().getId()));
//				log.setUser_name(getCurrentUser() == null ? null : getCurrentUser().getName());
//				log.setType(0);
//				log.setType_tiny(2);
//				log.setMemo("对于权限列表的修改");
//				logService.save(log);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return "toList";
	}
	
	public String delete(){
		try{
			if(privilege != null){
				privilege = privilegeService.findEntity(privilege.getId());
				privilegeService.delete(privilege);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return "toList";
	}		
	
	public Privilege getPrivilege() {
		return privilege;
	}
	public void setPrivilege(Privilege privilege) {
		this.privilege = privilege;
	}
	public List<Privilege> getPrivileges() {
		return privileges;
	}
	public void setPrivileges(List<Privilege> privileges) {
		this.privileges = privileges;
	}
	public String getParent_id() {
		return parent_id;
	}
	public void setParent_id(String parentId) {
		parent_id = parentId;
	}
	public String getParent_name() {
		return parent_name;
	}
	public void setParent_name(String parentName) {
		parent_name = parentName;
	}
	
}

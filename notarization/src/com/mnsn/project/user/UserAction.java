package com.mnsn.project.user;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.xwork.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.mnsn.common.BaseAction;
import com.mnsn.project.group.Group;
import com.mnsn.project.privilege.Privilege;
import com.mnsn.utils.MyUtils;

/**
 * 2015-10-29 11:02:11
 * @author 李志勇
 *
 */
@SuppressWarnings("serial")
@Controller
@Scope("prototype")
public class UserAction extends BaseAction<User>{
	private User user;
	private List<User> users;
	private Privilege privilege;
	private List<Privilege> privileges;
	private String loginname;
	private String password;
	private List<Group> groups;
	private Group group;
	//登录
	public String login() {
		try {
			if (StringUtils.isNotEmpty(loginname) && StringUtils.isNotEmpty(password)) {
				String hql = " FROM User u WHERE u.loginname = ? AND u.password = ? AND role_type = 2 ";
				users = userService.findByConditions(hql, loginname.trim(), password.trim());
				if (users != null && users.size() > 0) {
					user = users.get(0);
					putInSession("loginUser", user);
					if (user.getRole_id() != null) {
						List<String> sessionPriCodes = new ArrayList<String>();
						Map<String,Integer> sessionPriUrls = new HashMap<String,Integer>();
						
						
						for (Privilege p : user.getGroup().getPrivileges()) {
							sessionPriCodes.add(p.getCode());//用来在main.jsp页面上是否显示出餐单
							if(p.getUrl().trim().length()>5){
								sessionPriUrls.put(p.getUrl(), 2);//用来做权限拦截
							}
						}
						//放进session中
						putInSession("sessionPriCodes",sessionPriCodes);
						putInSession("sessionPriUrls",sessionPriUrls);
						
					} else {
						meg = "4";
						return "notpass";
					}
					String memo ="后台用户【"+user.getName()+"】";
					insertLog(1, 5, memo);
				} else {
					meg = "2";
					return "notpass";
				}
			} else {
				meg = "3";
				return "notpass";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
		return "pass";
	}
	//后台用户列表
	public String list () {

		
		try {
			String hql = " from User where role_type = 2 ";
			if(StringUtils.isNotEmpty(searchTearm1)){
				hql += " and name like '%"+searchTearm1.trim()+"%' ";
			}
			if(StringUtils.isNotEmpty(searchTearm2)){
				hql += " and loginname like '%"+searchTearm2.trim()+"%' ";
			}
			hql += " order by create_time desc ";
			pageBean = userService.findByHql(hql, pageBean);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "list";
	}
	//跳转到添加后台用户
	public String toInsert () {
		try {
			groups = groupService.findAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "toInsert";
	}
	//添加保存后台用户
	public String insert () {
		//action跳action对中文参数编码
		tranScoding();
		try {
			if(getCurrentUser().getId()!= null){
				user.setCreate_time(MyUtils.getCreate_time_Date());
				user.setRole_type(2);
				userService.save(user);
				String memo ="后台用户【"+user.getName()+"】";
				insertLog(1, 1, memo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "tolist";
	}
	
	//删除后台用户
	public String delete () {
		//action跳action对中文参数编码
		tranScoding();
		try {
			user=userService.findEntity(user.getId());
			String memo ="后台用户【"+user.getName()+"】数据id【"+user.getId()+"】";
			userService.delete(user);
			insertLog(1, 3, memo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "tolist";
	}
	//跳转到编辑页面
	public String toUpdate () {
		try {
			if(user != null){
				user = userService.findEntity(user.getId());
				groups = groupService.findAll();
			}
		} catch (Exception e) {
		}
		return "toUpdate";
	}
	//更新后台用户
	public String update () {
		//action跳action对中文参数编码
		tranScoding();
		try {
			if(user != null){
				String memo ="后台用户【"+user.getName()+"】";
				userService.update(user);
				insertLog(1, 2, memo);
			}
		} catch (Exception e) {
		}
		return "tolist";
	}
	//查看后台用户
	public String toDetails () {
		try {
			if(user != null){
				user = userService.findEntity(user.getId());
				if(user.getRole_id() != null && !(user.getRole_id().equals(""))){
					group = groupService.findEntity(user.getRole_id());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "details";
	}
	
	
	
	
	
	private User appUser;
	private List<User> appUsers;
	/**
	 * APP用户
	 * @return
	 */
	//前往APP用户列表
	public String listSM(){
		try{
			String hql = "From User where role_type = 1";
			if(StringUtils.isNotEmpty(searchTearm1)){
				hql += " and name like '%"+searchTearm1.trim()+"%' ";
			}
			if(StringUtils.isNotEmpty(searchTearm2)){
				hql += " and loginname like '%"+searchTearm2.trim()+"%' ";
			}
			hql += " order by id desc";
			pageBean = userService.findByHql(hql, pageBean);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return "listSM";
	}
	
	
	//跳转修改页面
	public String toUpdateSM(){
		try {
			if(appUser != null){
				appUser = userService.findEntity(appUser.getId());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "toUpdateSM";
	}

	//修改APP用户数据
	public String updateSM(){
		//action跳action对中文参数编码
		tranScoding();
		try{
			appUser.setRole_id(null);
			appUser.setRole_type(1);
			userService.update(appUser);
			String memo ="app用户【"+appUser.getName()+"】";
			insertLog(1, 2, memo);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return "toListSM";
	}
	
	//跳转详情页面
	public String toDetailsSM(){
		try {
			if(appUser != null){
				appUser = userService.findEntity(appUser.getId());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "toDetailsSM";
	}

	//删除APP用户数据
	public String deleteSM(){
		//action跳action对中文参数编码
		tranScoding();
		try{
			if(appUser != null){
				appUser = userService.findEntity(appUser.getId());
				userService.delete(appUser);
				String memo ="app用户【"+appUser.getName()+"】数据id【"+appUser.getId()+"】";
				insertLog(1, 3, memo);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return "toListSM";
	}
	
	
	//ajax登录名
	public void ajaxLoginName(){
		try{
			if(appUser != null){
				String hql = "FROM User WHERE loginname = '"+appUser.getLoginname()+"'";
				appUsers = userService.findByHql(hql);
				if(appUsers.size() > 0){
					writeToPage("0");
				}else{
					writeToPage("1");
				}
			}else{
				writeToPage("-1");
			}
		}catch (Exception e) {
			writeToPage("-1");
			e.printStackTrace();
		}
	}
	
	//检查旧密码
	public String checkpd(){
	        User user = getCurrentUser();
		try {	
				user = userService.findEntity(user.getId());			
		    }catch (Exception e) {
			    e.printStackTrace();
		}
 		if(fileName.equals(user.getPassword())){
 			meg="0";
 			writeToPage(meg);

		}else{
 			meg="1";
 			writeToPage(meg);
			
		}
 		return "updatepwd";
	}
	
	//修改密码
	public String updatepwd() {
			User user = getCurrentUser();
   			try {   	        
		    	user.setPassword(meg);
	            if(userService.update(user)){
	            	meg="0";
	                writeToPage(meg);
	            }else{
	            	meg="1";
	                writeToPage(meg);	            	
	            	
	            }
		    }catch (Exception e) {
			    e.printStackTrace();
		    }
   			
   			return "updatepwd"; 
	}
	//个人信息编辑
	public String toUpdateUC() {
		try {
			User usercenter = getCurrentUser();
			if(usercenter!=null){
				user = userService.findEntity(usercenter.getId());
				if(user.getRole_id() != null && !(user.getRole_id().equals(""))){
					group = groupService.findEntity(user.getRole_id());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "toUpdateUC";
	}
	//更新个人中心
	public String updateUC () {
		try {
			if(user != null){
				if(userService.update(user)){
					String memo ="当前用户【"+user.getName()+"】";
					insertLog(1, 2, memo);
					meg="0";
				}else{
					meg="1";
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "toUserCenter";
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public List<User> getUsers() {
		return users;
	}
	public void setUsers(List<User> users) {
		this.users = users;
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

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}



	public List<Group> getGroups() {
		return groups;
	}

	public void setGroups(List<Group> groups) {
		this.groups = groups;
	}

	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}
	public User getAppUser() {
		return appUser;
	}
	public void setAppUser(User appUser) {
		this.appUser = appUser;
	}
	public List<User> getAppUsers() {
		return appUsers;
	}
	public void setAppUsers(List<User> appUsers) {
		this.appUsers = appUsers;
	}

	


	
}

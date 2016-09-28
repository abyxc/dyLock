package com.mnsn.project.log;

import java.util.List;

import org.apache.commons.lang.xwork.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.mnsn.common.BaseAction;
import com.mnsn.project.user.User;

@SuppressWarnings("serial")
@Controller
@Scope("prototype")
public class LogAction extends BaseAction<Log>{
	private Log log;
	private List<Log> logs;
	private User user;
	private String logip;
	
	
	public String list(){
		try{
			String hql = "FROM Log WHERE 1=1 ";
			if(StringUtils.isNotEmpty(searchTearm1)){
				hql += " and ip like '%"+searchTearm1.trim()+"%' ";
			}
			if(StringUtils.isNotEmpty(searchTearm2)){
				hql += " and user_name like '%"+searchTearm2.trim()+"%' ";
			}
			hql += " order by create_time desc";
			pageBean = logService.findByHql(hql, pageBean);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return "list";
	}
	
	public String toDetails(){
		try{
			log = logService.findEntity(log.getId());
			user=userService.findEntity(log.getUser_id());
		}catch (Exception e) {
			e.printStackTrace();
		}
		return "toDetails";
	}
	
	
	
	
	
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Log getLog() {
		return log;
	}
	public void setLog(Log log) {
		this.log = log;
	}
	public List<Log> getLogs() {
		return logs;
	}
	public void setLogs(List<Log> logs) {
		this.logs = logs;
	}

	public String getLogip() {
		return logip;
	}

	public void setLogip(String logip) {
		this.logip = logip;
	}

	
}

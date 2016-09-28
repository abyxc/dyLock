package com.mnsn.project.messages;

import java.util.Date;
import java.util.List;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import com.mnsn.common.BaseAction;
import com.mnsn.utils.MyUtils;
/**
 * 
 * @author ck
 *  @Time 2015-12-28 下午 3:16:33
 */
@SuppressWarnings("serial")
@Controller
@Scope("prototype")
public class MessagesAction extends BaseAction<Messages>{
	private Messages messages;
	@SuppressWarnings("rawtypes")
	private List list;	
	private String[] str;
	public String list() {
		//int type=Integer.parseInt(ids);
		try {
			String hql = "FROM Messages WHERE state = 0 and info_type='2' ";
			if (MyUtils.notEmpty(searchTearm1)) {
				hql += " AND title like '%" + searchTearm1.trim() + "%' ";
			}
			hql+="order by create_time desc,id asc";
			pageBean = messagesService.findByHql(hql, pageBean);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "list";
	}

	public String detailmsg() {
		try {
				
			messages = messagesService.findEntity(ids);
/*        meg=msg.getUrl();     
        if(meg!=null&&!"".equals(meg)){
        	str=meg.split("//");
        	System.out.println(str+"--------str");
        }*/
				
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(meg!=null&"0".equals(meg)){
		   return "detailmsg";
		}else{
		   return "toupdate";
		}
	}
	
	public String delete() {
		try {
			//action跳action对中文参数编码
			tranScoding();
			groupService.excuteSql("delete from db_gz_type where id = '"+messages.getId()+"'");
			messages = messagesService.findEntity(messages.getId());
			String memo ="对【知识信息表】删除数据id【"+messages.getId()+"】";
			if (messages != null) {
				messagesService.delete(messages);
			}
			insertLog(7, 3, memo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "toList";
	}

	public String update() {
		try {
			//action跳action对中文参数编码
			tranScoding();
			if("1".equals(meg)){
				messages = messagesService.findEntity(ids);	
				messages.setState(1);
			}
			String memo ="对知识信息表更新";
			messagesService.update(messages);
			insertLog(7, 2, memo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "tolist";
	}

	public String insert() {
		try {
			//action跳action对中文参数编码
			tranScoding();
			if(messages.getId()!=null&!"".equals(messages.getId())){
				messages.setState(0);
				messages.setUpdate_time(MyUtils.getCreate_time_Date());
				messages.setUser_id(getCurrentUser().getId());
				messagesService.update(messages);
				writeToPage("0");
			}else{
				messages.setCreate_time(MyUtils.getCreate_time_Date());
				messages.setState(0);
				messages.setUser_id(getCurrentUser().getId());
				String memo ="对知识信息表添加";
				messagesService.save(messages);
				writeToPage("0");
				insertLog(7, 1, memo);
			}
			
		} catch (Exception e) {
			writeToPage("1");
			e.printStackTrace();
		}

		return "toList";
	}

	@SuppressWarnings("rawtypes")
	public List getList() {
		return list;
	}
	public void setList(@SuppressWarnings("rawtypes") List list) {
		this.list = list;
	}

	public String[] getStr() {
		return str;
	}

	public void setStr(String[] str) {
		this.str = str;
	}

	public Messages getMessages() {
		return messages;
	}

	public void setMessages(Messages messages) {
		this.messages = messages;
	}





	
}

package com.mnsn.project.information;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.mnsn.common.BaseAction;
import com.mnsn.project.type.Type;
import com.mnsn.project.uploadMain.UploadMain;
import com.mnsn.project.user.User;
/**
 * 已发送信息模块
 * @author admin
 *
 */
@SuppressWarnings("serial")
@Controller
@Scope("prototype")
public class InformationAction extends BaseAction<Information>{
	private Information information;
	@SuppressWarnings("unchecked")
	private List list;
	private UploadMain uploadMain;
	private User user;
	private Type type;
	//信息列表
	public String list(){
		try {
			String hql =" From Type where 1=1";
				list = informationService.findByHql(hql);
			String sql = "select gi.phone as giph,gi.send_time as gism,gi.type as gity," +
					"gt.gz_name as gtna,gi.state as gist,gi.ID as guid,gi.send_state from db_gz_information gi " +
					"LEFT JOIN db_gz_upload_main um ON gi.upload_main_id=um.ID " +
					"LEFT JOIN db_gz_type gt ON um.gz_type_id=gt.ID where 1=1 ";
			 	if(searchTearm1 != null && !"".equals(searchTearm1)){
			 		sql += " and gt.id = '"+searchTearm1+"'";
			 	}
			pageBean = informationService.findBySql(sql, "gi", pageBean);
//			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "list";
	}
	
	//详情
	public String toDetails(){
		try {
			if(information != null && !"".equals(information)){
				information = informationService.findEntity(information.getId());
				if(checkstr(information.getId())){
					if(!information.getAccept_user_id().equals("")){
						user = userService.findEntity(information.getAccept_user_id());
					}
					uploadMain = uploadMainService.findEntity(information.getUpload_main_id());
					type = typeService.findEntity(uploadMain.getGz_type_id());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "toDetails";
	}
	
	//删除
	public String delete(){
		try {
			information = informationService.findEntity(information.getId());
			String memo ="对短信列表删除短信【"+information.getId()+"】";
			if(information != null){
				informationService.delete(information);
			}
			insertLog(3, 3, memo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "toList";
	}
	
//	//发送信息
//	public String send(){
//		try {
//			String sql = "select gi.id,u.name,u.mobile_phone,t.gz_name,gi.state,gt.content,u.name,up.gz_class from db_gz_information" +
//			" gi left join db_gz_upload_main up on gi.upload_main_id = up.id" +
//			" left join db_gz_type t on t.id = up.gz_type_id left join" +
//			" db_gz_users u on u.id = gi.user_id left join db_gz_templet gt on gt.type_id=t.id" +
//			"  where u.role_type = 1 and up.gz_status = 2 and gt.state = 1 and gi.id='"+information.getId()+"'";
//			list = informationService.findBySql(sql);
//			for(int i=0;i<list.size();i++){
//				Object[] obj = (Object[])list.get(i);
//				String name = (String) obj[6];
//				String mobile = (String) obj[2];
//				String content = (String) obj[5];
//				String type = (String) obj[7];
//				String aa = content.replaceFirst("【姓名】",name);
//				content = aa.replaceAll("【申办类型】", type);
//				System.out.println(mobile);
//				System.out.println(content);
//				String memo ="发送短信短信";
//				//发送完成后将状态改为1
//				String ql = "update db_gz_information set state = '1' where id ='"+information.getId()+"'";
//				informationService.excuteSql(ql);
//				insertLog(3, 20, memo);
//			}
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return "toList";
//	}

	public Information getInformation() {
		return information;
	}

	public void setInformation(Information information) {
		this.information = information;
	}

	@SuppressWarnings("unchecked")
	public List getList() {
		return list;
	}

	@SuppressWarnings("unchecked")
	public void setList(List list) {
		this.list = list;
	}

	public UploadMain getUploadMain() {
		return uploadMain;
	}

	public void setUploadMain(UploadMain uploadMain) {
		this.uploadMain = uploadMain;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}
	
}

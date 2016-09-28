package com.mnsn.project.uploadMain;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.mnsn.common.BaseAction;
import com.mnsn.project.information.Information;
import com.mnsn.project.type.Type;
import com.mnsn.project.user.User;
import com.mnsn.utils.MyUtils;

/**
 * 2015-10-29 11:02:11
 * @author 李志勇
 *
 */
@SuppressWarnings("serial")
@Controller
@Scope("prototype")
public class UploadMainAction extends BaseAction<UploadMain>{
	private List list;
	private List listImg;
	private UploadMain uploadMain;
	private List<Type> typelist;
	private List<UploadMain> loadlist;
	private int count;
	private Information information;
	private String price_pages;
	private String price_languages;
	private String price_countrys;
	public String list () {
		try {
			String sql = " SELECT um.order_num,u.name, u.idcard_type, u.idcard_number, t.gz_name, um.gz_status,um.id as umId,u.id as uId,um.gz_receive_type " +
					" FROM db_gz_upload_main um  " +
					" LEFT JOIN db_gz_type t ON um.gz_type_id = t.id " +
					" LEFT JOIN db_gz_users u ON um.users_id = u.id where 1=1 " ;
			if(status != null && "2".equals(status)){
				sql += " and um.gz_status in (0,4,6)  ";//查看状态为拒绝和撤销，已完结的
			}
			if(MyUtils.notEmpty(searchTearm1)){
				sql += " and  um.order_num like '%"+searchTearm1.trim()+"%' ";//记录编码
			}
			if(MyUtils.notEmpty(searchTearm2)){//db_gz_upload_picture表时存用户上传图片的地方
				sql += " and u.name like '%"+searchTearm2.trim()+"%' ";
			}
			if(MyUtils.notEmpty(searchTearm3)){
				sql += " and  u.idcard_number like '%"+searchTearm3.trim()+"%' ";//where um.gz_status in (0,1,2,3 ) 
			}
				
			
			sql +=	" AND u.role_type = 1 order by um.create_time desc";//ORDER BY um.create_time DESC
			pageBean = uploadMainService.findBySql(sql, "um", pageBean);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(status != null && "2".equals(status)){
			return "historyList";
		}else {
			return "toList";
		}
	}
	//去审核页面
	public String checks () {
		try {
			if(uploadMain != null){
				String sql = " SELECT u.name, u.idcard_type, u.idcard_number, u.mobile_phone, t.gz_name ,gm.id, gm.gz_count,gm.order_num,u.idcard_number,gm.gz_class,gm.language_id,gm.gz_use,gm.country_id,gm.gz_receive_type " +
				" FROM db_gz_upload_main gm " +
				" LEFT JOIN db_gz_users u ON gm.users_id = u.id " +
				" LEFT JOIN db_gz_type t ON t.id = gm.gz_type_id " +
				" WHERE gm.id = '"+uploadMain.getId()+"' ";
				list = uploadMainService.findBySql(sql);
				sql = " select pn.pic_name,up.picture_url,up.picture_url_thumbnail,up.id,pn.remark " +
						" from db_gz_upload_picture up " +
						" LEFT JOIN db_gz_picture_name pn on pn.id=up.picture_name_id  " +
						" where up.upload_main_id = '"+uploadMain.getId()+"'order by up.id desc ";
				listImg = uploadMainService.findBySql(sql);
				uploadMain = uploadMainService.findEntity(uploadMain.getId());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(action != null && MyUtils.notEmpty(action.trim()) && "1".equals(action.trim()) || "2".equals(action)){
			return "toDetails";//当action=1时去相亲页面
		}else{
			return "checks";
//			return "toList";
		}
	}
	public String delete () {
		//action跳action对中文参数编码
		tranScoding();
		try {
			if(uploadMain != null){
				uploadMainService.excuteSql(" delete from db_gz_upload_main where id = '"+uploadMain.getId()+"' ");
				String memo ="对申办记录删除数据id为【"+uploadMain.getId()+"】";
				insertLog(8, 3, memo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "list";
	}
	//保存审核内容
	public void toChecks () {
		try {
			if(getCurrentUser()!= null && MyUtils.notEmpty(ids) && MyUtils.notEmpty(meg)){
				uploadMain = uploadMainService.findEntity(ids);
				if("2".equals(uploadMain.getGz_status()) || "3".equals(uploadMain.getGz_status()) ){
					writeToPage("0");
					return;
				}
				if(uploadMain != null){
					if(meg.equals("2")){//通过
						if(MyUtils.notEmpty(price_countrys)){
							uploadMain.setPrice_country( new BigDecimal(price_countrys));
						}
						if(MyUtils.notEmpty(price_languages)){
							uploadMain.setPrice_language( new BigDecimal(price_languages));
						}
						if(MyUtils.notEmpty(price_pages )){
							uploadMain.setPrice_page( new BigDecimal(price_pages));
						}
					}else{//不通过
						uploadMain.setRemark(status);
					}
					uploadMain.setGz_status(Integer.parseInt(meg));
					uploadMainService.update(uploadMain);
					
					//往信息表中插入数据
					User user=userService.findEntity(uploadMain.getUsers_id());
					information.setAccept_user_id(uploadMain.getUsers_id());
					information.setUpload_main_id(uploadMain.getId());
					information.setSend_time(MyUtils.getCreate_time_Date());
					information.setPhone(user.getMobile_phone());
					informationService.save(information);
					
					//审核成功后发送短信
					String sql = " select gt.gz_name from db_gz_type gt left join db_gz_upload_main gu on gu.gz_type_id = gt.id where gu.id ='"+uploadMain.getId()+"'"; 
					list = informationService.findBySql(sql);
					for(int i = 0;i<list.size();i++){
						Object obj = (Object) list.get(i);
						String gz_name = (String) obj; 
						String name = user.getName();
						
						String content = "";
						String type = uploadMain.getGz_class();
						String str =  uploadMain.getGz_status().toString();
						String end="";
						if("2".equals(str)){
							if("国内".equals(type)){
								information.setType(2);
							}else{
								information.setType(3);
							}
							content = "审核通过：【"+name+"】您申办的公证已经审核通过，请尽快进入APP申办管理内进行缴费完成申办。如有疑问请拨打0571-85105000（国内业务）或者0571-85065391(涉外业务)进行咨询。";
							information.setContent(content);
//							end=MyUtils.sendSms(information.getPhone(), content);
//							String[] ends=end.split(":");
//							information.setState(Integer.parseInt(ends[0]));
//							if(ends[0].equals("0")){
//								information.setSend_state(ends[1]);
//							}
						}else{
							if("国内".equals(type)){
								content = " 申办失败：（国内业务）【"+name+"】您申办的【"+gz_name+"】公证由于缺少【"+uploadMain.getRemark()+"】等相关材料，无法进行申办，请尽快补齐材料完成申办。如需帮助请拨打国内业务咨询电话0571-85105000  ";
								information.setType(4);
							}else{
								content = "申办失败：（涉外业务）【"+name+"】您申办的【"+gz_name+"】公证由于缺少【"+uploadMain.getRemark()+"】等相关材料，无法进行申办，请尽快补齐材料完成申办。如需帮助请拨打国内业务咨询电话0571-85105000";
								information.setType(5);
							}
							information.setContent(content);
//							end=MyUtils.sendSms(information.getPhone(), content);
//							String[] ends=end.split(":");
//							information.setState(Integer.parseInt(ends[0]));
//							if(ends[0].equals("0")){
//								information.setSend_state(ends[1]);
//							}
						}
//						informationService.update(information);
					}
				}
				String memo ="对申办记录审核";
				insertLog(8, 6, memo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
//		return "list";
	}
	//申办记录
	public String historyList () {//页面重用
		try {
			searchTearm4 = getUUID();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "toHistoryList";
	}
	
	//把状态改成已完结
	public void ends () {
		try {
			if(getCurrentUser()!= null && MyUtils.notEmpty(ids)){
				String end = "";
				if(meg != null && "0".equals(meg)){
					int num = uploadMainService.excuteSql(" UPDATE db_gz_upload_main set gz_status = '0' where id = '"+ids+"' and gz_status = 1 ");
					if(num == 0){//拒绝
						writeToPage("1");
					}else{
						
						uploadMain=uploadMainService.findEntity(ids);
						User user=userService.findEntity(uploadMain.getUsers_id());
						information=new Information();
						information.setUpload_main_id(uploadMain.getId());
						information.setContent("拒绝申办：【"+user.getName()+"】由于您申办的公证不在我处受理范围内，故无法办理公证。");
						information.setAccept_user_id(uploadMain.getUsers_id());
						information.setPhone(user.getMobile_phone());
						information.setSend_time(MyUtils.getCreate_time_Date());
						information.setType(6);
						informationService.save(information);
						
						//电泳短信接口 发送短信
//						end=MyUtils.sendSms(information.getPhone(), information.getContent());
//						String[] ends=end.split(":");
//						information.setState(Integer.parseInt(ends[0]));
//						if(ends[0].equals("0")){
//							information.setSend_state(ends[1]);
//						}
						writeToPage(ids+"reject");
					}
//					informationService.update(information);
				}else{
					int num = uploadMainService.excuteSql(" UPDATE db_gz_upload_main set gz_status = '6' where id = '"+ids+"' and gz_status = 5 ");
					if(num == 0){//
						writeToPage("1");
					}else{
						writeToPage(ids);
						uploadMain=uploadMainService.findEntity(ids);
						User user=userService.findEntity(uploadMain.getUsers_id());
						information=new Information();
						information.setUpload_main_id(uploadMain.getId());
						information.setContent("缴费成功：【"+user.getName()+"】您申办的公证缴费成功！");
						information.setAccept_user_id(uploadMain.getUsers_id());
						information.setPhone(user.getMobile_phone());
						information.setSend_time(MyUtils.getCreate_time_Date());
//						information.setSms_id("123456");
						information.setState(1);
//						information.setState_memo("123456");
						information.setType(7);
						informationService.save(information);
					}
				}
				String memo ="对 申办记录完结";
				insertLog(8, 6, memo);
			}
		} catch (Exception e) {
			writeToPage("0");
			e.printStackTrace();
		}
	}
	
	
	
	/**
	 * 以下都是统计的方法
	 * @return
	 */
	//统计列表
	public String countList(){
		try {
			String sql = "select dm.id,du.name,du.idcard_type,du.idcard_number,dt.gz_name,dm.gz_status " +
			"from db_gz_upload_main dm left join db_gz_users du on dm.users_id = du.id " +
			" left join db_gz_type dt on dm.gz_type_id = dt.id where du.role_type = 1";
			if(checkstr(searchTearm1)){
				sql += " and dm.gz_class like '%"+searchTearm1+"%'";
			}else{
				sql += " and dm.gz_class like '%涉外%'";
				searchTearm1="涉外(含港澳台)";
			}
			if(checkstr(searchTearm2)){
				sql += " and dm.gz_status like '%"+searchTearm2+"%'";
			}else{
				sql += " and dm.gz_status='6'";
				searchTearm2="6";
			}
			if(checkstr(searchTearm3)){
				sql += " and dm.create_time >= '"+searchTearm3+"'";
			}
			if(checkstr(searchTearm4)){
				sql += " and dm.create_time<='"+searchTearm4+"'";
			}
			pageBean = uploadMainService.findBySql(sql, "dm", pageBean);
			searchTearm5 = String.valueOf(pageBean.getRecordCnt());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "countList";
	}
	
	
	public List getList() {
		return list;
	}
	public void setList(List list) {
		this.list = list;
	}

	public UploadMain getUploadMain() {
		return uploadMain;
	}

	public void setUploadMain(UploadMain uploadMain) {
		this.uploadMain = uploadMain;
	}
	public List getListImg() {
		return listImg;
	}
	public void setListImg(List listImg) {
		this.listImg = listImg;
	}
	public List<Type> getTypelist() {
		return typelist;
	}
	public void setTypelist(List<Type> typelist) {
		this.typelist = typelist;
	}
	public List<UploadMain> getLoadlist() {
		return loadlist;
	}
	public void setLoadlist(List<UploadMain> loadlist) {
		this.loadlist = loadlist;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public Information getInformation() {
		return information;
	}
	public void setInformation(Information information) {
		this.information = information;
	}
	public String getPrice_pages() {
		return price_pages;
	}
	public void setPrice_pages(String pricePages) {
		price_pages = pricePages;
	}
	public String getPrice_languages() {
		return price_languages;
	}
	public void setPrice_languages(String priceLanguages) {
		price_languages = priceLanguages;
	}
	public String getPrice_countrys() {
		return price_countrys;
	}
	public void setPrice_countrys(String priceCountrys) {
		price_countrys = priceCountrys;
	}
	
}

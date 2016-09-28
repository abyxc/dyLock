package com.mnsn.project.wechat.wcbid;

import java.util.List;

import org.apache.commons.lang.xwork.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.mnsn.common.BaseAction;
import com.mnsn.project.pictureName.PictureName;
import com.mnsn.project.type.Type;
import com.mnsn.project.uploadMain.UploadMain;
import com.mnsn.project.uploadPicture.UploadPicture;
import com.mnsn.project.user.User;

/**
 * 
 * @author mys
 *
 */
@SuppressWarnings("serial")
@Controller
@Scope("prototype")
public class WcBidAction extends BaseAction<UploadMain>{
	private UploadMain uploadMain;
	private List<UploadMain> uploadMains;
	private Type type;
	private List list;
	private Object[] objArr;
	private List<User> users;
	private String user_name;
	private List<PictureName> picList;//只在详情页面中用的集合，作用是显示多组照片
	/*
	 * 申办列表
	 */
	public String list(){
		try {
			String user_id = (String) getSession().getAttribute("user_id");
			
			String sql = "select um.id,t.gz_name,um.create_time,um.gz_count, " +
					"cast(isnull(um.price_language, 0)as DECIMAL(10,2) )+ cast(isnull(um.price_country, 0)as DECIMAL(10,2)) " +
					"+ cast(isnull(um.price_page, 0) as DECIMAL(10,2))," +
					"um.gz_status " +
					"from db_gz_upload_main um " +
					"left join db_gz_type t on um.gz_type_id = t.id " +
					"where um.users_id = '"+user_id+"' order by um.create_time desc ";
			User u = userService.findEntity(user_id);
			if(u != null){
				user_name = u.getName();
			}
			list = uploadMainService.findBySql(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "list";
	}
	
	/*
	 * 申办记录详情
	 */
	@SuppressWarnings("unchecked")
	public String toDetails(){
		try {
			if(uploadMain != null){
				//下边这一块，是查询除了照片以外别的数据-----开始-----
				String sql = "select u.name," +
						"u.idcard_type," +
						"u.idcard_number," +
						"u.mobile_phone," +
						"t.gz_name," +
						"um.gz_receive_type," +
						"um.gz_count " +
						"from db_gz_upload_main um " +
						"left join db_gz_users u on um.users_id = u.id " +
						"left join db_gz_type t on um.gz_type_id = t.id " +
						"where um.id = '"+uploadMain.getId()+"'";
				System.out.println(uploadMain.getId());
				List l = uploadMainService.findBySql(sql);
				if(l.size() > 0){
					objArr = (Object[]) l.get(0);
				}
				//-------结束-------
				
				//查询照片数组,先查询需要显示的picture_name，即照片类型
				String hql = "FROM PictureName WHERE id in (" +
						"select DISTINCT(picture_name_id) from UploadPicture " +
						"where upload_main_id = '"+uploadMain.getId()+"'" +
						")";
				picList = pictureNameService.findByHql(hql);
				for(PictureName pn:picList){
					hql = "FROM UploadPicture WHERE picture_name_id = '"+pn.getId()
						+"' AND upload_main_id = '"+uploadMain.getId()+"'";
					List<UploadPicture> uploadPictures = uploadPictureService.findByHql(hql);
					pn.setUploadPictures(uploadPictures);
				}
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "toDetails";
	}

	
	/*
	 * 将我的申办中的一条数据进行撤回
	 * @return
	 */
	private String upload_main_id;
	public void putBack(){
		try{
			if(StringUtils.isNotEmpty(upload_main_id)){
				uploadMain = uploadMainService.findEntity(upload_main_id);
				uploadMain.setGz_status(4);
//				uploadMainService.update(uploadMain);
				printStr("1");//成功，并进行刷新
			}
		}catch (Exception e) {
			printStr("2");//失败，并进行提示
			e.printStackTrace();
		}
	}
	
	/*
	 * 将我的申办中的一条数据进行付款
	 * @return
	 */
	public String toPayMoney(){
		try{
			if(uploadMain != null){
				String sql = "select " +
					"cast(isnull(um.price_language, 0)as DECIMAL(10,2) )+ " +
					"cast(isnull(um.price_country, 0)as DECIMAL(10,2)) + cast(isnull(um.price_page, 0) as DECIMAL(10,2))," +
					"t.gz_name " +
					"from db_gz_upload_main um " +
					"left join db_gz_type t on um.gz_type_id = t.id " +
					"where um.id = '"+uploadMain.getId()+"'";
				List l = uploadMainService.findBySql(sql);
				if(l.size() > 0){
					objArr = (Object[]) l.get(0);
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return "toPayMoney";
	}
	
	
	
	
	
	public UploadMain getUploadMain() {
		return uploadMain;
	}

	public void setUploadMain(UploadMain uploadMain) {
		this.uploadMain = uploadMain;
	}

	public List<UploadMain> getUploadMains() {
		return uploadMains;
	}

	public void setUploadMains(List<UploadMain> uploadMains) {
		this.uploadMains = uploadMains;
	}

	public Object[] getObjArr() {
		return objArr;
	}

	public void setObjArr(Object[] objArr) {
		this.objArr = objArr;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public List getList() {
		return list;
	}

	public void setList(List list) {
		this.list = list;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String userName) {
		user_name = userName;
	}

	public List<PictureName> getPicList() {
		return picList;
	}

	public void setPicList(List<PictureName> picList) {
		this.picList = picList;
	}

	public String getUpload_main_id() {
		return upload_main_id;
	}

	public void setUpload_main_id(String uploadMainId) {
		upload_main_id = uploadMainId;
	}
}

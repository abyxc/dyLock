package com.mnsn.common;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Date;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.xwork.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.mnsn.project.group.GroupServiceIntf;
import com.mnsn.project.information.InformationServiceIntf;
import com.mnsn.project.log.Log;
import com.mnsn.project.log.LogServiceIntf;
import com.mnsn.project.messages.MessagesServiceIntf;
import com.mnsn.project.pictureName.PictureNameServiceIntf;
import com.mnsn.project.pictureRelevance.PictureRelevanceServiceIntf;
import com.mnsn.project.privilege.PrivilegeServiceIntf;
import com.mnsn.project.templet.TempletServiceIntf;
import com.mnsn.project.type.TypeServiceIntf;
import com.mnsn.project.uploadMain.UploadMainServiceIntf;
import com.mnsn.project.uploadPicture.UploadPictureServiceIntf;
import com.mnsn.project.user.User;
import com.mnsn.project.user.UserServiceIntf;
import com.opensymphony.xwork2.ActionSupport;
/**
 * 
 * @author ll
 * <h3>优雅的代码需要愉快的心情</h3>
 * 2015-1-17下午01:26:25
 * http://www.cnblogs.com/mvilplss/
 * @param <T>
 */
@SuppressWarnings("serial")
@Controller
@Scope("prototype")
public class BaseAction<T> extends ActionSupport {

	@Resource
	protected LogServiceIntf logService;//日志类注入
	@Resource
	protected UserServiceIntf userService;//路线类注入
	@Resource
	protected GroupServiceIntf groupService;//角色类注入
	@Resource
	protected PrivilegeServiceIntf privilegeService;//权限类注入
	@Resource
	protected UploadMainServiceIntf uploadMainService;//公正事务主表
	@Resource
	protected TypeServiceIntf typeService;//公众事项类型
	@Resource
	protected PictureRelevanceServiceIntf pictureRelevanceService;//事项类型和证件类型并联
	@Resource
	protected PictureNameServiceIntf pictureNameService; //证件类型
	@Resource
	protected MessagesServiceIntf messagesService; //信息知识
	@Resource
	protected TempletServiceIntf templetService;//模板类型
	@Resource
	protected InformationServiceIntf informationService;//模板类型
	@Resource
	protected UploadPictureServiceIntf uploadPictureService;//上传照片服务类
	
	



	// 公共变量
	protected String status;
	protected String action;
	protected String ids;
	protected String meg;
	protected PageBean<T> pageBean = new PageBean<T>(1,12);
	protected String fileName;
	protected String filePath;
	protected File upload;
	protected String uploadFileName;
	protected String uploadContentType;
	protected String contentDisposition;
	protected String searchTearm1;
	protected String searchTearm2;
	protected String searchTearm3;
	protected String searchTearm4;
	protected String searchTearm5;
	protected String searchTearm6;
	protected String searchTearm7;
	protected String searchTearmx=getUUID();	
	//上传的图片路径，只能上传.jpg格式
//	protected String pathImgs = "\\\\vmware-host\\Shared Folders\\D\\GZCService_share\\detail_pic";
	protected String pathImgs =  ServletActionContext.getServletContext().getRealPath("")+"/detail_pic";
	//jsp页面读取的绝对位置
	protected String pathImgRead = "http://60.190.236.51:9015/";
	

	// 公共方法
	
	//Ajax提交方法
	public void printStr(String str) {
		try {
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println(str);
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//做操作日志，别的action都调用这里
	public void insertLog(int Type,int Type_tiny,String Memo){
		try{
			Log log = new Log();
			log.setType(Type);
			log.setType_tiny(Type_tiny);
			log.setMemo(Memo);
			String userip = ServletActionContext.getRequest().getRemoteAddr();
			log.setIp(userip);
			log.setUser_id((getCurrentUser() == null ? null : getCurrentUser().getId()));
			log.setUser_name(getCurrentUser() == null ? null : getCurrentUser().getName());
			log.setCreate_time(new Date());
			
			logService.save(log);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	//判断字符串不为空并且不为null
	public boolean checkstr(String str){
		if(str!=null&&!"".equals(str)){
			return true;
		}else{
			return false;
		}
	}
	/**
	 * 将内容content写到前台页面
	 */
	protected void writeToPage(String content) {
		try {
			ServletActionContext.getResponse().setCharacterEncoding("utf-8");
			PrintWriter printWriter= ServletActionContext.getResponse().getWriter();
			printWriter.write(content);
			printWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 将value存入Session中
	 */
	protected void putInSession(String key, Object value) {
		ServletActionContext.getRequest().getSession().setAttribute(key, value);
	}

	/**
	 * 将value存入application中
	 */
	protected void putInApplication(String key, Object value) {
		ServletActionContext.getPageContext().setAttribute(key, value);
	}

	/**
	 * 将value存入request中
	 */
	protected void putInRequest(String key, Object value) {
		ServletActionContext.getRequest().setAttribute(key, value);
	}

	/**
	 * 获取httpSession
	 */
	protected HttpSession getSession() {
		return ServletActionContext.getRequest().getSession();
	}

	/**
	 * 获取httpSession
	 */
	protected User getCurrentUser() {
		return (User) ServletActionContext.getRequest().getSession()
				.getAttribute("loginUser");
	}

	// =======================================get/set==========================================================
	public String getAction() {
		return action;
	}
	
	public void setAction(String action) {
		this.action = action;
	}

	public PageBean<T> getPageBean() {
		return pageBean;
	}

	public void setPageBean(PageBean<T> pageBean) {
		this.pageBean = pageBean;
	}
	public String getIds() {
		return ids;
	}
	public void setIds(String ids) {
		this.ids = ids;
	}
	public String getMeg() {
		return meg;
	}
	public void setMeg(String meg) {
		this.meg = meg;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	public File getUpload() {
		return upload;
	}
	public void setUpload(File upload) {
		this.upload = upload;
	}
	public String getUploadFileName() {
		return uploadFileName;
	}
	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}
	public String getUploadContentType() {
		return uploadContentType;
	}
	public void setUploadContentType(String uploadContentType) {
		this.uploadContentType = uploadContentType;
	}
	public String getContentDisposition() {
		return contentDisposition;
	}
	public void setContentDisposition(String contentDisposition) {
		this.contentDisposition = contentDisposition;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getSearchTearm1() {
		return searchTearm1;
	}
	public void setSearchTearm1(String searchTearm1) {
		try {
			this.searchTearm1 = URLDecoder.decode(searchTearm1,"UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
	public String getSearchTearm2() {
		return searchTearm2;
	}
	public void setSearchTearm2(String searchTearm2) {
		try {
			this.searchTearm2  = URLDecoder.decode(searchTearm2,"UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
	public String getSearchTearm3() {
		return searchTearm3;
	}
	public void setSearchTearm3(String searchTearm3) {
		try {
			this.searchTearm3 = URLDecoder.decode(searchTearm3,"UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};
	}
	public String getSearchTearm4() {
		return searchTearm4;
	}
	public void setSearchTearm4(String searchTearm4) {
		try {
			this.searchTearm4 = URLDecoder.decode(searchTearm4,"UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public String getSearchTearm5() {
		return searchTearm5;
	}
	public void setSearchTearm5(String searchTearm5) {
		try {
			this.searchTearm5 = URLDecoder.decode(searchTearm5,"UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public String getSearchTearm6() {
		return searchTearm6;
	}
	public void setSearchTearm6(String searchTearm6) {
		try {
			this.searchTearm6 = URLDecoder.decode(searchTearm6,"UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public String getSearchTearm7() {
		return searchTearm7;
	}
	public void setSearchTearm7(String searchTearm7) {
		try {
			this.searchTearm7 = URLDecoder.decode(searchTearm7,"UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//搜索条件转码，用于action跳action
	public void tranScoding(){
		try {
			if(StringUtils.isNotEmpty(searchTearm1)){
				searchTearm1=URLEncoder.encode(searchTearm1, "UTF-8");
				searchTearm1=searchTearm1.replaceAll("%", "%25");
			}
			if(StringUtils.isNotEmpty(searchTearm2)){
				searchTearm2=URLEncoder.encode(searchTearm2, "UTF-8");
				searchTearm2=searchTearm2.replaceAll("%", "%25");
			}	
			if(StringUtils.isNotEmpty(searchTearm3)){
				searchTearm3=URLEncoder.encode(searchTearm3, "UTF-8");
				searchTearm3=searchTearm3.replaceAll("%", "%25");
			}	
			if(StringUtils.isNotEmpty(searchTearm4)){
				searchTearm4=URLEncoder.encode(searchTearm4, "UTF-8");
				searchTearm4=searchTearm4.replaceAll("%", "%25");
			}	
			if(StringUtils.isNotEmpty(searchTearm5)){
				searchTearm5=URLEncoder.encode(searchTearm5, "UTF-8");
				searchTearm5=searchTearm5.replaceAll("%", "%25");
			}	
			if(StringUtils.isNotEmpty(searchTearm6)){
				searchTearm6=URLEncoder.encode(searchTearm6, "UTF-8");
				searchTearm6=searchTearm6.replaceAll("%", "%25");
			}	
			if(StringUtils.isNotEmpty(searchTearm7)){
				searchTearm7=URLEncoder.encode(searchTearm7, "UTF-8");
				searchTearm7=searchTearm7.replaceAll("%", "%25");
			}	
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public String getUUID(){ 
        String s = UUID.randomUUID().toString(); 
        return s.substring(0,8)+s.substring(9,13)+s.substring(14,18)+s.substring(19,23)+s.substring(24); 
    } 
	public String getPathImgs() {
		return pathImgs;
	}

	public void setPathImgs(String pathImgs) {
		this.pathImgs = pathImgs;
	}

	public String getPathImgRead() {
		return pathImgRead;
	}

	public void setPathImgRead(String pathImgRead) {
		this.pathImgRead = pathImgRead;
	}

	public String getSearchTearmx() {
		return searchTearmx;
	}

	public void setSearchTearmx(String searchTearmx) {
		this.searchTearmx = searchTearmx;
	}

	
}

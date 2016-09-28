package com.mnsn.project.wechat.wcuploadmain;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.mnsn.common.BaseAction;
import com.mnsn.project.type.Type;
import com.mnsn.project.uploadMain.UploadMain;
import com.mnsn.project.uploadPicture.UploadPicture;
import com.mnsn.project.user.User;
import com.mnsn.utils.MyUtils;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

/**
 * 
 * @author crj
 *
 */
@SuppressWarnings("serial")
@Controller
@Scope("prototype")
public class WcUploadMainAction extends BaseAction<UploadMain>{
	private UploadMain uploadMain;
	private List<UploadMain> uploadMains;
	private File file;
	private File[] uploads;
	private String uploadsFileName;
	private Integer gz_type;
	private Type type;
	private List<Type> types;
	private User user;
	private List list;
	private List listId;
	private UploadPicture uploadPicture;
	private String upload_mainId;
	
	public String toRegister(){
		try {
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "register";
	}
	
	public String toCheck(){
		try {
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "check";
	}
	
	public String toCheckType(){
		try {
			String hql="from Type Where type="+gz_type;
			types=typeService.findByHql(hql);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return "checkType";
	}
	
	public void  uploadImg (){
		
//		http://60.190.236.51:9015/v1.0/UpGzPic
			try {
				System.out.println("开始");
				upload_mainId = "";
				if(uploads != null || status != null ){
//					Thread.sleep(17000);
					String newFileMonth = MyUtils.getDataYmdhms2();
					String newFileMonths = newFileMonth.substring(0,6);
					newFileMonths = pathImgs+"/"+newFileMonths;//创建月
					String newFileDays = newFileMonth.substring(6,8);
					newFileDays = newFileMonths +"/"+newFileDays;//创建天
					file =new File(newFileDays);
					if  (!file .exists()  && !file .isDirectory()) {     
						    file .mkdirs();    
						}
					String[] uploadFileNames = uploadsFileName.split(",");//遍历文件名，（文件名不能含有逗号）
					for(int i=0;i<uploads.length;i++){
						uploadFileName = uploadFileNames[i].substring(uploadFileNames[i].indexOf("."), uploadFileNames[i].length());
						String strName = getUUID()+uploadFileName;
						File uploadFile=new File(newFileDays,strName);//strName为文件名uploadFile为文件全路径
						upload = uploads[i];//上传图片
						FileUtils.copyFile(upload, uploadFile);
						
						String  url_thumbnail= newFileDays+"/url_thumbnail"+strName;//最小缩略图
						saveMinPhoto(newFileDays+"/"+strName,url_thumbnail,339, 0.9d);
						String url_thummedu = newFileDays+"/url_thummedu"+strName;
						saveMinPhoto(newFileDays+"/"+strName,url_thummedu,639, 0.9d);//中等缩略图
						String  files_Name = newFileDays.substring(newFileDays.indexOf("detail_pic"), newFileDays.length()); 
						uploadPicture = new UploadPicture();
//						uploadPicture.setUpload_main_id(ids);
						uploadPicture.setPicture_name_id(meg);
						uploadPicture.setPicture_url(files_Name+strName);
						uploadPicture.setPicture_url_thumbnail(files_Name+"/url_thumbnail"+strName);
						uploadPicture.setPicture_url_thummedu(files_Name+"/url_thummedu"+strName);
						uploadPictureService.save(uploadPicture);
						upload_mainId += uploadPicture.getId()+",";
					}
//					upload_mainId = upload_mainId.substring(0, upload_mainId.length()-2);
					System.out.println("................id........."+upload_mainId);
					writeToPage(upload_mainId);
				}
				System.out.println("结束");
			} catch (Exception e) {
				e.printStackTrace();
			}
	}
	
	/**
	 * 等比例压缩算法： 
	 * 算法思想：根据压缩基数和压缩比来压缩原图，生产一张图片效果最接近原图的缩略图
	 * @param srcURL 原图地址
	 * @param deskURL 缩略图地址
	 * @param comBase 压缩基数
	 * @param scale 压缩限制(宽/高)比例  一般用1：
	 * 当scale>=1,缩略图height=comBase,width按原图宽高比例;若scale<1,缩略图width=comBase,height按原图宽高比例
	 * @throws Exception
	 * @author lzy
	 * @createTime 2016年1月28日13:54:22
	 */
	public static void saveMinPhoto(String srcURL, String deskURL, double comBase,
			double scale) {
		try {
			File srcFile = new java.io.File(srcURL);
			Image src = ImageIO.read(srcFile);
			int srcHeight = src.getHeight(null);
			int srcWidth = src.getWidth(null);
			int deskHeight = 0;// 缩略图高
			int deskWidth = 0;// 缩略图宽
			double srcScale = (double) srcHeight / srcWidth;
			/**缩略图宽高算法*/
			if ((double) srcHeight > comBase || (double) srcWidth > comBase) {
				if (srcScale >= scale || 1 / srcScale > scale) {
					if (srcScale >= scale) {
						deskHeight = (int) comBase;
						deskWidth = srcWidth * deskHeight / srcHeight;
					} else {
						deskWidth = (int) comBase;
						deskHeight = srcHeight * deskWidth / srcWidth;
					}
				} else {
					if ((double) srcHeight > comBase) {
						deskHeight = (int) comBase;
						deskWidth = srcWidth * deskHeight / srcHeight;
					} else {
						deskWidth = (int) comBase;
						deskHeight = srcHeight * deskWidth / srcWidth;
					}
				}
			} else {
				deskHeight = srcHeight;
				deskWidth = srcWidth;
			}
			BufferedImage tag = new BufferedImage(deskWidth, deskHeight, BufferedImage.TYPE_3BYTE_BGR);
			tag.getGraphics().drawImage(src, 0, 0, deskWidth, deskHeight, null); //绘制缩小后的图
			FileOutputStream deskImage = new FileOutputStream(deskURL); //输出到文件流
			JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(deskImage);
			encoder.encode(tag); //近JPEG编码
			deskImage.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String detail (){
		try {
			ids = "402881ea5234930c01523494b5a10963";//用户id
			status = "";
			String sql = "select pn.pic_name,pn.id,pn.remark,pr.id,pn.mast " +//pn.mast值：是否必须拍照0不必须  1必须
			"from db_gz_picture_relevance pr " +
			"left join db_gz_picture_name pn on pn.id = pr.picture_name_id " +
			"where pr.type_id='"+uploadMain.getGz_type_id()+"' order by pr.id desc ";
			list = pictureRelevanceService.findBySql(sql);
			listId = pictureRelevanceService.findBySql("select picture_name_id from db_gz_picture_relevance where type_id = '"+uploadMain.getGz_type_id()+"' order by id desc ");
			for(int i=0;i<listId.size();i++){
				status += listId.get(i)+"||";
			}
			if(status.length()>0){
				status = status.substring(0, status.length()-2);
			}
			System.out.println(status);
			type = typeService.findEntity(uploadMain.getGz_type_id());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "toDetail";
	}
	
	/**
	 * 
	 * @return
	 */
	public String toFillRecord(){
		try {
			type=typeService.findEntity(uploadMain.getGz_type_id());
//			user=getCurrentUser();
//			if(user!=null){
				user=userService.findEntity("402881ea5234930c01523494b5a10963");
//			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "fillRecord"; 
	}
	
	
	public String toFillRecord2(){
		try {
			uploadMain.setGz_status(1);
			uploadMain.setCreate_time(MyUtils.getCreate_time_Date());
			if(uploadMain.getGz_receive_type().equals("1")){
				uploadMain.setGz_receive_type("本人");
			}else if(uploadMain.getGz_receive_type().equals("2")){
				uploadMain.setGz_receive_type("代理");
			}
			String sql="exec getOrderNum";
			List list= uploadMainService.findBySql(sql);
			if(list.size()>0){
				uploadMain.setOrder_num(list.get(0).toString());
			}
			uploadMainService.save(uploadMain);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "fillRecord2"; 
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

	public Integer getGz_type() {
		return gz_type;
	}

	public void setGz_type(Integer gzType) {
		gz_type = gzType;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public File[] getUploads() {
		return uploads;
	}

	public void setUploads(File[] uploads) {
		this.uploads = uploads;
	}
	public String getUploadsFileName() {
		return uploadsFileName;
	}

	public void setUploadsFileName(String uploadsFileName) {
		this.uploadsFileName = uploadsFileName;
	}

	

	public List<Type> getTypes() {
		return types;
	}

	public void setTypes(List<Type> types) {
		this.types = types;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List getList() {
		return list;
	}

	public void setList(List list) {
		this.list = list;
	}

	public List getListId() {
		return listId;
	}

	public void setListId(List listId) {
		this.listId = listId;
	}

	public UploadPicture getUploadPicture() {
		return uploadPicture;
	}

	public void setUploadPicture(UploadPicture uploadPicture) {
		this.uploadPicture = uploadPicture;
	}

	public String getUpload_mainId() {
		return upload_mainId;
	}

	public void setUpload_mainId(String uploadMainId) {
		upload_mainId = uploadMainId;
	}
	
}

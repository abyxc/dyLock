package com.mnsn.project.pictureName;

import java.util.List;

import org.apache.commons.lang.xwork.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.mnsn.common.BaseAction;
import com.mnsn.utils.MyUtils;

/**
 * 这个action是zdb于2016年1月18日添加
 * @author moon
 *
 */
@SuppressWarnings("serial")
@Controller
@Scope("prototype")
public class PictureNameAction extends BaseAction<PictureName>{
	private PictureName pictureName;
	private List<PictureName> pictureNames;
	
	public String list(){
		try{
			String hql = "FROM PictureName WHERE 1=1 ";
			if(StringUtils.isNotEmpty(searchTearm1)){
				hql += " and pic_name like '%"+searchTearm1.trim()+"%' ";
			}
			hql += " order by create_time desc "; 
			pageBean = pictureNameService.findByHql(hql, pageBean);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return "list";
	}
	
	//前往添加页面
	public String toInsert(){
		try{
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return "toInsert";
	}
	
	//添加数据
	public String insert(){
		try{
			if(pictureName != null){
				pictureName.setCreate_time(MyUtils.getCreate_time_Date());
				pictureNameService.save(pictureName);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return "toList";
	}
	
	//前往修改页面
	public String toUpdate(){
		try{
			if(pictureName != null){
				pictureName = pictureNameService.findEntity(pictureName.getId());
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return "toUpdate";
	}
	
	//修改数据
	public String update(){
		try{
			if(pictureName != null){
				pictureNameService.update(pictureName);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return "toList";
	}
	
	//查看详情
	public String toDetails(){
		try{
			if(pictureName != null){
				pictureName = pictureNameService.findEntity(pictureName.getId());
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return "toDetails";
	}
	
	
	//删除数据
	public String delete(){
		try{
			if(pictureName != null){
				pictureNameService.delete(pictureName);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return "toList";
	}
	
	
	
	
	
	public PictureName getPictureName() {
		return pictureName;
	}
	public void setPictureName(PictureName pictureName) {
		this.pictureName = pictureName;
	}
	public List<PictureName> getPictureNames() {
		return pictureNames;
	}
	public void setPictureNames(List<PictureName> pictureNames) {
		this.pictureNames = pictureNames;
	}
}

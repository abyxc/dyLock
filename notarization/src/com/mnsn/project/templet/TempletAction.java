package com.mnsn.project.templet;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.mnsn.common.BaseAction;
import com.mnsn.project.type.Type;
@SuppressWarnings("serial")
@Controller
@Scope("prototype")
public class TempletAction extends BaseAction<Templet>{
	private List<Templet> templets;
	private Templet templet;
	private List<Type> typeList;
	private Type type;
	
	//模板列表
	public String list(){
		try {
			String hql =" From Type where 1=1";
			typeList = typeService.findByHql(hql);
			String sql ="select te.id,te.name,t.gz_name,te.state,te.type_id from db_gz_templet te" +
					" left join db_gz_type t on te.type_id = t.id where 1=1";
			if(searchTearm1 != null && !"".equals(searchTearm1)){
				sql += " and te.type_id = '"+searchTearm1+"'";
			}
			pageBean = templetService.findBySql(sql, "te", pageBean);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "list";
	}
	
	//跳转增加页面
	public String toInsert(){
		try {
			String hql =" From Type where 1=1";
			typeList = typeService.findByHql(hql);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "toInsert";
	}
	
	//增加
	public String insert(){
		try {
			if(templet!=null){
				String sql = " select * from db_gz_templet where type_id = '"+templet.getType_id()+"'";
				List list = templetService.findBySql(sql);
//				System.out.println(list.size());
				if(list.size() == 0){
					templet.setState("1");
				}else{
				templet.setState("0");
				}
				templetService.save(templet);
				String memo ="对 信息管理模板表添加模板【"+templet.getName()+"】";
				insertLog(3, 1, memo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "toList";
	}
	
	//跳转修改页面
	public String toUpdate(){
		try {
			String hql =" From Type where 1=1";
			typeList = typeService.findByHql(hql);
			if(templet!= null){
				templet = templetService.findEntity(templet.getId());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "toUpdate";
	}
	
	//修改
	public String update(){
		try {
			if(templet!= null){
				templetService.update(templet);
				String memo ="对 信息管理模板表修改模板【"+templet.getName()+"】";
				insertLog(3, 2, memo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "toList";
	}
	
	
	//删除
	public String delete(){
		try {
			if(templet!= null){
				templet = templetService.findEntity(templet.getId());
				templetService.delete(templet);
				String memo ="对 信息管理模板表删除模板【"+templet.getName()+"】数据id【"+templet.getId()+"】";
				insertLog(3, 3, memo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "toList";
	}
	
	//详情页面
	public String toDetails(){
		try {
			if(templet != null){
				templet = templetService.findEntity(templet.getId());
				if(templet.getType_id() != null && !"".equals(templet)){
					type = typeService.findEntity(templet.getType_id());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "toDetails";
	}
	
	//启用模板
	public String work(){
		try {
			String sql = "update db_gz_templet set state = 0 where type_id = '"+templet.getType_id()+"'";
			templetService.excuteSql(sql);
			
			templet = templetService.findEntity(templet.getId());
			templet.setState("1");
			templetService.update(templet);
			String memo ="对 信息管理模板表启用模板【"+templet.getName()+"】";
			insertLog(3, 20, memo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "toList";
	}
	public List<Templet> getTemplets() {
		return templets;
	}
	public void setTemplets(List<Templet> templets) {
		this.templets = templets;
	}

	public Templet getTemplet() {
		return templet;
	}

	public void setTemplet(Templet templet) {
		this.templet = templet;
	}

	public List<Type> getTypeList() {
		return typeList;
	}

	public void setTypeList(List<Type> typeList) {
		this.typeList = typeList;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

}

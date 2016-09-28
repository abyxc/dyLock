package com.mnsn.project.type;

import java.util.ArrayList;
import java.util.List;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import com.mnsn.common.BaseAction;
import com.mnsn.project.pictureName.PictureName;
import com.mnsn.project.pictureRelevance.PictureRelevance;
import com.mnsn.utils.MyUtils;
/**
 * 公正事项类型
 * @author ck
 *  @Time 2015-12-28 下午 3:16:33								
 */
@SuppressWarnings("serial")
@Controller
@Scope("prototype")
public class TypeAction extends BaseAction<Type>{
	private Type type;
	private List<Type> types;
	private List<PictureName> pictureNames;	
	private int abc;	
	@SuppressWarnings("rawtypes")
	private List list;	
	private String[] idd;	
	public String list() {
		try {
		String hql = "FROM Type WHERE 1 = 1";
			if (MyUtils.notEmpty(searchTearm1)) {
				hql += " AND gz_name like '%" + searchTearm1.trim() + "%'";
			}
			hql += " order by id";
			pageBean = typeService.findByHql(hql, pageBean);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "list";
	}
    //列表事项所需证件
	public String listpapers() {
		try {
			meg=type.getGz_name();
			String sql = "select pn.pic_name,pr.id " +
					"from db_gz_picture_relevance pr " +
					"left join db_gz_picture_name pn on pn.id = pr.picture_name_id " +
					"where pr.type_id='"+type.getId()+"'";
			list= pictureRelevanceService.findBySql(sql);
             
		} catch (Exception e) {
			e.printStackTrace();
		}
       
		return "listpapers";
	}
    //跳转修改事项所需证件
	public String uppapers() {
		try {
			
			String hql = "from PictureName" ;				
			pictureNames= pictureNameService.findByHql(hql);
			if(ids!=null&!"0".equals(ids)){
				meg=type.getGz_name();
				searchTearm1=type.getId();
				String sql = "select pr.picture_name_id " +
							 "from db_gz_picture_relevance pr " +
							 "left join db_gz_picture_name pn on pn.id = pr.picture_name_id " +
							 "where pr.type_id='"+type.getId()+"'";
				list= pictureRelevanceService.findBySql(sql);  
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if(ids!=null&"0".equals(ids)){
			return "oper";
		}else{
			return "uppapers";
		}
		
	}	
	
	public String delete() {
		try {
			    String sql = "DELETE FROM db_gz_picture_relevance WHERE type_id ='"+type.getId()+"' ";
			    try {
					pictureRelevanceService.excuteSql(sql);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
				type = typeService.findEntity(type.getId());
				if (type != null) {
					typeService.delete(type);
					String memo ="对 公正事项类型表删除公正类型【"+type.getGz_name()+"】id【"+type.getId()+"";
					insertLog(6, 3, memo);
				}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "tolist";
	}

	public String update() {
		try {
			typeService.update(type);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "tolist";
	}

	public String insert() {
		try {
			
			if(type.getId()!=null&&!"".equals(type.getId())){
				typeService.update(type);
			    String sql3 = "DELETE FROM db_gz_picture_relevance WHERE type_id ='"+type.getId()+"' ";
			    try {
					pictureRelevanceService.excuteSql(sql3);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
			    List<PictureRelevance> ps=new ArrayList<PictureRelevance>();

				for(String s:idd){
				    PictureRelevance p=new PictureRelevance();
					p.setType_id(type.getId());			
		            p.setPicture_name_id(s);  
		            ps.add(p);
				}
				pictureRelevanceService.saveBatch(ps);
				String memo ="对 公正事项类型表修改公正类型【"+type.getGz_name()+"】";
				insertLog(6, 2, memo);
			}else{
				typeService.save(type);
			    List<PictureRelevance> ps=new ArrayList<PictureRelevance>();
			    if(idd.length>0){
			    	for(String s:idd){
			    		PictureRelevance p=new PictureRelevance();
			    		p.setType_id(type.getId());			
			    		p.setPicture_name_id(s);  
			    		ps.add(p);
			    	}
			    }
				pictureRelevanceService.saveBatch(ps);
				String memo ="对 公正事项类型表添加公正类型【"+type.getGz_name()+"】";
				insertLog(6, 1, memo);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "tolist";
	}
	public String toupdate() {
		try {
			type=typeService.findEntity(type.getId());
			meg="1";
			String hql = "from PictureName" ;				
			pictureNames= pictureNameService.findByHql(hql);
			if(ids!=null&!"0".equals(ids)){
				meg=type.getGz_name();
				searchTearm1=type.getId();
				String sql = "select pr.picture_name_id " +
							 "from db_gz_picture_relevance pr " +
							 "left join db_gz_picture_name pn on pn.id = pr.picture_name_id " +
							 "where pr.type_id='"+type.getId()+"'";
				list= pictureRelevanceService.findBySql(sql);  
			}	
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "toupdate";
	}	


	

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public List<Type> getTypes() {
		return types;
	}

	public void setTypes(List<Type> types) {
		this.types = types;
	}
	@SuppressWarnings("rawtypes")
	public List getList() {
		return list;
	}
	public void setList(@SuppressWarnings("rawtypes") List list) {
		this.list = list;
	}
	public int getAbc() {
		return abc;
	}
	public void setAbc(int abc) {
		this.abc = abc;
	}
	public List<PictureName> getPictureNames() {
		return pictureNames;
	}
	public void setPictureNames(List<PictureName> pictureNames) {
		this.pictureNames = pictureNames;
	}
	public String[] getIdd() {
		return idd;
	}
	public void setIdd(String[] idd) {
		this.idd = idd;
	}

	
}

package com.mnsn.common;

import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

/**
 * 
 * @author ll
 * <h3>优雅的代码需要愉快的心情</h3>
 * 2015-1-17下午01:27:10
 * http://www.cnblogs.com/mvilplss/
 * @param <T>
 */
public class PageBean<T> {

	private static int pageSize;// 页面大小
	private static int currentPage = 1;// 当前页码
	private static int recordCnt;// 记录总数
	private static int pageCnt;// 页码总数
	private static int recordStart;// 开始记录数
	private List<T> dataList;
	
	public PageBean( int currentPage ,int pageSize) {
		String url = ServletActionContext.getRequest().getRequestURI();
		if(url.contains("parityPriceContent")){
			this.pageSize = 7;
		}else{
			this.pageSize = pageSize;
		}
		this.currentPage = currentPage;
	}

	public PageBean(int currentPage,int pageSize,int recordCnt ,List<T> dataList) {
		this.currentPage = currentPage;
		this.pageSize = pageSize;
		this.recordCnt = recordCnt;
		this.pageCnt = (recordCnt+pageSize-1)/pageSize;//页码总数
		this.dataList = dataList;
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("page", getPagination(request));
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(String pageSize) {
		try {
			this.pageSize = Integer.parseInt(pageSize);
		} catch (Exception e) {
			this.pageSize = 10;
		}
		System.out.println(pageSize);
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(String currentPage) {
		try {
			this.currentPage = Integer.parseInt(currentPage);
		} catch (Exception e) {
			this.currentPage = 1;
		}
	}

	public void setPageCnt(int pageCnt) {
		this.pageCnt = pageCnt;
	}

	public int getRecordCnt() {
		return recordCnt;
	}

	public void setRecordCnt(int recordCnt) {
		this.recordCnt = recordCnt;
	}

	public int getRecordStart() {
		return recordStart;
	}

	public void setRecordStart(int recordStart) {
		this.recordStart = recordStart;
	}

	public int getPageCnt() {
		return pageCnt;
	}

	public List<T> getDataList() {
		return dataList;
	}

	public void setDataList(List<T> dataList) {
		this.dataList = dataList;
	}

	public static String getPagination(HttpServletRequest request) {
		
		//String baseph=ServletActionContext.getRequest().getSession().getServletContext().getRealPath("/");
		String baseph=ServletActionContext.getRequest().getContextPath();
		Enumeration es = request.getParameterNames();
		StringBuffer pagination = new StringBuffer();
		pagination.append("<form action='"+request.getRequestURI()+"' id='pageForm' method='post' style='width: 100%;'>");
		while(es.hasMoreElements()){
			String fieldName = (String) es.nextElement();
			String fieldValue = request.getParameter(fieldName);
			if(!"pageBean.currentPage".equals(fieldName)){
				pagination.append("<input type='hidden' name='"+fieldName+"' value='"+fieldValue+"'/>");
			}
		}
        	pagination.append("<div class='pageDiv'><ul style='float: right;'><li class='pageLi'>共"+recordCnt+"条</li>");
			pagination.append("<li class='pageLi' onclick='jump(1)' onmouseover='mouseOverColor(this);' onmouseout='mouseOutColor(this)'>首页</li>");
			if(currentPage>1){
				pagination.append("<li class='pageLi' onclick='jump("+(currentPage-1)+")' onmouseover='mouseOverColor(this);' onmouseout='mouseOutColor(this)'>上一页</li>");
			}
			pagination.append("<li class='pageLi'><input type='hidden' name='pageBean.currentPage' id='cp' value='"+currentPage+"' /></li>");
			if(currentPage<4){
				for(int i=1;i<=pageCnt;i++){
					if(i<6){
						if(i==currentPage){
							pagination.append("<li onclick='jump("+i+")' style = 'color: #ff5400;' class='pageLi'>"+i+"</li>");
						}else{
							pagination.append("<li onclick='jump("+i+")' class='pageLi' >"+i+"</li>");
						}
					}
				}
			}else{
				for(int i=currentPage-2;i<=pageCnt;i++){
					if(i<currentPage+3){
						if(i==currentPage){
							pagination.append("<li onclick='jump("+i+")' style = 'color: #ff5400;' class='pageLi'>"+i+"</li>");
						}else{
							pagination.append("<li onclick='jump("+i+")' class='pageLi' >"+i+"</li>");
						}
					}
				}
			}
			pagination.append("<li class='pageLi' onclick='jump("+(currentPage+1)+")' onmouseover='mouseOverColor(this);' onmouseout='mouseOutColor(this)'>下一页</li>");
			pagination.append("<li class='pageLi' onclick='jump("+pageCnt+")' onmouseover='mouseOverColor(this);' onmouseout='mouseOutColor(this)'>末页</li></ul></div>");
			
			pagination.append("</form>");
			pagination.append("<script type=\"text/javascript\">");
			pagination.append("function jump(cp){" +
					"if(isNaN(cp)){cp = 1;}if(cp == 0 || cp > "+pageCnt+"){return false;}document.getElementById('cp').value=cp;" +
			          "$.ajax({async:true,cache:false,type:'post', url:'"+request.getRequestURI()+"',data:$('#pageForm').serialize(),beforeSend: function(){showHideDiv();}, success:function(d){$('.content-right').html(d);} });}");
	        pagination.append("</script>");	
		

		return pagination.toString();
	}

	public static String getPaginationPc(HttpServletRequest request) {
		
		@SuppressWarnings("unused")
		String baseph=ServletActionContext.getRequest().getContextPath();
		@SuppressWarnings("rawtypes")
		Enumeration es = request.getParameterNames();
		StringBuffer pagination = new StringBuffer();
		pagination.append("<form action='"+request.getRequestURI()+"' id='pageForm' method='post' style='width: 100%;'>");

		while(es.hasMoreElements()){
			String fieldName = (String) es.nextElement();
			String fieldValue = request.getParameter(fieldName);
			if(!"pageBean.currentPage".equals(fieldName)){
				pagination.append("<input type='hidden' name='"+fieldName+"' value='"+fieldValue+"'/>");
			}
		}
		
		String url=request.getRequestURI();

		pagination.append("<div class='pageDiv'><ul style='float: right;'><li class='pageLi'>共"+recordCnt+"条</li>");
		pagination.append("<li class='pageLi' onclick='jump(1)' onmouseover='mouseOverColor(this);' onmouseout='mouseOutColor(this)'>首页</li>");
		if(currentPage>1){
			pagination.append("<li class='pageLi' onclick='jump("+(currentPage-1)+")' onmouseover='mouseOverColor(this);' onmouseout='mouseOutColor(this)'>上一页</li>");
		}
		pagination.append("<li class='pageLi'><input type='hidden' name='pageBean.currentPage' id='cp' value='"+currentPage+"' /></li>");
		if(currentPage<4){
			for(int i=1;i<=pageCnt;i++){
				if(i<6){
					if(i==currentPage){
						pagination.append("<li onclick='jump("+i+")' style = 'color: #ff5400;' class='pageLi'>"+i+"</li>");
					}else{
						pagination.append("<li onclick='jump("+i+")' class='pageLi' >"+i+"</li>");
					}
				}
			}
		}else{
			for(int i=currentPage-2;i<=pageCnt;i++){
				if(i<currentPage+3){
					if(i==currentPage){
						pagination.append("<li onclick='jump("+i+")' style = 'color: #ff5400;' class='pageLi'>"+i+"</li>");
					}else{
						pagination.append("<li onclick='jump("+i+")' class='pageLi' >"+i+"</li>");
					}
				}
			}
		}
		pagination.append("<li class='pageLi' onclick='jump("+(currentPage+1)+")' onmouseover='mouseOverColor(this);' onmouseout='mouseOutColor(this)'>下一页</li>");
		pagination.append("<li class='pageLi' onclick='jump("+pageCnt+")' onmouseover='mouseOverColor(this);' onmouseout='mouseOutColor(this)'>末页</li></ul></div>");
		
		pagination.append("</form>");
		pagination.append("<script type=\"text/javascript\">");
		pagination.append("function jump(cp){if(isNaN(cp)){cp = 1;}if(cp == 0 || cp > "+pageCnt+"){return false;}document.getElementById('cp').value=cp;document.getElementById('pageForm').submit();}");
		pagination.append("</script>");	
	
		return pagination.toString();
	}


}

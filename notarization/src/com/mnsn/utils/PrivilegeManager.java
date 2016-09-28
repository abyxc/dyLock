package com.mnsn.utils;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspContext;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.JspFragment;
import javax.servlet.jsp.tagext.JspTag;
import javax.servlet.jsp.tagext.SimpleTag;
/**
 * @AUTHER LiuLonglong
 * @Motto Goals determine what you are going to be.
 * @URL http://www.cnblogs.com/mvilplss/
 * @Time 下午05:14:07
 * @Version
 */
public class PrivilegeManager implements SimpleTag {
	private String code;
	private String name="priCodes";
	PageContext pageContext;
	JspFragment jf;

	public void setJspContext(JspContext jc) {
		this.pageContext = ((PageContext) jc);
	}

	public void setJspBody(JspFragment jf) {
		this.jf = jf;
	}

	@SuppressWarnings("unchecked")
	public void doTag() throws JspException, IOException {
		Collection<String> prisAll = PriCache.prisAll;
		HttpServletRequest request = (HttpServletRequest) this.pageContext
				.getRequest();
		Collection<String> pris = (Collection<String>) request.getSession()
				.getAttribute(this.name);
		if(prisAll.contains(this.code)){
			if ((pris != null) && (pris.contains(this.code))){
				this.jf.invoke(null);
			}
		}else{
			this.jf.invoke(null);
		}
		
	}

	public void setCode(String code) {
		this.code = code;
	}

	public void setName(String name) {
		this.name = name;
	}

	public JspTag getParent() {
		return null;
	}

	public void setParent(JspTag arg0) {
	}
}

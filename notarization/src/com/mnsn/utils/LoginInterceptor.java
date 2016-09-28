package com.mnsn.utils;

import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;

import com.mnsn.project.user.User;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
/**
 * @AUTHER LiuLonglong
 * @Motto Goals determine what you are going to be.
 * @URL http://www.cnblogs.com/mvilplss/
 * @Time 上午09:29:25
 * @Version
 */
@SuppressWarnings("unchecked")
public class LoginInterceptor extends AbstractInterceptor {
	private static final long serialVersionUID = 1L;

	@Override
	public String intercept(ActionInvocation ai) throws Exception {
		String action = ServletActionContext.getRequest().getRequestURI();
		if(action.contains("login.action")){//不拦截后台登录方法
			return ai.invoke();
		}
		
		User user = (User) ServletActionContext.getRequest().getSession().getAttribute("loginUser");
		if(user == null){
			return "nologin";
		}
		//拦截权限
		try{
			action = action.substring("/notarization/".length(),action.length());
			Map<String,Integer> applicationPriAllUrls = (Map<String,Integer>) ServletActionContext.getServletContext().getAttribute("applicationPriAllUrls");
			Map<String,Integer> sessionPriUrls = (Map<String,Integer>) ServletActionContext.getRequest().getSession().getAttribute("sessionPriUrls");
			if(applicationPriAllUrls.containsKey(action)){
				if(sessionPriUrls.containsKey(action)){
					return ai.invoke();
				}else{
					return "nopri";
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		System.gc();
		return ai.invoke();
	}

}

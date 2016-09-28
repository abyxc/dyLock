package net.dy.common;

import javax.annotation.Resource;

import net.dy.service.AddLockService;
import net.dy.service.CompanyService;
import net.dy.service.LogService;
import net.dy.service.OpenLockService;
import net.dy.service.RefreshService;
import net.dy.service.UserService;
import net.dy.service.VerifyCodeService;
import net.dy.util.ToolClass;
import net.dy.util.ToolString;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;

/**
 * @author 作者 E-mail:elizhiyong@163.com 
 * @version 创建时间：2016年8月26日 上午11:16:15 
 * {@link http://www.chncode.net }
 */
@SuppressWarnings("serial")
@Controller
@Scope("prototype")
public class BaseAction<T> extends ActionSupport  {

	@Resource
	protected LogService logService;
	
	@Resource
	protected VerifyCodeService verifyCodeService;
	
	@Resource
	protected UserService userService;
	
	@Resource
	protected CompanyService companyService;
	
	@Resource
	protected OpenLockService openLockService;
	
	@Resource
	protected AddLockService addLockService;
	
	@Resource
	protected RefreshService refreshService;
	
	/**
	 * 处理json对象
	 * @param code
	 * @param t
	 * @return
	 */
	public ToolClass jsonObject(String code,T t){
		ToolClass tool = new ToolClass();
		tool.setCode(code);
		tool.getDate().add(t);
		return tool;
	}
	
	public ToolString jsonString(String code,String date){
		ToolString tool = new ToolString();
		tool.setCode(code);
		tool.setData(date);
		return  tool;
	}
	
}

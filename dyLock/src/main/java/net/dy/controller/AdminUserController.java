package net.dy.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import net.dy.common.BaseAction;
import net.dy.domain.AdminUser;

/**
 * @author 作者 E-mail:elizhiyong@163.com 
 * @version 创建时间：2016年9月5日 上午11:40:39 
 * {@link http://www.chncode.net }
 */
@SuppressWarnings("serial")
@Controller
@RequestMapping("/adminUser")
public class AdminUserController extends BaseAction<AdminUser> {

	private static Logger LOG = LoggerFactory.getLogger(AdminUserController.class);
	
	@RequestMapping("/goLogin")
	public String goLogin(){
		return "/login/login";
	}
	
	@RequestMapping("/login")
	public String login(){
		
		return "/common/common";
	}
}

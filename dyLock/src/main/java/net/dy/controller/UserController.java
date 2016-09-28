package net.dy.controller;

import net.dy.common.BaseAction;
import net.dy.domain.User;
import net.dy.domain.VerifyCode;
import net.dy.util.UtilFile;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

/**
 * @author 作者 E-mail:elizhiyong@163.com 
 * @version 创建时间：2016年9月5日 上午11:40:39 
 * {@link http://www.chncode.net }
 */
@SuppressWarnings("serial")
@Controller
@RequestMapping("/user")
public class UserController extends BaseAction<User> {

	private static Logger LOG = LoggerFactory.getLogger(UserController.class);
	
	/**
	 * 获取验证码
	 * @param verifyCode
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/register")
	public String register(User user){
		String str = null;
		try {
			if(StringUtils.isNotEmpty(user.getCode()) && StringUtils.isNotEmpty(user.getMobile()) && StringUtils.isNotEmpty(user.getCompanyId()) 
					&& StringUtils.isNotEmpty(user.getPhoneModel()) && StringUtils.isNotEmpty(user.getSdkV()) && StringUtils.isNotEmpty(user.getSystemV())){
				if(companyService.selectId(user.getCompanyId()) == null){
					str = JSON.toJSONString(jsonString("103", "CompanyId不存在"));
				}else {
					VerifyCode vc = verifyCodeService.selectMobileCode(user.getMobile(),user.getCode());
					if(vc != null){
						verifyCodeService.updateCode(vc.getId());
						LOG.info("更新短信id为："+vc.getId() + "的状态");
						User obj = userService.selectMobile(user.getMobile());
						if(obj == null){
							user.setUpdateTime(UtilFile.currentTimestamp());
							user.setId(UtilFile.randomUUID());
							userService.insert(user);
						}else{//如该用户存在就更新资料
							user.setUpdateTime(UtilFile.currentTimestamp());
							userService.updateMobile(user);
						}
						str = JSON.toJSONString(jsonString("100", "success"));
					}else {
						str = JSON.toJSONString(jsonString("102", "验证码错误"));
					}
				}
			}else{
				str = JSON.toJSONString(jsonString("101", "参数错误"));
			}
		} catch (Exception e) {
			str = JSON.toJSONString(jsonString("500", "系统异常"));
			e.printStackTrace();
		}
		return str;
	}

	/**
	 * 登录
	 */
	@ResponseBody
	@RequestMapping("/login")
	public String login(User user){
		String str = null;
		try {
			if(StringUtils.isNotEmpty(user.getCompanyId()) && StringUtils.isNoneEmpty(user.getMobile()) && StringUtils.isNoneEmpty(user.getSdkV()) && StringUtils.isNoneEmpty(user.getPhoneModel())){
				if(companyService.selectId(user.getCompanyId()) == null){
					str = JSON.toJSONString(jsonString("103", "CompanyId不存在"));
				}else {
					User us = userService.selectMobile(user.getMobile());
					if(us == null){
						str = JSON.toJSONString(jsonString("102", "该手机号不存在"));
					}else {
						user.setUpdateTime(UtilFile.currentTimestamp());
						userService.updateMobile(user);
						str = JSON.toJSONString(jsonString("100", "success"));
					}
				}
			}else{
				str = JSON.toJSONString(jsonString("101", "参数错误"));
			}
		} catch (Exception e) {
			str = JSON.toJSONString(jsonString("500", "系统异常"));
			e.printStackTrace();
		}
		return str;
	}
	
}

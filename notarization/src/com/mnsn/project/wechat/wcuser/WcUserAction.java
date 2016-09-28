package com.mnsn.project.wechat.wcuser;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.xwork.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.json.JSONObject;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.mnsn.common.BaseAction;
import com.mnsn.project.user.User;
import com.mnsn.utils.MyUtils;

/**
 * 2016年1月25日zdb添加
 * judge方法的作用就是进行各种判断
 * 1.这个微信的openid是否在user表中存在
 * 	分为2种情况：1存在，直接登录
 * 	2不存在；
 * 					
 * 
 * @author moon
 *
 */
@SuppressWarnings("serial")
@Controller
@Scope("prototype")
public class WcUserAction extends BaseAction<User>{
	private User user;
	private List<User> users;
	private String code;
	
	/*
	 * 用户注册时提交的信息，用实体类可以，但是这里全部写成变量
	 */
	private String name;
	private String idcard_type;
	private String idcard_number;
	private String mobile_phone;
	private String test_code_jsp;//用户在jsp页面上填写的验证码
	private String test_code_sms;//短信发送的验证码      
	
	//下边这个参数代表的是是不是添加成功：0成功，别的值就是都是各种错误，作用是提示
	private String success_state;
	
	
	
	//这个方法是用来进行各种判断的链接
	public String judge(){
		try{
			String resultStr = MyUtils.HttpPost("https://api.weixin.qq.com/sns/oauth2/access_token" +
					"?appid=wx282ede80199f6bdc" +
					"&secret=d3653dc207bafe381afa45d97eb48ada" +
					"&code="+code+"" +
					"&grant_type=authorization_code");
			if(resultStr.contains("{\"")){//判断是不是json
				JSONObject obj = new JSONObject(resultStr);
				if(resultStr.contains("40029")){
					return ERROR;
				}
				String openid = obj.getString("openid");
				//查询这个openid是否存在于数据库中，如果存在直接登录
				String hql = "FROM User WHERE openid = '"+openid+"'";
				users = userService.findByHql(hql);
				if(users.size() > 0){//证明已经存在
					code = "";//这个字段用来在页面上判断这个用户是不是需要注册
					putInSession("user_id",users.get(0).getId());
				}else{
					putInSession("openid",openid);
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
		return "main";
	}
	
	//前往注册页面
	public String toRegister(){
		try{
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return "toRegister";
	}
	
	
	//发送验证码
	public void sendTestCode(){
		try{
			Pattern p = Pattern.compile("^((1[3|4|5|7|8][0-9]))\\d{8}$");  
			Matcher m = p.matcher(mobile_phone);  
			if(m.matches()){
				System.out.println(mobile_phone);
				int num = (int)((Math.random()*9+1)*100000);
				test_code_sms = num+"";
				putInSession("test_code_sms",test_code_sms);
				printStr("1");//1表示正确
				System.out.println(test_code_sms);
			}else{
				printStr("3");
			}
		}catch (Exception e) {
			printStr("2");//系统错误
			e.printStackTrace();
		}
	}
	
	
	//注册用户，验证码只能在这里验证，在这里验证还稍微安全一点，无视存在session中
	public void insert(){
		try{
			test_code_sms = (String) ServletActionContext.getRequest().getSession().getAttribute("test_code_sms");
			if(StringUtils.isNotEmpty(test_code_sms)){
				if(test_code_sms.equals(test_code_jsp)){
					//页面传递过来的参数
					user = new User();
					user.setName(name.trim());
					if(StringUtils.isNotEmpty(idcard_type)){
						if("身份证".equals(idcard_type)){
							user.setIdcard_type(1);
						}else if("护照".equals(idcard_type)){
							user.setIdcard_type(2);
						}else if("台湾地区身份证".equals(idcard_type)){
							user.setIdcard_type(3);
						}else if("港澳通行证".equals(idcard_type)){
							user.setIdcard_type(4);
						}
					}
					user.setIdcard_number(idcard_number.trim());
					user.setMobile_phone(mobile_phone.trim());
					//后台自动设置
					user.setSex(0);
					user.setLoginname(mobile_phone.trim());
					user.setRole_type(2);
					user.setCreate_time(MyUtils.getCreate_time_Date());
					
					//存放openid
					String openid = (String) ServletActionContext.getRequest().getSession().getAttribute("openid");
					if(StringUtils.isNotEmpty(openid)){
						user.setOpenid(openid);
						userService.save(user);
						putInSession("user_id", user.getId());
						success_state = "0";//成功了
					}else{
						success_state = "3";//获取不到微信标识符,超时导致
					}
				}else{
					success_state = "2";//验证码错误
				}
			}else{
				success_state = "1";//未获取到验证码
			}
			putInSession("test_code_sms", "");//不管验证成功还是失败，都将验证码设置成空的
			printStr(success_state);
		}catch (Exception e) {
			printStr("4");
			e.printStackTrace();
		}
	}

	
	//注册成功后前往成功提示页面
	public String toSuccess(){
		try{
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return "toSuccess";
	}
	
	
	//从注册成功页面自动跳转到主页面
	public String toMain(){
		try{
			code = "";//设置成空
		}catch (Exception e) {
			e.printStackTrace();
		}
		return "main";
	}
	
	
	
	
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public List<User> getUsers() {
		return users;
	}
	public void setUsers(List<User> users) {
		this.users = users;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getTest_code_jsp() {
		return test_code_jsp;
	}
	public void setTest_code_jsp(String testCodeJsp) {
		test_code_jsp = testCodeJsp;
	}
	public String getTest_code_sms() {
		return test_code_sms;
	}
	public void setTest_code_sms(String testCodeSms) {
		test_code_sms = testCodeSms;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIdcard_type() {
		return idcard_type;
	}
	public void setIdcard_type(String idcardType) {
		idcard_type = idcardType;
	}
	public String getIdcard_number() {
		return idcard_number;
	}
	public void setIdcard_number(String idcardNumber) {
		idcard_number = idcardNumber;
	}
	public String getMobile_phone() {
		return mobile_phone;
	}
	public void setMobile_phone(String mobilePhone) {
		mobile_phone = mobilePhone;
	}
	public String getSuccess_state() {
		return success_state;
	}
	public void setSuccess_state(String successState) {
		success_state = successState;
	}
}
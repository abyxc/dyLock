package com.mnsn.utils;

import java.io.IOException;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;

import org.dom4j.Document;   
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;   
import org.dom4j.Element;   




/***
 *crj2016-1-18
 *短信服务类
 *调用短信接口
 **/
public class SmsUtil {
	private static String Url = "http://106.ihuyi.cn/webservice/sms.php?method=Submit";
	private static final String account = "cf_ZHGZ";
	private static final String password = "85065650";
	public void sendMessage(String phone,String content){
		HttpClient client = new HttpClient(); 
		PostMethod method = new PostMethod(Url); 
		client.getParams().setContentCharset("UTF-8");
		method.setRequestHeader("ContentType","application/x-www-form-urlencoded;charset=UTF-8");
		NameValuePair[] data = {//提交短信
			new NameValuePair("account", account), 
			new NameValuePair("password",password), //密码可以使用明文密码或使用32位MD5加密
			//new NameValuePair("password", util.StringUtil.MD5Encode("密码")),
			new NameValuePair("mobile", phone), 
			new NameValuePair("content", content),
		};
		method.setRequestBody(data);		
		try {
			client.executeMethod(method);	
			String SubmitResult =method.getResponseBodyAsString();
			Document doc = DocumentHelper.parseText(SubmitResult); 
			Element root = doc.getRootElement();
			String code = root.elementText("code");	
			String msg = root.elementText("msg");	
			String smsid = root.elementText("smsid");	
			if("2".equals(code)){
				System.out.println(content);
				System.out.println("短信提交成功");
			}
		} catch (HttpException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
}

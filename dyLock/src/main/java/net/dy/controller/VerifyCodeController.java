package net.dy.controller;

import java.net.URLEncoder;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

import net.dy.common.BaseAction;
import net.dy.domain.AdminUser;
import net.dy.domain.VerifyCode;
import net.dy.util.UtilFile;

/**
 * @author 作者 E-mail:elizhiyong@163.com 
 * @version 创建时间：2016年9月5日 上午11:40:39 
 * {@link http://www.chncode.net }
 */
@SuppressWarnings("serial")
@Controller
@RequestMapping("/code")
public class VerifyCodeController extends BaseAction<VerifyCode> {

	private static Logger LOG = LoggerFactory.getLogger(VerifyCodeController.class);
	
	/**
	 * 获取验证码
	 * @param verifyCode
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getCode")
	public String getCode(VerifyCode verifyCode){
		String  num = null;
		try {
			if (StringUtils.isNotEmpty(verifyCode.getMobile()) && StringUtils.isNotEmpty(verifyCode.getContent()) ) {
				VerifyCode obj = verifyCodeService.selectMobile(verifyCode.getMobile());
				if(obj != null){
					Long Old = Long.valueOf(obj.getValidTime()).longValue();
					if((System.currentTimeMillis() - Old) < 40000){//验证码一分钟后才能发
						num = JSON.toJSONString(jsonString("102", "操作发送太频繁"));
					}else {
						num=sends(verifyCode.getMobile(), verifyCode.getContent());
						if(num.length() == 4){
							verifyCode.setId(UtilFile.randomUUID());
							verifyCode.setCreateTime(UtilFile.currentTimestamp());
							verifyCode.setCode(num);
							verifyCode.setType("2");//2表示为使用；1表示使用
							verifyCode.setValidTime(System.currentTimeMillis()+"");
							verifyCodeService.insert(verifyCode);
							num = JSON.toJSONString(jsonString("100", "success"));
							LOG.info("给：" + verifyCode.getMobile() + "发送验证码成功<" + num +">");
						}
					}
				}
			}else {
				num = JSON.toJSONString(jsonString("101", "参数错误"));
			}
		} catch (Exception e) {
			num = JSON.toJSONString(jsonString("500", "系统异常"));
			e.printStackTrace();
		}
		return num;
	}

	
	
	/**
	 * 向第三方接口获取验证码短信
	 */
	
	public  String sends (String mobile,String content){
		String res = "";
		String num = null;
		 // 创建默认的httpClient实例.    
        CloseableHttpClient hc = null;
		try {
			num = randoms();
			hc = HttpClients.createDefault();
			RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(1000 * 15).setConnectTimeout(1000 * 15).build();//(15s)设置请求和传输超时时间
			StringBuffer sb = new StringBuffer(UtilFile.MASSAGE_PORT);
			sb.append("accName=" + UtilFile.ACC_NAME);
			sb.append("&accPwd=" +toMD5(UtilFile.ACC_PWD));
			sb.append("&aimcodes=" + mobile);
			sb.append("&content=" + URLEncoder.encode(content+num+"【盾云科技】", "UTF-8"));
			SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
			sb.append("&bizId=" + df.format(new Date()));
			sb.append("&dataType=string");
			HttpPost post = new HttpPost(sb.toString());
			post.setConfig(requestConfig);
			HttpResponse response = hc.execute(post);
			HttpEntity resEntity = response.getEntity();
			res = EntityUtils.toString(resEntity);
			if (res != "" && res.contains("发送成功")) {
				res = num;
			}else {
				LOG.info("给:<"+mobile+">短信发送失败；第三方短信平台错误，原因：" +res);
				res = JSON.toJSONString(jsonString("500", "第三方短信平台异常"));
			}
		} catch (Exception e) {
			LOG.info("网络错误！！！给:<"+mobile+">短信发送失败");
			LOG.error("短信发送失败", e.getMessage());
		}
		return res;
	}
	
	
	/**
	 * 生成4位验证码
	 */
	public String randoms() {
		Random random = new Random();
		String content = random.nextInt(10000) + "";
		content = "0000" + content;
		content = content.substring(content.length() - 4,content.length() );
		return content;
	}
	
	/**
	 * 
	 * @param plainText供发短信使用
	 * @return
	 */
	public    String toMD5(String plainText) {//
		StringBuffer buf = new StringBuffer("");
	     try {
	        MessageDigest md = MessageDigest.getInstance("MD5");  
	        md.update(plainText.getBytes());
	        byte b[] = md.digest();
	        int i;
	        for (int offset = 0; offset < b.length; offset++) {
	          i = b[offset];
	          if (i < 0)
	            i += 256;
	          if (i < 16)
	            buf.append("0");
	          buf.append(Integer.toHexString(i));
	        }
	     } 
	     catch (Exception e) {
	       e.printStackTrace();
	     }
	     return buf.toString().toUpperCase();// 32位的加密
	   }
	
	
}

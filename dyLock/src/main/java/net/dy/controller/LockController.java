package net.dy.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

import net.dy.common.BaseAction;
import net.dy.domain.AddLock;
import net.dy.domain.AdminUser;
import net.dy.domain.OpenLock;
import net.dy.domain.User;
import net.dy.service.AddLockService;
import net.dy.util.UtilFile;

/**
 * @author 作者 E-mail:elizhiyong@163.com 
 * @version 创建时间：2016年9月5日 上午11:40:39 
 * {@link http://www.chncode.net }
 */
@SuppressWarnings("serial")
@Controller
@RequestMapping("/lock")
public class LockController extends BaseAction<OpenLock> {

	private static Logger LOG = LoggerFactory.getLogger(LockController.class);
	
	/**
	 * 保存开门记录
	 * @param openLock
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/openLock")
	public String openLock(OpenLock openLock,HttpServletRequest request){
		String str = null;
		try {
			if(StringUtils.isNotEmpty(openLock.getCompanyId()) && StringUtils.isNotEmpty(openLock.getSystemV())  
					&& StringUtils.isNotEmpty(openLock.getJudge()) && StringUtils.isNotEmpty(openLock.getLockMac()) 
					&& StringUtils.isNotEmpty(openLock.getMobile()) && StringUtils.isNotEmpty(openLock.getOpenTime()) 
					&& StringUtils.isNotEmpty(openLock.getPhoneModel()) && StringUtils.isNotEmpty(openLock.getSdkV())){
				if(companyService.selectId(openLock.getCompanyId()) == null){
					str = JSON.toJSONString(jsonString("103", "CompanyId不存在"));
				}else {
					User us = userService.selectMobile(openLock.getMobile());
					if(us == null){
						str = JSON.toJSONString(jsonString("102", "该手机号不存在"));
					}else {
						openLock.setId(UtilFile.randomUUID());
						openLock.setCreateTime(UtilFile.currentTime());
						openLock.setIpAddress(getIp(request));
						if(openLock.getJudge().equals("1")){
							if(StringUtils.isEmpty(openLock.getJudgeContent())){
								str = JSON.toJSONString(jsonString("104", "开门不成功时，请上传原因"));
							}else {
								openLockService.insert(openLock);
								str = JSON.toJSONString(jsonString("100", "success"));
							}
						}
						if(openLock.getJudge().equals("2")){
							openLockService.insert(openLock);
							str = JSON.toJSONString(jsonString("100", "success"));
						}
					}
				}
			}else {
				str = JSON.toJSONString(jsonString("101", "参数错误"));
			}
		} catch (Exception e) {
			str = JSON.toJSONString(jsonString("500", "系统异常"));
			e.printStackTrace();
		}
		return str;
	}
	@ResponseBody
	@RequestMapping("/addLock")
	public String addLock(AddLock addLock,HttpServletRequest request){
		String str = null;
		try {
			if(StringUtils.isNotEmpty(addLock.getCompanyId()) && StringUtils.isNotEmpty(addLock.getJudge())
					&& StringUtils.isNotEmpty(addLock.getLockFirmware()) && StringUtils.isNotEmpty(addLock.getLockMac())
					&& StringUtils.isNotEmpty(addLock.getMobile()) && StringUtils.isNotEmpty(addLock.getOpenTime()) 
					&& StringUtils.isNotEmpty(addLock.getPhoneModel()) && StringUtils.isNotEmpty(addLock.getSdkV()) 
					&& StringUtils.isNotEmpty(addLock.getSystemV())){
				if(companyService.selectId(addLock.getCompanyId()) == null){
					str = JSON.toJSONString(jsonString("103", "CompanyId不存在"));
				}else {
					User us = userService.selectMobile(addLock.getMobile());
					if(us == null){
						str = JSON.toJSONString(jsonString("102", "该手机号不存在"));
					}else {
						addLock.setId(UtilFile.randomUUID());
						addLock.setCreateTime(UtilFile.currentTime());
						addLock.setIpAddress(getIp(request));
						if(addLock.getJudge().equals("1")){
							if(StringUtils.isEmpty(addLock.getJudgeContent())){
								str = JSON.toJSONString(jsonString("104", "添加钥匙不成功时，请上传原因"));
							}else {
								addLockService.insert(addLock);
								str = JSON.toJSONString(jsonString("100", "success"));
							}
						}
						if(addLock.getJudge().equals("2")){
							addLockService.insert(addLock);
							str = JSON.toJSONString(jsonString("100", "success"));
						}
					}
				}
			}else {
				str = JSON.toJSONString(jsonString("101", "参数错误"));
			}
		} catch (Exception e) {
			str = JSON.toJSONString(jsonString("500", "系统异常"));
			e.printStackTrace();
		}
		return str;
	}
	
	
	/**
	 * 获取ip
	 * @param request
	 * @return
	 */
	public String getIp(HttpServletRequest request){
		System.out.println(request.getRemoteAddr());
		String address = request.getHeader("X-Forwarded-For");
		if (address != null && address.length() > 0
		&& !"unknown".equalsIgnoreCase(address)) {
		return address;
		}
		address = request.getHeader("Proxy-Client-IP");
		if (address != null && address.length() > 0
		&& !"unknown".equalsIgnoreCase(address)) {
		return address;
		}
		address = request.getHeader("WL-Proxy-Client-IP");
		if (address != null && address.length() > 0
		&& !"unknown".equalsIgnoreCase(address)) {
		return address;
		}
		return request.getRemoteAddr();
	}
	
}

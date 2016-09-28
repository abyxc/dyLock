package net.dy.controller;

import net.dy.common.BaseAction;
import net.dy.domain.Refresh;
import net.dy.domain.User;
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
 * @version 创建时间：2016年9月19日 下午3:09:21 
 * {@link http://www.chncode.net }
 */
@SuppressWarnings("serial")
@Controller
@RequestMapping("/firmware")
public class RefreshController extends BaseAction<Refresh>{

	private static Logger LOG = LoggerFactory.getLogger(RefreshController.class);
	
	
	/**
	 * 固件刷新
	 */
	@ResponseBody
	@RequestMapping("/refresh")
	public String refresh(Refresh refresh){
		String str = null;
		try {
			if(StringUtils.isNotEmpty(refresh.getFirmwareNew()) && StringUtils.isNotEmpty(refresh.getFirmwareOld()) 
					&& StringUtils.isNotEmpty(refresh.getLockMac()) && StringUtils.isNotEmpty(refresh.getMobile()) 
					&& StringUtils.isNotEmpty(refresh.getPhoneModel()) && StringUtils.isNotEmpty(refresh.getSdkV())
					&& StringUtils.isNotEmpty(refresh.getSoftwareV()) && StringUtils.isNotEmpty(refresh.getSystemV()) && StringUtils.isNotEmpty(refresh.getCompanyId())){
				if(companyService.selectId(refresh.getCompanyId()) == null){
					str = JSON.toJSONString(jsonString("103", "CompanyId不存在"));
				}else {
					User us = userService.selectMobile(refresh.getMobile());
					if(us == null){
						str = JSON.toJSONString(jsonString("102", "该手机号不存在"));
					}else {
						refresh.setId(UtilFile.randomUUID());
						refresh.setCreateTime(UtilFile.currentTimestamp());
						refreshService.insert(refresh);
						str = JSON.toJSONString(jsonString("100", "success"));
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
	
}

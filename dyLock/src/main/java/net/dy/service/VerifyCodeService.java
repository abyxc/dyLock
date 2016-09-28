package net.dy.service;

import net.dy.domain.VerifyCode;

/**
 * @author 作者 E-mail:elizhiyong@163.com 
 * @version 创建时间：2016年8月29日 下午1:54:49 
 * {@link http://www.chncode.net }
 */
public interface VerifyCodeService {

	
	public void insert(VerifyCode verifyCode);
	
	public VerifyCode  selectMobile(String mobile);
	
	public VerifyCode selectMobileCode(String mobile,String code);
	
	public void updateCode(String id);
}

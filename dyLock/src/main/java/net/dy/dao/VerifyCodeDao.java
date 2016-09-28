package net.dy.dao;

import net.dy.domain.VerifyCode;

/**
 * @author 作者 E-mail:elizhiyong@163.com 
 * @version 创建时间：2016年9月13日 上午10:28:23 
 * {@link http://www.chncode.net }
 */
public interface VerifyCodeDao {

	public void insert(VerifyCode verifyCode);
	
	public VerifyCode selectMobile(String mobile);
	
	public VerifyCode selectMobileCode(VerifyCode verifyCode);
	
	public void updateCode(String id);
}

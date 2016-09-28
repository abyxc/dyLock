package net.dy.service;

import javax.annotation.Resource;

import net.dy.dao.VerifyCodeDao;
import net.dy.domain.VerifyCode;

import org.springframework.stereotype.Service;

/**
 * @author 作者 E-mail:elizhiyong@163.com 
 * @version 创建时间：2016年8月29日 下午1:54:49 
 * {@link http://www.chncode.net }
 */
@Service
public class VerifyCodeServiceImpl implements 	VerifyCodeService{

	@Resource
	VerifyCodeDao verifyCodeDao;
	
	public void insert(VerifyCode verifyCode){
		verifyCodeDao.insert(verifyCode);
	}


	public VerifyCode selectMobile(String mobile) {
		return verifyCodeDao.selectMobile(mobile);
	}

	public VerifyCode selectMobileCode(String mobile,String code){
		VerifyCode verifyCode = new VerifyCode();
		verifyCode.setCode(code);
		verifyCode.setMobile(mobile);
		return verifyCodeDao.selectMobileCode(verifyCode);
	}

	public void updateCode(String id) {
		verifyCodeDao.updateCode(id);
	}

}

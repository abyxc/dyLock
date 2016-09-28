package net.dy.service;

import javax.annotation.Resource;

import net.dy.dao.CompanyDao;
import net.dy.domain.Company;

import org.springframework.stereotype.Service;

/**
 * @author 作者 E-mail:elizhiyong@163.com 
 * @version 创建时间：2016年9月18日 上午10:21:57 
 * {@link http://www.chncode.net }
 */
@Service
public class CompanyServiceImpl implements CompanyService {

	@Resource
	private CompanyDao companyDao;
	
	public Company selectId(String id) {
		return companyDao.selectId(id);
	}

}

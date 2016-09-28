package net.dy.service;

import javax.annotation.Resource;

import net.dy.dao.OpenLockDao;
import net.dy.domain.OpenLock;

import org.springframework.stereotype.Service;

/**
 * @author 作者 E-mail:elizhiyong@163.com 
 * @version 创建时间：2016年9月18日 上午10:21:57 
 * {@link http://www.chncode.net }
 */
@Service
public class OpenLockServiceImpl implements OpenLockService {

	@Resource
	private OpenLockDao openLockDao;

	public void insert(OpenLock openLock) {
		openLockDao.insert(openLock);
	}
	

}

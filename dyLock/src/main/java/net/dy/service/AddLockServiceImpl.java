package net.dy.service;

import javax.annotation.Resource;

import net.dy.dao.AddLockDao;
import net.dy.domain.AddLock;

import org.springframework.stereotype.Service;

/**
 * @author 作者 E-mail:elizhiyong@163.com 
 * @version 创建时间：2016年9月18日 上午10:21:57 
 * {@link http://www.chncode.net }
 */
@Service
public class AddLockServiceImpl implements AddLockService {

	@Resource
	private AddLockDao addLockDao;

	public void insert(AddLock addLock) {
		addLockDao.insert(addLock);
	}
	

}

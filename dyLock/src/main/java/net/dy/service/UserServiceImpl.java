package net.dy.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import net.dy.dao.UserDao;
import net.dy.domain.User;

/**
 * @author 作者 E-mail:elizhiyong@163.com 
 * @version 创建时间：2016年9月14日 上午9:14:18 
 * {@link http://www.chncode.net }
 */
@Service
public class UserServiceImpl implements UserService {

	@Resource
	UserDao userDao;
	
	public void insert(User user) {
		userDao.insert(user);
	}

	public User selectMobile(String mobile) {
		return userDao.selectMobile(mobile);
	}

	public void updateMobile(User user) {
		userDao.updateMobile(user);
	}


}

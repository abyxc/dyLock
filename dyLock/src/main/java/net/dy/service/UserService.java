package net.dy.service;

import net.dy.domain.User;


/**
 * @author 作者 E-mail:elizhiyong@163.com 
 * @version 创建时间：2016年8月29日 下午1:54:49 
 * {@link http://www.chncode.net }
 */
public interface UserService {

	
	public void insert(User user);
	
	public User selectMobile(String mobile);
	
	public void updateMobile(User user);
	
}

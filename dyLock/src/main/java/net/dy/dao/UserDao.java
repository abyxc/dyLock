package net.dy.dao;

import net.dy.domain.User;


/**
 * @author 作者 E-mail:elizhiyong@163.com 
 * @version 创建时间：2016年9月13日 上午10:28:23 
 * {@link http://www.chncode.net }
 */
public interface UserDao {
	
	public void insert(User user);
	
	public User selectMobile(String mobile);
	
	public void updateMobile(User user);
}

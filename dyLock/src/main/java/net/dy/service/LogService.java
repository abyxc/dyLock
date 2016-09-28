package net.dy.service;

import java.util.List;

import net.dy.domain.Log;

/**
 * @author 作者 E-mail:elizhiyong@163.com 
 * @version 创建时间：2016年8月29日 下午1:54:49 
 * {@link http://www.chncode.net }
 */
public interface LogService {

	public List<Log> select();
	
	public List<Log> selectList(Log log);
	
	public Log selectId(String id);
	
	public void updateId(Log log);
	
	public void deleteId(String id);
	
	public void insert(Log log);
}

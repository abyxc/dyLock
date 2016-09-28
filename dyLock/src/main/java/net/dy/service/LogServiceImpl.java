package net.dy.service;

import java.util.List;

import javax.annotation.Resource;

import net.dy.dao.LogDao;
import net.dy.domain.Log;

import org.springframework.stereotype.Service;

/**
 * @author 作者 E-mail:elizhiyong@163.com 
 * @version 创建时间：2016年8月29日 下午1:56:49 
 * {@link http://www.chncode.net }
 */
@Service
public class LogServiceImpl implements LogService {

	@Resource
	private LogDao logDao;
	
	
	public List<Log> selectList(Log log){
		List<Log> l = logDao.selectList(log);
		return l;
	}

	public Log selectId(String id) {
		return logDao.selectId(id);
	}
	
	public void updateId(Log log){
		logDao.updateId(log);
	}
	
	public void deleteId(String id){
		logDao.deleteId(id);
	}
	
	public List<Log> select(){
		return logDao.select();
	}
	
	public void insert(Log log){
		logDao.insert(log);
	}
}

package net.dy.service;

import javax.annotation.Resource;

import net.dy.dao.RefreshDao;
import net.dy.domain.Refresh;

import org.springframework.stereotype.Service;

/**
 * @author 作者 E-mail:elizhiyong@163.com 
 * @version 创建时间：2016年9月18日 上午10:21:57 
 * {@link http://www.chncode.net }
 */
@Service
public class RefreshServiceImpl implements RefreshService {

	@Resource
	private RefreshDao refreshDao;

	public void insert(Refresh readable) {
		refreshDao.insert(readable);		
	}
}

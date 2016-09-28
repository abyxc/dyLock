package com.mnsn.common;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
/**
 * 
 * @author ll
 * <h3>优雅的代码需要愉快的心情</h3>
 * 2015-1-17下午01:26:51
 * http://www.cnblogs.com/mvilplss/
 * @param <T>
 */
@Service
@Transactional
public class BaseService<T> implements BaseServiceIntf<T> {

	private Class<T> entryClass;

	@SuppressWarnings("unchecked")
	public BaseService() {
		// 使用反射技术得到T的真实类型
		Type type = this.getClass().getGenericSuperclass();
		if (type instanceof ParameterizedType) {
			ParameterizedType pt = (ParameterizedType) this.getClass()
					.getGenericSuperclass(); // 获取当前new的对象的 泛型的父类 类型
			this.entryClass = (Class<T>) pt.getActualTypeArguments()[0]; // 获取第一个类型参数的真实类型
		}
	}

	@Resource
	protected BaseDao<T> baseDao;

	public boolean save(T entity) {
		boolean result = true;
		try {
			baseDao.save(entity);
		} catch (Exception e) {
			e.printStackTrace();
			result = false;
		}
		return result;
	}

	public boolean delete(T entity) {
		boolean result = true;
		try {
			baseDao.delete(entity);
		} catch (Exception e) {
			e.printStackTrace();
			result = false;
		}
		return result;
	}

	public boolean update(T entity) {
		boolean result = true;
		try {
			baseDao.update(entity);
		} catch (Exception e) {
			e.printStackTrace();
			result = false;
		}
		return result;
	}

	public List<T> findAll() {
		List<T> list;
		try {
			list = baseDao.findAll(entryClass);
		} catch (Exception e) {
			e.printStackTrace();
			list = Collections.emptyList();
		}
		return list;
	}

	public T findEntity(Serializable id) {
		T t = null;
		try {
			t = baseDao.findEntity(entryClass, id);
		} catch (Exception e) {
			e.printStackTrace();
			return t;
		}
		return t;

	}

	public boolean saveOrUpdate(T entity) {
		boolean result = true;
		try {
			baseDao.saveOrUpdate(entity);
		} catch (Exception e) {
			e.printStackTrace();
			result = false;
		}
		return result;
	}

	public PageBean<T> findByHql(String hql, PageBean<T> pageBean) {
		try {
			pageBean = baseDao.findByHql(hql, pageBean.getCurrentPage(),
					pageBean.getPageSize());
			// 用于删除最后一行list的时候 跳转到上一页面
//			if (pageBean.getDataList().size() < 1 && pageBean.getPageCnt() > 0) {
//				pageBean.setCurrentPage((pageBean.getCurrentPage() - 1)+""); 
//				pageBean = findByHql(hql, pageBean);
//			}
		} catch (Exception e) {
			e.printStackTrace();
			pageBean = null;
		}
		return pageBean;
	}

	public boolean deleteList(Collection<T> entities) {
		boolean result = true;
		try {
			baseDao.getHibernateTemplate().deleteAll(entities);
		} catch (Exception e) {
			e.printStackTrace();
			result = false;
		}
		return result;
	}

	public PageBean<T> findBySql(String sql, String alias, PageBean<T> pageBean) {
		try {
			pageBean = baseDao.findBySql(sql,alias, pageBean.getCurrentPage(),
					pageBean.getPageSize(), entryClass);
		} catch (Exception e) {
			e.printStackTrace();
			pageBean = null;
		}
		return pageBean;
	}

	public List<T> findByConditions(String hql, Object... conditions){
		List<T> list ;
		try {
			list = baseDao.findByConditions(hql, conditions);
		} catch (Exception e) {
			e.printStackTrace();
			list = Collections.emptyList();
		}
		return list;
	}

	public List<T> findByIds(Long[] ids){
		List<T> list ;
		try {
			list = baseDao.findByIds(ids, entryClass);
		} catch (Exception e) {
			e.printStackTrace();
			list = Collections.emptyList();
		}
		return list;
	}

	public List<T> findByHql(String hql) throws Exception {
		List<T> list ;
		try {
			list = baseDao.findByHql(hql);
		} catch (Exception e) {
			e.printStackTrace();
			list = Collections.emptyList();
		}
		return list;
	}

	public List<Object[]> findBySql(String sql) {
		List<Object[]> list ;
		try {
			list = baseDao.findBySql(sql);
		} catch (Exception e) {
			e.printStackTrace();
			list = Collections.emptyList();
		}
		return list;
	}

	public int excuteSql(String sql) {
		int result = 0;
		try{
		result = baseDao.excuteSql(sql);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public int findCntBySql(String sql){
		int result=0;
		try{
			result=baseDao.findCntBySql(sql);
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	public List excuteProc(String procName, Object... args){
		List list = null;
		try {
			list = baseDao.excuteProc(procName, args);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(list==null){
			list = Collections.EMPTY_LIST;
		}
		return list;
	}
	public void saveBatch(Collection<T> entities){
		try {
			baseDao.saveBatch(entities);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}

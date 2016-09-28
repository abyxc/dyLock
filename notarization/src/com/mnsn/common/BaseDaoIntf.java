package com.mnsn.common;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
/**
 * 
 * @author ll
 * <h3>优雅的代码需要愉快的心情</h3>
 * 2015-1-17下午01:26:43
 * http://www.cnblogs.com/mvilplss/
 * @param <T>
 */
public interface BaseDaoIntf<T> {

	/**
	 * 保存一个对象
	 * 
	 * @param entity
	 */
	public void save(T entity) throws Exception;

	/**
	 * 删除一个对象
	 * 
	 * @param entity
	 */
	public void delete(T entity) throws Exception;

	/**
	 * 删除一个对象集合
	 * 
	 * @throws Exception
	 */
	public void deleteList(Collection<T> entities) throws Exception;

	/**
	 * 更新一个对象
	 * 
	 * @param entity
	 */
	public void update(T entity) throws Exception;

	/**
	 * 保存或更新一个对象
	 * 
	 * @throws Exception
	 */
	public void saveOrUpdate(T entity) throws Exception;

	/**
	 * 查找一个对象
	 * 
	 * @param clzss
	 * @param id
	 *            对象的主键id
	 * @throws Exception
	 */
	public T findEntity(Class<T> entryClass, Serializable id) throws Exception;

	/**
	 * 查询所有对象
	 * 
	 * @param clzss
	 */
	public List<T> findAll(Class<T> clzss) throws Exception;

	/**
	 * 根据HQL语句带分页查询
	 * 
	 * @param hql
	 * @param pageBean
	 * @return 被查询对象的集合
	 * @throws Exception
	 */
	public PageBean<T> findByHql(String hql, int crurrentPage, int pageSize)
			throws Exception;
	
	/**
	 * 根据HQL语句查询
	 * 
	 * @param hql
	 * @return 被查询对象的集合
	 * @throws Exception
	 */
	public List<T> findByHql(String hql)
			throws Exception;

	/**
	 * 根据SQL语句带分页查询
	 * 
	 * @param sql
	 * @param alias
	 *            要查询对象表的别名
	 * @param crurrentPage
	 * @param pageSize
	 * @param entryClass
	 *            要查询对象的类
	 * @return 被查询对象的集合
	 * @throws Exception
	 */
	public PageBean<T> findBySql(String sql, String alias, int crurrentPage,
			int pageSize, Class<T> entryClass) throws Exception;

	/**
	 * 根据条件查询
	 * 
	 * @param hql
	 *            From User where name = ? and ...
	 * @param conditions
	 * @return list集合
	 * @throws Exception
	 */
	public List<T> findByConditions(String hql, Object... conditions)
			throws Exception;
	
	/**
	 * 根据id数组来查询
	 * @param ids
	 * @return
	 * @throws Exception
	 */
	public List<T> findByIds(Long[] ids,Class<T> entryClass) throws Exception;
	
	/**
	 * 根据原生sql查询
	 * @param sql
	 * @return
	 */
	public List<Object[]> findBySql(String sql);
	
	/**
	 * 执行SQL
	 * @param sql
	 * @return 影响行数
	 */
	public int excuteSql(String sql);
	
	/**
	 * 根据SQL查询数量
	 * @param sql
	 * @return 数量
	 */
	public int findCntBySql(String sql);
	
	/**
	 * 执行存储过程
	 * @param procName
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List excuteProc(String procName,Object... args);
	
	/**
	 * 批量保存
	 */
	public<T> void saveBatch(Collection<T> entities);	
}

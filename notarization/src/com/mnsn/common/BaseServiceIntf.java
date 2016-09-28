package com.mnsn.common;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
/**
 * 
 * @author ll
 * <h3>优雅的代码需要愉快的心情</h3>
 * 2015-1-17下午01:27:01
 * http://www.cnblogs.com/mvilplss/
 * @param <T>
 */
public interface BaseServiceIntf<T> {

	/**
	 * 保存一个对象
	 * 
	 * @param entity
	 */
	public boolean save(T entity) throws Exception;

	/**
	 * 删除一个对象
	 * 
	 * @param entity
	 */
	public boolean delete(T entity) throws Exception;

	/**
	 * 删除一个对象集合
	 * 
	 * @throws Exception
	 */
	public boolean deleteList(Collection<T> entities) throws Exception;

	/**
	 * 更新一个对象
	 * 
	 * @param entity
	 */
	public boolean update(T entity) throws Exception;

	/**
	 * 保存或更新一个对象
	 * 
	 * @throws Exception
	 */
	public boolean saveOrUpdate(T entity) throws Exception;

	/**
	 * 查找一个对象
	 * 
	 * @param clzss
	 * @param id
	 *            对象的主键id
	 * @throws Exception
	 */
	public T findEntity(Serializable id) throws Exception;

	/**
	 * 查询所有对象
	 * 
	 */
	public List<T> findAll() throws Exception;

	/**
	 * 根据HQL语句带分页查询
	 * 
	 * @param hql
	 * @param pageBean
	 * @return 被查询对象的集合
	 * @throws Exception
	 */
	public PageBean<T> findByHql(final String hql, final PageBean<T> pageBean)
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
	 * 根据SQL语句带分页查询List<T>
	 * 
	 * @param sql
	 *            <p>sql格式必须为 ：select * from tb_user...</p> 
	 *            <p>连接多表查询T对象的时候sql格式：select {u.*} from tb_user u,tb_role r where ...</p> 
	 * @param alias 要查询对象表的别名 不能为null,无别名的时候可以为""
	 * @param crurrentPage
	 * @param pageSize
	 * @return 被查询对象的集合
	 * @throws Exception
	 */
	public PageBean<T> findBySql(String sql, String alias, PageBean<T> pageBean)
			throws Exception;
	
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
	public List<T> findByIds(Long[] ids) throws Exception;
	
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
	public int excuteSql(String sql) throws Exception;
	/**
	 * 执行存储过程
	 * @param procName
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List excuteProc(String procName,Object... args) throws Exception;
	
	public int findCntBySql(String sql) throws Exception;
	/**
	 * 批量保存
	 * @return 
	 */
	public void saveBatch(Collection<T> entities);	
	
}

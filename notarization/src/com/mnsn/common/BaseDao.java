package com.mnsn.common;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
/**
 * 
 * @author ll
 * <h3>优雅的代码需要愉快的心情</h3>
 * 2015-1-17下午01:26:31
 * http://www.cnblogs.com/mvilplss/
 * @param <T>
 */
@Repository
public class BaseDao<T> extends HibernateDaoSupport implements BaseDaoIntf<T> {
	@Resource(name="sessionFactory")
	public void setSessionFactory0(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}
	
	public void save(T entity) throws Exception {
		this.getHibernateTemplate().save(entity);
	}
	
	public void delete(T entity) throws Exception {
		this.getHibernateTemplate().delete(entity);
	}

	public void update(T entity) throws Exception {
		this.getHibernateTemplate().update(entity);
	}

	@SuppressWarnings("unchecked")
	public T findEntity(Class<T> entryClass, Serializable id) throws Exception {
		return (T) this.getHibernateTemplate().get(entryClass, id);
	}

	@SuppressWarnings("unchecked")
	public List<T> findAll(Class<T> clzss) throws Exception {
		return this.getHibernateTemplate().loadAll(clzss);
	}

	public void saveOrUpdate(T entity) throws Exception {
		this.getHibernateTemplate().saveOrUpdate(entity);
	}

	@SuppressWarnings("unchecked")
	public PageBean<T> findByHql(String hql, int crurrentPage , int pageSize) throws Exception {
		Session session = getSessionFactory().getCurrentSession();
		Query query = session.createQuery(hql);
		query.setFirstResult((crurrentPage-1)*pageSize);
		query.setMaxResults(pageSize);
		if(hql.indexOf("order")>0){
			hql = hql.substring(0, hql.indexOf("order"));
		}
		Query queryCnt = session.createQuery("select count(id) " + hql);
		Long recordCnt = (Long) queryCnt.uniqueResult();
		PageBean pageBean =  new PageBean(crurrentPage, pageSize, recordCnt.intValue() ,query.list());
		return pageBean;
	}

	public void deleteList(Collection<T> entities) throws Exception {
		this.getHibernateTemplate().deleteAll(entities);
	}
	
	@SuppressWarnings("unchecked")
	public PageBean<T> findBySql(String sql, String alias, int crurrentPage, int pageSize,
			Class<T> entryClass) throws Exception{
		Session session = getSessionFactory().getCurrentSession();
		SQLQuery query = session.createSQLQuery(sql);
		query.setFirstResult((crurrentPage-1)*pageSize);
		query.setMaxResults(pageSize);
		if(sql.indexOf(" order ")>0){
			sql = sql.substring(0, sql.indexOf(" order "));
		}
		Query queryCnt = session.createSQLQuery("select count("+alias+".id) "+sql.substring(sql.toLowerCase().indexOf("from"),sql.length()));
		Integer  recordCntBig = (Integer ) queryCnt.uniqueResult();
		Integer recordCnt = Integer.valueOf(""+recordCntBig);
		return new PageBean(crurrentPage, pageSize, recordCnt.intValue() ,query.list());
	}
	
	@SuppressWarnings("unchecked")
	public List<T> findByConditions(String hql,Object... conditions) throws Exception{
		Session session = getSessionFactory().getCurrentSession();
		Query query = session.createQuery(hql);
		for (int i = 0; i < conditions.length; i++) {
			query.setParameter(i, conditions[i]);
		}
		return query.list();
	}

	@SuppressWarnings("unchecked")
	public List<T> findByIds(Long[] ids,Class<T> entryClass) throws Exception {
		if(ids==null||ids.length==0){
			return Collections.emptyList();
		}else{
			Session session = getSessionFactory().getCurrentSession();
			return session.createQuery("From "+entryClass.getSimpleName()+" WHERE id IN(:ids)").setParameterList("ids", ids).list();
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> findBySql(String sql){
		Session session = getSessionFactory().getCurrentSession();
		SQLQuery sqlQuery = session.createSQLQuery(sql);
		List<Object[]> list = sqlQuery.list();
		return list;
	}

	@SuppressWarnings("unchecked")
	public List<T> findByHql(String hql) throws Exception {
		Session session = getSessionFactory().getCurrentSession();
		Query query = session.createQuery(hql);
		return query.list();
	}
	
	public int excuteSql(String sql){
		int cnt = 0;
		Session session = getSessionFactory().getCurrentSession();
		cnt = session.createSQLQuery(sql).executeUpdate();
		session.clear();
		return cnt;
	}
	
	public int findCntBySql(String sql){
		Integer cnt = 0;
		Session session = getSessionFactory().getCurrentSession();
		cnt = (Integer) session.createSQLQuery(sql).uniqueResult();
		return cnt;
	}
	
	@SuppressWarnings({ "unchecked", "deprecation" })
	public List excuteProc(String procName,Object...args){
		CallableStatement stmt = null; 
		List list=new ArrayList();
		try {
			stmt = getSessionFactory().getCurrentSession().connection().prepareCall(procName);
			if(args!=null){
				for (int i = 0; i < args.length; i++) {
					stmt.setObject(i+1, args[i]);
					System.out.println(args[i]);
				}
			}
			ResultSet rs = stmt.executeQuery();
			int size = rs.getMetaData().getColumnCount();
			while(rs.next()){
				for (int i = 1; i <= size; i++) {
					list.add(rs.getObject(i));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	public<T> void saveBatch(Collection<T> entities) {
		this.getHibernateTemplate().saveOrUpdateAll(entities);
	}	
	
}

package com.tinner.dao.impl;


import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.tinner.dao.UserDao;
import com.tinner.pojo.Users;

@Repository
public class UserDaoImpl implements UserDao{
	
	@Autowired
	private HibernateTemplate hibernateTemplate;

	@Override
	public void insertUsers(Users user) {
		// TODO Auto-generated method stub
		this.hibernateTemplate.save(user);
	}

	@Override
	public void updateUsers(Users user) {
		// TODO Auto-generated method stub
		this.hibernateTemplate.update(user);
	}

	@Override
	public void deleteUsers(Users user) {
		// TODO Auto-generated method stub
		this.hibernateTemplate.delete(user);
	}

	@Override
	public Users selectUserById(Integer id) {
		// TODO Auto-generated method stub
		return this.hibernateTemplate.get(Users.class, id);	
	}

	@Override
	public List<Users> selectUserByName(String userName) {
		//getCurrentSession:当前session必须要有事务边界，且只能处理唯一的一个事务。当事务提交或者回滚后session自动失效
		//openSession:每次都会打开一个新的session.假如每次使用多次。则获得的是不同session对象。使用完毕后我们需要手动的调用colse方法关闭session
		Session session = this.hibernateTemplate.getSessionFactory().getCurrentSession();
		Query query = session.createQuery("from Users where username = :name").setString("name", userName);
		return query.list();
	}

	@Override
	public List<Users> selectUserByNameUseSQL(String userName) {
		Session session = this.hibernateTemplate.getSessionFactory().getCurrentSession();
		Query query = session.createSQLQuery("select * from t_users where username = ?").addEntity(Users.class).setString(0,userName );
		// TODO Auto-generated method stub
		return query.list();
	}

	@Override
	public List<Users> selectUserByNameUseCriteria(String userName) {
		Session session = this.hibernateTemplate.getSessionFactory().getCurrentSession();
		//sql:select * from t_users where username = 张三
		Criteria criteria = session.createCriteria(Users.class);
		//eq即相等
		criteria.add(Restrictions.eq("username", userName));
		return criteria.list();
	}

}

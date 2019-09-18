package com.tinner.test;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.tinner.dao.UserDao;
import com.tinner.pojo.Users;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class UserDaoImplTest {
	
	@Autowired
	private UserDao userDao;
	
	/**
	 * 添加用户
	 */
	@Test
	@Transactional// 在测试类对于事务提交方式默认的是回滚。
	@Rollback(false)//取消自动回滚
	public void testInsertUsers() {
		Users user = new Users();
		user.setUserage(20);
		user.setUsername("张三");
		this.userDao.insertUsers(user);
	}
	
	/**
	 * 更新用户
	 */
	@Test
	@Transactional
	@Rollback(false)
	public void testUpdateUsers(){
		Users users = new Users();
		users.setUserid(2);
		users.setUserage(22);
		users.setUsername("李四");
		this.userDao.updateUsers(users);
	}
	
	/**
	 * 根据userid查询用户
	 */
	@Test
	public void testSelectUsersById(){
		Users users = this.userDao.selectUserById(2);
		System.out.println(users);
	}
	
	/**
	 * 删除用户
	 */
	@Test
	@Transactional
	@Rollback(false)
	public void testDeleteUsers(){
		Users users = new Users();
		users.setUserid(2);
		this.userDao.deleteUsers(users);
	}
	
	/**
	 * HQL测试
	 */
	@Test
	@Transactional
	public void testSelectUserByName(){
		List<Users> list = this.userDao.selectUserByName("张三");
		for (Users users : list) {
			System.out.println(users);
		}
	}
	
	/**
	 * SQL测试
	 */
	@Test
	@Transactional
	public void testSelectUserByNameUseSQL(){
		List<Users> list = this.userDao.selectUserByNameUseSQL("张三");
		for (Users users : list) {
			System.out.println(users);
		}
	}
	
	/**
	 * Criteria测试
	 */
	@Test
	@Transactional
	public void testSelectUserByNameUseCriteria(){
		List<Users> list = this.userDao.selectUserByNameUseCriteria("张三");
		for (Users users : list) {
			System.out.println(users);
		}
	}



}


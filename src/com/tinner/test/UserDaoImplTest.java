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
	 * ����û�
	 */
	@Test
	@Transactional// �ڲ�������������ύ��ʽĬ�ϵ��ǻع���
	@Rollback(false)//ȡ���Զ��ع�
	public void testInsertUsers() {
		Users user = new Users();
		user.setUserage(20);
		user.setUsername("����");
		this.userDao.insertUsers(user);
	}
	
	/**
	 * �����û�
	 */
	@Test
	@Transactional
	@Rollback(false)
	public void testUpdateUsers(){
		Users users = new Users();
		users.setUserid(2);
		users.setUserage(22);
		users.setUsername("����");
		this.userDao.updateUsers(users);
	}
	
	/**
	 * ����userid��ѯ�û�
	 */
	@Test
	public void testSelectUsersById(){
		Users users = this.userDao.selectUserById(2);
		System.out.println(users);
	}
	
	/**
	 * ɾ���û�
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
	 * HQL����
	 */
	@Test
	@Transactional
	public void testSelectUserByName(){
		List<Users> list = this.userDao.selectUserByName("����");
		for (Users users : list) {
			System.out.println(users);
		}
	}
	
	/**
	 * SQL����
	 */
	@Test
	@Transactional
	public void testSelectUserByNameUseSQL(){
		List<Users> list = this.userDao.selectUserByNameUseSQL("����");
		for (Users users : list) {
			System.out.println(users);
		}
	}
	
	/**
	 * Criteria����
	 */
	@Test
	@Transactional
	public void testSelectUserByNameUseCriteria(){
		List<Users> list = this.userDao.selectUserByNameUseCriteria("����");
		for (Users users : list) {
			System.out.println(users);
		}
	}



}


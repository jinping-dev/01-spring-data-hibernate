package com.tinner.dao;

import java.util.List;

import com.tinner.pojo.Users;

public interface UserDao {

	void insertUsers(Users user);
	
	void updateUsers(Users user);
	
	void deleteUsers(Users user);
	
	Users selectUserById(Integer id);
	
	List<Users> selectUserByName(String userName);
	
	List<Users> selectUserByNameUseSQL(String userName);
	
	 List<Users> selectUserByNameUseCriteria(String userName);
}

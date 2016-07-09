package org.joel.content.dao;

import org.apache.ibatis.annotations.Param;
import org.joel.content.entity.Person;

public interface PersonDao {
	/**
	 * 根据用户名和密码获取用户信息
	 * @param userName
	 * @param password
	 * @return
	 */
	public Person getPerson(@Param("userName") String userName, @Param("password") String password);
}

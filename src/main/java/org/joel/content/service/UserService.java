package org.joel.content.service;

import org.joel.content.entity.Person;

public interface UserService {
	/**
	 * 根据用户名和密码获取用户信息
	 * @param userName
	 * @param password
	 * @return
	 */
	Person getUser(String userName, String password);
}

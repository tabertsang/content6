package org.joel.content.service.impl;

import org.joel.content.dao.PersonDao;
import org.joel.content.entity.Person;
import org.joel.content.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserServiceImpl implements UserService {
	@Autowired
	private PersonDao personDao;

	@Override
	public Person getUser(String userName, String password) {
		return personDao.getPerson(userName, password);
	}

}

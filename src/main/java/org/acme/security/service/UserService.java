package org.acme.security.service;

import java.util.List;

import org.acme.security.model.User;

public interface UserService {
	User save(User user);

	List<User> findAll();

	User get(long id);

	void delete(long id);
}

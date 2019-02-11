package org.acme.security.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.acme.security.model.User;
import org.acme.security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service(value = "userService")
public class UserServiceImpl implements UserDetailsService, UserService {
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(final String userId) throws UsernameNotFoundException {
		final User user = userRepository.findByUsername(userId);
		if (user == null) {
			throw new UsernameNotFoundException("Invalid username or password.");
		}
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
																	  getAuthority());
	}

	private List<SimpleGrantedAuthority> getAuthority() {
		return Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"));
	}

	@Override
	public List<User> findAll() {
		final List<User> list = new ArrayList<>();
		userRepository.findAll().iterator().forEachRemaining(list::add);
		return list;
	}

	@Override
	public User get(final long id) {
		final Optional<User> user = userRepository.findById(id);
		if (user.isPresent()) {
			return user.get();
		}
		throw new RuntimeException("Unable to begin our journey, no user found.");
	}

	@Override
	public void delete(final long id) {
		userRepository.deleteById(id);
	}

	@Override
	public User save(final User user) {
		return userRepository.save(user);
	}
}

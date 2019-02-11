package org.acme.security.controller;

import java.util.List;

import org.acme.security.model.User;
import org.acme.security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping
	public List<User> listUsers() {
		return userService.findAll();
	}

	@GetMapping("/{id}")
	public User getUser(@PathVariable("id") final Long id) {
		return userService.get(id);
	}

	@PostMapping
	public User create(@RequestBody final User user) {
		return userService.save(user);
	}

	@DeleteMapping(value = "/{id}")
	public String delete(@PathVariable("id") final Long id) {
		userService.delete(id);
		return "success";
	}
}

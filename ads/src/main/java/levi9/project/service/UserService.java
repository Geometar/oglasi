package levi9.project.service;

import java.util.List;

import org.springframework.data.domain.Page;

import levi9.project.model.User;

public interface UserService {
	
	public List<User> findAll();

	public User findOne(Long id);

	public User save(User user);

	public List<User> save(List<User> user);

	public User delete(Long id);

	public List<User> delete(List<User> user);
	
	public User findByUsername(String username);

}

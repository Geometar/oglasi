package levi9.project.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import levi9.project.model.KateogorijeOglasa;
import levi9.project.model.User;
import levi9.project.repository.UserRepositoy;
import levi9.project.service.UserService;

@Service
@Transactional
public class JpaUserService implements UserService{

	@Autowired
	private UserRepositoy repository;
	
	@Override
	public List<User> findAll() {
		// TODO Auto-generated method stub
		return repository.findAll();
	}

	@Override
	public User findOne(Long id) {
		// TODO Auto-generated method stub
		return repository.findOne(id);
	}

	@Override
	public User save(User user) {
		// TODO Auto-generated method stub
		return repository.save(user);
	}

	@Override
	public List<User> save(List<User> user) {
		// TODO Auto-generated method stub
		return repository.save(user);
	}

	@Override
	public User delete(Long id) {
		User ret = repository.findOne(id);
		if(ret==null){
			return null;
		}
		repository.delete(id);
		return ret;
	}

	@Override
	public List<User> delete(List<User> users) {
		List<User> ret = new ArrayList<User>();
		for(User k : users){
			ret.add(delete(k.getId()));
		}
		return ret;

	}

	@Override
	public User findByUsername(String username) {
		
		User user = repository.findByUsername(username);
		if(user == null){
			return null;
		}
		return user;
	}
	
	

}

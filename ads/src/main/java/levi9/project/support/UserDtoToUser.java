package levi9.project.support;

import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import levi9.project.model.User;
import levi9.project.service.UserService;
import levi9.project.web.dto.UserDTO;

@Component
public class UserDtoToUser implements Converter<UserDTO, User> {

	@Autowired
	private UserService service;

	@Override
	public User convert(UserDTO dto) {

		User user = new User();
		if (dto.getId() != null) {
			user = service.findOne(dto.getId());
			if (user == null) {
				throw new IllegalStateException("Tried to " + "modify a non-existant user");
			}
		}
		
		user.setId(dto.getId());
		user.setEmail(dto.getEmail());
		user.setNumber(dto.getNumber());
		user.setRole(dto.getRole());
		user.setUsername(dto.getUsername());
		
		return user;

	}
	
	public List<User> convert(List<UserDTO> dtos){
		List<User> ret = new ArrayList<>();
		
		for(UserDTO d: dtos){
			ret.add(convert(d));
		}
		
		return ret;
	}
}

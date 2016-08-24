package levi9.project.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import levi9.project.model.User;
import levi9.project.service.UserService;
import levi9.project.web.dto.UserDTO;
import levi9.project.web.dto.UserRegistrationDTO;

@Component
public class UserRegistrationDTOToUser implements Converter<UserRegistrationDTO, User> {

	@Autowired
	private UserService userService;
	
	@Override
	public User convert(UserRegistrationDTO dto) {
		
		if(dto == null){
			return null;
		}
		
		User ret = new User();
		ret.setEmail(dto.getEmail());
		ret.setId(dto.getId());
		ret.setPassword(dto.getPassword());
		ret.setNumber(dto.getNumber());
		ret.setRole("USER");
		ret.setUsername(dto.getUsername());
		return ret;
	}
	
	public List<User> convert(List<UserRegistrationDTO> dtos){
		List<User> ret = new ArrayList<>();
		
		for(UserRegistrationDTO dto: dtos){
			ret.add(convert(dto));
		}
		
		return ret;
	}

}

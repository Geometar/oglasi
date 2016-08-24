package levi9.project.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import levi9.project.model.User;
import levi9.project.web.dto.UserDTO;

@Component
public class UserToUserDTO implements Converter<User, UserDTO> {

	@Override
	public UserDTO convert(User user) {
		
		if(user == null){
			return null;
		}
		
		UserDTO dto = new UserDTO();
		dto.setEmail(user.getEmail());
		dto.setId(user.getId());
		dto.setNumber(user.getNumber());
		dto.setRole(user.getRole());
		dto.setUsername(user.getUsername());
		return dto;
	}
	
	public List<UserDTO> convert(List<User> users){
		List<UserDTO> ret = new ArrayList<>();
		
		for(User u: users){
			ret.add(convert(u));
		}
		
		return ret;
	}

}

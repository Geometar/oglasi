package levi9.project.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import levi9.project.model.User;
import levi9.project.service.UserService;
import levi9.project.support.UserDtoToUser;
import levi9.project.support.UserRegistrationDTOToUser;
import levi9.project.support.UserToUserDTO;
import levi9.project.web.dto.UserDTO;
import levi9.project.web.dto.UserRegistrationDTO;


@RestController
@RequestMapping("/api/users")
public class UserRegistrationController {
	
	@Autowired
	private UserRegistrationDTOToUser toUser;
	
	@Autowired
	private UserToUserDTO toDTO;
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(method = RequestMethod.POST, consumes="application/json")
	public ResponseEntity<UserDTO> save(@RequestBody UserRegistrationDTO dto){
		
		if(dto.getId()!=null || dto.getPassword()==null || dto.getPasswordConfirm()== null || !dto.getPassword().equals(dto.getPasswordConfirm())){
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		if(!dto.getPassword().equals(dto.getPasswordConfirm())){
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		User saved = userService.save(toUser.convert(dto));
		
		return new ResponseEntity<>(toDTO.convert(saved), HttpStatus.CREATED);  
	}

	
}

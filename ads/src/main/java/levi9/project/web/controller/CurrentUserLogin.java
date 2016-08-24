package levi9.project.web.controller;


import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import levi9.project.model.User;
import levi9.project.service.UserService;
import levi9.project.support.UserToUserDTO;
import levi9.project.web.dto.UserDTO;

@RestController
@RequestMapping("api/currentuser")
public class CurrentUserLogin {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserToUserDTO toDTO;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<UserDTO> findOne(Principal principal){
		
		User user = userService.findByUsername(principal.getName());
		if(user== null){
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		
		return new ResponseEntity<>(toDTO.convert(user), HttpStatus.OK);
		
	}
}

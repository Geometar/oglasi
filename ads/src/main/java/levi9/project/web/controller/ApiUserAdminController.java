package levi9.project.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import levi9.project.model.MaliOglas;
import levi9.project.model.User;
import levi9.project.service.UserService;
import levi9.project.support.UserDtoToUser;
import levi9.project.support.UserToUserDTO;
import levi9.project.web.dto.MaliOglasiDTO;
import levi9.project.web.dto.UserDTO;

@RestController
@RequestMapping("api/admin/users")
public class ApiUserAdminController {
	
	@Autowired 
	private UserService userService;
	
	@Autowired
	private UserToUserDTO toDTO;
	
	@Autowired
	private UserDtoToUser toUser;
	
	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<UserDTO>> findAll(){
		
		List<User> users = userService.findAll();
		
		return new ResponseEntity<>(toDTO.convert(users), HttpStatus.OK);
		
	}
	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(method=RequestMethod.GET, value="/{id}")
	public ResponseEntity<UserDTO> findOne(@PathVariable Long id){
		
		User users = userService.findOne(id);
		
		if(users == null){
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		
		return new ResponseEntity<>(toDTO.convert(users), HttpStatus.OK);
		
	}
	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(method = RequestMethod.DELETE, value="/{id}")
	public ResponseEntity<UserDTO> delete(@PathVariable Long id){
		User user = userService.delete(id);
		if(user==null){
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		
		return new ResponseEntity<>(toDTO.convert(user), HttpStatus.OK);
		
	}
	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(method = RequestMethod.PUT, consumes = "application/json", value = "/{id}")
	public ResponseEntity<UserDTO> edit(@RequestBody UserDTO dto, @PathVariable Long id, @RequestParam(required=false, value="reset") String reset) {
		
		if (dto.getId() != id) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		if(reset.equals("true")){
			User ret = userService.findOne(dto.getId());
			ret.setPassword("default");
			User saved = userService.save(ret);
			return new ResponseEntity<>(toDTO.convert(saved), HttpStatus.OK);
		}
		
		User saved = userService.save(toUser.convert(dto));
		
		return new ResponseEntity<>(toDTO.convert(saved), HttpStatus.OK);

	}
	
	

}

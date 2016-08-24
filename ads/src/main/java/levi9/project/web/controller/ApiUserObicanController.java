package levi9.project.web.controller;

import java.security.Principal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.authentication.UserServiceBeanDefinitionParser;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import levi9.project.model.MaliOglas;
import levi9.project.model.User;
import levi9.project.service.MaliOglasService;
import levi9.project.service.UserService;
import levi9.project.support.MaliOglasToDto;
import levi9.project.support.MaliOglasiDtoToMOglasi;
import levi9.project.support.UserToUserDTO;
import levi9.project.web.dto.MaliOglasiDTO;
import levi9.project.web.dto.PromenaLozinkeDTO;
import levi9.project.web.dto.UserDTO;

@RestController
@RequestMapping("api/user")
public class ApiUserObicanController {

	@Autowired
	private MaliOglasService service;
	
	@Autowired
	private UserToUserDTO toUserDTO;

	@Autowired
	MaliOglasiDtoToMOglasi toOglas;

	@Autowired
	MaliOglasToDto toDTO;

	@Autowired
	private UserService userService;

	@RequestMapping(method = RequestMethod.GET, value = "/ads")
	public ResponseEntity<List<MaliOglasiDTO>> findAll(Principal princ) throws ParseException {

		User user = userService.findByUsername(princ.getName());

		List<MaliOglas> ret = service.findByUserUsername(user.getUsername());

		return new ResponseEntity<List<MaliOglasiDTO>>(toDTO.convert(ret), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.PUT, consumes = "application/json")
	public ResponseEntity<UserDTO> put(@RequestBody PromenaLozinkeDTO promenaLozinkeDTO, Principal princ ) {
		
		System.out.println(promenaLozinkeDTO.getStaraLozinka());
		System.out.println(promenaLozinkeDTO.getNovaLozinka());
		System.out.println(promenaLozinkeDTO.getPonovoLozinka());
		
		User user = userService.findByUsername(princ.getName());
		
		if(!user.getPassword().equals(promenaLozinkeDTO.getStaraLozinka()) || !promenaLozinkeDTO.getNovaLozinka().equals(promenaLozinkeDTO.getPonovoLozinka())){
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		user.setPassword(promenaLozinkeDTO.getNovaLozinka());
		
		User saved = userService.save(user);
		
		return new ResponseEntity<>(toUserDTO.convert(saved), HttpStatus.OK);
	}

}
package levi9.project.web.controller;

import static org.mockito.Mockito.RETURNS_DEEP_STUBS;

import java.security.Principal;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import levi9.project.model.KateogorijeOglasa;
import levi9.project.model.MaliOglas;
import levi9.project.model.User;
import levi9.project.service.KategorijeOglasaService;
import levi9.project.service.MaliOglasService;
import levi9.project.service.UserService;
import levi9.project.support.MaliOglasRegistrationDTOToMaliOglasi;
import levi9.project.support.MaliOglasToDto;
import levi9.project.support.MaliOglasiDtoToMOglasi;
import levi9.project.web.dto.MaliOglasRegistrationDTO;
import levi9.project.web.dto.MaliOglasiDTO;

@RestController
@RequestMapping("/api/ad")
public class ApiMaliOglasiController {
	
	@Autowired
	private MaliOglasService maliOglasService;
	
	@Autowired
	private MaliOglasiDtoToMOglasi toMaliOglasi;
	
	@Autowired
	private MaliOglasToDto toDto;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private KategorijeOglasaService kategorijeService;
	
	@Autowired
	private MaliOglasRegistrationDTOToMaliOglasi registrationToOglasi;
	
	@RequestMapping(method=RequestMethod.GET, value="/{id}")
	public ResponseEntity<MaliOglasiDTO> findOne(@PathVariable Long id){
		MaliOglas oglas = maliOglasService.findOne(id);
		if(oglas==null || oglas.getIstice().before(new Date())){
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		
		return new ResponseEntity<>(toDto.convert(oglas), HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.POST, consumes="application/json" )
	public ResponseEntity<MaliOglasiDTO> save(@RequestBody MaliOglasRegistrationDTO dto, Principal princ){
		
		if(dto.getIstice() == null || dto.getIstice().before(new Date()) || dto.getNaslov()==null|| dto.getTekst()==null){
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		User user = userService.findByUsername(princ.getName());
		KateogorijeOglasa kateogorijeOglasa = kategorijeService.findByNaziv(dto.getKategorija());
		
		if(user==null || kateogorijeOglasa==null){
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		MaliOglas maliOglas = registrationToOglasi.convert(dto);
		maliOglas.setUser(user);
		maliOglas.setKateogorijeOglasa(kateogorijeOglasa);
		MaliOglas saved = maliOglasService.save(maliOglas);
		
		return new ResponseEntity<>(toDto.convert(saved), HttpStatus.CREATED);
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value="/{id}")
	public ResponseEntity<MaliOglasiDTO> delete(@PathVariable Long id, Principal princ){
		
		User user = userService.findByUsername(princ.getName());
		MaliOglas maliOglass = maliOglasService.findOne(id);
		if(user.getRole().equals("USER") && user.getId()!=maliOglass.getUser().getId()){
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		}
		
		MaliOglas maliOglas = maliOglasService.delete(id);
		if(maliOglas==null){
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		
		return new ResponseEntity<>(toDto.convert(maliOglas), HttpStatus.OK);
		
	}
}

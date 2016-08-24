package levi9.project.web.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import levi9.project.model.KateogorijeOglasa;
import levi9.project.model.User;
import levi9.project.service.KategorijeOglasaService;
import levi9.project.service.UserService;
import levi9.project.support.KategorijeDtoToKategorijeOglasa;
import levi9.project.support.KategorijeOglasaToKategorijeOglasaDTO;
import levi9.project.web.dto.KategorijeOglasaDTO;

@RestController
@RequestMapping("/api/categories")
public class ApiKategorijeController {

	@Autowired
	private KategorijeOglasaService kategorijeService;
	@Autowired
	private KategorijeDtoToKategorijeOglasa toOglas;
	@Autowired
	private KategorijeOglasaToKategorijeOglasaDTO toDTO;
	@Autowired
	private UserService userService;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<KategorijeOglasaDTO>> findAll(
			@RequestParam(required = false, value = "page") Integer page,
			@RequestParam(required = false, value = "elemenata") Integer elemenata,
			@RequestParam(required = false, value = "orderBy") String orderBy,
			@RequestParam(required = false, value = "sortBy") String sortBy, Principal princ) {

		User user = userService.findByUsername(princ.getName());

		if (page == null)
			page = 0;
		if (elemenata == null)
			elemenata = 10;
		if (orderBy == null)
			orderBy = "true";
		if (sortBy == null)
			sortBy = "id";
		
		HttpHeaders httpHeaders = new HttpHeaders();
		
		httpHeaders.add("user-auth", user.getUsername());
		httpHeaders.add("user-role", user.getRole());

		Page<KateogorijeOglasa> findAll = kategorijeService.findAll(page, elemenata, orderBy, sortBy);
		List<KateogorijeOglasa> fAll = findAll.getContent();
		return new ResponseEntity<>(toDTO.convert(fAll),httpHeaders, HttpStatus.OK);
	}
}

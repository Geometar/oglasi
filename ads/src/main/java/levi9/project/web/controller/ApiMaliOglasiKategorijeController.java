package levi9.project.web.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import levi9.project.model.MaliOglas;
import levi9.project.service.MaliOglasService;
import levi9.project.support.MaliOglasToDto;
import levi9.project.support.MaliOglasiDtoToMOglasi;
import levi9.project.web.dto.MaliOglasiDTO;

@RestController
@RequestMapping("api/categories/{categoriesName}/ads")
public class ApiMaliOglasiKategorijeController {

	@Autowired
	private MaliOglasService service;

	@Autowired
	MaliOglasiDtoToMOglasi toOglas;

	@Autowired
	MaliOglasToDto toDTO;

	@RequestMapping(method = RequestMethod.GET)
	private ResponseEntity<List<MaliOglasiDTO>> findAll(@PathVariable() String categoriesName,
			@RequestParam(required = false, value = "page") Integer page,
			@RequestParam(required = false, value = "elemenata") Integer elemenata,
			@RequestParam(required = false, value = "orderBy") String orderBy,
			@RequestParam(required = false, value = "sortBy") String sortBy,
			@RequestParam(required = false, value = "istice") String istice,
			@RequestParam(required = false, value = "search") String search,
			@RequestParam(required = false, value = "kriterijum") String kriterijum) throws ParseException {

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date datumIsteka;
		if (page == null)
			page = 0;
		if (elemenata == null)
			elemenata = 15;
		if (orderBy == null)
			orderBy = "true";
		if (sortBy == null)
			sortBy = "id";
		System.out.println(istice);
		if (istice == null)
			datumIsteka = new Date();
		else {
			datumIsteka = format.parse(istice);
		}

		if (datumIsteka.before(new Date())) {
			datumIsteka = new Date();
		}
		Page<MaliOglas> ret = null;
		if (search != null) {
			if (kriterijum.equals("contains")) {
				ret = service.findByKategorijeNazivAndNaslovLikeOrUserUsernameLikeAndIsticeGreaterThan(categoriesName, search, page,
						elemenata, orderBy, sortBy, datumIsteka);
			} else if (kriterijum.equals("not")) {
				ret = service.findByKategorijeNazivAndNaslovLikeOrUserUsernameLikeAndIsticeGreaterThan(categoriesName, search, page,
						elemenata, orderBy, sortBy, datumIsteka);
			}
		}

		else {
			ret = service.findByKategorijeNaziv(categoriesName, page, elemenata, orderBy, sortBy, datumIsteka);
		}
		return new ResponseEntity<List<MaliOglasiDTO>>(toDTO.convert(ret.getContent()), HttpStatus.OK);
	}

}

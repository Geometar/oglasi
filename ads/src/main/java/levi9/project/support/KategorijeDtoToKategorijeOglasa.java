package levi9.project.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import levi9.project.model.KateogorijeOglasa;
import levi9.project.service.KategorijeOglasaService;
import levi9.project.web.controller.ApiKategorijeController;
import levi9.project.web.dto.KategorijeOglasaDTO;

@Component
public class KategorijeDtoToKategorijeOglasa implements Converter<KategorijeOglasaDTO, KateogorijeOglasa> {

	@Autowired
	private KategorijeOglasaService service;

	@Override
	public KateogorijeOglasa convert(KategorijeOglasaDTO dto) {
		KateogorijeOglasa ktOglasa = new KateogorijeOglasa();

		if (dto.getId() != null) {
			ktOglasa = service.findOne(dto.getId());
			if (ktOglasa == null) {
				throw new IllegalStateException("Tried to " + "modify a non-existant categorie");
			}
		}
		
		ktOglasa.setId(dto.getId());
		ktOglasa.setNaziv(dto.getNaziv());
		ktOglasa.setOpis(dto.getOpis());
		
		return ktOglasa;
	}
	
	public List<KateogorijeOglasa> convert(List<KategorijeOglasaDTO> dtos){
		List<KateogorijeOglasa> ret = new ArrayList<>();
		
		for(KategorijeOglasaDTO dto: dtos){
			ret.add(convert(dto));
		}
		
		return ret;
	}

}

package levi9.project.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import levi9.project.model.KateogorijeOglasa;
import levi9.project.web.dto.KategorijeOglasaDTO;

@Component
public class KategorijeOglasaToKategorijeOglasaDTO implements Converter<KateogorijeOglasa, KategorijeOglasaDTO> {

	@Override
	public KategorijeOglasaDTO convert(KateogorijeOglasa oglas) {

		if (oglas == null) {
			return null;
		}

		KategorijeOglasaDTO dto = new KategorijeOglasaDTO();

		dto.setId(oglas.getId());
		dto.setNaziv(oglas.getNaziv());
		dto.setOpis(oglas.getOpis());

		return dto;
	}

	public List<KategorijeOglasaDTO> convert(List<KateogorijeOglasa> oglasi) {
		List<KategorijeOglasaDTO> ret = new ArrayList<>();

		for (KateogorijeOglasa ko : oglasi) {
			ret.add(convert(ko));
		}

		return ret;
	}

}

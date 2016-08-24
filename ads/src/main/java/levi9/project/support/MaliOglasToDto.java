package levi9.project.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import levi9.project.model.MaliOglas;
import levi9.project.web.dto.MaliOglasiDTO;

@Component
public class MaliOglasToDto implements Converter<MaliOglas, MaliOglasiDTO> {

	@Autowired
	private KategorijeOglasaToKategorijeOglasaDTO toKategorijeToDTO;
	
	@Autowired
	private KategorijeDtoToKategorijeOglasa toKategorije;
	
	@Autowired
	private UserToUserDTO toUserDTO;
	
	@Autowired
	private UserDtoToUser toUser;
	
	@Override
	public MaliOglasiDTO convert(MaliOglas oglas) {
		if(oglas==null){
			return null;
		}
		
		MaliOglasiDTO ret = new MaliOglasiDTO();
		ret.setId(oglas.getId());
		ret.setIstice(oglas.getIstice());
		ret.setTekst(oglas.getTekst());
		ret.setNaslov(oglas.getNaslov());
		ret.setPostavljen(oglas.getPostavljen());
		ret.setUserDTO(toUserDTO.convert(oglas.getUser()));
		ret.setKategorijeOglasaDTO(toKategorijeToDTO.convert(oglas.getKateogorijeOglasa()));
		
		return ret;
	}
	
	public List<MaliOglasiDTO> convert(List<MaliOglas> oglasi){
		List<MaliOglasiDTO> ret = new ArrayList<>();
		
		for(MaliOglas m: oglasi){
			ret.add(convert(m));
		}
		
		return ret;
	}

}

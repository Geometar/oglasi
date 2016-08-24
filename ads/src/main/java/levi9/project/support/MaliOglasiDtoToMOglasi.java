package levi9.project.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import levi9.project.model.MaliOglas;
import levi9.project.service.MaliOglasService;
import levi9.project.web.dto.KategorijeOglasaDTO;
import levi9.project.web.dto.MaliOglasiDTO;

@Component
public class MaliOglasiDtoToMOglasi implements Converter<MaliOglasiDTO, MaliOglas>{

	@Autowired
	private MaliOglasService service;
	
	@Autowired
	private KategorijeOglasaToKategorijeOglasaDTO toKategorijeToDTO;
	
	@Autowired
	private KategorijeDtoToKategorijeOglasa toKategorije;
	
	@Override
	public MaliOglas convert(MaliOglasiDTO dto) {
		MaliOglas maliOglas = new MaliOglas();
		if(dto.getId()!=null){
			maliOglas = service.findOne(dto.getId());
			if(maliOglas==null){
				throw new IllegalStateException("Tried to " + "modify a non-existant ad");
			}
		}
		
		maliOglas.setId(dto.getId());
		maliOglas.setNaslov(dto.getNaslov());
		maliOglas.setPostavljen(dto.getPostavljen());
		maliOglas.setIstice(dto.getIstice());
		maliOglas.setTekst(dto.getTekst());
		
		return maliOglas;
	}
	
	public List<MaliOglas> convert(List<MaliOglasiDTO> dtos){
		List<MaliOglas> ret = new ArrayList<>();
		
		for(MaliOglasiDTO d: dtos){
			ret.add(convert(d));
		}
		return ret;
	}

}

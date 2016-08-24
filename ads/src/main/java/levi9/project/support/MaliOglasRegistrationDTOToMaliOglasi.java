package levi9.project.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import levi9.project.model.MaliOglas;
import levi9.project.model.User;
import levi9.project.web.dto.MaliOglasRegistrationDTO;
import levi9.project.web.dto.UserRegistrationDTO;

@Component
public class MaliOglasRegistrationDTOToMaliOglasi implements Converter<MaliOglasRegistrationDTO, MaliOglas> {

	@Override
	public MaliOglas convert(MaliOglasRegistrationDTO dto) {
		if(dto == null){
			return null;
		}
		
		MaliOglas ret = new MaliOglas();
		ret.setIstice(dto.getIstice());
		ret.setNaslov(dto.getNaslov());
		ret.setTekst(dto.getTekst());
		return ret;
	}
	
	public List<MaliOglas> convert(List<MaliOglasRegistrationDTO> dtos){
		List<MaliOglas> ret = new ArrayList<>();
		
		for(MaliOglasRegistrationDTO dto: dtos){
			ret.add(convert(dto));
		}
		
		return ret;
	}

}

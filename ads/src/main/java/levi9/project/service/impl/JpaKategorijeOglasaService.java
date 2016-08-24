package levi9.project.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import levi9.project.model.KateogorijeOglasa;
import levi9.project.repository.KategorijeOglasaRepository;
import levi9.project.service.KategorijeOglasaService;

@Service
@Transactional
public class JpaKategorijeOglasaService implements KategorijeOglasaService {

	@Autowired
	private KategorijeOglasaRepository repository;
	
	@Override
	public Page<KateogorijeOglasa> findAll(Integer page, Integer element, String orderBy, String sortBy) {
		if(orderBy.equals("true")){
			return repository.findAll(new PageRequest(page, element, Direction.ASC, sortBy));
		}
		else{
		return repository.findAll(new PageRequest(page, element, Direction.DESC, sortBy));
		}
	}

	@Override
	public KateogorijeOglasa findOne(Long id) {
		// TODO Auto-generated method stub
		return repository.findOne(id);
	}

	@Override
	public KateogorijeOglasa save(KateogorijeOglasa kateogorijeOglasa) {
		// TODO Auto-generated method stub
		return repository.save(kateogorijeOglasa);
	}

	@Override
	public List<KateogorijeOglasa> save(List<KateogorijeOglasa> kateogorijeOglasas) {
		// TODO Auto-generated method stub
		return repository.save(kateogorijeOglasas);
	}

	@Override
	public KateogorijeOglasa delete(Long id) {
		KateogorijeOglasa ret = repository.findOne(id);
		if(ret==null){
			return null;
		}
		repository.delete(id);
		return ret;
	}

	@Override
	public List<KateogorijeOglasa> delete(List<KateogorijeOglasa> kateogorijeOglasas) {
		List<KateogorijeOglasa> ret = new ArrayList<KateogorijeOglasa>();
		for(KateogorijeOglasa k : kateogorijeOglasas){
			ret.add(delete(k.getId()));
		}
		return ret;
	}

	@Override
	public KateogorijeOglasa findByNaziv(String naziv) {
		return repository.findByNaziv(naziv);
	}

}

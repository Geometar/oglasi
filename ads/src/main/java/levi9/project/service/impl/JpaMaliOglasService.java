package levi9.project.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import levi9.project.model.KateogorijeOglasa;
import levi9.project.model.MaliOglas;
import levi9.project.repository.MaliOglasiRepository;
import levi9.project.service.MaliOglasService;

@Service
@Transactional
public class JpaMaliOglasService implements MaliOglasService {

	@Autowired
	private MaliOglasiRepository repository;

	@Override
	public List<MaliOglas> findAll() {
		// TODO Auto-generated method stub
		return repository.findAll();
	}

	@Override
	public MaliOglas findOne(Long id) {
		// TODO Auto-generated method stub
		return repository.findOne(id);
	}

	@Override
	public MaliOglas save(MaliOglas kateogorijeOglasa) {
		// TODO Auto-generated method stub
		return repository.save(kateogorijeOglasa);
	}

	@Override
	public List<MaliOglas> save(List<MaliOglas> kateogorijeOglasas) {
		// TODO Auto-generated method stub
		return repository.save(kateogorijeOglasas);
	}

	@Override
	public MaliOglas delete(Long id) {
		MaliOglas ret = repository.findOne(id);
		if (ret == null) {
			return null;
		}
		repository.delete(id);
		return ret;
	}

	@Override
	public List<MaliOglas> delete(List<MaliOglas> oglasi) {
		List<MaliOglas> ret = new ArrayList<MaliOglas>();
		for (MaliOglas k : oglasi) {
			ret.add(delete(k.getId()));
		}
		return ret;
	}

	@Override
	public List<MaliOglas> findByKategorijeId(Long kategorijeId) {
		return repository.findByKategorijeId(kategorijeId);
	}

	@Override
	public Page<MaliOglas> findByKategorijeNaziv(String kategorijeNaziv, int page, int elemenata, String orderBy,
			String sortBy, Date istice) {
		if (orderBy.equals("false"))
			return repository.findByKategorijeNazivAndIsticeGreaterThan(
					new PageRequest(page, elemenata, Direction.DESC, sortBy), kategorijeNaziv, istice);
		else {
			return repository.findByKategorijeNazivAndIsticeGreaterThan(
					new PageRequest(page, elemenata, Direction.ASC, sortBy), kategorijeNaziv, istice);
		}
	}

	@Override
	public Page<MaliOglas> findByKategorijeNazivAndNaslovLikeOrUserUsernameLikeAndIsticeGreaterThan(
			String kategorijeNaziv, String search, int page, int elemenata, String orderBy, String sortBy, Date istice) {
		if (orderBy.equals("false"))
			return repository.findByKategorijeNazivAndIsticeGreaterThanAndNaslovLikeOrKategorijeNazivAndIsticeGreaterThanAndUserUsernameLike(
					new PageRequest(page, elemenata, Direction.DESC, sortBy), kategorijeNaziv,istice, "%"+search+"%", kategorijeNaziv,istice, "%"+search+"%");
		else {
			return repository.findByKategorijeNazivAndIsticeGreaterThanAndNaslovLikeOrKategorijeNazivAndIsticeGreaterThanAndUserUsernameLike(
					new PageRequest(page, elemenata, Direction.ASC, sortBy), kategorijeNaziv,istice, "%"+search+"%", kategorijeNaziv,istice, "%"+search+"%");
		}
	}

	@Override
	public List<MaliOglas> findByUserUsername(String username) {
			
		return repository.findByUserUsername(username);
	}



}

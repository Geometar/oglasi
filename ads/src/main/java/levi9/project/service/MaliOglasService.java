package levi9.project.service;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import levi9.project.model.MaliOglas;


public interface MaliOglasService {
	
	public List<MaliOglas> findAll();

	public MaliOglas findOne(Long id);

	public MaliOglas save(MaliOglas kateogorijeOglasa);

	public List<MaliOglas> save(List<MaliOglas> kateogorijeOglasas);

	public MaliOglas delete(Long id);

	public List<MaliOglas> delete(List<MaliOglas> kateogorijeOglasas);
	
	public List<MaliOglas> findByKategorijeId(Long kategorijeId);
	
	public Page<MaliOglas> findByKategorijeNaziv(String kategorijeNaziv, int page, int size, String sortOrder, String sortBy, Date istice );
	
	public Page<MaliOglas> findByKategorijeNazivAndNaslovLikeOrUserUsernameLikeAndIsticeGreaterThan(String kategorijeNaziv, String search, int page, int size, String sortOrder, String sortBy, Date istice );	
	
	public List<MaliOglas> findByUserUsername(String username);
	
}

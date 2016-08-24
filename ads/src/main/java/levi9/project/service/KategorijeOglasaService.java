package levi9.project.service;

import java.util.List;

import org.springframework.data.domain.Page;

import levi9.project.model.KateogorijeOglasa;

public interface KategorijeOglasaService {

	public Page<KateogorijeOglasa> findAll(Integer page, Integer element, String orderBy, String sortBy);

	public KateogorijeOglasa findOne(Long id);

	public KateogorijeOglasa save(KateogorijeOglasa kateogorijeOglasa);

	public List<KateogorijeOglasa> save(List<KateogorijeOglasa> kateogorijeOglasas);

	public KateogorijeOglasa delete(Long id);

	public List<KateogorijeOglasa> delete(List<KateogorijeOglasa> kateogorijeOglasas);
	
	public KateogorijeOglasa findByNaziv(String naziv);
}

package levi9.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import levi9.project.model.KateogorijeOglasa;

@Repository
public interface KategorijeOglasaRepository extends JpaRepository<KateogorijeOglasa, Long> {

	public KateogorijeOglasa findByNaziv(String naziv);
	
}

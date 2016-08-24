package levi9.project.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import levi9.project.model.MaliOglas;

@Repository
public interface MaliOglasiRepository extends JpaRepository<MaliOglas, Long> {

	public List<MaliOglas> findByKategorijeId(Long kategorijeId);

	public Page<MaliOglas> findByKategorijeNazivAndIsticeGreaterThan(Pageable page, String kategorijeNaziv, Date danas);

	public Page<MaliOglas> findByKategorijeNazivAndIsticeGreaterThanAndNaslovLikeOrKategorijeNazivAndIsticeGreaterThanAndUserUsernameLike(
			Pageable page, String kategorijeNaziv, Date danas, String naslov, String kategorijeNaziv2, Date danas2,
			String username);

	public List<MaliOglas> findByUserUsername(String username);

}

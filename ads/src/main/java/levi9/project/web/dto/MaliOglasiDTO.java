package levi9.project.web.dto;

import java.util.Date;

public class MaliOglasiDTO {

	private Long id;
	private String naslov;
	private String tekst;
	private Date postavljen;
	private Date istice;
	private UserDTO userDTO;
	private KategorijeOglasaDTO kategorijeOglasaDTO;

	public UserDTO getUserDTO() {
		return userDTO;
	}

	public void setUserDTO(UserDTO userDTO) {
		this.userDTO = userDTO;
	}

	public KategorijeOglasaDTO getKategorijeOglasaDTO() {
		return kategorijeOglasaDTO;
	}

	public void setKategorijeOglasaDTO(KategorijeOglasaDTO kategorijeOglasaDTO) {
		this.kategorijeOglasaDTO = kategorijeOglasaDTO;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNaslov() {
		return naslov;
	}

	public void setNaslov(String naslov) {
		this.naslov = naslov;
	}

	public Date getPostavljen() {
		return postavljen;
	}

	public void setPostavljen(Date postavljen) {
		this.postavljen = postavljen;
	}

	public Date getIstice() {
		return istice;
	}

	public void setIstice(Date istice) {
		this.istice = istice;
	}

	public String getTekst() {
		return tekst;
	}

	public void setTekst(String tekst) {
		this.tekst = tekst;
	}

}

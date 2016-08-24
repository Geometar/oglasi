package levi9.project.web.dto;

public class KategorijeOglasaDTO {

	private Long id;
	private String naziv;
	private String opis;

	public KategorijeOglasaDTO(String naziv, String opis) {
		super();
		this.naziv = naziv;
		this.opis = opis;
	}

	public KategorijeOglasaDTO() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public String getOpis() {
		return opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}

}

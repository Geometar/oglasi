package levi9.project.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;



@Entity
@Table(name="tbl_kategorije")
public class KateogorijeOglasa {
	
	@Id
	@GeneratedValue
	@Column
	private Long id;
	@Column
	private String naziv;
	@Column
	private String opis;
	@OneToMany(mappedBy="kategorije", cascade=CascadeType.REMOVE)
	private List<MaliOglas> oglasi = new ArrayList<MaliOglas>();
	
	public void addMaliOglas(MaliOglas maliOglasi){
		this.oglasi.add(maliOglasi);
		
		if(maliOglasi.getKateogorijeOglasa()!=this){
			maliOglasi.setKateogorijeOglasa(this);
		}
	}
	
	public void removeOglas(MaliOglas maliOglas){
		maliOglas.setKateogorijeOglasa(null);
		oglasi.remove(maliOglas);
	}
	
	@JsonIgnore
	public List<MaliOglas> getOglasi() {
		return oglasi;
	}

	public void setOglasi(List<MaliOglas> oglasi) {
		this.oglasi = oglasi;
	}

	public KateogorijeOglasa(String naziv, String opis) {
		super();
		this.naziv = naziv;
		this.opis = opis;
	}

	public KateogorijeOglasa() {
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

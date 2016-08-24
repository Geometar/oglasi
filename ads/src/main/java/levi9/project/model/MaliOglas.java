package levi9.project.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "tbl_maliOglasi")
public class MaliOglas {

	@Id
	@GeneratedValue
	@Column
	private Long id;
	@Column
	private String naslov;
	@Column
	private String tekst;
	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private Date postavljen;
	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private Date istice;
	@ManyToOne(fetch = FetchType.EAGER)
	private KateogorijeOglasa kategorije;
	@ManyToOne(fetch = FetchType.EAGER)
	private User user;

	@PrePersist
	public void preUpdate(){
		postavljen = new Date();
	}
	
	public MaliOglas() {
		super();
	}

	public MaliOglas(String naslov, Date postavljen, Date istice) {
		super();
		this.naslov = naslov;
		this.postavljen = postavljen;
		this.istice = istice;
	}

	public MaliOglas(String naslov, Date postavljen, Date istice, KateogorijeOglasa kateogorijeOglasa, User user) {
		super();
		this.naslov = naslov;
		this.postavljen = postavljen;
		this.istice = istice;
		this.kategorije = kateogorijeOglasa;
		this.user = user;
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

	public KateogorijeOglasa getKateogorijeOglasa() {
		return kategorije;
	}

	public void setKateogorijeOglasa(KateogorijeOglasa kateogorijeOglasa) {
		if (this.kategorije != null && !this.kategorije.equals(kateogorijeOglasa)) {
			this.kategorije.getOglasi().remove(this);
		}
		this.kategorije = kateogorijeOglasa;
		if (kateogorijeOglasa != null && !kateogorijeOglasa.getOglasi().contains(this)) {
			kateogorijeOglasa.addMaliOglas(this);
		}
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		if (this.user != null && !this.user.equals(user)) {
			this.user.getMaliOglas().remove(this);
		}
		this.user = user;
		if (user != null && !user.getMaliOglas().contains(this)) {
			user.getMaliOglas().add(this);
		}
	}

	public String getTekst() {
		return tekst;
	}

	public void setTekst(String tekst) {
		this.tekst = tekst;
	}

}

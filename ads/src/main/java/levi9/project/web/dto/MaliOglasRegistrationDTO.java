package levi9.project.web.dto;

import java.util.Date;

public class MaliOglasRegistrationDTO {
	
	private String kategorija;
	private String naslov;	
	private String tekst;
	private Date istice;
	
	public String getNaslov() {
		return naslov;
	}
	public void setNaslov(String naslov) {
		this.naslov = naslov;
	}
	public String getTekst() {
		return tekst;
	}
	public void setTekst(String tekst) {
		this.tekst = tekst;
	}
	public Date getIstice() {
		return istice;
	}
	public void setIstice(Date istice) {
		this.istice = istice;
	}
	public String getKategorija() {
		return kategorija;
	}
	public void setKategorija(String kategorija) {
		this.kategorija = kategorija;
	}
	
	

}

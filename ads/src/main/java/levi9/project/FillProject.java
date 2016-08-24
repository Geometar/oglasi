package levi9.project;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import levi9.project.model.KateogorijeOglasa;
import levi9.project.model.MaliOglas;
import levi9.project.model.User;
import levi9.project.service.KategorijeOglasaService;
import levi9.project.service.MaliOglasService;
import levi9.project.service.UserService;

@Component
public class FillProject {

	@Autowired
	private KategorijeOglasaService kategorijeService;

	@Autowired
	private UserService userService;

	@Autowired
	private MaliOglasService oglasiService;

	@PostConstruct
	public void init() throws ParseException {
		
		User user = new User();
		user.setEmail("user@gmail.com");
		user.setNumber("064279667");
		user.setPassword("pass");
		user.setRole("ADMIN");
		user.setUsername("User");
		userService.save(user);
		
		KateogorijeOglasa kateogorijeOglasa = new KateogorijeOglasa();
		kateogorijeOglasa.setNaziv("Polovni Automobili ");
		kateogorijeOglasa.setOpis("Oglasi za polovne automobile");
		kategorijeService.save(kateogorijeOglasa);
		SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
		for (int i = 0; i < 8; i++) {
			
			MaliOglas mali = new MaliOglas();
			mali.setIstice(format.parse("2017/" + (i+1) + "/01"));
			mali.setNaslov("Naslov za automobil " + i);
			mali.setTekst("Tekst o prodaji Automobila audi a" + i);
			
			mali.setKateogorijeOglasa(kateogorijeOglasa);
			mali.setUser(user);
			
			user.addMaliOglas(mali);
			kateogorijeOglasa.addMaliOglas(mali);
			
			oglasiService.save(mali);
		}
		MaliOglas malOglasi = new MaliOglas();
		malOglasi.setIstice(format.parse("2016/7/01"));
		malOglasi.setNaslov("Naslov za automobil istekao, NE PRIKAZUJ");
		
		malOglasi.setKateogorijeOglasa(kateogorijeOglasa);
		malOglasi.setUser(user);
		
		user.addMaliOglas(malOglasi);
		kateogorijeOglasa.addMaliOglas(malOglasi);
		
		oglasiService.save(malOglasi);
		
		User user1 = new User();
		user1.setEmail("ana@gmail.com");
		user1.setNumber("066446644");
		user1.setPassword("pass1");
		user1.setRole("USER");
		user1.setUsername("ana");
		userService.save(user1);
		
		MaliOglas malOglasi2 = new MaliOglas();
		malOglasi2.setIstice(format.parse("2018/7/03"));
		malOglasi2.setNaslov("Naslov za automobil");
		malOglasi2.setTekst("Ubacen tekst zbog datuma.");
		
		malOglasi2.setKateogorijeOglasa(kateogorijeOglasa);
		malOglasi2.setUser(user1);
		
		user1.addMaliOglas(malOglasi);
		kateogorijeOglasa.addMaliOglas(malOglasi);
		
		oglasiService.save(malOglasi2);
		
		KateogorijeOglasa kateogorijeOglasa1 = new KateogorijeOglasa();
		kateogorijeOglasa1.setNaziv("Knjige");
		kateogorijeOglasa1.setOpis("Oglasi za knjige i literaturu");
		kategorijeService.save(kateogorijeOglasa1);
		for (int i = 0; i < 8; i++) {
			
			MaliOglas mali = new MaliOglas();
			mali.setIstice(format.parse("2017/" + (i+1) + "/01"));
			mali.setNaslov("Naslov za knjigu " + i);
			mali.setTekst("Tekst na oglas o prodaji knjiga broj " + i);
			
			mali.setKateogorijeOglasa(kateogorijeOglasa1);
			mali.setUser(user1);
			
			user1.addMaliOglas(mali);
			kateogorijeOglasa1.addMaliOglas(mali);
			
			oglasiService.save(mali);
		}
		
		User user2 = new User();
		user2.setEmail("test@gmail.com");
		user2.setNumber("066446644");
		user2.setPassword("pass2");
		user2.setRole("USER");
		user2.setUsername("test");
		userService.save(user2);
		
		KateogorijeOglasa kateogorijeOglasa2 = new KateogorijeOglasa();
		kateogorijeOglasa2.setNaziv("Posao");
		kateogorijeOglasa2.setOpis("Oglasi za posao i strucnu praksu");
		kategorijeService.save(kateogorijeOglasa2);
		for (int i = 0; i < 8; i++) {
			
			MaliOglas mali = new MaliOglas();
			mali.setIstice(format.parse("2017/" + (i+1) + "/01"));
			mali.setNaslov("Naslov za Posao " + i);
			mali.setTekst("Tekst u kategoriji za pronalazenju posla " + i);
			
			mali.setKateogorijeOglasa(kateogorijeOglasa2);
			mali.setUser(user1);
			
			user2.addMaliOglas(mali);
			kateogorijeOglasa2.addMaliOglas(mali);
			
			oglasiService.save(mali);
		}
	}

}

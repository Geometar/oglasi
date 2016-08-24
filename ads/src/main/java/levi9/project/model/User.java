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
@Table(name="tbl_user")
public class User {
	
	@Id
	@GeneratedValue
	@Column
	private Long id;
	@Column
	private String username;
	@Column
	private String password;
	@Column
	private String email;
	@Column
	private String number;
	@Column
	private String role;

	@OneToMany(mappedBy="user", cascade=CascadeType.REMOVE)
	private List<MaliOglas> maliOglasi = new ArrayList<>();
	
	public void addMaliOglas(MaliOglas maliOglas){
		this.maliOglasi.add(maliOglas);
		
		if(maliOglas.getUser()!=this){
			maliOglas.setUser(this);
		}
	}
	
	@JsonIgnore
	public List<MaliOglas> getMaliOglas() {
		return maliOglasi;
	}
	public void setMaliOglas(List<MaliOglas> maliOglas) {
		this.maliOglasi = maliOglas;
	}
	public User() {
		super();
	}
	public User(String username, String password, String email, String number, String role) {
		super();
		this.username = username;
		this.password = password;
		this.email = email;
		this.number = number;
		this.role = role;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	
	

}

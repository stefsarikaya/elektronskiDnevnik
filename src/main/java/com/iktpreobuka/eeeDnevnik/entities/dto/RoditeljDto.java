package com.iktpreobuka.eeeDnevnik.entities.dto;

import java.util.List;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonView;
import com.iktpreobuka.eeeDnevnik.security.Views;

public class RoditeljDto {
	
	@JsonView(Views.nastavnik.class)
	private String ime;
	
	@JsonView(Views.nastavnik.class)

	private String prezime;
	
	@JsonView(Views.admin.class)
	private String jmbg;
	
	@JsonView(Views.admin.class)
	@Pattern(regexp = "^(muski|zenski)$", message = "Pol nije ispravan")
	private String pol;
	
	@JsonView(Views.nastavnik.class)
	@Size(max = 50, message = "Email mora biti maksimim {max} karaktera dužine.")
	private String email;

	@JsonView(Views.admin.class)
	private List<String> ucenici;

	@JsonView(Views.admin.class)
	@Size(min = 5, max = 20, message = "Username mora biti između {min} i {max} karaktera dužine.")
	private String username;
	
	@JsonView(Views.admin.class)
	@Pattern(regexp = "^(admin|nastavnik|roditelj|ucenik)$", message = "Uloga nije ispravna.")
	private String uloga;
	
	@Size(min = 5, message = "Password mora biti {min} karaktera dužine ili veći.")
	private String password;


	/**
	 * 
	 */
	public RoditeljDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * @param ime
	 * @param prezime
	 * @param jmbg
	 * @param pol
	 * @param email
	 * @param ucenici
	 * @param username
	 * @param uloga
	 * @param password
	 */
	public RoditeljDto(String ime, String prezime, String jmbg,
			@Pattern(regexp = "^(muski|zenski)$", message = "Pol nije ispravan") String pol,
			@Size(max = 50, message = "Email mora biti maksimim {max} karaktera dužine.") String email,
			List<String> ucenici,
			@Size(min = 5, max = 20, message = "Username mora biti između {min} i {max} karaktera dužine.") String username,
			@Pattern(regexp = "^(admin|nastavnik|roditelj|ucenik)$", message = "Uloga nije ispravna.") String uloga,
			@Size(min = 5, message = "Password mora biti {min} karaktera dužine ili veći.") String password) {
		super();
		this.ime = ime;
		this.prezime = prezime;
		this.jmbg = jmbg;
		this.pol = pol;
		this.email = email;
		this.ucenici = ucenici;
		this.username = username;
		this.uloga = uloga;
		this.password = password;
	}






	public String getIme() {
		return ime;
	}



	public void setIme(String ime) {
		this.ime = ime;
	}



	public String getPrezime() {
		return prezime;
	}



	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}



	public String getJmbg() {
		return jmbg;
	}



	public void setJmbg(String jmbg) {
		this.jmbg = jmbg;
	}



	public String getPol() {
		return pol;
	}



	public void setPol(String pol) {
		this.pol = pol;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public List<String> getUcenici() {
		return ucenici;
	}



	public void setUcenici(List<String> ucenici) {
		this.ucenici = ucenici;
	}



	public String getUsername() {
		return username;
	}



	public void setUsername(String username) {
		this.username = username;
	}



	public String getUloga() {
		return uloga;
	}



	public void setUloga(String uloga) {
		this.uloga = uloga;
	}



	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}


}

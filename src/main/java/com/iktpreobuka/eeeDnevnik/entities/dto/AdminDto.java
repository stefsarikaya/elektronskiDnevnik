package com.iktpreobuka.eeeDnevnik.entities.dto;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonView;
import com.iktpreobuka.eeeDnevnik.security.Views;

public class AdminDto {
	
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
	private String mobilniTelefon ;
	
	@JsonView(Views.ucenik.class)
	@Size(max = 50, message = "E-mail must be maximum {max} characters long.")

	private String email;

	@JsonView(Views.admin.class)
	@Size(min = 5, max = 20, message = "Username mora biti između {min} i {max} karaktera dužine.")
	private String username;
	
	@JsonView(Views.admin.class)
	@Pattern(regexp = "^(admin|nastavnik|roditelj|ucenik)$", message = "Uloga nije ispravno uneta.")
	private String uloga;
	
	@Size(min = 5, message = "Password mora biti {min} karaktera dužine ili više.")

	private String password;

	public AdminDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param ime
	 * @param prezime
	 * @param jmbg
	 * @param pol
	 * @param mobilniTelefon
	 * @param email
	 * @param username
	 * @param uloga
	 * @param password
	 */
	public AdminDto(String ime, String prezime, String jmbg,
			@Pattern(regexp = "^(muski|zenski)$", message = "Pol nije ispravan") String pol, String mobilniTelefon,
			@Size(max = 50, message = "E-mail must be maximum {max} characters long.") String email,
			@Size(min = 5, max = 20, message = "Username mora biti između {min} i {max} karaktera dužine.") String username,
			@Pattern(regexp = "^(admin|nastavnik|roditelj|ucenik)$", message = "Uloga nije ispravno uneta.") String uloga,
			@Size(min = 5, message = "Password mora biti {min} karaktera dužine ili više.") String password) {
		super();
		this.ime = ime;
		this.prezime = prezime;
		this.jmbg = jmbg;
		this.pol = pol;
		this.mobilniTelefon = mobilniTelefon;
		this.email = email;
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

	public String getMobilniTelefon() {
		return mobilniTelefon;
	}

	public void setMobilniTelefon(String mobilniTelefon) {
		this.mobilniTelefon = mobilniTelefon;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

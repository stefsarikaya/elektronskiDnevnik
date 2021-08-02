package com.iktpreobuka.eeeDnevnik.entities.dto;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonView;
import com.iktpreobuka.eeeDnevnik.security.Views;


public class KorisnikNalogDto {
	
	@JsonView(Views.admin.class)
	@Size(min=5, max=20, message = "Username mora biti izmedju {min} i {max} karaktera duzine.")
	private String username;
	
	@JsonView(Views.admin.class)
	@Pattern(regexp="^(admin|nastavnik|roditelj|ucenik)$",message="Uloga nije dobro uneta")
	private String uloga;
	
	@Size(min=5, message = "Password must be {min} characters long or higher.")
	private String password;
	

	private String korisnikId;


	/**
	 * 
	 */
	public KorisnikNalogDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	/**
	 * @param username
	 * @param uloga
	 * @param password
	 * @param korisnikId
	 */
	public KorisnikNalogDto(
			@Size(min = 5, max = 20, message = "Username mora biti izmedju {min} i {max} karaktera duzine.") String username,
			@Pattern(regexp = "^(admin|nastavnik|roditelj|ucenik)$", message = "Uloga nije dobro uneta") String uloga,
			@Size(min = 5, message = "Password must be {min} characters long or higher.") String password,
			String korisnikId) {
		super();
		this.username = username;
		this.uloga = uloga;
		this.password = password;
		this.korisnikId = korisnikId;
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


	public String getKorisnikId() {
		return korisnikId;
	}


	public void setKorisnikId(String korisnikId) {
		this.korisnikId = korisnikId;
	}
	
}

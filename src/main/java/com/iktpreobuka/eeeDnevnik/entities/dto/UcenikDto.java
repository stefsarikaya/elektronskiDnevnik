package com.iktpreobuka.eeeDnevnik.entities.dto;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonView;
import com.iktpreobuka.eeeDnevnik.enumeration.ERazred;
import com.iktpreobuka.eeeDnevnik.security.Views;

public class UcenikDto {
	
	@JsonView(Views.nastavnik.class)
	private String ime;
	
	@JsonView(Views.nastavnik.class)
	private String prezime;
	
	@JsonView(Views.admin.class)
	private String jmbg;
	
	@JsonView(Views.admin.class)
	private String pol;

	
	@JsonView(Views.admin.class)
	@Size(min = 5, max = 20, message = "Username mora biti između {min} i {max} karaktera dužine.")
	private String username;
	
	@JsonView(Views.admin.class)
	@Pattern(regexp = "^(admin|nastavnik|roditelj|ucenik)$", message = "Uloga nije ispravno uneta.")
	private String uloga;
	
	@Size(min = 5, message = "Password mora biti {min} karaktera dužine ili više.")
	private String password;
	
	@JsonView(Views.roditelj.class)
	private String skolskiIdentifikacioniBroj;
	
	
	@JsonView(Views.ucenik.class)
	private ERazred eRazred;
	
	@JsonView(Views.ucenik.class)
	@Pattern(regexp = "^([A-Za-z]{1,1})$", message="Odeljenje ucenika nije lepo uneti, može biti samo jedno slovo.")
	private String ucenik_odeljenje;
	
	@JsonView(Views.roditelj.class)
	private String datumUpisa;

	
	/**
	 * 
	 */
	public UcenikDto() {
		super();
		// TODO Auto-generated constructor stub
	}


	/**
	 * @param ime
	 * @param prezime
	 * @param jmbg
	 * @param pol
	 * @param username
	 * @param uloga
	 * @param password
	 * @param skolskiIdentifikacioniBroj
	 * @param eRazred
	 * @param ucenik_odeljenje
	 * @param datumUpisa
	 */
	public UcenikDto(String ime, String prezime, String jmbg, String pol,
			@Size(min = 5, max = 20, message = "Username mora biti između {min} i {max} karaktera dužine.") String username,
			@Pattern(regexp = "^(admin|nastavnik|roditelj|ucenik)$", message = "Uloga nije ispravno uneta.") String uloga,
			@Size(min = 5, message = "Password mora biti {min} karaktera dužine ili više.") String password,
			String skolskiIdentifikacioniBroj, ERazred eRazred,
			@Pattern(regexp = "^([A-Za-z]{1,1})$", message = "Odeljenje ucenika nije lepo uneti, može biti samo jedno slovo.") String ucenik_odeljenje,
			String datumUpisa) {
		super();
		this.ime = ime;
		this.prezime = prezime;
		this.jmbg = jmbg;
		this.pol = pol;
		this.username = username;
		this.uloga = uloga;
		this.password = password;
		this.skolskiIdentifikacioniBroj = skolskiIdentifikacioniBroj;
		this.eRazred = eRazred;
		this.ucenik_odeljenje = ucenik_odeljenje;
		this.datumUpisa = datumUpisa;
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

	public String getSkolskiIdentifikacioniBroj() {
		return skolskiIdentifikacioniBroj;
	}

	public void setSkolskiIdentifikacioniBroj(String skolskiIdentifikacioniBroj) {
		this.skolskiIdentifikacioniBroj = skolskiIdentifikacioniBroj;
	}

	public ERazred geteRazred() {
		return eRazred;
	}

	public void seteRazred(ERazred eRazred) {
		this.eRazred = eRazred;
	}

	public String getUcenik_odeljenje() {
		return ucenik_odeljenje;
	}

	public void setUcenik_odeljenje(String ucenik_odeljenje) {
		this.ucenik_odeljenje = ucenik_odeljenje;
	}

	public String getDatumUpisa() {
		return datumUpisa;
	}

	public void setDatumUpisa(String datumUpisa) {
		this.datumUpisa = datumUpisa;
	}
	
}

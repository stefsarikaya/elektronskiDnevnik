package com.iktpreobuka.eeeDnevnik.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import com.iktpreobuka.eeeDnevnik.enumeration.EUloga;
import com.iktpreobuka.eeeDnevnik.security.Views;

@Entity
@Table (name = "korisnicki_nalog")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class KorisnikNalogEntity {
	
	@Column(name="uloga")
	@JsonView(Views.ucenik.class)
	@Enumerated(EnumType.STRING)
	@NotNull (message = "Morate uneti ulogu")
	private EUloga uloga;
	
	
	@Column(name="username", unique=true, length=50)
	@JsonView(Views.ucenik.class)
	@NotNull (message = "Username mora biti unet")
	@Size(min=5, max=20, message = "Username mora biti dužine između {min} i {max} karaktera.")
	private String username;
	

	@Column(name="password")
	@NotNull (message = "Password mora bit unet")
	//@Pattern(regexp = "^[A-Za-z0-9]*$", message="Password is not valid, must contin only letters and numbers.")
	//@Size(min=5, message = "Password must be {min} characters long or higher.")
	private String password;
	
	@JsonView(Views.ucenik.class)
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "korisnik")
	@NotNull (message = "Korisnik mora biti unet")
	private KorisnikEntity korisnik;
	
	
	@JsonView(Views.ucenik.class)
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="nalog_id")
	private Integer id;
	
	@JsonView(Views.admin.class)
	@Max(1)
    @Min(-1)
    @Column(name = "status", nullable = false)
	private Integer status;
	
    
	@JsonIgnore
	@Version
	private Integer verzija;
	
	private static final Integer NEAKTIVAN = 0;
	private static final Integer AKTIVAN = 1;
	private static final Integer ARHIVIRAN = -1;
	
	public KorisnikNalogEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	// Nisi stavio da se unosi ID u kosntruktor
	public KorisnikNalogEntity(@NotNull(message = "Korisnik mora biti unet") KorisnikEntity korisnik,
			@NotNull(message = "Morate uneti ulogu") EUloga uloga,
			@NotNull(message = "Username mora biti unet") @Size(min = 5, max = 20, message = "Username mora biti dužine između {min} i {max} karaktera.") String username,
			@NotNull(message = "Password mora bit unet") String password) {
		super();
		this.korisnik = korisnik;
		this.uloga = uloga;
		this.username = username;
		this.password = password;
	}

	public KorisnikEntity getKorisnik() {
		return korisnik;
	}

	public void setKorisnik(KorisnikEntity korisnik) {
		this.korisnik = korisnik;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public EUloga getUloga() {
		return uloga;
	}

	public void setUloga(EUloga uloga) {
		this.uloga = uloga;
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

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getVerzija() {
		return verzija;
	}

	public void setVerzija(Integer verzija) {
		this.verzija = verzija;
	}

	public static Integer getNeaktivan() {
		return NEAKTIVAN;
	}

	public static Integer getAktivan() {
		return AKTIVAN;
	}

	public static Integer getArhiviran() {
		return ARHIVIRAN;
	}
	
	public void setNeaktivan() {
		this.status = getNeaktivan();
	}

	public void setAktivan() {
		this.status = getAktivan();
	}

	public void setArhiviran() {
		this.status = getArhiviran();
	}

}

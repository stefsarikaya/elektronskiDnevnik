package com.iktpreobuka.eeeDnevnik.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.annotations.DiscriminatorOptions;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import com.iktpreobuka.eeeDnevnik.enumeration.EPol;
import com.iktpreobuka.eeeDnevnik.enumeration.EUloga;
import com.iktpreobuka.eeeDnevnik.security.Views;

@Entity
@Table(name = "korisnik")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorOptions(force = true)
public class KorisnikEntity {
	
	@JsonView(Views.ucenik.class)
	@Column(name = "ime")
	@NotNull(message = "Morate uneti ime")
	protected String ime;
	
	@JsonView(Views.ucenik.class)
	@Column(name = "prezime")
	@NotNull(message = "Morate uneti prezime")
	protected String prezime;
	
	@JsonView(Views.ucenik.class)
	@Column(name = "jmbg", unique = true, length = 13, nullable = false)
	@Size(min=13, max=13, message = "Jmbg mora biti {min} karaktera dužine")
	@NotNull(message = "Morate uneti jmbg.")
	protected String jmbg;
	
	@JsonView(Views.ucenik.class)
	@Column(name = "pol")
	@Enumerated(EnumType.STRING)
	@NotNull(message = "Morate uneti pol")
	protected EPol pol;
	
	@JsonView(Views.admin.class)
	@Column(name = "uloga", nullable = false)
	@Enumerated(EnumType.STRING)
	@NotNull(message = "User role must be provided.")
	protected EUloga uloga;
	
	@JsonIgnore
	@Version
	protected Integer verzija;
	
	@JsonView(Views.admin.class)
	@JsonIgnore
	@OneToMany(mappedBy = "korisnik", fetch = FetchType.LAZY, cascade = { CascadeType.REFRESH })
	private List<KorisnikNalogEntity> nalozi = new ArrayList<>();

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@JsonView(Views.ucenik.class)
	@Column(name = "korisnik_id")
	protected Integer id;

	public KorisnikEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	public List<KorisnikNalogEntity> getNalozi() {
		return nalozi;
	}

	/**
	 * @param ime
	 * @param prezime
	 * @param jmbg
	 * @param pol
	 * @param uloga
	 * @param verzija
	 * @param nalozi
	 */
	// ID nsii stavio za unos u kosntruktor
	public KorisnikEntity(@NotNull(message = "Morate uneti ime") String ime,
			@NotNull(message = "Morate uneti prezime") String prezime,
			@Size(min = 13, max = 13, message = "Jmbg mora biti {min} karaktera dužine") @NotNull(message = "Morate uneti jmbg.") String jmbg,
			@NotNull(message = "Morate uneti pol") EPol pol,
			@NotNull(message = "User role must be provided.") EUloga uloga, Integer verzija,
			List<KorisnikNalogEntity> nalozi) {
		super();
		this.ime = ime;
		this.prezime = prezime;
		this.jmbg = jmbg;
		this.pol = pol;
		this.uloga = uloga;
		this.verzija = verzija;
		this.nalozi = nalozi;
	}

	public void setNalozi(List<KorisnikNalogEntity> nalozi) {
		this.nalozi = nalozi;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public EPol getPol() {
		return pol;
	}

	public void setPol(EPol pol) {
		this.pol = pol;
	}

	public EUloga getUloga() {
		return uloga;
	}

	public void setUloga(EUloga uloga) {
		this.uloga = uloga;
	}

	public Integer getVerzija() {
		return verzija;
	}

	public void setVerzija(Integer verzija) {
		this.verzija = verzija;
	}
	

}

package com.iktpreobuka.eeeDnevnik.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import com.iktpreobuka.eeeDnevnik.security.Views;

@Entity
@Table(name = "predmet")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE) //??????
public class PredmetEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@JsonView(Views.nastavnik.class)
	@Column(name = "predmet_id")
	private Integer id;

	@JsonView(Views.ucenik.class)
	@Column(name = "ime_predmeta", unique = true, length = 50)
	@NotNull(message = "Ime predmeta mora biti uneto")
	private String imePredmet;

	@JsonView(Views.ucenik.class)
	@Column(name = "nedeljni_broj_casova")
	@NotNull(message = "Nedeljni broj časova mora biti unet")
	@Min(value = 0, message = "Broj časova u nedelji mora biti {value} ili veći!")
	private Integer nedeljniBrojCasova;
	
	@JsonIgnore
	@JsonView(Views.nastavnik.class)
	@OneToMany(mappedBy = "predmet", fetch = FetchType.LAZY, cascade = { CascadeType.REFRESH }, orphanRemoval = true)
	private List<RazredPredmetEntity> razredi = new ArrayList<>();

	@JsonIgnore
	@JsonView(Views.nastavnik.class)
	@OneToMany(mappedBy = "predmet", fetch = FetchType.LAZY, cascade = { CascadeType.REFRESH }, orphanRemoval = true)
	private List<NastavnikPredmetEntity> nastavnici = new ArrayList<>();

	@JsonView(Views.nastavnik.class)
	@JsonIgnore
	@OneToMany(mappedBy = "predajePredmet", fetch = FetchType.LAZY, cascade = { CascadeType.REFRESH })
	private List<NastavnikPredmetOdeljenjeEntity> nastavnici_odeljenja = new ArrayList<>();
	
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
	public PredmetEntity() {
		super();
		// TODO Auto-generated constructor stub
	}



	/**
	 * @param id
	 * @param imePredmet
	 * @param nedeljniBrojCasova
	 * @param razredi
	 * @param nastavnici
	 * @param nastavnici_odeljenja
	 * @param status
	 * @param verzija
	 */
	public PredmetEntity(Integer id, @NotNull(message = "Ime predmeta mora biti uneto") String imePredmet,
			@NotNull(message = "Nedeljni broj časova mora biti unet") @Min(value = 0, message = "Broj časova u nedelji mora biti {value} ili veći!") Integer nedeljniBrojCasova,
			List<RazredPredmetEntity> razredi, List<NastavnikPredmetEntity> nastavnici,
			List<NastavnikPredmetOdeljenjeEntity> nastavnici_odeljenja, @Max(1) @Min(-1) Integer status,
			Integer verzija) {
		super();
		this.id = id;
		this.imePredmet = imePredmet;
		this.nedeljniBrojCasova = nedeljniBrojCasova;
		this.razredi = razredi;
		this.nastavnici = nastavnici;
		this.nastavnici_odeljenja = nastavnici_odeljenja;
		this.status = status;
		this.verzija = verzija;
	}



	public List<RazredPredmetEntity> getRazredi() {
		return razredi;
	}

	public void setRazredi(List<RazredPredmetEntity> razredi) {
		this.razredi = razredi;
	}

	public List<NastavnikPredmetEntity> getNastavnici() {
		return nastavnici;
	}

	public void setNastavnici(List<NastavnikPredmetEntity> nastavnici) {
		this.nastavnici = nastavnici;
	}

	public List<NastavnikPredmetOdeljenjeEntity> getNastavnici_odeljenja() {
		return nastavnici_odeljenja;
	}

	public void setNastavnici_odeljenja(List<NastavnikPredmetOdeljenjeEntity> nastavnici_odeljenja) {
		this.nastavnici_odeljenja = nastavnici_odeljenja;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getImePredmet() {
		return imePredmet;
	}

	public void setImePredmet(String imePredmet) {
		this.imePredmet = imePredmet;
	}

	public Integer getNedeljniBrojCasova() {
		return nedeljniBrojCasova;
	}

	public void setNedeljniBrojCasova(Integer nedeljniBrojCasova) {
		this.nedeljniBrojCasova = nedeljniBrojCasova;
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

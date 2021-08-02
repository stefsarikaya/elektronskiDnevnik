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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import com.iktpreobuka.eeeDnevnik.enumeration.ERazred;
import com.iktpreobuka.eeeDnevnik.security.Views;

@Entity
@Table(name = "razred")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class RazredEntity {
	
	@JsonIgnore
	@JsonView(Views.nastavnik.class)
	@OneToMany(mappedBy = "razred", fetch = FetchType.LAZY, cascade = { CascadeType.REFRESH }, orphanRemoval = true)
	private List<RazredPredmetEntity> predmeti = new ArrayList<>();

	@JsonIgnore
	@JsonView(Views.nastavnik.class)
	@OneToMany(mappedBy = "razred", fetch = FetchType.LAZY, cascade = { CascadeType.REFRESH }, orphanRemoval = true)
	private List<OdeljenjeRazredEntity> odeljenja = new ArrayList<>();

	@JsonView(Views.nastavnik.class)
	@JsonIgnore
	@OneToMany(mappedBy = "predajePredmet", fetch = FetchType.LAZY, cascade = { CascadeType.REFRESH })
	private List<NastavnikPredmetOdeljenjeEntity> nastavnici_predmeti_odeljenja = new ArrayList<>();

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@JsonView(Views.ucenik.class)
	@Column(name = "razred_id")
	private Integer id;

	@JsonView(Views.ucenik.class)
	@Column(name = "razred_broj")
	@Enumerated(EnumType.STRING)
	@NotNull(message = "Broj razreda mora biti unet.")
	private ERazred razredBroj;
	
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
	public RazredEntity() {
		super();
		// TODO Auto-generated constructor stub
		
	}
	public RazredEntity(@NotNull(message = "Broj razreda mora biti unet.") ERazred razredBroj,
			@Max(1) @Min(-1) Integer status) {
		super();
		this.razredBroj = razredBroj;
		this.status = status;
	}
	public List<RazredPredmetEntity> getPredmeti() {
		return predmeti;
	}
	public void setPredmeti(List<RazredPredmetEntity> predmeti) {
		this.predmeti = predmeti;
	}
	public List<OdeljenjeRazredEntity> getOdeljenja() {
		return odeljenja;
	}
	public void setOdeljenja(List<OdeljenjeRazredEntity> odeljenja) {
		this.odeljenja = odeljenja;
	}
	public List<NastavnikPredmetOdeljenjeEntity> getNastavnici_predmeti_odeljenja() {
		return nastavnici_predmeti_odeljenja;
	}
	public void setNastavnici_predmeti_odeljenja(List<NastavnikPredmetOdeljenjeEntity> nastavnici_predmeti_odeljenja) {
		this.nastavnici_predmeti_odeljenja = nastavnici_predmeti_odeljenja;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public ERazred getRazredBroj() {
		return razredBroj;
	}
	public void setRazredBroj(ERazred razredBroj) {
		this.razredBroj = razredBroj;
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

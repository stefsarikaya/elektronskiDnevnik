package com.iktpreobuka.eeeDnevnik.entities;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.persistence.Version;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import com.iktpreobuka.eeeDnevnik.security.Views;

@Entity
@Table(name = "nastavnik_predmet", uniqueConstraints=@UniqueConstraint(columnNames= {"nastavnik_id", "predmet_id", "datum_dodeljivanja"}))
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class NastavnikPredmetEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@JsonView(Views.admin.class)
	@Column(name="nastavnik_predmet_id")
	private Integer id;
	
	@JsonView(Views.nastavnik.class)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	@NotNull (message = "Datum dodeljivanja mora biti unet.")
	@Column(name="datum_dodeljivanja")
	private Date datumDodeljivanja;

	@JsonIgnore
	@JsonView(Views.admin.class)
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "nastavnik_id", nullable=false)
	@NotNull (message = "Nastavnik mora biti unet")
	private NastavnikEntity nastavnik;
	
	@JsonIgnore
	@JsonView(Views.nastavnik.class)
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "predmet_id", nullable=false)
	@NotNull (message = "Predmet mora biti unet")
	private PredmetEntity predmet;
	
	
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
	public NastavnikPredmetEntity() {
		super();
		// TODO Auto-generated constructor stub
	}
	public NastavnikPredmetEntity(@NotNull(message = "Nastavnik mora biti unet") NastavnikEntity nastavnik,
			@NotNull(message = "Predmet mora biti unet") PredmetEntity predmet,
			@NotNull(message = "Datum dodeljivanja mora biti unet.") Date datumDodeljivanja) {
		super();
		this.nastavnik = nastavnik;
		this.predmet = predmet;
		this.datumDodeljivanja = datumDodeljivanja;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public NastavnikEntity getNastavnik() {
		return nastavnik;
	}
	public void setNastavnik(NastavnikEntity nastavnik) {
		this.nastavnik = nastavnik;
	}
	public PredmetEntity getPredmet() {
		return predmet;
	}
	public void setPredmet(PredmetEntity predmet) {
		this.predmet = predmet;
	}
	public Date getDatumDodeljivanja() {
		return datumDodeljivanja;
	}
	public void setDatumDodeljivanja(Date datumDodeljivanja) {
		this.datumDodeljivanja = datumDodeljivanja;
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

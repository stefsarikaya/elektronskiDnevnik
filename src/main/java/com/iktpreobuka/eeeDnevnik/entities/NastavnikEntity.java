package com.iktpreobuka.eeeDnevnik.entities;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import com.iktpreobuka.eeeDnevnik.enumeration.EPol;
import com.iktpreobuka.eeeDnevnik.enumeration.EUloga;
import com.iktpreobuka.eeeDnevnik.security.Views;

@Entity
@Table(name = "nastavnik")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class NastavnikEntity extends KorisnikEntity{
	
	@JsonView(Views.nastavnik.class)
	@Column(name = "diploma")
	@NotNull(message = "Diploma mora biti uneta")
	private String diploma;

	@JsonView(Views.nastavnik.class)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	@Column(name = "datum_zaposlenja")
	@NotNull(message = "Datum zasposlenja mora biti unet")
	private Date datumZaposlenja;
	
	@JsonView(Views.nastavnik.class)
	@JsonIgnore
	@OneToMany(mappedBy = "nastavnik", fetch = FetchType.LAZY, cascade = { CascadeType.REFRESH }, orphanRemoval = true)
	private List<NastavnikPredmetEntity> predmeti = new ArrayList<>();

	@JsonView(Views.nastavnik.class)
	@JsonIgnore
	@OneToMany(mappedBy = "razredniStaresina", fetch = FetchType.LAZY, cascade = { CascadeType.REFRESH })
	private List<RazredniStaresinaOdeljenjeEntity> odeljenja = new ArrayList<>();

	@JsonView(Views.nastavnik.class)
	@JsonIgnore
	@OneToMany(mappedBy = "predajuciNastavnik", fetch = FetchType.LAZY, cascade = { CascadeType.REFRESH })
	private List<NastavnikPredmetOdeljenjeEntity> predmeti_odeljenja = new ArrayList<>();

	@JsonView(Views.admin.class)
	@Max(1)
    @Min(-1)
    @Column(name = "status", nullable = false)
	private Integer status;
	
	private static final Integer NEAKTIVAN = 0;
	private static final Integer AKTIVAN = 1;
	private static final Integer ARHIVIRAN = -1;
	


	/**
	 * 
	 */
	public NastavnikEntity() {
		super();
		// TODO Auto-generated constructor stub
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
	public NastavnikEntity(@NotNull(message = "Morate uneti ime") String ime,
			@NotNull(message = "Morate uneti prezime") String prezime,
			@Size(min = 13, max = 13, message = "Jmbg mora biti {min} karaktera dužine") @NotNull(message = "Morate uneti jmbg.") String jmbg,
			@NotNull(message = "Morate uneti pol") EPol pol,
			@NotNull(message = "User role must be provided.") EUloga uloga, Integer verzija,
			List<KorisnikNalogEntity> nalozi) {
		super(ime, prezime, jmbg, pol, uloga, verzija, nalozi);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param diploma
	 * @param datumZaposlenja
	 * @param predmeti
	 * @param odeljenja
	 * @param predmeti_odeljenja
	 * @param status
	 */
	public NastavnikEntity(@NotNull(message = "Diploma mora biti uneta") String diploma,
			@NotNull(message = "Datum zasposlenja mora biti unet") Date datumZaposlenja,
			List<NastavnikPredmetEntity> predmeti, List<RazredniStaresinaOdeljenjeEntity> odeljenja,
			List<NastavnikPredmetOdeljenjeEntity> predmeti_odeljenja, @Max(1) @Min(-1) Integer status) {
		super();
		this.diploma = diploma;
		this.datumZaposlenja = datumZaposlenja;
		this.predmeti = predmeti;
		this.odeljenja = odeljenja;
		this.predmeti_odeljenja = predmeti_odeljenja;
		this.status = status;
	}

	public List<NastavnikPredmetEntity> getPredmeti() {
		return predmeti;
	}
	public void setPredmeti(List<NastavnikPredmetEntity> predmeti) {
		this.predmeti = predmeti;
	}
	public List<RazredniStaresinaOdeljenjeEntity> getOdeljenja() {
		return odeljenja;
	}
	public void setOdeljenja(List<RazredniStaresinaOdeljenjeEntity> odeljenja) {
		this.odeljenja = odeljenja;
	}
	public List<NastavnikPredmetOdeljenjeEntity> getPredmeti_odeljenja() {
		return predmeti_odeljenja;
	}
	public void setPredmeti_odeljenja(List<NastavnikPredmetOdeljenjeEntity> predmeti_odeljenja) {
		this.predmeti_odeljenja = predmeti_odeljenja;
	}
	public String getDiploma() {
		return diploma;
	}
	public void setDiploma(String diploma) {
		this.diploma = diploma;
	}
	public Date getDatumZaposlenja() {
		return datumZaposlenja;
	}
	public void setDatumZaposlenja(Date datumZaposlenja) {
		this.datumZaposlenja = datumZaposlenja;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
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

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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.persistence.Version;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import com.iktpreobuka.eeeDnevnik.security.Views;

@Entity
@Table(name = "nastavnik_predmet_odeljenje", uniqueConstraints = @UniqueConstraint(columnNames = { "nastavnik_id",
		"predmet_id", "odeljenje_id", "razred_id", "skolska_godina" }))
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class NastavnikPredmetOdeljenjeEntity {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@JsonView(Views.ucenik.class)
	@Column(name = "nastavni_predmet_odeljenje_id")
	private Integer id;
	
	@JsonIgnore
	@JsonView(Views.ucenik.class)
	@OneToMany(mappedBy = "nastavnik_predmet_odeljenje", fetch = FetchType.LAZY, cascade = { CascadeType.REFRESH })
	private List<OcenaEntity> ocene = new ArrayList<>();

	@JsonView(Views.ucenik.class)
	@Column(name = "skolska_godina", nullable = false)
	@NotNull(message = "Školska godina mora biti uneta")
	private String skolskaGodina;

	@JsonIgnore
	@JsonView(Views.ucenik.class)
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "odeljenje_id", nullable = false)
	@NotNull(message = "Odeljenje mora biti dodato.")
	private OdeljenjeEntity predajeOdeljenje;

	@JsonIgnore
	@JsonView(Views.ucenik.class)
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "razred_id", nullable = false)
	@NotNull(message = "Razred mora biti dodat.")
	private RazredEntity predajeRazred;

	@JsonIgnore
	@JsonView(Views.ucenik.class)
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "predmet_id", nullable = false)
	@NotNull(message = "Predmet mora biti dodat.")
	private PredmetEntity predajePredmet;

	@JsonIgnore
	@JsonView(Views.ucenik.class)
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "nastavnik_id", nullable = false)
	@NotNull(message = "Nastavnik mora biti dodat")
	private NastavnikEntity predajuciNastavnik;
	
	@JsonView(Views.ucenik.class)
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
	
	public NastavnikPredmetOdeljenjeEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	/**
	 * @param ocene
	 * @param skolskaGodina
	 * @param predajeOdeljenje
	 * @param predajeRazred
	 * @param predajePredmet
	 * @param predajuciNastavnik
	 * @param status
	 * @param verzija
	 */
	public NastavnikPredmetOdeljenjeEntity(List<OcenaEntity> ocene,
			@NotNull(message = "Školska godina mora biti uneta") String skolskaGodina,
			@NotNull(message = "Odeljenje mora biti dodato.") OdeljenjeEntity predajeOdeljenje,
			@NotNull(message = "Razred mora biti dodat.") RazredEntity predajeRazred,
			@NotNull(message = "Predmet mora biti dodat.") PredmetEntity predajePredmet,
			@NotNull(message = "Nastavnik mora biti dodat") NastavnikEntity predajuciNastavnik,
			@Max(1) @Min(-1) Integer status, Integer verzija) {
		super();
		this.ocene = ocene;
		this.skolskaGodina = skolskaGodina;
		this.predajeOdeljenje = predajeOdeljenje;
		this.predajeRazred = predajeRazred;
		this.predajePredmet = predajePredmet;
		this.predajuciNastavnik = predajuciNastavnik;
		this.status = status;
		this.verzija = verzija;
	}




	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public OdeljenjeEntity getPredajeOdeljenje() {
		return predajeOdeljenje;
	}
	public void setPredajeOdeljenje(OdeljenjeEntity predajeOdeljenje) {
		this.predajeOdeljenje = predajeOdeljenje;
	}
	public RazredEntity getPredajeRazred() {
		return predajeRazred;
	}
	public void setPredajeRazred(RazredEntity predajeRazred) {
		this.predajeRazred = predajeRazred;
	}
	public PredmetEntity getPredajePredmet() {
		return predajePredmet;
	}
	public void setPredajePredmet(PredmetEntity predajePredmet) {
		this.predajePredmet = predajePredmet;
	}
	public NastavnikEntity getPredajuciNastavnik() {
		return predajuciNastavnik;
	}
	public void setPredajuciNastavnik(NastavnikEntity predajuciNastavnik) {
		this.predajuciNastavnik = predajuciNastavnik;
	}
	public List<OcenaEntity> getOcene() {
		return ocene;
	}
	public void setOcene(List<OcenaEntity> ocene) {
		this.ocene = ocene;
	}
	public String getSkolskaGodina() {
		return skolskaGodina;
	}
	public void setSkolskaGodina(String skolskaGodina) {
		this.skolskaGodina = skolskaGodina;
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
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}

}

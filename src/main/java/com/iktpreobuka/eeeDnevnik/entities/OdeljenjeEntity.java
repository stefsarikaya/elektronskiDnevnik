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
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import com.iktpreobuka.eeeDnevnik.security.Views;

@Entity
@Table(name = "odeljenje")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class OdeljenjeEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@JsonView(Views.ucenik.class)
	@Column(name = "odeljenje_id")
	private Integer id;

	@Column(name = "odeljenje_oznaka", nullable = false)
	@JsonView(Views.ucenik.class)
	@Min(value = 1, message = "Oznaka odeljenja mora biti {value} ili veća!")
	@NotNull(message = "Oznaka odeljenja mora biti uneta.")
	private String odeljenjeOznaka;

	@JsonView(Views.ucenik.class)
	@Column(name = "godina_upisa", nullable = false)
	@NotNull(message = "Godina upisa mora biti dodata")
	private String godinaUpisa;
	
	
	@JsonIgnore
	@JsonView(Views.nastavnik.class)
	@OneToMany(mappedBy = "odeljenje", fetch = FetchType.LAZY, cascade = { CascadeType.REFRESH }, orphanRemoval = true)
	private List<UcenikOdeljenjeEntity> studenti = new ArrayList<>();

	@JsonView(Views.nastavnik.class)
	@JsonIgnore
	@OneToMany(mappedBy = "glavnoOdeljenje", fetch = FetchType.LAZY, cascade = { CascadeType.REFRESH })
	private List<RazredniStaresinaOdeljenjeEntity> nastavnici = new ArrayList<>();

	@JsonIgnore
	@JsonView(Views.nastavnik.class)
	@OneToMany(mappedBy = "odeljenje", fetch = FetchType.LAZY, cascade = { CascadeType.REFRESH }, orphanRemoval = true)
	private List<OdeljenjeRazredEntity > razredi = new ArrayList<>();

	@JsonView(Views.ucenik.class)
	@JsonIgnore
	@OneToMany(mappedBy = "predajeOdeljenje", fetch = FetchType.LAZY, cascade = { CascadeType.REFRESH })
	private List<NastavnikPredmetOdeljenjeEntity> nastavnici_predmeti = new ArrayList<>();
	
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
	public OdeljenjeEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	
	/**
	 * @param id
	 * @param odeljenjeOznaka
	 * @param godinaUpisa
	 * @param studenti
	 * @param nastavnici
	 * @param razredi
	 * @param nastavnici_predmeti
	 * @param status
	 * @param verzija
	 */
	public OdeljenjeEntity(Integer id,
			@Min(value = 1, message = "Oznaka odeljenja mora biti {value} ili veća!") @NotNull(message = "Oznaka odeljenja mora biti uneta.") String odeljenjeOznaka,
			@NotNull(message = "Godina upisa mora biti dodata") String godinaUpisa,
			List<UcenikOdeljenjeEntity> studenti, List<RazredniStaresinaOdeljenjeEntity> nastavnici,
			List<OdeljenjeRazredEntity> razredi, List<NastavnikPredmetOdeljenjeEntity> nastavnici_predmeti,
			@Max(1) @Min(-1) Integer status, Integer verzija) {
		super();
		this.id = id;
		this.odeljenjeOznaka = odeljenjeOznaka;
		this.godinaUpisa = godinaUpisa;
		this.studenti = studenti;
		this.nastavnici = nastavnici;
		this.razredi = razredi;
		this.nastavnici_predmeti = nastavnici_predmeti;
		this.status = status;
		this.verzija = verzija;
	}



	public List<UcenikOdeljenjeEntity> getStudenti() {
		return studenti;
	}
	public void setStudenti(List<UcenikOdeljenjeEntity> studenti) {
		this.studenti = studenti;
	}
	public List<RazredniStaresinaOdeljenjeEntity> getNastavnici() {
		return nastavnici;
	}
	public void setNastavnici(List<RazredniStaresinaOdeljenjeEntity> nastavnici) {
		this.nastavnici = nastavnici;
	}
	public List<OdeljenjeRazredEntity> getRazredi() {
		return razredi;
	}
	public void setRazredi(List<OdeljenjeRazredEntity> razredi) {
		this.razredi = razredi;
	}
	public List<NastavnikPredmetOdeljenjeEntity> getNastavnici_predmeti() {
		return nastavnici_predmeti;
	}
	public void setNastavnici_predmeti(List<NastavnikPredmetOdeljenjeEntity> nastavnici_predmeti) {
		this.nastavnici_predmeti = nastavnici_predmeti;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getOdeljenjeOznaka() {
		return odeljenjeOznaka;
	}
	public void setOdeljenjeOznaka(String odeljenjeOznaka) {
		this.odeljenjeOznaka = odeljenjeOznaka;
	}
	public String getGodinaUpisa() {
		return godinaUpisa;
	}
	public void setGodinaUpisa(String godinaUpisa) {
		this.godinaUpisa = godinaUpisa;
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

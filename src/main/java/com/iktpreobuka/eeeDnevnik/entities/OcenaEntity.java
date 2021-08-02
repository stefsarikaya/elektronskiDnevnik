package com.iktpreobuka.eeeDnevnik.entities;

import java.util.Date;

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

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import com.iktpreobuka.eeeDnevnik.enumeration.ESemestar;
import com.iktpreobuka.eeeDnevnik.security.Views;

@Entity
@Table(name = "ocena")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class OcenaEntity {
	
	@JsonView(Views.ucenik.class)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	@NotNull(message = "Datum ocene mora biti dodat")
	@Column(name = "datum_ocene")
	private Date datumOcene;

	@JsonView(Views.ucenik.class)
	@Column(name = "Semestar")
	@Enumerated(EnumType.STRING)
	@NotNull(message = "Semestar mora biti dodat")
	private ESemestar semestar;
	
	
	@JsonIgnore
	@JsonView(Views.roditelj.class)
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "ucenik")
	@NotNull(message = "Ucenik mora biti dodat")
	private UcenikEntity ucenik;

	@JsonView(Views.ucenik.class)
	@JsonIgnore
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "nastavnik_predmet_odeljenje")
	@NotNull(message = "Nastavnik_predmet_odeljenje je potrebno uneti.")
	private NastavnikPredmetOdeljenjeEntity nastavnik_predmet_odeljenje;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@JsonView(Views.ucenik.class)
	@Column(name = "ocena_id")
	private Integer id;

	@JsonView(Views.ucenik.class)
	@Column(name = "vrednost_Ocene")
	@NotNull(message = "Vrednost ocene mora biti uneta!")
	@Min(value = 1, message = "Vrednost ocene mora biti {value} ili veća!")
	@Max(value = 5, message = "Vrednost ocene mora biti {value} ili manja!")
	private Integer vrednostOcene;
	
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
	
	public OcenaEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	public OcenaEntity(@NotNull(message = "Ucenik mora biti dodat") UcenikEntity ucenik,
			@NotNull(message = "Nastavnik_predmet_odeljenje je potrebno uneti.") NastavnikPredmetOdeljenjeEntity nastavnik_predmet_odeljenje,
			@NotNull(message = "Vrednost ocene mora biti uneta!") @Min(value = 1, message = "Vrednost ocene mora biti {value} ili veća!") @Max(value = 5, message = "Vrednost ocene mora biti {value} ili manja!") Integer vrednostOcene,
			@NotNull(message = "Datum ocene mora biti dodat") Date datumOcene,
			@NotNull(message = "Semestar mora biti dodat") ESemestar semestar) {
		super();
		this.ucenik = ucenik;
		this.nastavnik_predmet_odeljenje = nastavnik_predmet_odeljenje;
		this.vrednostOcene = vrednostOcene;
		this.datumOcene = datumOcene;
		this.semestar = semestar;
	}

	public UcenikEntity getUcenik() {
		return ucenik;
	}

	public void setUcenik(UcenikEntity ucenik) {
		this.ucenik = ucenik;
	}

	public NastavnikPredmetOdeljenjeEntity getNastavnik_predmet_odeljenje() {
		return nastavnik_predmet_odeljenje;
	}

	public void setNastavnik_predmet_odeljenje(NastavnikPredmetOdeljenjeEntity nastavnik_predmet_odeljenje) {
		this.nastavnik_predmet_odeljenje = nastavnik_predmet_odeljenje;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getVrednostOcene() {
		return vrednostOcene;
	}

	public void setVrednostOcene(Integer vrednostOcene) {
		this.vrednostOcene = vrednostOcene;
	}

	public Date getDatumOcene() {
		return datumOcene;
	}

	public void setDatumOcene(Date datumOcene) {
		this.datumOcene = datumOcene;
	}

	public ESemestar getSemestar() {
		return semestar;
	}

	public void setSemestar(ESemestar semestar) {
		this.semestar = semestar;
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

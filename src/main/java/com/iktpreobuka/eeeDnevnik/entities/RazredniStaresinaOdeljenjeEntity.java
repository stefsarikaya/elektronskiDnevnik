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
@Table(name = "razredni_staresina_odeljenje", uniqueConstraints = @UniqueConstraint(columnNames = { "nastavnik_id",
		"odeljenje_id", "datum_dodele" }))
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class RazredniStaresinaOdeljenjeEntity {
	
	@JsonIgnore
	@JsonView(Views.nastavnik.class)
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "odeljenje_id", nullable = false)
	@NotNull(message = "Odeljenje mora biti uneto")
	private OdeljenjeEntity glavnoOdeljenje;  

	@JsonView(Views.nastavnik.class)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	@NotNull(message = "Dadum dodele mora biti unet.")
	@Column(name = "datum_dodele")
	private Date datumDodele;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@JsonView(Views.nastavnik.class)
	@Column(name = "razredni_staresina_id")
	private Integer id;

	@JsonIgnore
	@JsonView(Views.admin.class)
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "nastavnik_id", nullable = false)
	@NotNull(message = "Nastavnik mora biti unet.")
	private NastavnikEntity razredniStaresina;
	
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
	
	public RazredniStaresinaOdeljenjeEntity() {
		super();
		// TODO Auto-generated constructor stub
	}



	/**
	 * @param glavnoOdeljenje
	 * @param datumDodele
	 * @param id
	 * @param razredniStaresina
	 * @param status
	 * @param verzija
	 */
	public RazredniStaresinaOdeljenjeEntity(
			@NotNull(message = "Odeljenje mora biti uneto") OdeljenjeEntity glavnoOdeljenje,
			@NotNull(message = "Dadum dodele mora biti unet.") Date datumDodele, Integer id,
			@NotNull(message = "Nastavnik mora biti unet.") NastavnikEntity razredniStaresina,
			@Max(1) @Min(-1) Integer status, Integer verzija) {
		super();
		this.glavnoOdeljenje = glavnoOdeljenje;
		this.datumDodele = datumDodele;
		this.id = id;
		this.razredniStaresina = razredniStaresina;
		this.status = status;
		this.verzija = verzija;
	}



	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public NastavnikEntity getRazredniStaresina() {
		return razredniStaresina;
	}

	public void setRazredniStaresina(NastavnikEntity razredniStaresina) {
		this.razredniStaresina = razredniStaresina;
	}

	public OdeljenjeEntity getGlavnoOdeljenje() {
		return glavnoOdeljenje;
	}

	public void setGlavnoOdeljenje(OdeljenjeEntity glavnoOdeljenje) {
		this.glavnoOdeljenje = glavnoOdeljenje;
	}

	public Date getDatumDodele() {
		return datumDodele;
	}

	public void setDatumDodele(Date datumDodele) {
		this.datumDodele = datumDodele;
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

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
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import com.iktpreobuka.eeeDnevnik.security.Views;

@Entity
@Table(name = "ucenik_odeljenje", uniqueConstraints = @UniqueConstraint(columnNames = { "ucenik_id", "odeljenje_id",
		"datum_prebacivanja" }))
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class UcenikOdeljenjeEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@JsonView(Views.ucenik.class)
	@Column(name = "ucenik_odeljenje_id")
	private Integer id;

	@JsonIgnore
	@JsonView(Views.ucenik.class)
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "ucenik_id")
	@NotNull(message = "ucenik mora biti unet")
	private UcenikEntity ucenik;
	
	@JsonView(Views.ucenik.class)
	@Column(name = "datum_prebacivanja", nullable = false)
	@NotNull(message = "Datum prebacivanja mora biti dodat")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	private Date datumPrebacivanja;

	@JsonIgnore
	@JsonView(Views.ucenik.class)
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "odeljenje_id", nullable = false)
	@NotNull(message = "Odeljenje mora biti uneto")
	private OdeljenjeEntity odeljenje;

	
	@JsonView(Views.admin.class)
	@Max(1)
    @Min(-1)
    @Column(name = "status", nullable = false)
	private Integer status;
	
	private static final Integer NEAKTIVAN = 0;
	private static final Integer AKTIVAN = 1;
	private static final Integer ARHIVIRAN = -1;
	
	public UcenikOdeljenjeEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UcenikOdeljenjeEntity(@NotNull(message = "ucenik mora biti unet") UcenikEntity ucenik,
			@NotNull(message = "Odeljenje mora biti uneto") OdeljenjeEntity odeljenje,
			@NotNull(message = "Datum prebacivanja mora biti dodat") Date datumPrebacivanja) {
		super();
		this.ucenik = ucenik;
		this.odeljenje = odeljenje;
		this.datumPrebacivanja = datumPrebacivanja;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public UcenikEntity getUcenik() {
		return ucenik;
	}

	public void setUcenik(UcenikEntity ucenik) {
		this.ucenik = ucenik;
	}

	public OdeljenjeEntity getOdeljenje() {
		return odeljenje;
	}

	public void setOdeljenje(OdeljenjeEntity odeljenje) {
		this.odeljenje = odeljenje;
	}

	public Date getDatumPrebacivanja() {
		return datumPrebacivanja;
	}

	public void setDatumPrebacivanja(Date datumPrebacivanja) {
		this.datumPrebacivanja = datumPrebacivanja;
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

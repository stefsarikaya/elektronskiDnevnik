package com.iktpreobuka.eeeDnevnik.entities;

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
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import com.iktpreobuka.eeeDnevnik.security.Views;

@Entity
@Table(name = "odeljenje_razred", uniqueConstraints = @UniqueConstraint(columnNames = { "razred_id", "odeljenje_id",
		"skolska_godina" }))
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class OdeljenjeRazredEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@JsonView(Views.ucenik.class)
	@Column(name = "odeljenje_razred__id")
	private Integer id;

	@JsonIgnore
	@JsonView(Views.ucenik.class)
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "razred_id")
	@NotNull(message = "Razred mora biti unet")
	private RazredEntity razred;

	@JsonIgnore
	@JsonView(Views.ucenik.class)
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "odeljenje_id", nullable = false)
	@NotNull(message = "Odeljenje mora biti uneto")
	private OdeljenjeEntity odeljenje;

	@JsonView(Views.ucenik.class)
	@Column(name = "skolska_godina", nullable = false)
	@NotNull(message = "Skolska godina mora biti uneta")
	private String skolskaGodina;
	
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
	

	/**
	 * 
	 */
	public OdeljenjeRazredEntity() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * @param id
	 * @param razred
	 * @param odeljenje
	 * @param skolskaGodina
	 * @param status
	 * @param verzija
	 */
	public OdeljenjeRazredEntity(Integer id, @NotNull(message = "Razred mora biti unet") RazredEntity razred,
			@NotNull(message = "Odeljenje mora biti uneto") OdeljenjeEntity odeljenje,
			@NotNull(message = "Skolska godina mora biti uneta") String skolskaGodina, @Max(1) @Min(-1) Integer status,
			Integer verzija) {
		super();
		this.id = id;
		this.razred = razred;
		this.odeljenje = odeljenje;
		this.skolskaGodina = skolskaGodina;
		this.status = status;
		this.verzija = verzija;
	}





	/**
	 * @param razred
	 * @param odeljenje
	 * @param skolskaGodina
	 */
	public OdeljenjeRazredEntity(@NotNull(message = "Razred mora biti unet") RazredEntity razred,
			@NotNull(message = "Odeljenje mora biti uneto") OdeljenjeEntity odeljenje,
			@NotNull(message = "Skolska godina mora biti uneta") @Pattern(regexp = "^(20|[3-9][0-9])[0-9]{2}\\-(20|[3-9][0-9])[0-9]{2}$", message = "Skolska godina mora biti u formatu YYYY-YYYY.") String skolskaGodina) {
		super();
		this.razred = razred;
		this.odeljenje = odeljenje;
		this.skolskaGodina = skolskaGodina;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public RazredEntity getRazred() {
		return razred;
	}

	public void setRazred(RazredEntity razred) {
		this.razred = razred;
	}

	public OdeljenjeEntity getOdeljenje() {
		return odeljenje;
	}

	public void setOdeljenje(OdeljenjeEntity odeljenje) {
		this.odeljenje = odeljenje;
	}

	public String getSkolskaGodina() {
		return skolskaGodina;
	}

	public void setSkolskaGodina(String skolskaGodina) {
		this.skolskaGodina = skolskaGodina;
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

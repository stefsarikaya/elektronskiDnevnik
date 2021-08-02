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
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import com.iktpreobuka.eeeDnevnik.security.Views;

@Entity
@Table(name = "razred_predmet", uniqueConstraints = @UniqueConstraint(columnNames = { "razred_id", "predmet_id",
		"plan_nastave" }))
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class RazredPredmetEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@JsonView(Views.ucenik.class)
	@Column(name = "razred_predmet__id")
	private Integer id;
	
	@JsonView(Views.ucenik.class)
	@Column(name = "plan_nastave")
	@NotNull(message = "Plan nastave mora biti unet.")
	private String planNastave;

	@JsonIgnore
	@JsonView(Views.ucenik.class)
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	@JoinColumn(name = "razred_id", nullable = false)
	@NotNull(message = "Razred mora biti unet.")
	private RazredEntity razred;

	@JsonIgnore
	@JsonView(Views.ucenik.class)
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	@JoinColumn(name = "predmet_id", nullable = false)
	@NotNull(message = "Predmet mora biti unet.")
	private PredmetEntity predmet;
	
	
	@JsonView(Views.admin.class)
	@Max(1)
    @Min(-1)
    @Column(name = "status", nullable = false)
	private Integer status;
	
	private static final Integer NEAKTIVAN = 0;
	private static final Integer AKTIVAN = 1;
	private static final Integer ARHIVIRAN = -1;
	public RazredPredmetEntity() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	// dodao si i plan nastave
	public RazredPredmetEntity(@NotNull(message = "Razred mora biti unet.") RazredEntity razred,
			@NotNull(message = "Predmet mora biti unet.") PredmetEntity predmet,
			@NotNull(message = "Plan nastave mora biti unet.") String planNastave) {
		super();
		this.razred = razred;
		this.predmet = predmet;
		this.planNastave = planNastave;
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

	public PredmetEntity getPredmet() {
		return predmet;
	}

	public void setPredmet(PredmetEntity predmet) {
		this.predmet = predmet;
	}

	public String getPlanNastave() {
		return planNastave;
	}

	public void setPlanNastave(String planNastave) {
		this.planNastave = planNastave;
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

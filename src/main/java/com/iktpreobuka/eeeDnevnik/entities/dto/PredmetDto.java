package com.iktpreobuka.eeeDnevnik.entities.dto;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonView;
import com.iktpreobuka.eeeDnevnik.security.Views;



public class PredmetDto {
	
	@JsonView(Views.ucenik.class)
	private String imePredmet;

	@JsonView(Views.ucenik.class)
	@Min(value = 0, message = "Broj časova u nedelji mora biti {value} ili veći!")
	private Integer nedeljniBrojCasova;

	@JsonView(Views.nastavnik.class)
	private List<String> razredi;
	
	@JsonView(Views.ucenik.class)
	private String planNastave;

	@JsonView(Views.nastavnik.class)
	private List<String> nastavnici;

	@JsonView(Views.admin.class)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	private Date datumDodele;

	@JsonView(Views.nastavnik.class)
	private String predajeOdeljenje;

	@JsonView(Views.nastavnik.class)
	private String predajuciNastavnik;

	/**
	 * 
	 */
	public PredmetDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param imePredmet
	 * @param nedeljniBrojCasova
	 * @param razredi
	 * @param planNastave
	 * @param nastavnici
	 * @param datumDodele
	 * @param predajeOdeljenje
	 * @param predajuciNastavnik
	 */
	public PredmetDto(String imePredmet,
			@Min(value = 0, message = "Broj časova u nedelji mora biti {value} ili veći!") Integer nedeljniBrojCasova,
			List<String> razredi, String planNastave, List<String> nastavnici, Date datumDodele,
			String predajeOdeljenje, String predajuciNastavnik) {
		super();
		this.imePredmet = imePredmet;
		this.nedeljniBrojCasova = nedeljniBrojCasova;
		this.razredi = razredi;
		this.planNastave = planNastave;
		this.nastavnici = nastavnici;
		this.datumDodele = datumDodele;
		this.predajeOdeljenje = predajeOdeljenje;
		this.predajuciNastavnik = predajuciNastavnik;
	}



	public String getImePredmet() {
		return imePredmet;
	}

	public void setImePredmet(String imePredmet) {
		this.imePredmet = imePredmet;
	}

	public Integer getNedeljniBrojCasova() {
		return nedeljniBrojCasova;
	}

	public void setNedeljniBrojCasova(Integer nedeljniBrojCasova) {
		this.nedeljniBrojCasova = nedeljniBrojCasova;
	}

	public List<String> getRazredi() {
		return razredi;
	}

	public void setRazredi(List<String> razredi) {
		this.razredi = razredi;
	}

	public String getPlanNastave() {
		return planNastave;
	}

	public void setPlanNastave(String planNastave) {
		this.planNastave = planNastave;
	}

	public List<String> getNastavnici() {
		return nastavnici;
	}

	public void setNastavnici(List<String> nastavnici) {
		this.nastavnici = nastavnici;
	}

	public Date getDatumDodele() {
		return datumDodele;
	}

	public void setDatumDodele(Date datumDodele) {
		this.datumDodele = datumDodele;
	}

	public String getPredajeOdeljenje() {
		return predajeOdeljenje;
	}

	public void setPredajeOdeljenje(String predajeOdeljenje) {
		this.predajeOdeljenje = predajeOdeljenje;
	}

	public String getPredajuciNastavnik() {
		return predajuciNastavnik;
	}

	public void setPredajuciNastavnik(String predajuciNastavnik) {
		this.predajuciNastavnik = predajuciNastavnik;
	}

}

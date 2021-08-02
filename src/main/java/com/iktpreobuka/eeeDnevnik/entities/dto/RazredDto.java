package com.iktpreobuka.eeeDnevnik.entities.dto;

import java.util.List;

import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonView;
import com.iktpreobuka.eeeDnevnik.entities.OdeljenjeEntity;
import com.iktpreobuka.eeeDnevnik.entities.PredmetEntity;
import com.iktpreobuka.eeeDnevnik.security.Views;


public class RazredDto {
	
	@JsonView(Views.ucenik.class)
	private String razredBroj;
	
	@JsonView(Views.ucenik.class)
	private List<String> predmeti;
	
	@JsonView(Views.admin.class)
	private List<String> odeljenja;
	
	@JsonView(Views.ucenik.class)
	private PredmetEntity predmet;
	
	@JsonView(Views.ucenik.class)
	private OdeljenjeEntity odeljenje;

	/**
	 * 
	 */
	public RazredDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param razredBroj
	 * @param predmeti
	 * @param odeljenja
	 * @param predmet
	 * @param odeljenje
	 */
	public RazredDto(String razredBroj, List<String> predmeti, List<String> odeljenja, PredmetEntity predmet,
			OdeljenjeEntity odeljenje) {
		super();
		this.razredBroj = razredBroj;
		this.predmeti = predmeti;
		this.odeljenja = odeljenja;
		this.predmet = predmet;
		this.odeljenje = odeljenje;
	}



	public String getRazredBroj() {
		return razredBroj;
	}

	public void setRazredBroj(String razredBroj) {
		this.razredBroj = razredBroj;
	}

	public List<String> getPredmeti() {
		return predmeti;
	}

	public void setPredmeti(List<String> predmeti) {
		this.predmeti = predmeti;
	}

	public List<String> getOdeljenja() {
		return odeljenja;
	}

	public void setOdeljenja(List<String> odeljenja) {
		this.odeljenja = odeljenja;
	}

	public PredmetEntity getPredmet() {
		return predmet;
	}

	public void setPredmet(PredmetEntity predmet) {
		this.predmet = predmet;
	}

	public OdeljenjeEntity getOdeljenje() {
		return odeljenje;
	}

	public void setOdeljenje(OdeljenjeEntity odeljenje) {
		this.odeljenje = odeljenje;
	}
}

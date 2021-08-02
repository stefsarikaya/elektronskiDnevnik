package com.iktpreobuka.eeeDnevnik.entities.dto;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonView;
import com.iktpreobuka.eeeDnevnik.entities.NastavnikEntity;
import com.iktpreobuka.eeeDnevnik.entities.PredmetEntity;
import com.iktpreobuka.eeeDnevnik.security.Views;

public class PredmetNastavnikDto {
	
	@JsonView(Views.ucenik.class)
	@NotNull (message = "Nastavnik mora biti unet.")
	private NastavnikEntity nastavnik;
	
	@JsonView(Views.ucenik.class)
	@NotNull (message = "Predmet mora biti unet.")
	private PredmetEntity predmet;

	/**
	 * 
	 */
	public PredmetNastavnikDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param nastavnik
	 * @param predmet
	 */
	public PredmetNastavnikDto(@NotNull(message = "Nastavnik mora biti unet.") NastavnikEntity nastavnik,
			@NotNull(message = "Predmet mora biti unet.") PredmetEntity predmet) {
		super();
		this.nastavnik = nastavnik;
		this.predmet = predmet;
	}

	public NastavnikEntity getNastavnik() {
		return nastavnik;
	}

	public void setNastavnik(NastavnikEntity nastavnik) {
		this.nastavnik = nastavnik;
	}

	public PredmetEntity getPredmet() {
		return predmet;
	}

	public void setPredmet(PredmetEntity predmet) {
		this.predmet = predmet;
	}

}

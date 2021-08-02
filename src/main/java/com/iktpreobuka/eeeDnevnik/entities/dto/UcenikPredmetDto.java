package com.iktpreobuka.eeeDnevnik.entities.dto;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonView;
import com.iktpreobuka.eeeDnevnik.entities.PredmetEntity;
import com.iktpreobuka.eeeDnevnik.entities.UcenikEntity;
import com.iktpreobuka.eeeDnevnik.security.Views;

public class UcenikPredmetDto {
	
	@JsonView(Views.ucenik.class)
	@NotNull (message = "Ucenik mora biti dodat.")
	private UcenikEntity ucenik;
	
	@JsonView(Views.ucenik.class)
	@NotNull (message = "Predmet mora biti dodat.")
	private PredmetEntity predmet;

	/**
	 * 
	 */
	public UcenikPredmetDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param ucenik
	 * @param predmet
	 */
	public UcenikPredmetDto(@NotNull(message = "Ucenik mora biti dodat.") UcenikEntity ucenik,
			@NotNull(message = "Predmet mora biti dodat.") PredmetEntity predmet) {
		super();
		this.ucenik = ucenik;
		this.predmet = predmet;
	}

	public UcenikEntity getUcenik() {
		return ucenik;
	}

	public void setUcenik(UcenikEntity ucenik) {
		this.ucenik = ucenik;
	}

	public PredmetEntity getPredmet() {
		return predmet;
	}

	public void setPredmet(PredmetEntity predmet) {
		this.predmet = predmet;
	}
	
	
	

}

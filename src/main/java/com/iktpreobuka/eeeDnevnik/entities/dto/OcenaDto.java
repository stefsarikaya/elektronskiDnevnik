package com.iktpreobuka.eeeDnevnik.entities.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonView;
import com.iktpreobuka.eeeDnevnik.security.Views;


public class OcenaDto {
	
	@JsonView(Views.ucenik.class)
	private String ucenik;
	
	@JsonView(Views.ucenik.class)
	private String nastavnik;
	
	@JsonView(Views.ucenik.class)
	private String predmet;
	
	@JsonView(Views.ucenik.class)
	@Min(value=1, message = "Vrednost ocene mora biti {value} ili veća!")
	@Max(value=5, message = "Vrednost ocene mora biti  {value} ili manja!")
	private Integer vrednostOcene;
	
	@JsonView(Views.ucenik.class)
	private String datumOcene;
	
	@JsonView(Views.ucenik.class)
	private String semestar;


	/**
	 * 
	 */
	public OcenaDto() {
		super();
		// TODO Auto-generated constructor stub
	}
		

	/**
	 * @param ucenik
	 * @param nastavnik
	 * @param predmet
	 * @param vrednostOcene
	 * @param datumOcene
	 * @param semestar
	 */
	public OcenaDto(String ucenik, String nastavnik, String predmet,
			@Min(value = 1, message = "Vrednost ocene mora biti {value} ili veća!") @Max(value = 5, message = "Vrednost ocene mora biti  {value} ili manja!") Integer vrednostOcene,
			String datumOcene, String semestar) {
		super();
		this.ucenik = ucenik;
		this.nastavnik = nastavnik;
		this.predmet = predmet;
		this.vrednostOcene = vrednostOcene;
		this.datumOcene = datumOcene;
		this.semestar = semestar;
	}



	public String getUcenik() {
		return ucenik;
	}

	public void setUcenik(String ucenik) {
		this.ucenik = ucenik;
	}

	public String getNastavnik() {
		return nastavnik;
	}

	public void setNastavnik(String nastavnik) {
		this.nastavnik = nastavnik;
	}

	public String getPredmet() {
		return predmet;
	}

	public void setPredmet(String predmet) {
		this.predmet = predmet;
	}

	public Integer getVrednostOcene() {
		return vrednostOcene;
	}

	public void setVrednostOcene(Integer vrednostOcene) {
		this.vrednostOcene = vrednostOcene;
	}

	public String getDatumOcene() {
		return datumOcene;
	}

	public void setDatumOcene(String datumOcene) {
		this.datumOcene = datumOcene;
	}

	public String getSemestar() {
		return semestar;
	}

	public void setSemestar(String semestar) {
		this.semestar = semestar;
	}

}
